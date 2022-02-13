 package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    EditText et_height;
    EditText et_weight;
    Button btn_click;
    TextView tv_mass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        et_height = findViewById(R.id.et_height);
        et_weight = findViewById(R.id.et_weight);
        btn_click = findViewById(R.id.btn_click);
        tv_mass = findViewById(R.id.tv_mass);

        btn_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this,(getString(R.string.You_clicked_the_button)),Toast.LENGTH_SHORT).show();

                String et_heightstr = et_height.getText().toString();
                String et_weightstr = et_weight.getText().toString();


                if (TextUtils.isEmpty(et_heightstr)) {
                    et_height.setError(getString(R.string.Please_enter_your_height));
                    et_height.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(et_weightstr)) {
                    et_weight.setError(getString(R.string.Please_enter_your_weight));
                    et_weight.requestFocus();
                    return;
                }


                //Get the user values from the widget reference
                float weight = Float.parseFloat(et_weightstr);
                float height = Float.parseFloat(et_heightstr) / 100;

                //Calculate BMI value
                float bmiValue = BMI(weight, height);

                //Define the meaning of the bmi value
                String bmiInterpretation = interpretBMI(bmiValue);

                tv_mass.setText(String.valueOf(bmiValue + "-" + bmiInterpretation));

            }
        });

    }

    //Calculate BMI
    private float BMI(float weight, float height) {
        return (float) (weight / (height * height));
    }

    // Interpret what BMI means
    private String interpretBMI(float bmiValue) {

        if (bmiValue < 16) {
            return (getString(R.string.Severely_underweight));
        } else if (bmiValue < 18.5) {

            return (getString(R.string.Underweight));
        } else if (bmiValue < 25) {

            return (getString(R.string.Normal));
        } else if (bmiValue < 30) {

            return(getString(R.string.Overweight));
        } else {
            return (getString(R.string.Obese));
        }
    }
}
