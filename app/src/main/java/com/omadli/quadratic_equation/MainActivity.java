package com.omadli.quadratic_equation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    static EditText edit_a, edit_b, edit_c;
    static TextView x1_text, x2_text, result_text;
    static Button calc, clear;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit_a = findViewById(R.id.edit_a);
        edit_b = findViewById(R.id.edit_b);
        edit_c = findViewById(R.id.edit_c);
        x1_text = findViewById(R.id.x1);
        x2_text = findViewById(R.id.x2);
        result_text = findViewById(R.id.result_text);
        calc = findViewById(R.id.calc);
        clear = findViewById(R.id.clear);
        img = findViewById(R.id.image_formula);

        clear.setOnClickListener(view -> {
            img.setImageResource(R.drawable.formula_default);
            edit_a.getText().clear();
            edit_b.getText().clear();
            edit_c.getText().clear();
            clear_texts();
        });

        calc.setOnClickListener(view -> {
            if (edit_a.getText().toString().isEmpty() || edit_b.getText().toString().isEmpty() ||
            edit_c.getText().toString().isEmpty()){
                Toast.makeText(getApplicationContext(), "a, b, c koeffitsientlarni kiriting", Toast.LENGTH_SHORT).show();
            }else{
                double a = Double.parseDouble(edit_a.getText().toString());
                double b = Double.parseDouble(edit_b.getText().toString());
                double c = Double.parseDouble(edit_c.getText().toString());
                clear_texts();
                double D;
                hideSoftKeyboard(this);
                if(a == 0){
                    Toast.makeText(this, "Kvadrat tenglama uchun a≠0 bo'lishi kerak", Toast.LENGTH_SHORT).show();
                }else {
                    D = Math.pow(b, 2) - 4 * a * c;
                    if (D < 0) {
                        // yechimga ega emas
                        result_text.setText("Tenglama haqiqiy yechimga ega emas");
                        result_text.setTextColor(Color.RED);
                        img.setImageResource(R.drawable.formula_unknown);
                    } else if (D == 0) {
                        // yagona yechim
                        double x = (-1 * b) / (2 * a);
                        result_text.setText("Tenglama yagona yechimga ega");
                        result_text.setTextColor(Color.parseColor("#047A09"));
                        img.setImageResource(R.drawable.formula_x);
                        x1_text.setText("x = " + num_format(x));
                    } else {
                        // 2 ta haqiqiy yechim
                        double x1, x2;
                        x1 = (-1 * b + Math.sqrt(D)) / (2 * a);
                        x2 = (-1 * b - Math.sqrt(D)) / (2 * a);
                        result_text.setText("Tenglama 2 ta yechimga ega");
                        result_text.setTextColor(Color.parseColor("#047A09"));
                        img.setImageResource(R.drawable.formula_x12);
                        x1_text.setText("x1 = " + num_format(x1));
                        x2_text.setText("x2 = " + num_format(x2));
                    }
                }
            }
        });


    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        if(inputMethodManager.isAcceptingText()){
            inputMethodManager.hideSoftInputFromWindow(
                    activity.getCurrentFocus().getWindowToken(),
                    0
            );
        }
    }

    public static void clear_texts(){
        x1_text.setText("");
        x2_text.setText("");
        result_text.setText("Kvadrat tenglama");
        result_text.setTextColor(Color.BLACK);
    }

    public static String num_format(double x){
        int butun = (int) x;
        if(x - butun == 0){
            return String.valueOf(butun);
        }else{
            return String.valueOf(x);
        }
    }

}