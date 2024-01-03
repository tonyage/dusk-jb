package com.tonyage

import com.intellij.ide.plugins.PluginManagerCore
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.extensions.PluginId
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.StartupManager
import com.intellij.util.messages.MessageBusConnection
import com.tonyage.notification.Notifications
import com.tonyage.settings.ThemeSettings
import java.util.*

object ThemeManager {
  private lateinit var messageBus: MessageBusConnection
  const val PLUGIN_ID = "com.tonyage.dusk"

  fun registerStartup(project: Project) {
    if (!this::messageBus.isInitialized) {
      registerUser()

      attemptToDisplayUpdates(project)

      subscribeToEvents()
    }
  }

  private fun registerUser() {
    if (ThemeSettings.instance.userId.isEmpty()) {
      ThemeSettings.instance.userId = UUID.randomUUID().toString()
    }
  }

  private fun attemptToDisplayUpdates(project: Project) {
    getVersion().ifPresent { currentVersion ->
      if (ThemeSettings.instance.version != currentVersion) {
        ThemeSettings.instance.version = currentVersion
        StartupManager.getInstance(project).runWhenProjectIsInitialized {
          Notifications.displayUpdateNotification(currentVersion)
        }
      }
    }
  }

  private fun getVersion(): Optional<String> =
    PluginManagerCore.getPlugin(PluginId.getId(PLUGIN_ID)).toOptional()
      .map { it.version }

  private fun subscribeToEvents() {
    messageBus = ApplicationManager.getApplication().messageBus.connect()
  }
}