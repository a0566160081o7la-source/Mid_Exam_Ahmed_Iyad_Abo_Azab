package com.example.mid_exam_ahmed_abo_azab;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mid_exam_ahmed_abo_azab.databinding.ActivityArtworkDetailsBinding;

public class ArtworkDetailsActivity extends AppCompatActivity {

    private ActivityArtworkDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityArtworkDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String title = getIntent().getStringExtra(MainActivity.EXTRA_ARTWORK_TITLE);
        String artist = getIntent().getStringExtra(MainActivity.EXTRA_ARTWORK_ARTIST);
        String year = getIntent().getStringExtra(MainActivity.EXTRA_ARTWORK_YEAR);

        binding.tvArtworkDetails.setText("Title: " + title + "\nArtist: " + artist + "\nYear: " + year);

        binding.btnVisitWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.artic.edu/"));
                startActivity(intent);
            }
        });

        binding.btnBackToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}