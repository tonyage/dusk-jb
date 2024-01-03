plugins {
    `kotlin-dsl`
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation("org.jsoup:jsoup:1.15.3")
    implementation("com.google.code.gson:gson:2.8.9")
}
