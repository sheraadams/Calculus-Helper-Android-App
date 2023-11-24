package com.example.calculushelper;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class GraphActivity extends AppCompatActivity {
    private LineChart chartTop, chartBottom;
    FloatingActionButton calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        chartTop = findViewById(R.id.chartTop);
        chartBottom = findViewById(R.id.chartBottom);
        calculateButton = findViewById(R.id.calculate_button);

        // Retrieve data from the intent
        double coefficient = getIntent().getDoubleExtra("COEFFICIENT", 1.0);
        double power = getIntent().getDoubleExtra("POWER", 2.0);
        double coefficientAnswer = getIntent().getDoubleExtra("COEFFICIENT_ANSWER", 1.0);
        double powerAnswer = getIntent().getDoubleExtra("POWER_ANSWER", 2.0);

        Log.d("YourTag", "Coefficients: " + coefficient + "\nPower: " + power + "\nCoefficient Answer: "
                + coefficientAnswer + "\nPower Answer: " + powerAnswer);


        String equationText = getString(R.string.equation_template, coefficient, power);
        String equationTextBottom = getString(R.string.equation_template, coefficientAnswer, powerAnswer);

        setupChart(chartTop, "Quadratic Function", "Coefficient x^Power");
        setupChart(chartBottom, "Quadratic Function Answer", "Coefficient Answer x^Power Answer");

        // Create data sets
        List<Entry> dataSetTop = generateQuadraticData(coefficient, power);
        List<Entry> dataSetBottom = generateQuadraticData(coefficientAnswer, powerAnswer);

        int colorTop = ContextCompat.getColor(this, R.color.teal_200);
        int colorBottom = ContextCompat.getColor(this, R.color.purple_500);

        // Set data to charts and label them with the equations
        setData(chartTop, dataSetTop, equationText, colorTop);
        setData(chartBottom, dataSetBottom, equationTextBottom, colorBottom);

        // Set click listener for the graph button
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchCalculatorActivity();
            }
        });
    }

    private void setupChart(LineChart chart, String title, String yAxisLabel) {
        chart.getDescription().setText(title);
        chart.setNoDataText("No data available");
        chart.setNoDataTextColor(ContextCompat.getColor(this, android.R.color.black));
        chart.setDrawGridBackground(false);
        chart.setDrawBorders(true);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setDrawGridLines(true);
        xAxis.setAxisMaximum(10f); // show positive and negative x axis equally
        xAxis.setAxisMinimum(-10f); // show positive and negative x axis equally
        xAxis.setTextColor(Color.CYAN);
        xAxis.setTextSize(12f);

        YAxis yAxis = chart.getAxisLeft();
        yAxis.setGranularity(1f);
        yAxis.setDrawGridLines(true);
        yAxis.setAxisMaximum(15f); // show positive and negative y axis equally
        yAxis.setAxisMinimum(-15f); // show positive and negative y axis equally
        yAxis.setLabelCount(5, true);
        yAxis.setGranularity(10f);
        yAxis.setTextColor(Color.CYAN);
        yAxis.setTextSize(14f);

        chart.getAxisRight().setEnabled(false);

        chart.getLegend().setEnabled(true);
        chart.getLegend().setTextColor(Color.MAGENTA);
        chart.getLegend().setTextSize(14f);

        chart.getAxisLeft().setDrawGridLines(false);

        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                return String.valueOf((int) value);
            }
        });
    }

    private List<Entry> generateQuadraticData(double coefficient, double power) {
        List<Entry> entries = new ArrayList<>();

        // increment by .1 to smooth out the curves
        for (float x = -20; x <= 20; x += 0.1) {
            float y = (float) (coefficient * Math.pow(x, power));
            entries.add(new Entry(x, y));
        }

        Log.d("GraphActivity", "generateQuadraticData entries: " + entries);
        return entries;
    }


    private void setData(LineChart chart, List<Entry> dataSet, String label, int lineColor) {
        LineDataSet lineDataSet = new LineDataSet(dataSet, label);
        lineDataSet.setColor(lineColor);
        lineDataSet.setLineWidth(2f);

        LineData lineData = new LineData(lineDataSet);
        chart.setData(lineData);
        // chart.setBackgroundColor(ContextCompat.getColor(this, R.color.purple_700));

        // Refresh chart
        chart.invalidate();
    }

    void launchCalculatorActivity() {
        Intent intent = new Intent(GraphActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
