package com.example.krepco;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class HelpActivity extends AppCompatActivity {

    private Button btnAbout, btnFaq, btnBack;
    private LinearLayout linearLayoutAbout, linearLayoutFaq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        initViews();
        setupListeners();
        showAboutSection();
    }

    private void initViews() {
        btnAbout = findViewById(R.id.btnAbout);
        btnFaq = findViewById(R.id.btnFaq);
        btnBack = findViewById(R.id.btnBack);
        linearLayoutAbout = findViewById(R.id.linearLayoutAbout);
        linearLayoutFaq = findViewById(R.id.linearLayoutFaq);
    }

    private void setupListeners() {
        btnAbout.setOnClickListener(v -> showAboutSection());
        btnFaq.setOnClickListener(v -> showFaqSection());
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(HelpActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });
    }

    private void showAboutSection() {
        linearLayoutAbout.setVisibility(View.VISIBLE);
        linearLayoutFaq.setVisibility(View.GONE);
        btnAbout.setBackgroundTintList(getResources().getColorStateList(R.color.orange));
        btnFaq.setBackgroundTintList(getResources().getColorStateList(R.color.gray));
    }

    private void showFaqSection() {
        linearLayoutAbout.setVisibility(View.GONE);
        linearLayoutFaq.setVisibility(View.VISIBLE);
        btnAbout.setBackgroundTintList(getResources().getColorStateList(R.color.gray));
        btnFaq.setBackgroundTintList(getResources().getColorStateList(R.color.orange));
    }
}