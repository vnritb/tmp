import org.jetbrains.compose.desktop.application.dsl.TargetFormat

//Warning:(4, 5) 'kotlinOptions(KotlinJvmOptionsDeprecated /* = KotlinJvmOptions */.() -> Unit): Unit' is deprecated. Please migrate to the compilerOptions DSL. More details are here: https://kotl.in/u1r8ln
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "21"  // Asegúrate de que coincide con compileJava
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))  // Debe coincidir con Kotlin
    }
}


plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

dependencies {
    // Note, if you develop a library, you should use compose.desktop.common.
    // compose.desktop.currentOs should be used in launcher-sourceSet
    // (in a separate module for demo project and in testMain).
    // With compose.desktop.common you will also lose @Preview functionality
    implementation(compose.desktop.currentOs)
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "composedemo"
            packageVersion = "1.0.0"
        }
    }
}
