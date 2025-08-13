plugins {
    alias(libs.plugins.android.application)

    id ("com.google.gms.google-services") // Firebase plugin

}

android {
    namespace = "com.example.den_week4"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.den_week4"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // Firebase
    implementation ("com.google.firebase:firebase-database:22.0.0")

// AdMob (Google Mobile Ads)
    implementation ("com.google.android.gms:play-services-ads:24.5.0")


}