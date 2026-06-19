package com.example.zakatgold;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {

    TextView tvGitHubLink;
    Button btnShare;
    String githubUrl = "https://github.com/2024788857-wq/ZakatGold" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        tvGitHubLink = findViewById(R.id.tvGitHubLink);
        btnShare = findViewById(R.id.btnShare);

        tvGitHubLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(githubUrl));
                startActivity(intent);
            }
        });

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Zakat Gold Application");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Download my Gold Zakat Calculator app at: " + githubUrl);
                startActivity(Intent.createChooser(shareIntent, "Share via"));
            }
        });
    }
}