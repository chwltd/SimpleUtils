buildscript {
    repositories {
        google()
        mavenCentral()
        mavenLocal()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.1.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.20")
    }
}

plugins {
    alias(libs.plugins.android.library)
    id("maven-publish")
}

android {
    namespace = "com.chwltd.utils"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            consumerProguardFiles("consumer-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    api("androidx.appcompat:appcompat:1.6.1")
    api("androidx.palette:palette:1.0.0")
    api("androidx.constraintlayout:constraintlayout:2.1.4")
    api("com.google.android.material:material:1.9.0")
    api("com.github.bumptech.glide:glide:4.12.0")
    api("com.airbnb.android:lottie:6.5.0")
    api("com.squareup.okhttp3:okhttp:4.9.1")
    api("com.google.code.gson:gson:2.11.0")
    api("com.squareup.picasso:picasso:2.8")
}

afterEvaluate {
    publishing {
        publications {
            publications {
                create<MavenPublication>("maven") { // 使用 create 方法
                    groupId = "com.github.chwltd"
                    artifactId = "SimpleUtils"
                    version = "0.1.1"
                    pom {
                        description.set("SimpleUtils") // 使用 set 方法
                    }
                }
            }
            repositories { // 添加此部分
                mavenLocal()
            }
        }
    }
}