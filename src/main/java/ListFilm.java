
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author rizal
 */
public class ListFilm {
    public ArrayList<Film> listFilm;
    
    public ListFilm() {
        listFilm = new ArrayList<>();
    }

    public void addFilm(Film film) {
        listFilm.add(film);
    }
    
    public List<Film> getFilm() {
        return listFilm;
    }

    public ArrayList<Film> getListFilm() {
        return listFilm;
    }
    
    public Film getFilm(int index) {
        return listFilm.get(index);
    } 
}
