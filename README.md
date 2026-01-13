# Android Note-Taking App

A simple and functional note-taking application for Android that allows users to create, edit, view, and delete notes.

## Features

- **Create Notes**: Add new notes with titles and content
- **View Notes**: Browse all your notes in a clean list view
- **Edit Notes**: Modify existing notes anytime
- **Delete Notes**: Remove notes you no longer need
- **Persistent Storage**: Notes are saved locally using SharedPreferences with Gson serialization
- **Modern UI**: Material Design components with CardView and FloatingActionButton

## Technical Details

- **Minimum SDK**: Android 5.0 (API 21)
- **Target SDK**: Android 13 (API 33)
- **Language**: Java
- **Architecture**: Simple MVC pattern
- **Data Persistence**: SharedPreferences with Gson

## Project Structure

```
app/
├── src/main/
│   ├── java/com/notetaking/app/
│   │   ├── MainActivity.java          # Main screen showing list of notes
│   │   ├── NoteEditorActivity.java    # Screen for creating/editing notes
│   │   ├── Note.java                  # Note data model
│   │   ├── NotesAdapter.java          # RecyclerView adapter for notes list
│   │   └── NotesManager.java          # Handles data persistence
│   ├── res/
│   │   ├── layout/                    # XML layouts
│   │   ├── values/                    # Strings, colors, themes
│   │   └── menu/                      # Menu resources
│   └── AndroidManifest.xml
└── build.gradle
```

## Building the Project

1. Clone the repository:
   ```bash
   git clone https://github.com/Jeffcheung205/Android_Note-taking.git
   ```

2. Open the project in Android Studio

3. Sync Gradle files

4. Run on an emulator or physical device

## Usage

1. **Add a Note**: Tap the floating action button (+) on the main screen
2. **Edit a Note**: Tap on any note in the list to open and edit it
3. **Save a Note**: Tap the save icon in the editor toolbar
4. **Delete a Note**: Open a note and tap the delete icon in the toolbar

## Dependencies

- AndroidX AppCompat
- Material Components
- RecyclerView
- CardView
- ConstraintLayout
- Gson (for JSON serialization)

## License

This project is open source and available for educational purposes.
