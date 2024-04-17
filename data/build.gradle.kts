import com.android.build.api.variant.BuildConfigField

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hiltAndroid)
}

android {
    namespace = "com.example.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        buildConfig = true
    }
}

androidComponents {
    onVariants(selector().withName("debug")) {
        it.buildConfigFields.put(
            "API_URL",
            BuildConfigField(
                "String", "\"https://catfact.ninja/\"", ""
            )
        )
    }
    onVariants(selector().withName("release")) {
        it.buildConfigFields.put(
            "API_URL",
            BuildConfigField(
                "String", "\"https://catfact.ninja/\"", ""
            )
        )
    }
}

dependencies {
    implementation(project(":domain"))

    implementation(libs.timber)

    implementation(libs.coroutines)

    implementation(libs.hilt)
    ksp(libs.hilt.compiler)

    ksp(libs.room.compiler)
    implementation(libs.bundles.room)

    implementation(libs.javax.inject)

    implementation(platform(libs.okhttp.bom))
    implementation(libs.bundles.network)
    ksp(libs.moshi.codegen)

    implementation(libs.preference.datastore)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}