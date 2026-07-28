import com.android.build.api.dsl.ViewBinding

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("com.google.devtools.ksp")

}

android {
    namespace = "com.example.anroiddevelopment"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.anroiddevelopment"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    ksp{
        arg(k = "room.schemaLocation", v = "${projectDir}/schemas")
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
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures{   //For ViewBinding and databinding
        viewBinding =true
        dataBinding=true
    }

}

dependencies {

    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.fragment)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.volley)  //to use volley library we have to use this dependency without it we can't use volley library
    implementation(libs.glide)    //to use glide library we have to use this dependency without it we can't use glide library
    annotationProcessor(libs.compiler)
    implementation(libs.androidx.appcompat.v170)
    implementation(libs.androidx.drawerlayout)  //to use navigation drawer we use this dependency without it we can't use drawer
    implementation(libs.androidx.cardview)  //to use cardview we use this dependency without it we can't use cardview
    implementation(libs.retrofit)  //to use retrofit we use this dependency without it we can't use retrofit
    implementation(libs.converter.gson) //to use converter like Gson we have to use this dependency
    implementation(libs.logging.interceptor)
    implementation(libs.jsoup)  //to use jsoup parsing we use this dependency

    implementation(libs.androidx.recyclerview) //for recyclerview

    // Import the Firebase BoM
    implementation(platform(libs.firebase.bom))
    //adding firebase crashlytics in my app to check
    implementation(libs.firebase.crashlytics)

    // Add the Firebase Cloud Messaging library
    implementation(libs.firebase.messaging)

    // Activity KTX for viewModels()
    implementation(libs.androidx.activity.ktx.v193)

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // Coroutine Lifecycle Scopes
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // DataStore
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.androidx.datastore.preferences.core)

    //for WorkManager
    implementation(libs.androidx.work.runtime.ktx)

    //for RoomDatabase
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)


    //for firebase realtime database
    implementation(platform(libs.firebase.bom.v34160))
    implementation(libs.firebase.database)

}