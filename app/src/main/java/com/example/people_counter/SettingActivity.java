package com.example.people_counter;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class SettingActivity extends AppCompatActivity {
    public static int SETTING_REQUEST = 1234;
    private EditText max_limit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        max_limit =findViewById(R.id.max_limit);


    }

    public void savedClicked(View view) {
        //store limit in variable
        String text = max_limit.getText().toString();
        Intent intent = new Intent();
        int limit=Integer.parseInt(text);
        intent.putExtra("limit",limit);
        setResult(RESULT_OK,intent);
        finish();
    }

}