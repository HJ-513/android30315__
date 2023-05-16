package com.example.animalhospital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class InfoActivity extends AppCompatActivity  {

    int imgs[] = {R.drawable.info1, R.drawable.info2,
                    R.drawable.info3, R.drawable.info4, R.drawable.info5};
    int img_index = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Intent intent = getIntent();
        String str = intent.getStringExtra("title");
        TextView txtView = findViewById(R.id.txt_info);
        txtView.setText(str);

        //btn_info_back : 뒤로가기 버튼
        findViewById(R.id.btn_info_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //이미지 클릭 시 다른 그림으로 바꾸기
        findViewById(R.id.img_info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView img = findViewById(R.id.img_info);
                img.setImageResource(imgs[++img_index % imgs.length]);
            }
        });
    }

}