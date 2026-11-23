package com.example.mid_exam_ahmed_abo_azab;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mid_exam_ahmed_abo_azab.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static ActivityMainBinding mainBinding;

    private static final String KEY_CURRENT_INDEX = "current_index";
    public static final String EXTRA_ARTWORK_TITLE = "extra_artwork_title";
    public static final String EXTRA_ARTWORK_ARTIST = "extra_artwork_artist";
    public static final String EXTRA_ARTWORK_YEAR = "extra_artwork_year";

    private List<Artwork> artworks;
    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initArtworks();

        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt(KEY_CURRENT_INDEX, 0);
        }

        updateUI();

        mainBinding.btnNext.setOnClickListener(v -> {
            if (currentIndex < artworks.size() - 1) {
                currentIndex++;
                updateUI();
            }
        });

        mainBinding.btnPrevious.setOnClickListener(v -> {
            if (currentIndex > 0) {
                currentIndex--;
                updateUI();
            }
        });

        mainBinding.ivArtwork.setOnClickListener(v -> {
            Artwork currentArtwork = artworks.get(currentIndex);
            Intent intent = new Intent(MainActivity.this, ArtworkDetailsActivity.class);
            intent.putExtra(EXTRA_ARTWORK_TITLE, currentArtwork.getTitle());
            intent.putExtra(EXTRA_ARTWORK_ARTIST, currentArtwork.getArtist());
            intent.putExtra(EXTRA_ARTWORK_YEAR, currentArtwork.getYear());
            startActivity(intent);
        });
    }

    private void initArtworks() {
        artworks = new ArrayList<>();
        artworks.add(new Artwork(R.drawable.art1, "The Starry Night", "Vincent van Gogh", "1889"));
        artworks.add(new Artwork(R.drawable.art2, "Mona Lisa", "Leonardo da Vinci", "1503"));
        artworks.add(new Artwork(R.drawable.art3, "The Scream", "Edvard Munch", "1893"));
        artworks.add(new Artwork(R.drawable.art4, "Girl with a Pearl Earring", "Johannes Vermeer", "1665"));
        artworks.add(new Artwork(R.drawable.art5, "The Persistence of Memory", "Salvador Dali", "1931"));
        artworks.add(new Artwork(R.drawable.art6, "Guernica", "Pablo Picasso", "1937"));
    }

    private void updateUI() {
        Artwork currentArtwork = artworks.get(currentIndex);

        mainBinding.tvCounter.setText((currentIndex + 1) + " / " + artworks.size());
        mainBinding.ivArtwork.setImageResource(currentArtwork.getImageResId());
        mainBinding.tvTitle.setText(currentArtwork.getTitle());
        mainBinding.tvArtistYear.setText(currentArtwork.getArtist() + " (" + currentArtwork.getYear() + ")");

        mainBinding.btnPrevious.setEnabled(currentIndex > 0);
        mainBinding.btnNext.setEnabled(currentIndex < artworks.size() - 1);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_CURRENT_INDEX, currentIndex);
    }
}