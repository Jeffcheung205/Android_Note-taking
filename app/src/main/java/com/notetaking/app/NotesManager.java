package com.notetaking.app;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class NotesManager {
    private static final String PREFS_NAME = "NotesPrefs";
    private static final String NOTES_KEY = "notes";
    private SharedPreferences sharedPreferences;
    private Gson gson;

    public NotesManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    public List<Note> loadNotes() {
        String json = sharedPreferences.getString(NOTES_KEY, null);
        if (json == null) {
            return new ArrayList<>();
        }
        Type type = new TypeToken<ArrayList<Note>>() {}.getType();
        return gson.fromJson(json, type);
    }

    public void saveNotes(List<Note> notes) {
        String json = gson.toJson(notes);
        sharedPreferences.edit().putString(NOTES_KEY, json).apply();
    }

    public void addNote(Note note) {
        List<Note> notes = loadNotes();
        notes.add(0, note); // Add at the beginning
        saveNotes(notes);
    }

    public void updateNote(Note updatedNote) {
        List<Note> notes = loadNotes();
        for (int i = 0; i < notes.size(); i++) {
            if (notes.get(i).getId().equals(updatedNote.getId())) {
                notes.set(i, updatedNote);
                break;
            }
        }
        saveNotes(notes);
    }

    public void deleteNote(String noteId) {
        List<Note> notes = loadNotes();
        for (int i = 0; i < notes.size(); i++) {
            if (notes.get(i).getId().equals(noteId)) {
                notes.remove(i);
                break;
            }
        }
        saveNotes(notes);
    }
}
