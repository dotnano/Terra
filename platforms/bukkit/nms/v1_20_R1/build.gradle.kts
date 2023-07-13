apply(plugin = "io.papermc.paperweight.userdev")

repositories {
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
}

dependencies {
    api(project(":platforms:bukkit:common"))
    paperDevBundle(Versions.Bukkit.paperDevBundle)
    implementation("xyz.jpenilla", "reflection-remapper", "0.1.0-SNAPSHOT")
}

tasks {
    assemble {
        dependsOn("reobfJar")
    }
}