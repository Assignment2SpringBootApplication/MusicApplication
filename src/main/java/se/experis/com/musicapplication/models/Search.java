package se.experis.com.musicapplication.models;

public class Search {
    private String name;
    private String artistName;
    private String albumTitle;
    private String genreName;

    public Search(String name, String artistName, String albumTitle, String genreName) {
        this.name = name;
        this.artistName = artistName;
        this.albumTitle = albumTitle;
        this.genreName = genreName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }
}
