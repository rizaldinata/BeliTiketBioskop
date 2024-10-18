
import java.time.LocalTime;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author rizal
 */
public class Jadwal {
    private Film film;
    private Auditorium auditorium;
    
    private int id_jadwal;
    private LocalTime waktu_mulai;
    private LocalTime waktu_selesai;

    public Jadwal(Film film, Auditorium auditorium, int id_jadwal, LocalTime waktu_mulai, LocalTime waktu_selesai) {
        this.film = film;
        this.auditorium = auditorium;
        this.id_jadwal = id_jadwal;
        this.waktu_mulai = waktu_mulai;
        this.waktu_selesai = waktu_selesai;
    }

    public Film getFilm() {
        return film;
    }

    public Auditorium getAuditorium() {
        return auditorium;
    }

    public int getId_jadwal() {
        return id_jadwal;
    }

    public LocalTime getWaktu_mulai() {
        return waktu_mulai;
    }

    public LocalTime getWaktu_selesai() {
        return waktu_selesai;
    }

    @Override
    public String toString() {
        return waktu_mulai + " - " + waktu_selesai;
    }
    
    
    
}
