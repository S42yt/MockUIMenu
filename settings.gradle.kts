pluginManagement {
    repositories {
        maven {
            name = "Fabric"
            url = uri("https://maven.fabricmc.net/")
        }
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        maven {
            name = "Wisp Forest"
            url = uri("https://maven.wispforest.io")
        }
    }
}

rootProject.name = "MockUIMenu"