/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author rizal
 */
public class Auditorium {
    public int id_auditorium;
    public String nama;

    public Auditorium(int id_auditorium, String nama) {
        this.id_auditorium = id_auditorium;
        this.nama = nama;
    }

    public int getId_auditorium() {
        return id_auditorium;
    }

    public String getNama() {
        return nama;
    }

    @Override
    public String toString() {
        return nama;
    }
}
