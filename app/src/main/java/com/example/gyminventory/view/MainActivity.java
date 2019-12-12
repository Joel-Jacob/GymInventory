package com.example.gyminventory.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gyminventory.R;
import com.example.gyminventory.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView gymItemsListView;
    private ListView purchasedItemsListView;

    private SharedPreferences sharedPreferences;

    private final String[] gymEquipment = {"Dumbbells", "Squat Rack", "Bench Press", "Treadmill", "Elliptical",
    "Row Machine", "Medicine Ball"};

    private List purchasedItems = new ArrayList<String>();

    private String spKey = "gym_items_key";
    private int REQUEST_CHECK_OUT = 800;
    public static String return_key = "return_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gymItemsListView = findViewById(R.id.gymItemsListView);
        purchasedItemsListView = findViewById(R.id.purchasedItemsListView);

        sharedPreferences = getSharedPreferences(Constants.SHARED_PREFRENCES_NAME, Context.MODE_PRIVATE);

        String messages = sharedPreferences.getString(spKey, "");

        if(messages.length() > 0) {
            String[] tempArray = messages.split(",");

            for(int i = 0; i < tempArray.length;i++){
                purchasedItems.add(tempArray[i]);
            }
        }
        //Toast.makeText(MainActivity.this, messages.toString(), Toast.LENGTH_SHORT).show();


        readGymItems();
        readPurchasedItems();
    }

    private void readGymItems(){

        ArrayAdapter userAdapter = new ArrayAdapter<String>(
                this,
                R.layout.item_layout,
                R.id.item_info_textView,
                gymEquipment);

        gymItemsListView.setAdapter(userAdapter);


        gymItemsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView myTextView = view.findViewById(R.id.item_info_textView);
                //Toast.makeText(MainActivity.this, myTextView.getText().toString(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, CheckOutActivity.class);
                intent.putExtra(CheckOutActivity.checkOut_key, myTextView.getText().toString());


                startActivityForResult(intent, REQUEST_CHECK_OUT);
            }
        });
    }

    private void readPurchasedItems(){

        ArrayAdapter userAdapter = new ArrayAdapter<String>(
                this,
                R.layout.item_layout,
                R.id.item_info_textView,
                purchasedItems);

        purchasedItemsListView.setAdapter(userAdapter);
    }

    @Override
    protected void onStop() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < purchasedItems.size(); i++) {
            sb.append(purchasedItems.get(i)).append(",");
        }

        super.onStop();
        SharedPreferences.Editor spEditor = sharedPreferences.edit();
        spEditor.putString(spKey, sb.toString());

        spEditor.apply();
        //spEditor.remove(spKey);
        //spEditor.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == AppCompatActivity.RESULT_OK && requestCode == REQUEST_CHECK_OUT) {
            //Toast.makeText(MainActivity.this, data.getStringExtra(return_key), Toast.LENGTH_SHORT).show();
            //user2_messageView.setText(data.getStringExtra(user2Key));
            purchasedItems.add(data.getStringExtra(return_key));
            readPurchasedItems();
        }
    }

}
