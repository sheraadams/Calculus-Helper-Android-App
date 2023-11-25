# Calculus Helper Android App

[![GitHub](https://img.shields.io/github/license/sheraadams/Calculus-Helper-Android-App)](https://img.shields.io/github/license/sheraadams/Calculus-Helper-Android-App) 
![Static Badge](https://img.shields.io/badge/MP%20Android%20-%20Chart%20-%20blue?link=https%3A%2F%2Fgithub.com%2FPhilJay%2FMPAndroidChart)
![Static Badge](https://img.shields.io/badge/Android%20-%20Studio%20-%20purple?link=https%3A%2F%2Fdeveloper.android.com%2Fstudio)




## About the Project
This app was programmed with Java in [Android Studio](https://developer.android.com/studio). It is compiled with SDK 34 and targets SDK 24 - 33. It is a helpful tool for mathematical modeling and for quick conversion between integrals and derivatives. It is inspired from one of [my first projects](https://github.com/sheraadams/Simple-RSA-Mesage-Encryption) which I originally programmed with Python and Tkinter. 

## Screenshots

![ndroid calc2](https://github.com/sheraadams/Calculus-Helper-Android-App/assets/110789514/cc4e1d05-2cad-4565-aeda-3514f5b57e38)

## Calculations

We calculate the derivative as follows: 
```java
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
```
Similarly, we calculate the inegral:
```java
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
```
## Plotting the data

We can plot the data with the help of the [MPAndroidChart library](https://github.com/PhilJay/MPAndroidChart/tree/master). Once we set up some preferences and plot configurations, we can calulate our data points: 
```java
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
```

We plot the data with the following function: 
```java
    private void setData(LineChart chart, List<Entry> dataSet, String label, int lineColor) {
        LineDataSet lineDataSet = new LineDataSet(dataSet, label);
        lineDataSet.setColor(lineColor);
        lineDataSet.setLineWidth(2f);

        LineData lineData = new LineData(lineDataSet);
        chart.setData(lineData);

        // Refresh chart
        chart.invalidate();
    }
```

<div style="text-align: center;">
  <p><strong>Proudly crafted with ❤️ by <a href="https://github.com/sheraadams" target="_blank">Shera Adams</a>.</strong></p>
</div>
