# 🛒 MyTracker

MyTracker is a simple shopping tracker Android app built using modern Android development tools. It allows users to add items to a shopping list, mark them as shopped, and keep track of quantities and dates. The app uses clean MVI architecture with Jetpack Compose for UI.

## ✨ Features

- Add new shopping items via a dialog
- View items in a Pinterest-style grid layout
- Mark items as "shopped" and store the date
- Uses Jetpack Compose for UI
- ViewModel + StateFlow for state management
- Clean MVI architecture
- Dark & light theme support
- Material 3 theming

## 📸 Screenshots

| Add Item Dialog | Shopping List Grid | Expanded Item |
|------------------|--------------------|----------------|
| (screenshots/image.jpeg) | (screenshots/image2.jpeg) | (screenshots/image3.jpeg) |

## 🧱 Architecture

The app follows MVI pattern with the following layers:

- **UI Layer**: Jetpack Compose
- **ViewModel**: Handles business logic and emits states/events
- **State**: Immutable UI state passed to composables
- **Event**: User actions like button clicks
- **Model**: Represents each shopping item

## 🧪 Tech Stack

- Kotlin
- Jetpack Compose
- Android ViewModel
- StateFlow
- Material 3
- Navigation Compose

## 🧰 Setup

1. Clone the repo:
   ```bash
   git clone https://github.com/Arya151/MyTracker.git
