package com.github.corytheboyd.hugointellijplugin.services

import com.intellij.openapi.project.Project
import com.github.corytheboyd.hugointellijplugin.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
