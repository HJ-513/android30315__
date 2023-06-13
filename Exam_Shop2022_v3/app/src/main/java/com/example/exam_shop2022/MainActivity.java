package com.example.exam_shop2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView img_product_main;
    EditText edit_count;
    TextView txt_price, txt_delivery, txt_pay;
    CheckBox chk_agree;

    int val_price;                  // 제품의 총 가격(배송비 미포함)
    int val_delivery;               // 배송비 ( 제품 총 가격이 10000원 이상이면 0원, 미만이면 2500원)
    int val_pay;                    // 총 결제 금액
    int selected_product=2000;      // 선택한 제품 한개의 가격
    int selected_count;             // 선택한 수량

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // findview
        img_product_main = findViewById(R.id.img_product_main);
        edit_count = findViewById(R.id.edit_count);
        txt_price = findViewById(R.id.txt_price);
        txt_delivery = findViewById(R.id.txt_delivery);
        txt_pay= findViewById(R.id.txt_pay);
        chk_agree = findViewById(R.id.chk_agree);

        //click event
        findViewById(R.id.radio1).setOnClickListener(this);
        findViewById(R.id.radio2).setOnClickListener(this);
        findViewById(R.id.radio3).setOnClickListener(this);
        findViewById(R.id.radio4).setOnClickListener(this);
        findViewById(R.id.btn_plus).setOnClickListener(this);
        findViewById(R.id.btn_minus).setOnClickListener(this);
        findViewById(R.id.btn_pay).setOnClickListener(this);

        //기본 세팅값 계산
        sumTotal();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.radio1:
                img_product_main.setImageResource(R.drawable.coffee1);
                selected_product = 2000;
                sumTotal();
                break;
            case R.id.radio2:
                img_product_main.setImageResource(R.drawable.coffee2);
                selected_product = 3000;
                sumTotal();
                break;
            case R.id.radio3:
                img_product_main.setImageResource(R.drawable.coffee3);
                selected_product = 4000;
                sumTotal();
                break;
            case R.id.radio4:
                img_product_main.setImageResource(R.drawable.coffee4);
                selected_product = 5000;
                sumTotal();
                break;
            case R.id.btn_minus:
                selected_count = Integer.parseInt(edit_count.getText().toString());
                if(selected_count <= 1)
                    Toast.makeText(this, "최소주문은 1개입니다.", Toast.LENGTH_SHORT).show();
                else{
                    --selected_count;
                    edit_count.setText(Integer.toString(selected_count));
                    sumTotal();
                }
                break;
            case R.id.btn_plus:
                selected_count = Integer.parseInt(edit_count.getText().toString());
                if(selected_count>=5)
                    Toast.makeText(this, "최대주문은 5개입니다.", Toast.LENGTH_SHORT).show();
                else{
                    ++selected_count;
                    edit_count.setText(Integer.toString(selected_count));
                    sumTotal();
                }
                break;
            case R.id.btn_pay:
                if(chk_agree.isChecked()){
                    Intent intent = new Intent(MainActivity.this, SubActivity.class);
                    intent.putExtra("MSG", "30315조혜준");
                    intent.putExtra("PRICE", val_pay);
                    startActivity(intent);
                }
                else Toast.makeText(this, "동의하세요!", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void sumTotal() {
        // 선택한 수량
        selected_count = Integer.parseInt(edit_count.getText().toString());
        // 단가 * 수량
        val_price = selected_count * selected_product;
        // 배송료
        if(val_price>=10000)
            val_delivery = 0;
        else
            val_delivery = 2500;

        // 총금액
        val_pay = val_price + val_delivery;
        // 화면에 표시
        txt_price.setText(val_price+"원");
        txt_delivery.setText(val_delivery+"원");
        txt_pay.setText(val_pay+"원");
    }
}