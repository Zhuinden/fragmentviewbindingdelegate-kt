plugins {
    id("com.android.library")
    kotlin("android")
    id("com.github.dcendents.android-maven")
}

android {
    compileSdkVersion(28)

    defaultConfig {
        minSdkVersion(9)
        targetSdkVersion(28)
        versionCode = 1
        versionName = "1.0.0"
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    lintOptions {
        isAbortOnError = false
    }

    compileOptions {
        this.sourceCompatibility(JavaVersion.VERSION_1_8)
        this.targetCompatibility(JavaVersion.VERSION_1_8)
    }
    
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    //implementation(mapOf("dir" to "libs", "include" to listOf("*.jar")))
    api("androidx.lifecycle:lifecycle-common-java8:2.2.0")
    implementation("androidx.fragment:fragment-ktx:1.2.5")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.2.0")

    testImplementation("junit:junit:4.13.1")
    testImplementation("org.assertj:assertj-core:3.16.1")
    testImplementation("org.mockito:mockito-core:2.23.0")
    testImplementation("androidx.arch.core:core-testing:2.1.0")
    androidTestImplementation("junit:junit:4.13.1")
}

// build a jar with source files
val sourcesJar by tasks.registering(Jar::class) {
    from(android.sourceSets["main"].java.srcDirs)
    archiveClassifier.set("sources")
}

val javadoc by tasks.registering(Javadoc::class) {
    isFailOnError = false
    source = android.sourceSets["main"].java.getSourceFiles()
    classpath += project.files(android.bootClasspath.joinToString(separator = File.pathSeparator))
    classpath += configurations.compile
}

// build a jar with javadoc
val javadocJar by tasks.registering(Jar::class) {
    dependsOn(javadoc)
    archiveClassifier.set("javadoc")
    from(javadoc.get().destinationDir)
}

artifacts {
    archives(sourcesJar)
    archives(javadocJar)
}
