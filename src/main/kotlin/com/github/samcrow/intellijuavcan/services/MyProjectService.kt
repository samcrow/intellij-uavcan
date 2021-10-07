package com.github.samcrow.intellijuavcan.services

import com.intellij.openapi.project.Project
import com.github.samcrow.intellijuavcan.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
