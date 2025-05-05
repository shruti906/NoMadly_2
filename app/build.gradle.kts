plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.kapt")
    id("org.jetbrains.kotlin.android")
}


android {
    namespace = "com.example.nomadly"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.nomadly"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro" // Corrected this line to list files directly
            )
        }
    }

    // Set the correct Kotlin JVM target version here
    kotlinOptions {
        jvmTarget = "1.8"  // Set JVM target version to 1.8 (as 21 is not supported)
    }
}
dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.12.0")

    implementation("androidx.room:room-runtime:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1") // Correct placement of kapt
}
