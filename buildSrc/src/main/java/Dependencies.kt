
object Versions {
    val KOTLIN = "1.3.72"
    val COMPILE_SDK = 28
    val MIN_SDK_VERSION = 16
    val TARGET_SDK_VERSION = 28
    val VERSION_CODE = 1
    val VERSION_NAME = "1.0"
    val RXJAVA_VERSION = "2.2.13"
    val RX_ANDROID = "2.1.1"
    val RX_RETROFIT_ADAPTER = "2.6.1"
    val DAGGER_VERSION = "2.22"
    val DAGGER_COMPILER = "2.22"
    val RETROFIT_VERSION = "2.7.1"
    val GSON_VERSION = "2.8.5"
    val GSON_CONVERTER = "2.6.1"
    val OKHTTP_LOGGING_INTERCEPTOR_VERSION = "3.12.1"
    val LIFE_CYCLE_VERSION = "2.2.0"
    val CARD_VIEW_VERSION = "1.0.0"
    val GLIDE_VERSION = "4.11.0"
    val RECYCLERVIEW_VERSION = "1.1.0"
    val CONSTRAINT_LAYOUT = "1.1.3"
    val ANDROID_GRADLE_VERSION = "4.0.1"
    val ESPRESSO_VERSION = "3.1.1"
    val APP_COMPAT_VERSION = "1.1.0"
    val CORE_KTX = "1.1.0"
    val ARCH_CORE_TESTING_VER = "2.0.0"
    val TEST_RUNNER_VER = "1.1.1"
    val RULES_VER = "1.1.1"
    val TRUTH_VER = "1.1.0"
    val JUNIT_EXT_VER = "1.1.0"
    val MATERIAL_VERSION = "1.0.0"
    val MOCKITO = "3.3.1"
    val MOCK_HTTP_SERVER = "2.7.5"
    val RX_IDLER = "0.10.0"
    val COROUTINES_VER = "1.4.1"
}

object BuildPlugins {
    val androidGradle = "com.android.tools.build:gradle:${Versions.ANDROID_GRADLE_VERSION}"
    val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}"
}

object Android {
    val minSDK = Versions.MIN_SDK_VERSION
    val targetSDK = Versions.TARGET_SDK_VERSION
    val versionCode = Versions.VERSION_CODE
    val versionName = Versions.VERSION_NAME
    val compileSDK = Versions.COMPILE_SDK
    val applicationId = "com.ramadan.androidtemplate"
}

object Libs {
    val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.KOTLIN}"
    val rxVersion = "io.reactivex.rxjava2:rxjava:${Versions.RXJAVA_VERSION}"
    val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.RX_ANDROID}"
    val rxAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.RX_RETROFIT_ADAPTER}"
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT_VERSION}"
    val material = "com.google.android.material:material:${Versions.MATERIAL_VERSION}"
    val gson = "com.google.code.gson:gson:${Versions.GSON_VERSION}"
    val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.GSON_CONVERTER}"
    val dagger = "com.google.dagger:dagger:${Versions.DAGGER_VERSION}"
    val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.DAGGER_COMPILER}"
    val loggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.OKHTTP_LOGGING_INTERCEPTOR_VERSION}"
    val recyclerview = "androidx.recyclerview:recyclerview:${Versions.RECYCLERVIEW_VERSION}"
    val cardview = "androidx.cardview:cardview:${Versions.CARD_VIEW_VERSION}"
    val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT}"
    val liveData = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFE_CYCLE_VERSION}"
    val viewModel = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.LIFE_CYCLE_VERSION}"
    val glide = "com.github.bumptech.glide:glide:${Versions.GLIDE_VERSION}"
    val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.GLIDE_VERSION}"
    val appCompat = "androidx.appcompat:appcompat:${Versions.APP_COMPAT_VERSION}"
    val coreExt = "androidx.core:core-ktx:${Versions.CORE_KTX}"
    val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINES_VER}"
    val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINES_VER}"
}

object TestLibs {
    val junit = "junit:junit:4.12"
    val espressoCore = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_VERSION}"
    val espressoContrib = "androidx.test.espresso:espresso-contrib:${Versions.ESPRESSO_VERSION}"
    val espressoIdlingResource =
        "androidx.test.espresso:espresso-idling-resource:${Versions.ESPRESSO_VERSION}"
    val archCoreTesting = "androidx.arch.core:core-testing:${Versions.ARCH_CORE_TESTING_VER}"
    val testRunner = "com.android.support.test:runner:${Versions.TEST_RUNNER_VER}"
    val rules = "androidx.test:rules:${Versions.RULES_VER}"
    val truth = "androidx.test.ext:truth:${Versions.TRUTH_VER}"
    val junitExt = "androidx.test.ext:junit:${Versions.JUNIT_EXT_VER}"
    val mockito = "org.mockito:mockito-core:${Versions.MOCKITO}"
    val mockitoAndroid = "org.mockito:mockito-android:${Versions.MOCKITO}"
    val mockHttpServer = "com.squareup.okhttp:mockwebserver:${Versions.MOCK_HTTP_SERVER}"
    val rxIdler = "com.squareup.rx.idler:rx2-idler:${Versions.RX_IDLER}"
    val coroutinesTest =  "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.COROUTINES_VER}"
}