pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://api.xposed.info/")
        maven("https://jitpack.io")
        maven("https://s01.oss.sonatype.org/content/repositories/releases/")
    }
}

include(":app")
rootProject.name = "Huluxia-Assistance"

