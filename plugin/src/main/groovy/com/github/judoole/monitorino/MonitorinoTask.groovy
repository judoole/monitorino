package com.github.judoole.monitorino

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.TaskExecutionException
import org.gradle.api.tasks.TaskInstantiationException
import org.gradle.api.DefaultTask

class MonitorinoTask extends DefaultTask {
    String url

    @TaskAction
    def parseUrl() {
        //def xml = new XmlSlurper().parse(new File("somexml"))
        if (url == null) {
            throw new TaskInstantiationException("Missing url argument")
        }
        def xml
        try {
            xml = new XmlSlurper().parse(url)
        } catch (Exception e) {
            throw new TaskInstantiationException("Sorry, could not find ${url}", e)
        }

        println "Result for ${xml.@name}:"

        xml.testcase.list()
                .sort {
                //Errors on the bottom, then failures, then OK.
            a, b -> a.error.size() <=> b.error.size() ?: a.failure.size() <=> b.failure.size()
        }
        .each {
            println "${status(it)} \"${it.@name}\", time: ${it.@time} sec ${message(it)}"
            //if(hasError(it)) println it.error.text()
        }
        println "\nTests: ${xml.@tests} Failures: \u001B[33m${xml.@failures}\u001B[0m Errors: \u001B[31m${xml.@errors}\u001B[0m"

        if((xml.@failures.text() as Integer) + (xml.@errors.text() as Integer) > 0){
            throw new TaskExecutionException(this, new RuntimeException("Result from url '${url}' was not successful. Errors: ${xml.@errors}, Failures: ${xml.@failures}"))
        }
    }

    def boolean hasError(testcase) {
        return !testcase.error.isEmpty();
    }

    def boolean hasFailure(testcase) {
        return !testcase.failure.isEmpty();
    }

    def String message(testcase) {
        if (hasFailure(testcase)) return ": " + testcase.failure.@message;
        else if (hasError(testcase)) return ": " + testcase.error.@message;
        else return "";
    }

    def boolean isOK(testcase) {
        return !hasFailure(testcase) && !hasError(testcase)
    }

    def String status(testcase) {
        if (hasError(testcase)) return "\u001B[31m[Error]\u001B[0m"
        else if (hasFailure(testcase)) return "\u001B[33m[Failure]\u001B[0m"
        else return "\u001B[32m[OK]\u001B[0m"
    }

}
