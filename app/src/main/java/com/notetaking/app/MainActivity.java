package com.notetaking.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NotesAdapter.OnNoteClickListener {
    private RecyclerView recyclerView;
    private TextView emptyTextView;
    private NotesAdapter adapter;
    private NotesManager notesManager;
    private static final int REQUEST_CODE_ADD = 1;
    private static final int REQUEST_CODE_EDIT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notesManager = new NotesManager(this);
        
        recyclerView = findViewById(R.id.recyclerView);
        emptyTextView = findViewById(R.id.emptyTextView);
        FloatingActionButton fab = findViewById(R.id.fab);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        
        List<Note> notes = notesManager.loadNotes();
        adapter = new NotesAdapter(notes, this);
        recyclerView.setAdapter(adapter);

        updateEmptyView();

        fab.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, NoteEditorActivity.class);
            startActivityForResult(intent, REQUEST_CODE_ADD);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshNotes();
    }

    private void refreshNotes() {
        List<Note> notes = notesManager.loadNotes();
        adapter.updateNotes(notes);
        updateEmptyView();
    }

    private void updateEmptyView() {
        if (adapter.getItemCount() == 0) {
            recyclerView.setVisibility(View.GONE);
            emptyTextView.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyTextView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onNoteClick(Note note) {
        Intent intent = new Intent(this, NoteEditorActivity.class);
        intent.putExtra("note", note);
        startActivityForResult(intent, REQUEST_CODE_EDIT);
    }
}
