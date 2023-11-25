package com.example.calculushelper;

import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import android.content.Intent;
public class ZoomedInActivity extends AppCompatActivity {
    private LineChart zoomedInChart;
    FloatingActionButton back_to_graph_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoomed_in);

        zoomedInChart = findViewById(R.id.zoomed_in_chart);

        // Retrieve data from the intent
        double coefficient = getIntent().getDoubleExtra("COEFFICIENT", 1.0);
        double power = getIntent().getDoubleExtra("POWER", 2.0);
        double coefficientAnswer = getIntent().getDoubleExtra("COEFFICIENT_ANSWER", 1.0);
        double powerAnswer = getIntent().getDoubleExtra("POWER_ANSWER", 2.0);
        int chartChoice = getIntent().getIntExtra("CHART_CHOICE", 1);
        // Create data set for zoomed-in chart
        if (chartChoice == 1) {
            String label = "Equation: " + coefficient + "x^" + power;
            List<Entry> zoomedInDataSet1 = generateQuadraticData1(coefficient, power);
            int colorZoomedIn1 = getColor(R.color.colorPrimary);
            setData(zoomedInChart, zoomedInDataSet1, "Zoomed In Chart", colorZoomedIn1);
            setupChart(zoomedInChart, "Zoomed In Chart", "Coefficient x^Power");
            setData(zoomedInChart, zoomedInDataSet1, label, colorZoomedIn1);
        }
        else {
            String label = "Equation: " + coefficientAnswer + "x^" + powerAnswer;
            List<Entry> zoomedInDataSet2 = generateQuadraticData2(coefficientAnswer, powerAnswer);
            int colorZoomedIn1 = getColor(R.color.colorPrimary);
            setData(zoomedInChart, zoomedInDataSet2, label, colorZoomedIn1);
            setupChart(zoomedInChart, "Zoomed In Chart", "Coefficient x^Power");

        }

        // Example usage in onCreate
        findViewById(R.id.back_to_graph_button).setOnClickListener(view ->
                backToGraph(coefficient, power, coefficientAnswer, powerAnswer));
    }

    private List<Entry> generateQuadraticData1(double coefficient, double power) {
        List<Entry> entries = new ArrayList<>();

        for (float x = -5; x <= 5; x += 0.1) {
            float y = (float) (coefficient * Math.pow(x, power));
            entries.add(new Entry(x, y));
        }

        return entries;
    }
    private List<Entry> generateQuadraticData2(double coefficientAnswer, double powerAnswer) {
        List<Entry> entries = new ArrayList<>();

        for (float x = -5; x <= 5; x += 0.1) {
            float y = (float) (coefficientAnswer * Math.pow(x, powerAnswer));
            entries.add(new Entry(x, y));
        }

        return entries;
    }

    private void setupChart(LineChart chart, String title, String yAxisLabel) {

        XAxis xAxis = chart.getXAxis();
        xAxis.setAxisMaximum(5f);
        xAxis.setAxisMinimum(-5f);
        xAxis.setGranularity(10f);
        xAxis.setEnabled(true);
        xAxis.setTextColor(Color.CYAN);
        xAxis.setTextSize(14f);

        YAxis yAxis = chart.getAxisLeft();
        yAxis.setAxisMaximum(100f);
        yAxis.setAxisMinimum(-100f);
        yAxis.setGranularity(10f);
        yAxis.setTextColor(Color.CYAN);
        yAxis.setTextSize(14f);
    }

    private void setData(LineChart chart, List<Entry> dataSet, String label, int lineColor) {
        LineDataSet lineDataSet = new LineDataSet(dataSet, label);
        lineDataSet.setColor(lineColor);
        lineDataSet.setLineWidth(2f);

        LineData lineData = new LineData(lineDataSet);
        chart.setData(lineData);

        chart.getLegend().setEnabled(true);
        chart.getLegend().setTextColor(Color.MAGENTA);
        chart.getLegend().setTextSize(14f);
        // Refresh chart
        chart.invalidate();
    }
    // Method to navigate back to GraphActivity with data
    private void backToGraph(double coefficient, double power, double coefficientAnswer, double powerAnswer) {
        Intent intent = new Intent(ZoomedInActivity.this, GraphActivity.class);
        intent.putExtra("COEFFICIENT", coefficient);
        intent.putExtra("POWER", power);
        intent.putExtra("COEFFICIENT_ANSWER", coefficientAnswer);
        intent.putExtra("POWER_ANSWER", powerAnswer);
        startActivity(intent);
    }

}
