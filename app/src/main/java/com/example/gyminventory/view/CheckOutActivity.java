package com.example.gyminventory.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gyminventory.R;

public class CheckOutActivity extends AppCompatActivity {

    private TextView titleTextView;
    private EditText quantityEditText;
    private Button saveButton;

    public static String checkOut_key = "check_out";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_out_activity);

        titleTextView = findViewById(R.id.checkOuTitleView);
        quantityEditText = findViewById(R.id.quantityEditText);
        saveButton = findViewById(R.id.checkOutButton);

        titleTextView.setText(getIntent().getStringExtra(checkOut_key));

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantityEditText.getText().toString().length() > 0 || quantityEditText.getText().toString().trim() != "0") {
                    String message = getIntent().getStringExtra(checkOut_key);
                    message += " x" + quantityEditText.getText().toString();

                    Intent intent = new Intent(CheckOutActivity.this, MainActivity.class);
                    intent.putExtra(MainActivity.return_key, message);
                    setResult(AppCompatActivity.RESULT_OK, intent);

                    finish();
                }
                else
                    Toast.makeText(CheckOutActivity.this, "Quantity must be greater than 1", Toast.LENGTH_SHORT).show();


            }
        });
    }
}
