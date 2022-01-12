package com.example.people_counter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public int sum = 0;
    private int limit;
    private TextView textView;
    private ProgressBar progressBar;
    private final Handler handler=new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        limit=0;
        textView = findViewById(R.id.count_view);
        progressBar=findViewById(R.id.progressBar);
        //progress bar code for rotation according to addition or subtraction
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(sum);
                        }
                    });
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

//        sum=savedInstanceState.getInt("sum");
//        limit=savedInstanceState.getInt("limit");

    }



    public void add_btn_clicked(View view) {
        //add numbers on button click
        int n = 1;
        if (limit==0){ Toast toast=Toast.makeText(getApplicationContext(),"Set limit",Toast.LENGTH_SHORT);
            toast.show();}
        else if(sum>=limit){ Toast toast=Toast.makeText(getApplicationContext(),"You Have Reached The Limit",Toast.LENGTH_SHORT);
            toast.show();}
        else{
            sum += n;
            String result = "" + sum;
            textView.setText(result);

        }
    }

    public void sub_btn_clicked(View view) {
        //sub numbers on button click
        if (sum <= 0) {
            sum = 0;
        } else {
            int n = 1;
            sum -= n;
        }
        String result = "" + sum;
        textView.setText(result);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //add setting button on top
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //providing action to the setting button
        if (item.getItemId() == R.id.action_setting) {
            Intent intent = new Intent(this, SettingActivity.class);
            startActivityForResult(intent, SettingActivity.SETTING_REQUEST);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        getting the limit from setting page
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==SettingActivity.SETTING_REQUEST)
        {
            if(resultCode==RESULT_OK)
            {
                limit=data.getIntExtra("limit",10);
            }
        }
    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("sum",sum);
        outState.putInt("limit",limit);
        outState.putString("text",textView.getText().toString());
        super.onSaveInstanceState(outState);

    }
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        sum=savedInstanceState.getInt("sum");
        limit=savedInstanceState.getInt("limit");
        textView.setText(savedInstanceState.getString("text"));

    }

}
