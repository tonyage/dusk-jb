package com.tonyage

import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.StartupActivity

class DuskTheme : StartupActivity, DumbAware {

    override fun runActivity(project: Project) {
        ThemeManager.registerStartup(project)
    }
}
