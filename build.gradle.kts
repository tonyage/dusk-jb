plugins {
    id("dusk-theme-plugin")
    id("org.jetbrains.intellij") version ("1.9.0")
    id("org.jetbrains.kotlin.jvm") version ("1.7.10")
}

val projectVersion = System.getenv().getOrDefault("VERSION", "").replace("refs/tags/v", "")
val version = projectVersion

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.sentry:sentry:5.0.1")
    implementation("commons-io:commons-io:2.6")
}

configurations {
    all {
        exclude("org.slf4j")
    }
}

intellij {
    version.set("2021.3.1")
    type.set("IU")
    downloadSources.set(true)
    updateSinceUntilBuild.set(true)
}

tasks.runIde {
    val idePath = if (project.hasProperty("idePath")) {
        project.findProperty("idePath")
    } else {
        ""
    }

    if ("" != idePath) {
        ideDir.set(idePath?.let { file(it) })
    }
}

tasks.runPluginVerifier {
    ideVersions.set(listOf("IC-2020.3.1", "IC-2022.3"))
}

tasks.patchPluginXml {
    version.set(projectVersion)
    sinceBuild.set("203.7148.57")
    untilBuild.set("233.*")
}

tasks.runIde {
    dependsOn("createThemes")
}

tasks.publishPlugin {
    token.set(System.getenv("PUBLISH_TOKEN"))
}