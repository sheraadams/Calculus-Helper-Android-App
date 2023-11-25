package com.example.calculushelper;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    Button calculateButton;
    FloatingActionButton graphButton;
    Switch choiceSwitch;

    EditText coefficient, coefficientAnswer, power, powerAnswer, state, state2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize EditText variables using findViewById
        coefficient = findViewById(R.id.editTextCoefficient);
        coefficientAnswer = findViewById(R.id.editTextCoefficientAnswer);
        power = findViewById(R.id.editTextPower);
        powerAnswer = findViewById(R.id.editTextPowerAnswer);

        // Initialize button and switch
        calculateButton = findViewById(R.id.calculate_button);
        choiceSwitch = findViewById(R.id.choice_switch);
        state = findViewById(R.id.state);
        state2 = findViewById(R.id.state2);
        graphButton = findViewById(R.id.graph_button);

        choiceSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateSwitchText();
            }
        });

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (choiceSwitch.isChecked()) {
                    integral();
                } else {
                    derivative();
                }
            }
        });
        // Set click listener for the graph button
        graphButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchGraphActivity();
            }
        });
    }

    void derivative() {
        try {
            double coefficientValue = Double.parseDouble(coefficient.getText().toString());
            double powerValue = Double.parseDouble(power.getText().toString());

            double answerCoefficient = coefficientValue * powerValue;
            double answerPower = powerValue - 1;

            coefficientAnswer.setText(String.valueOf(answerCoefficient));
            powerAnswer.setText(String.valueOf(answerPower));

        } catch (NumberFormatException e) {
            // Handle parsing errors
            Log.e("Error", "Invalid input");
        }
    }

    void integral() {
        try {
            double coefficientValue = Double.parseDouble(coefficient.getText().toString());
            double powerValue = Double.parseDouble(power.getText().toString());

            double answerPower = powerValue + 1;
            double answerCoefficient = coefficientValue / answerPower;

            powerAnswer.setText(String.valueOf(answerPower));
            coefficientAnswer.setText(String.valueOf(answerCoefficient));

        } catch (NumberFormatException e) {
            // Handle parsing errors
            Log.e("Error", "Invalid input");
        }
    }

    void updateSwitchText() {
        state.setText(choiceSwitch.isChecked() ? "Integral" : "Derivative");
        state2.setText(state.getText());
    }
    private void launchGraphActivity() {
        EditText coefficientEditText = findViewById(R.id.editTextCoefficient);
        EditText powerEditText = findViewById(R.id.editTextPower);

        String coefficientText = coefficientEditText.getText().toString().trim();
        String powerText = powerEditText.getText().toString().trim();

        if (!coefficientText.isEmpty() && !powerText.isEmpty()) {
            // Parse the values only if the strings are not empty
            double coefficient = Double.parseDouble(coefficientText);
            double power = Double.parseDouble(powerText);

            EditText coefficientAnswerEditText = findViewById(R.id.editTextCoefficientAnswer);
            EditText powerAnswerEditText = findViewById(R.id.editTextPowerAnswer);

            String coefficientAnswerText = coefficientAnswerEditText.getText().toString().trim();
            String powerAnswerText = powerAnswerEditText.getText().toString().trim();

            // Check if coefficientAnswerText and powerAnswerText are not empty
            if (!coefficientAnswerText.isEmpty() && !powerAnswerText.isEmpty()) {
                // Parse the values
                double coefficientAnswer = Double.parseDouble(coefficientAnswerText);
                double powerAnswer = Double.parseDouble(powerAnswerText);

                // Pass the values to the GraphActivity
                Intent intent = new Intent(MainActivity.this, GraphActivity.class);
                intent.putExtra("COEFFICIENT", coefficient);
                intent.putExtra("POWER", power);
                intent.putExtra("COEFFICIENT_ANSWER", coefficientAnswer);
                intent.putExtra("POWER_ANSWER", powerAnswer);

                startActivity(intent);
            } else {
                // Handle the case where one or both of the answer inputs are empty
                Toast.makeText(MainActivity.this, "Please enter valid answer values", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Handle the case where one or both of the inputs are empty
            Toast.makeText(MainActivity.this, "Please enter valid values", Toast.LENGTH_SHORT).show();
        }
    }



}
