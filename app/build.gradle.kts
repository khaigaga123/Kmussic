plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.kmusics"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.kmusics"
        minSdk = 29
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        multiDexEnabled = true
        vectorDrawables.useSupportLibrary = true
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
    buildToolsVersion = "33.0.1"
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.android.support:support-annotations:28.0.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.picasso:picasso:2.8") // Thay đổi phiên bản này thành phiên bản mới nhất
    implementation ("me.relex:circleindicator:2.1.6")
    implementation ("com.eftimoff:android-viewpager-transformers:1.0.1@aar")
    implementation ("androidx.cardview:cardview:1.0.0")
    implementation ("androidx.recyclerview:recyclerview:1.3.1")
    implementation ("com.google.android.material:material:1.4.0") // Thay đổi phiên bản này thành phiên bản mới nhất
    implementation ("de.hdodenhof:circleimageview:3.1.0")
    implementation ("com.android.support:multidex:2.0.1")

}