package com.example.zakatgold;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etWeight, etValue;
    Spinner spinnerType;
    Button btnCalculate, btnGoToAbout;
    TextView tvTotalValue, tvPayableValue, tvTotalZakat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etWeight = findViewById(R.id.etWeight);
        etValue = findViewById(R.id.etValue);
        spinnerType = findViewById(R.id.spinnerType);
        btnCalculate = findViewById(R.id.btnCalculate);
        btnGoToAbout = findViewById(R.id.btnGoToAbout);
        tvTotalValue = findViewById(R.id.tvTotalValue);
        tvPayableValue = findViewById(R.id.tvPayableValue);
        tvTotalZakat = findViewById(R.id.tvTotalZakat);

        String[] types = {"Keep", "Wear"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, types);
        spinnerType.setAdapter(adapter);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateZakat();
            }
        });

        spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (!etWeight.getText().toString().trim().isEmpty() && !etValue.getText().toString().trim().isEmpty()) {
                    calculateZakat();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnGoToAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });
    }

    private void calculateZakat() {

        if (etWeight.getText().toString().trim().isEmpty()) {
            etWeight.setError("PLEASE INSERT THE WEIGHT OF GOLD!");
            return;
        }
        if (etValue.getText().toString().trim().isEmpty()) {
            etValue.setError("PLEASE ENTER CURRENT GOLD VALUE!");
            return;
        }

        double weight = Double.parseDouble(etWeight.getText().toString());
        double valuePerGram = Double.parseDouble(etValue.getText().toString());
        String selectedType = spinnerType.getSelectedItem().toString();

        double X = selectedType.equals("Keep") ? 85.0 : 200.0;


        double totalValue = weight * valuePerGram;


        double weightMinusX = weight - X;
        double payableValue = 0.0;
        double totalZakat = 0.0;

        if (weightMinusX > 0) {
            payableValue = weightMinusX * valuePerGram;
            totalZakat = payableValue * 0.025;
        } else {
            payableValue = 0.0;
            totalZakat = 0.0;
            Toast.makeText(this, "The weight does not exceed the uruf. No zakat is imposed.", Toast.LENGTH_SHORT).show();
        }

        tvTotalValue.setText(String.format("Total Gold Value: RM %.2f", totalValue));
        tvPayableValue.setText(String.format("Zakat Payable Value: RM %.2f", payableValue));
        tvTotalZakat.setText(String.format("Total Zakat: RM %.2f", totalZakat));
    }
}