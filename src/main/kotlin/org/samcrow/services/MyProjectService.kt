package org.samcrow.services

import com.intellij.openapi.project.Project
import org.samcrow.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
