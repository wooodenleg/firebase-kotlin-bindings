plugins {
    alias(libs.plugins.kotlin.multiplatform)
    `maven-publish`
}

repositories {
    mavenCentral()
    google()
}


group = "dev.bitspittle"
version = libs.versions.firebase.bindings.get()

kotlin {
    js(IR) {
        browser()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
            }
        }

        val jsMain by getting {
            dependencies {
                implementation(npm("firebase", libs.versions.firebase.web.get()))
                implementation(libs.kotlinx.coroutines)
            }
        }
    }
}

publishing {
    val spaceUsername: String? by project
    val spacePassword: String? by project

    publications {
        create<MavenPublication>("firebaseBindings") {
            groupId = "dev.bitspittle"
            artifactId = "firebase-kotlin-bindings"
            version = "0.0.1"

            from(components["kotlin"])
        }
    }

    repositories {
        maven {
            url = uri("https://maven.pkg.jetbrains.space/tables/p/tables/cometes")
            credentials {
                username = spaceUsername
                password = spacePassword
            }
        }
    }
}

