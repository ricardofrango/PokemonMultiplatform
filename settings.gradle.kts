pluginManagement {
    repositories {
        google()
        jcenter()
        gradlePluginPortal()
        mavenCentral()
    }
    
}
rootProject.name = "PokemonMultiplatform"


include(":androidApp")
include(":pokemon_domain")

