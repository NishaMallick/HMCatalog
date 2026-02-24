# HM Catalog App

A Jetpack Compose product catalog application that fetches products from the H&M API and displays them in a responsive grid layout.

The goal of this project is to demonstrate clean architecture, pagination, error handling, accessibility support, and modern Android development practices.

---

## Features

- Product listing using Jetpack Compose
- Pagination (infinite scroll)
- Network error handling (no internet, server errors)
- Retry mechanism on failure
- Empty state handling
- Scroll-to-top button
- Accessibility support (TalkBack semantics)
- Dynamic font scaling support
- Unit tests for repository and ViewModel
- UI tests for Compose screens

---

## Tech Stack

| Technology     | Version / Type                  |
|---------------|----------------------------------|
| Language      | Kotlin                           |
| UI            | Jetpack Compose                  |
| Architecture  | Clean Architecture               |
| DI            | Hilt                             |
| Networking    | Retrofit + Moshi                 |
| Async         | Kotlin Coroutines                |
| Testing       | JUnit + Compose UI Test          |
| Build Tool    | Gradle (Kotlin DSL)              |

---

## Architecture

This project follows **Clean Architecture**.

- **UI Layer:** Jetpack Compose screens, manages UI state and user interactions
- **Domain Layer:** Use cases and business models
- **Data Layer:** Repository, API service, DTO mapping

This separation ensures scalability, testability, and maintainability.

---

## Error Handling

The app handles:

- Network errors (no internet connection)
- Server errors
- Unexpected API responses
- Empty product list state

User-friendly messages are shown with a retry mechanism.

---

## Accessibility

- Proper TalkBack support using semantics
- Descriptive content descriptions

---

## How to Run the App

1. Clone the repository -  git clone https://github.com/NishaMallick/HMCatalog.git
2. Open the project in Android Studio
3. Let Gradle sync complete
4. Run the app on an emulator or connected device

---

## Testing

- Repository mapping tests
- ViewModel state tests
- Compose UI tests

---

## Notes

- The app uses the H&M public API endpoint.
- Focus is on clean architecture, UI consistency, and error handling.
