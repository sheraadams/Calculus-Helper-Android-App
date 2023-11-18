package com.example.calculushelper;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.util.Log;
public class MainActivity extends AppCompatActivity {
    Button calculate_button;
    Switch choice_switch;

    EditText coefficient, coefficient_answer, power, power_answer, state, state2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize EditText variables using findViewById
        coefficient = findViewById(R.id.editTextCoefficient);
        coefficient_answer = findViewById(R.id.editTextCoefficientAnswer);
        power = findViewById(R.id.editTextPower);
        power_answer = findViewById(R.id.editTextPowerAnswer);

        // Initialize button and switch (assuming you have them in your layout)
        calculate_button = findViewById(R.id.calculate_button);
        choice_switch = findViewById(R.id.choice_switch);
        state = findViewById(R.id.state);
        state2 = findViewById(R.id.state2);
        choice_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (choice_switch.isChecked()) {
                    state.setText("Integral");
                    state2.setText("Integral");
                } else {
                    state.setText("Derivative");
                    state2.setText("Derivative");
                }
            }
        });
        calculate_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check the state of the switch to decide whether to perform derivative or integral
                if (choice_switch.isChecked()) {
                    integral();

                } else {
                    derivative();

                }
            }
        });
    }

    void derivative() {
        double coefficientValue = Double.parseDouble(coefficient.getText().toString());
        double powerValue = Double.parseDouble(power.getText().toString());

        Log.d("Debug", "coefficientValue: " + coefficientValue);
        Log.d("Debug", "powerValue: " + powerValue);
        System.out.println("c"+ coefficientValue);
        System.out.println("p"+powerValue);
        double answer_coefficient = coefficientValue * powerValue;

        double answer_power = powerValue - 1;

        Log.d("Debug", "answer_coefficient: " + answer_coefficient);
        Log.d("Debug", "answer_power: " + answer_power);

        coefficient_answer.setText(String.valueOf(answer_coefficient));
        power_answer.setText(String.valueOf(answer_power));
    }


    void integral() {
        // Antiderivative calculation
        double coefficientValue = Double.parseDouble(coefficient.getText().toString());
        double powerValue = Double.parseDouble(power.getText().toString());

        double answer_power = powerValue + 1;
        double answer_coefficient = coefficientValue / answer_power;

        // Set the results to the corresponding EditTexts
        power_answer.setText(String.valueOf(answer_power));
        coefficient_answer.setText(String.valueOf(answer_coefficient));
    }

}
