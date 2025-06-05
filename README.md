# Boogle

Boogle is a small sample Android application written in Kotlin. It demonstrates using
Jetpack Compose together with Retrofit and Room to search for books from the Google
Books API and store them locally.

## Features

- Search for books using the Google Books API.
- Display a list of search results with Compose UI.
- Persist book information in a local Room database.
- Material3 theming and a single `Search` screen.

The project targets **compileSdk 34** and uses Kotlin **1.9** with Android Gradle
Plugin **8.2**.

## Getting Started

1. Clone the repository and open it with Android Studio Flamingo or newer.
2. Obtain a Google Books API key and set it in
   `app/src/main/java/com/example/boogle/network/BookAPI.kt` by replacing the
   empty `APIKEY` constant.
3. Connect a device or start an emulator.
4. Build and install the debug APK:
   ```bash
   ./gradlew installDebug
   ```

Running the above command will build the app and deploy it to the connected device.
You can also use the **Run** action inside Android Studio.

## Running Tests

- Unit tests: `./gradlew test`
- Instrumented tests: `./gradlew connectedAndroidTest`

These tasks require an emulator or device for instrumented tests.

## Project Structure

```
app/                 # Android application module
  src/main/java/     # Kotlin source files
  src/main/res/      # Resources (layouts, strings, etc.)
  build.gradle.kts   # Module build file
build.gradle.kts     # Top-level Gradle config
```

The main entry point is `MainActivity`, which hosts the Compose UI.
Book data classes and database logic live under `com.example.boogle.data`.
Networking code resides in `com.example.boogle.network`.

## License

This project is provided for educational purposes. No explicit license is supplied.
