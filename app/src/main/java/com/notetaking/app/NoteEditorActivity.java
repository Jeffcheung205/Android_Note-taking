package com.notetaking.app;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

public class NoteEditorActivity extends AppCompatActivity {
    private EditText titleEditText;
    private EditText contentEditText;
    private NotesManager notesManager;
    private Note currentNote;
    private boolean isEditMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);

        notesManager = new NotesManager(this);
        
        titleEditText = findViewById(R.id.titleEditText);
        contentEditText = findViewById(R.id.contentEditText);

        // Check if we're editing an existing note
        if (getIntent().hasExtra("note")) {
            currentNote = (Note) getIntent().getSerializableExtra("note");
            if (currentNote != null) {
                isEditMode = true;
                titleEditText.setText(currentNote.getTitle());
                contentEditText.setText(currentNote.getContent());
                setTitle(R.string.edit_note);
            }
        } else {
            currentNote = new Note();
            setTitle(R.string.add_note);
        }

        // Enable back button
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Handle back press using OnBackPressedCallback
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        // Hide delete button if it's a new note
        if (!isEditMode) {
            menu.findItem(R.id.action_delete).setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        
        if (id == R.id.action_save) {
            saveNote();
            return true;
        } else if (id == R.id.action_delete) {
            deleteNote();
            return true;
        } else if (id == android.R.id.home) {
            finish();
            return true;
        }
        
        return super.onOptionsItemSelected(item);
    }

    private void saveNote() {
        String title = titleEditText.getText().toString().trim();
        String content = contentEditText.getText().toString().trim();

        if (TextUtils.isEmpty(title) && TextUtils.isEmpty(content)) {
            Toast.makeText(this, R.string.empty_note, Toast.LENGTH_SHORT).show();
            return;
        }

        // Use "Untitled" if no title is provided
        if (TextUtils.isEmpty(title)) {
            title = "Untitled";
        }

        currentNote.setTitle(title);
        currentNote.setContent(content);
        currentNote.setTimestamp(System.currentTimeMillis());

        if (isEditMode) {
            notesManager.updateNote(currentNote);
        } else {
            notesManager.addNote(currentNote);
        }

        Toast.makeText(this, R.string.note_saved, Toast.LENGTH_SHORT).show();
        finish();
    }

    private void deleteNote() {
        if (isEditMode && currentNote != null) {
            notesManager.deleteNote(currentNote.getId());
            Toast.makeText(this, R.string.note_deleted, Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
