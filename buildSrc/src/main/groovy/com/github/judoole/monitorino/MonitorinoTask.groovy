package com.github.judoole.monitorino

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction


class MonitorinoTask extends DefaultTask{
    String url

    @TaskAction
    def parseUrl() {
        println url
    }
}
