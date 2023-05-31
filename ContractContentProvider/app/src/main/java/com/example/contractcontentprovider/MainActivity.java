package com.example.contractcontentprovider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private  ArrayList<Contact> contactList = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bind UI
        RecyclerView rvContact = findViewById(R.id.rvContact);

        contactList = getAllContact();

       ContactAdapter contractAdapter = new ContactAdapter(
                this,
               contactList
        );

        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvContact.setLayoutManager(llm);
        rvContact.setAdapter(contractAdapter);

    }

    // Get all contract from Contact app
    @SuppressLint("Range")
    private ArrayList<Contact> getAllContact() {

        ArrayList <Contact> contactsCur = new ArrayList<Contact>();
        // Check permission
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission read contract
            ActivityCompat.requestPermissions(
                    this,
                    new String[] {android.Manifest.permission.READ_CONTACTS},
                    0);
        }
        ContentResolver contentResolver = getContentResolver();

        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = contentResolver.query(uri, null, null, null, null);

        if (cursor.getCount() >0) {
            while(cursor.moveToNext()) {

                // Get value from contract
                String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String phone = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                Contact contact = new Contact();
                contact.setDisplayName(displayName);
                contact.setPhone(phone);

                contactsCur.add(contact);
            }
        }

        return contactsCur;
    }

}