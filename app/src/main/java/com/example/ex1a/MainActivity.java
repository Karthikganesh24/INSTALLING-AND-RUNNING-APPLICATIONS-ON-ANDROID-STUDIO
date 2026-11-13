package com.example.ex1a;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout tilName;
    private EditText etName;
    private TextView tvGreeting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize UI views
        tilName = findViewById(R.id.tilName);
        etName = findViewById(R.id.etName);
        Button btnSubmit = findViewById(R.id.btnSubmit);
        Button btnClear = findViewById(R.id.btnClear);
        tvGreeting = findViewById(R.id.tvGreeting);

        // Submit button click listener
        btnSubmit.setOnClickListener(v -> {
            String name = etName.getText() != null ? etName.getText().toString().trim() : "";
            if (TextUtils.isEmpty(name)) {
                tilName.setError(getString(R.string.empty_name_error));
                Toast.makeText(MainActivity.this, R.string.empty_name_error, Toast.LENGTH_SHORT).show();
            } else {
                tilName.setError(null);
                String welcomeMessage = "Hello, " + name + "!\n\nYour simple Android project is up and running successfully.";
                tvGreeting.setText(welcomeMessage);
                Toast.makeText(MainActivity.this, "Welcome, " + name + "!", Toast.LENGTH_SHORT).show();
            }
        });

        // Clear button click listener
        btnClear.setOnClickListener(v -> {
            etName.setText("");
            tilName.setError(null);
            tvGreeting.setText(R.string.default_greeting);
            Toast.makeText(MainActivity.this, "Cleared", Toast.LENGTH_SHORT).show();
        });
    }
}