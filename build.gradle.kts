plugins {
    id("com.github.ben-manes.versions")
    id("net.researchgate.release") version "2.8.1"
}

tasks.register("build") {
    println("stub required for net.researchgate.release plugin")

    dependsOn("mainapp:build")
}


//tasks.release {
//    buildTasks = ['buildWar']
//}
//}