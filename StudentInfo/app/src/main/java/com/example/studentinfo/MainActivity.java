package com.example.studentinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView namedValueTextView,birthValueTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.editButton).setOnClickListener(this);
        findViewById(R.id.deleteButton).setOnClickListener(this);
        namedValueTextView = findViewById(R.id.nameValueTextView);
        birthValueTextView = findViewById(R.id.birthValueTextView);

        readData();
    }

    private void readData() {
        SharedPreferences sp = getSharedPreferences(getString(R.string.sp), MODE_PRIVATE);
        namedValueTextView.setText(sp.getString("이름","무명"));
        birthValueTextView.setText(sp.getString("생일", "모름"));
        // 혈액형, 기타정보 읽어서 화면에 표시
        // to do
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.editButton:
                Intent intent = new Intent(MainActivity.this, editActivity.class);
                startActivity(intent);
                break;
            case R.id.deleteButton:
                SharedPreferences.Editor editor = getSharedPreferences(getString(R.string.sp), MODE_PRIVATE).edit();
                editor.clear();
                editor.apply();
                readData();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        readData();
    }
}