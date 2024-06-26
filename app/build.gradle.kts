@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.parcelize)
}

android {
    namespace = "com.github.gunin_igor75.githubapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.github.gunin_igor75.githubapp"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
        }

        val key = property("apitoken")?.toString() ?: error(
            "You should and apitoken" +
                    " into gradle.properties"
        )
        buildConfigField("String", "API_TOKEN", "\"$key\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.7"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    implementation(libs.navigation.compose)
    implementation(libs.view.model.compose)
    implementation(libs.lifecycle.compose.core)

    //room
    implementation(libs.room.core)
    ksp(libs.room.compilier)
    implementation(libs.room.paging)

    //dagger
    implementation(libs.dagger.core)
    ksp(libs.dagger.compiler)

    //glide
    implementation(libs.glide.compose)

    //okhttp
    implementation(libs.okhttp.core)
    implementation(libs.okhttp.logging)

    //paging
    implementation(libs.paging.core)
    implementation(libs.paging.compose)

    //retrofit
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.gsonConverter)

    //constraintLayout
    implementation(libs.constraintLayout.compose)
        

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
}