Android Development Playground
A personal learning project built while going through the Cheezy Code Android (Java) YouTube playlist and converting everything into Kotlin from scratch. This repo is a collection of core Android features implemented and understood one by one — networking, image loading, lists, Firebase integration, and more.

This project was built as a hands-on learning exercise. Code quality reflects a learning journey — some naming and structure choices were made along the way while understanding Kotlin fundamentals.

Tech Stack

Language: Kotlin (converted from original Java tutorials)
IDE: Android Studio
Min SDK: 24
Target/Compile SDK: 36

Features Implemented
Networking

Volley — basic GET requests, fetching and displaying raw string responses (tested with google.com)
Retrofit — structured API calls using interfaces, Gson conversion, and Call/Callback handling

Integrated with NewsAPI (top headlines, search by query)
Integrated with a personal Blogger API v3 blog, fetching and displaying real blog posts


Jsoup — HTML parsing to extract clean text and image URLs from raw Blogger post content

Image Loading

Glide — loading images from URLs into ImageViews and RecyclerView items

Lists & UI

RecyclerView — custom adapters and ViewHolders for displaying dynamic lists

A standalone RecyclerView practice project (separate from the main app) converting a full Java RecyclerView + Adapter + ViewHolder setup (programming languages list) into Kotlin
News articles list (via Retrofit + NewsAPI)
Blog posts list (via Retrofit + Blogger API)


Infinite Scrolling — custom scroll listener that detects when the user reaches the bottom of a list and dynamically loads more items, with a ProgressBar loading indicator
CardView — card-style UI containers
Navigation Drawer (Hamburger Menu) — DrawerLayout + Toolbar + NavigationView, with custom menu item click handling
Splash Screen — timed splash screen with logo, transitioning to the main screen

Handlers & Threads

Handler + Looper — used for delayed execution (e.g., splash screen timing, simulating delayed data loading for infinite scroll)

Firebase Integration

Firebase Analytics — custom event logging (e.g., tracking button clicks)
Firebase Crashlytics — crash reporting, tested with a forced test crash
Firebase Cloud Messaging (FCM) — push notifications, including fetching and using the device FCM token for test notifications via Firebase Console

Navigation

Multiple activities connected via Intent, each demonstrating a different Android feature (Glide, Volley, Retrofit, RecyclerView, Handler-based demo)

What I Learned

Converting Java Android code to idiomatic Kotlin (lambdas, lateinit, scope functions, null safety, property access syntax instead of getters/setters)
Working with RecyclerView.Adapter and ViewHolder patterns, including building one completely from scratch following a Java tutorial
Making network requests with both callback-based (Volley) and interface-based (Retrofit) approaches
Parsing and cleaning HTML content from an API response
Using Handler and Looper for delayed/timed operations
Debugging real runtime crashes using Logcat (FATAL EXCEPTION, ClassNotFoundException from manifest/class name mismatches, UninitializedPropertyAccessException, Gradle plugin conflicts, Kotlin version mismatches)
Structuring app navigation using a Navigation Drawer
Integrating Firebase services (Analytics, Crashlytics, Cloud Messaging) into an existing app
Handling Gradle dependency management via libs.versions.toml

Setup

Clone the repository
Open in Android Studio
Add your own API keys where needed (see below) — this repo does not include real API keys
Add your own google-services.json file (from Firebase Console) into the app/ directory
Sync Gradle and run

API Keys Required
This project uses external APIs. Replace the placeholders in the relevant files with your own keys:

NewsAPI — newsapi.org
Blogger API v3 — Google Cloud Console
Firebase — Firebase Console

Never commit real API keys to a public repository. Consider using local.properties or environment variables for sensitive values.
Notes
This project is a work-in-progress learning archive rather than a production app — expect some rough edges, experimental naming, and features built purely for practice rather than polish.
Credit
Built while following the Cheezy Code YouTube playlist, converted and extended in Kotlin.
