package com.shoma.mypocketcare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.shoma.mypocketcare.model.IncomeMasterActivity;

public class MainActivity extends AppCompatActivity {
    TextView textViewIncome,textViewExpenses,textViewBalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewIncome=(TextView)findViewById(R.id.textView_Income);
        textViewExpenses=(TextView)findViewById(R.id.textView_Expenses);
        textViewBalance=(TextView)findViewById(R.id.textView_balance);
        textViewIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, IncomeMasterActivity.class);
                startActivity(intent);
            }
        });
    }
}
