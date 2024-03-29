plugins {
    kotlin("multiplatform") version "1.9.0"
    id("com.ncorti.ktfmt.gradle") version "0.13.0"
}

val ktor_version: String by project

java {
   toolchain.languageVersion.set(JavaLanguageVersion.of(18))
}

repositories {
    mavenCentral()
}

kotlin {
    targets {
        jvm()
        linuxX64() {
            binaries {
                executable {
                    entryPoint = "com.umaumax.waits.main"
                    runTask?.args("")
                }
            }
        }
        macosArm64() {
            binaries {
                executable {
                    entryPoint = "com.umaumax.waits.main"
                    runTask?.args("")
                }
            }
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.2")
                implementation("org.jetbrains.kotlinx:kotlinx-cli:0.3.5")
                implementation("com.michael-bull.kotlin-result:kotlin-result:1.1.18")
                implementation("com.michael-bull.kotlin-result:kotlin-result-coroutines:1.1.18")
                implementation("io.ktor:ktor-client-core:$ktor_version")
                implementation("io.ktor:ktor-client-cio:$ktor_version")
            }
        }
        val commonTest by getting {
            dependsOn(commonMain)
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-test")
                implementation("org.jetbrains.kotlin:kotlin-test-junit")
            }
        }
        val jvmMain by getting {
            dependsOn(commonMain)
        }

        val linuxX64Main by getting {
            dependsOn(commonMain)
        }

        val macosArm64Main by getting {
            dependsOn(commonMain)
        }
    }
}

tasks.withType<Jar> {
    doFirst {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        val main by kotlin.jvm().compilations.getting
        manifest {
            attributes(
                "Main-Class" to "com.umaumax.waits.AppKt",
            )
        }
        from({
            main.runtimeDependencyFiles.files.filter { it.name.endsWith("jar") }.map { zipTree(it) }
        })
    }
}
