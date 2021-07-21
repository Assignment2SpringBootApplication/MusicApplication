package se.experis.com.musicapplication.models;

public class CustomerGenre {
    public String genre;
    public int occurrences;

    public CustomerGenre(String genre, int occurrences) {
        this.genre = genre;
        this.occurrences = occurrences;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getOccurrences() {
        return occurrences;
    }

    public void setOccurences(int occurrences) {
        this.occurrences = occurrences;
    }
}
