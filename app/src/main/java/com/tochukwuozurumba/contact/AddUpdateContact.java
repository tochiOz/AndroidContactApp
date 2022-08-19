package com.tochukwuozurumba.contact;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class AddUpdateContact extends AppCompatActivity {

//    define contact variables
    private ImageView profileImageView;
    private Button submitButton;
    private EditText personName, personPhone, personEmail, personNote;

//    String Variables
    String name, email, phone, note;

//    action variables
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_update_contact);

        SharedPreferenceManager.init(AddUpdateContact.this);

//        init actionBar
        actionBar = getSupportActionBar();
        actionBar.setTitle("Add Contact");
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

//        back button
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

//        link variables
        profileImageView = findViewById(R.id.profileImageView);
        personName = findViewById(R.id.personName);
        personPhone = findViewById(R.id.personPhone);
        personEmail = findViewById(R.id.personEmail);
        personNote = findViewById(R.id.personNote);
        submitButton = findViewById(R.id.submit_contact);

//        add submit listener
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveContact();
            }
        });

    }

    private void saveContact() {
        name = personName.getText().toString();
        email = personEmail.getText().toString();
        phone = personPhone.getText().toString();
        note = personNote.getText().toString();
        Intent replyIntent = new Intent();

//        validate inputs
        if (name.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please input your Name..", Toast.LENGTH_SHORT).show();
            setResult(RESULT_CANCELED, replyIntent);
        } else if (phone.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please input your Phone..", Toast.LENGTH_SHORT).show();
            setResult(RESULT_CANCELED, replyIntent);
        } else if (email.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please input your Email..", Toast.LENGTH_SHORT).show();
            setResult(RESULT_CANCELED, replyIntent);
        } else if (note.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please input your Note..", Toast.LENGTH_SHORT).show();
            setResult(RESULT_CANCELED, replyIntent);
        } else {
//            submit your action
            SharedPreferenceManager.setString("contact_name", name);
            SharedPreferenceManager.setString("contact_phone", phone);
            SharedPreferenceManager.setString("contact_email", email);
            SharedPreferenceManager.setString("contact_note", note);

            setResult(RESULT_OK, replyIntent);
            finish();
        }
    }

//    back button function
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}