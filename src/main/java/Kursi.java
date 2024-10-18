/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author rizal
 */
public class Kursi {
    private Auditorium auditorium;
    
    private int id_kursi;
    private String nomor_kursi;
    private boolean status;

    public Kursi(Auditorium auditorium, int id_kursi, String nomor_kursi, boolean status) {
        this.auditorium = auditorium;
        this.id_kursi = id_kursi;
        this.nomor_kursi = nomor_kursi;
        this.status = status;
    }

    public int getId_kursi() {
        return id_kursi;
    }

    public String getNomor_kursi() {
        return nomor_kursi;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return nomor_kursi;
    }
    
    

}
