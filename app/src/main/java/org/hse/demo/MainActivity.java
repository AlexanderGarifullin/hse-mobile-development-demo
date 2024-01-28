package org.hse.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView result;
    private EditText number;

    private static final int MinValue = 0;
    private static final int MaxValue = 10;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        number = findViewById(R.id.editTextText);
        View button = findViewById(R.id.button);
        View button2 = findViewById(R.id.button2);
        result = findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton2();
            }
        });
    }

    // Получение числа.
    private int getNumber(){
        String numberVal = number.getText().toString();
        if (numberVal.isEmpty()) {
            numberVal = "0";
        }
        boolean cheackSize = numberVal.length() > 2;
        int curNumber = 0;

        if (!cheackSize)  curNumber = Integer.parseInt(numberVal);

        if (cheackSize || curNumber < MinValue || curNumber > MaxValue) {
            String errorMsg = (getString(R.string.error_msg,
                    numberVal, MinValue, MaxValue));
            Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show();
            curNumber = Integer.parseInt(numberVal.substring(0, 2));
            while(curNumber > MaxValue) {
                curNumber/=10;
            }
            number.setText(String.valueOf(curNumber));
        }
        return curNumber;
    }


    // Посчитаем все элементы в списке при нажатии на кнопку Button1
    private void clickButton() {
        int count = getNumber();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i< count; ++i){
            list.add(i+1);
        }
        int sum = list.stream().mapToInt(Integer::intValue).sum();
        setTextViewResult(sum);
    }

    // Произведение всех четных чисел от 0? до вводимого значения.
    private void clickButton2() {
        int count = getNumber();
        int product = 1;
        for (int i = 2; i <= count; i+=2) {
            product *= i;
        }
        if (product == 1) product = 0;
        setTextViewResult(product);
    }

    // Вывод результата.
    private void setTextViewResult(Integer res) {
        result.setText(String.format("Result: %d", res));
    }
}