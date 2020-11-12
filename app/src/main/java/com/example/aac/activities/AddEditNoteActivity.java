package com.example.aac.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aac.R;

public class AddEditNoteActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "com.example.aac.activities.EXTRA_ID";
    public static final String EXTRA_TITLE = "com.example.aac.activities.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "com.example.aac.activities.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY = "com.example.aac.activities.EXTRA_PRIORITY";

    private EditText titleEditText, descriptionEditText;
    private NumberPicker priorityNumberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        titleEditText = findViewById(R.id.title_edit_text);
        descriptionEditText = findViewById(R.id.description_edit_text);
        priorityNumberPicker = findViewById(R.id.priority_number_picker);

        priorityNumberPicker.setMinValue(1);
        priorityNumberPicker.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Note");
            titleEditText.setText(intent.getStringExtra(EXTRA_TITLE));
            descriptionEditText.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            priorityNumberPicker.setValue(intent.getIntExtra(EXTRA_PRIORITY, 1));
        } else {
            setTitle("Add Note");
        }

    }

    private void saveNote() {

        String title = titleEditText.getText().toString();
        String description = descriptionEditText.getText().toString();
        int priority = priorityNumberPicker.getValue();

        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this, "Please enter title & description!", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_PRIORITY, priority);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.save_menu) {
            saveNote();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
