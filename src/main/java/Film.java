/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author rizal
 */
public class Film {

    public int id_film;
    public String judul;
    public String gambar;
    public String genre;
    public String durasi;
    public String rating;
    public String sinopsis;

    public Film(int id_film, String judul, String gambar, String genre, String durasi, String rating, String sinopsis) {
        this.id_film = id_film;
        this.judul = judul;
        this.gambar = gambar;
        this.genre = genre;
        this.durasi = durasi;
        this.rating = rating;
        this.sinopsis = sinopsis;
    }

    public String getJudul() {
        return judul;
    }

    public int getId_film() {
        return id_film;
    }

    public String getGambar() {
        return gambar;
    }

    public String getGenre() {
        return genre;
    }

    public String getDurasi() {
        return durasi;
    }

    public String getRating() {
        return rating;
    }

    public String getSinopsis() {
        return sinopsis;
    }
}
