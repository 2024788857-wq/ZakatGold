package com.example.zaktgold;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etWeight, etPrice;
    private RadioGroup rgGoldType;
    private RadioButton rbKeep, rbWear;
    private Button btnCalculate, btnGoToAbout;
    private TextView tvTotalValue, tvZakatPayable, tvTotalZakat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etWeight = findViewById(R.id.etWeight);
        etPrice = findViewById(R.id.etPrice);
        rgGoldType = findViewById(R.id.rgGoldType);
        rbKeep = findViewById(R.id.rbKeep);
        rbWear = findViewById(R.id.rbWear);
        btnCalculate = findViewById(R.id.btnCalculate);
        btnGoToAbout = findViewById(R.id.btnGoToAbout);
        tvTotalValue = findViewById(R.id.tvTotalValue);
        tvZakatPayable = findViewById(R.id.tvZakatPayable);
        tvTotalZakat = findViewById(R.id.tvTotalZakat);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateZakat();
            }
        });

        btnGoToAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, aboutactivity.class);
                startActivity(intent);
            }
        });
    }

    private void calculateZakat() {
        String weightStr = etWeight.getText().toString().trim();
        String priceStr = etPrice.getText().toString().trim();

        if (weightStr.isEmpty() || priceStr.isEmpty()) {
            Toast.makeText(this, "PLEASE INSERT ALL FIELDS", Toast.LENGTH_SHORT).show();
            return;
        }

        double weight = Double.parseDouble(weightStr);
        double price = Double.parseDouble(priceStr);
        double uruf = rbKeep.isChecked() ? 85.0 : 200.0;

        double totalValue = weight * price;
        double urufWeight = Math.max(0, weight - uruf);
        double zakatPayable = urufWeight * price;
        double totalZakat = (weight >= uruf) ? zakatPayable * 0.025 : 0.0;

        tvTotalValue.setText(String.format("Total Gold Value: RM %.2f", totalValue));
        tvZakatPayable.setText(String.format("Zakat Payable Value: RM %.2f", zakatPayable));
        tvTotalZakat.setText(String.format("Total Zakat to Pay: RM %.2f", totalZakat));
    }
}