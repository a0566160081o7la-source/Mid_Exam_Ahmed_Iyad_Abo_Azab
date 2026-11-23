package com.example.mid_exam_ahmed_abo_azab;

public class Artwork {
    private final int imageResId;
    private final String title;
    private final String artist;
    private final String year;

    public Artwork(int imageResId, String title, String artist, String year) {
        this.imageResId = imageResId;
        this.title = title;
        this.artist = artist;
        this.year = year;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getYear() {
        return year;
    }
}