package com.example.driverregistration;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.BreakIterator;

public class RegistrationActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView imageView;
    private byte[] licenseImage;
    private BreakIterator firstNameEditText;

    public RegistrationActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);

        ImageView imageView = findViewById(R.id.imageView);
        EditText firstNameEditText = findViewById(R.id.fname2);
          EditText lastNameEditText = findViewById(R.id.lname);
        EditText emailEditText = findViewById(R.id.email);
        EditText passwordEditText = findViewById(R.id.password);
        EditText ageEditText = findViewById(R.id.age);
        Database database = new Database(this);

        imageView.setOnClickListener(this::onChooseImageClick);
    }



    public void onChooseImageClick(View view) {
        // Open the image picker
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        resultLauncher.launch(intent);
    }

    ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            this::onActivityResult);

    public void registerDriver(View view){
        String fname2 = firstNameEditText.getText().toString();
    }


    private byte[] getBytes(InputStream iStream) throws IOException{
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len;


        while ((len = iStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }


    private void onActivityResult(ActivityResult result) {
        if (result.getResultCode() == Activity.RESULT_OK) {
            Intent data = result.getData();
            try {
                // Get the selected image and convert it to a byte array

                Uri selectedImageUri = data != null ? data.getData() : null;
                InputStream iStream = getContentResolver().openInputStream(selectedImageUri);
                licenseImage = getBytes(iStream != null ? iStream : null);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
