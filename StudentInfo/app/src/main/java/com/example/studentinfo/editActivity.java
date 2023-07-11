package com.example.studentinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.TelephonyCallback;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

public class editActivity extends AppCompatActivity implements View.OnClickListener {

    TextView namedValueTextView,birthValueTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        namedValueTextView = findViewById(R.id.nameValueTextView);
        birthValueTextView = findViewById(R.id.birthValueTextView);

        findViewById(R.id.birthDateLayer).setOnClickListener(this); // 날짜 레이어 클릭
        findViewById(R.id.saveButton).setOnClickListener(this); // 저장버튼
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
            case R.id.birthDateLayer:
                //데이트 피커 생성
                DatePickerDialog.OnDateSetListener myDatePicker =
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                                ((TextView)findViewById(R.id.birthValueTextView)).setText(y+"/"+(m+1)+"/"+d);
                            }
                        };
                DatePickerDialog dialog = new DatePickerDialog(this,myDatePicker, 2023, 6, 11);
                dialog.show();
                break;
            case R.id.saveButton:
                // 데이터 저장
                saveDate();
                break;
        }
    }

    private void saveDate() {
        // 이름과 날짜를 읽어서 sp에 저장한다.
        String name = namedValueTextView.getText().toString();
        String birth = birthValueTextView.getText().toString();
        //s_p의 editor 열기
        SharedPreferences.Editor editor = getSharedPreferences(getString(R.string.sp), MODE_PRIVATE).edit();
        editor.putString("이름", name);
        editor.putString("생일", birth);

        // to do

        editor.apply();

        finish();
    }
}