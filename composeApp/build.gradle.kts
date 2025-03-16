import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
  alias(libs.plugins.kotlin.multiplatform)
  alias(libs.plugins.compose)
  alias(libs.plugins.compose.compiler)
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlinx.serialization)
}

kotlin {
  androidTarget()

  wasmJs {
    moduleName = "composeApp"
    browser {
      val projectDirPath = project.projectDir.path
      commonWebpackConfig {
        outputFileName = "composeApp.js"
        devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
          static = (static ?: mutableListOf()).apply {
            // Serve sources to debug inside browser
            add(projectDirPath)
          }
        }
      }
    }
    binaries.executable()
  }

  sourceSets {
    all {
      languageSettings {
        optIn("org.jetbrains.compose.resources.ExperimentalResourceApi")
      }
    }

    commonMain.dependencies {
      implementation(compose.runtime)
      implementation(compose.material3)
      implementation(compose.materialIconsExtended)

      @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
      implementation(compose.components.resources)

      implementation(libs.kotlinx.coroutines.core)
      implementation(libs.kotlinx.datetime)
      implementation(libs.kotlinx.serialization.json)

      implementation(libs.androidx.lifecycle.runtime.compose)
      implementation(libs.androidx.lifecycle.viewmodel.compose)
    }

    androidMain.dependencies {
      implementation(libs.androidx.appcompat)
      implementation(libs.androidx.activity.compose)
      implementation(libs.kotlinx.coroutines.android)
    }

    wasmJsMain.dependencies {
      
    }
  }
}

android {
  namespace = "dev.trindadedev.snakegame"
  compileSdk = 35

  defaultConfig {
    minSdk = 24
    targetSdk = 35

    applicationId = "dev.trindadedev.snakegame.androidApp"
    versionCode = 1
    versionName = "1.0.0"
  }

  sourceSets["main"].apply {
    manifest.srcFile("src/androidMain/AndroidManifest.xml")
    res.srcDirs("src/androidMain/resources")
    resources.srcDirs("src/commonMain/resources")
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
  }

  buildFeatures {
    compose = true
  }

  buildTypes {
    getByName("release") {
      isMinifyEnabled = true
      isShrinkResources = true
      signingConfig = signingConfigs.getByName("debug")
    }
  }
}