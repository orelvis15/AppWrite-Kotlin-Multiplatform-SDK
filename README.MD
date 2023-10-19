![Appwrite](https://appwrite.io/images/logos/appwrite.svg)

# Cross-platform SDK for Appwrite

**This SDK is compatible with Appwrite server version 1.4.x**

Appwrite is an open-source backend as a service server that abstract and simplify complex and repetitive development tasks behind a very simple to use REST API. Appwrite aims to help you develop your apps faster and in a more secure way. Use the Android SDK to integrate your app with the Appwrite server to easily start interacting with all of Appwrite backend APIs and tools. For full API documentation and tutorials go to [https://appwrite.io/docs](https://appwrite.io/docs)


## Installation

Currently working on uploading the library to MavenCentral, in the meantime you can download the project and compile the Lib module

### Gradle

**Coming soon**

## Getting Started

### Platform support

|         |   Account   |   Avatars   |  Databases  |  Functions  |   Graphql   |   Locale    |   Service   |   Storage   |    Teams    |
|:-------:|:-----------:|:-----------:|:-----------:|:-----------:|:-----------:|:-----------:|:-----------:|:-----------:|:-----------:|
| Desktop |      ✔      |      ✔      |      ✔      |      ✔      |      ✔      |      ✔      |      ✔      |      ✔      |      ✔      |
| Android | On Progress | On Progress | On Progress | On Progress | On Progress | On Progress | On Progress | On Progress | On Progress |
|   iOS   | Coming Soon | Coming Soon | Coming Soon | Coming Soon | Coming Soon | Coming Soon | Coming Soon | Coming Soon | Coming Soon |
|   Web   | Coming Soon | Coming Soon | Coming Soon | Coming Soon | Coming Soon | Coming Soon | Coming Soon | Coming Soon | Coming Soon |

### Test it

The sdk contains a test application, this contains both the documentation and functional examples of its operation with which you can interact.

### Init your SDK

Initialize your SDK with your Appwrite server API endpoint and project ID, which can be found in your project settings page.

```kotlin
import io.appwrite.Client
import io.appwrite.services.Account

val client = Client()
  .setEndpoint("https://[HOSTNAME_OR_IP]/v1") // Your API Endpoint
  .setProject("5df5acd0d48c2") // Your project ID
  .setSelfSigned(true) // Remove in production
```

### Make Your First Request

<p>Once your SDK object is set, access any of the Appwrite services and choose any request to send. Full documentation for any service method you would like to use can be found in your SDK documentation or in the [API References](https://appwrite.io/docs) section.

```kotlin
// Register User
val account = Account(client)
val response = account.createAccount(
    ID.unique(),
    "email@example.com", 
    "password"
)
```

### Full Example

```kotlin
import io.appwrite.Client
import io.appwrite.services.Account
import io.appwrite.ID

val client = Client()
  .setEndpoint("https://[HOSTNAME_OR_IP]/v1") // Your API Endpoint
  .setProject("5df5acd0d48c2") // Your project ID
  .setSelfSigned(true) // Remove in production

val account = Account(client)
val user = account.createAccount(
    ID.unique(),
    "email@example.com", 
    "password"
)
```

### Error Handling
The Appwrite Android SDK raises an `AppwriteException` object with `message`, `code` and `response` properties. You can handle any errors by catching `AppwriteException` and present the `message` to the user or handle it yourself based on the provided error information. Below is an example.

```kotlin
try {
    var user = account.createAccount(ID.unique(), "email@example.com", "password")
    println("Appwrite user", user.toMap())
} catch(e : AppwriteException) {
    e.printStackTrace()
}
```

## Contribution

You can contribute by sending your Pull Request or creating issues, I am attentive to any bugs, ideas or suggestions.

## License

Please see the [BSD-3-Clause license](https://raw.githubusercontent.com/orelvis15/AppWrite-Kotlin-Multiplatform-SDK/main/LICENSE) file for more information.