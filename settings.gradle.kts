import java.util.Locale

pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://papermc.io/repo/repository/maven-public/")
    }
}

rootProject.name = "omnipurpur"
for (name in listOf("OmniPurpur-API", "OmniPurpur-Server", "OmniPurpur-Common", "OmniPurpur-Bridge")) {
    val projName = name.toLowerCase(Locale.ENGLISH)
    include(projName)
    findProject(":$projName")!!.projectDir = file(name)
}
