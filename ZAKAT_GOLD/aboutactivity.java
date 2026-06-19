package com.example.zaktgold;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class aboutactivity extends AppCompatActivity {

    private TextView tvGitHubLink;
    private Button btnShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutactivity);

        tvGitHubLink = findViewById(R.id.tvGitHubLink);
        btnShare = findViewById(R.id.btnShare);

        tvGitHubLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://github.com/2024788857-wq/ZakatGold";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String shareBody = "Check out my Zakat Gold Calculator application on GitHub: https://github.com/2024788857-wq/ZakatGold";
                intent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(intent);
            }
        });
    }
}
