plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hiltAndroid)
}

android {
    namespace = "com.example.catfacts"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.catfacts"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.11"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.timber)

    implementation(project(":coreui"))
    implementation(project(":data"))
    implementation(project(":features:catfact"))

    implementation(libs.hilt)
    ksp(libs.hilt.compiler)

    api(platform(libs.compose.bom))
    api(libs.bundles.compose)

    implementation(libs.compose.activity)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    implementation(libs.compose.navigation)
    implementation(libs.compose.hilt.navigation)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
hilt {
    enableExperimentalClasspathAggregation = true
}