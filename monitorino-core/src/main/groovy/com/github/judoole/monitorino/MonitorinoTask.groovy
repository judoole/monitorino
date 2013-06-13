package com.github.judoole.monitorino

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.StopExecutionException
import org.gradle.api.tasks.TaskAction

class MonitorinoTask extends DefaultTask {
    String url

    @TaskAction
    def parseUrl() {
        //def xml = new XmlSlurper().parse(new File("somexml"))
        if (url == null) {
            println "Missing url argument"
            throw new StopExecutionException()
        }
        def xml
        try {
            xml = new XmlSlurper().parse(url)
        } catch (Exception e) {
            println "Sorry, could not find ${url}"
            throw new StopExecutionException()
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
