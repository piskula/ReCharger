rootProject.name = "recharger"

include("module-api")
include("module-server")

pluginManagement {
    repositories {
        gradlePluginPortal()
//        google()
        mavenCentral()
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id == "com.google.cloud.tools.appengine") {
                useModule("com.google.cloud.tools:appengine-gradle-plugin:${requested.version}")
            }
        }
    }
}
