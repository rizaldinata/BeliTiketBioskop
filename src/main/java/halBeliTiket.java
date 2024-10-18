
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author rizal
 */
public class halBeliTiket extends javax.swing.JFrame {

    private Film film;
    private JComboBox<Auditorium> auditoriumComboBox;
    private JComboBox<Jadwal> jadwalComboBox;
    private JComboBox<Kursi> kursiComboBox;
    private JButton beliTiketButton;
    private JButton kembaliButton;;

    /**
     * Creates new form halBeliTiket
     */
    public halBeliTiket(Film film) {
        if (Pengguna.penggunaSekarang == null) {
            System.out.println("Pengguna belum login. Mengarahkan ke halaman login.");
            dispose();
            new halLogin().setVisible(true);  
            return;  
        }

        this.film = film;
        System.out.print("beli " + film.getJudul());
        initializeUI();
        loadAuditorium();
    }

    private void initializeUI() {
        setTitle("Beli Tiket");
        setLayout(new BorderLayout()); 
        
        getContentPane().setBackground(new Color(51, 51, 51));

        JPanel mainPanel = new JPanel(new GridLayout(4, 2));
        mainPanel.setBackground(new Color(51, 51, 51));
        auditoriumComboBox = new JComboBox<>();
        jadwalComboBox = new JComboBox<>();
        kursiComboBox = new JComboBox<>();
        beliTiketButton = new JButton("Beli Tiket");
        kembaliButton = new JButton("Kembali"); 

        beliTiketButton.setPreferredSize(new Dimension(100, 30));
        kembaliButton.setPreferredSize(new Dimension(100, 30)); 

        JLabel auditoriumLabel = new JLabel("Pilih Auditorium:");
        auditoriumLabel.setForeground(Color.WHITE);
        mainPanel.add(auditoriumLabel);
        mainPanel.add(auditoriumComboBox);

        JLabel jadwalLabel = new JLabel("Pilih Jadwal:");
        jadwalLabel.setForeground(Color.WHITE);
        mainPanel.add(jadwalLabel);
        mainPanel.add(jadwalComboBox);

        JLabel kursiLabel = new JLabel("Pilih Kursi:");
        kursiLabel.setForeground(Color.WHITE);
        mainPanel.add(kursiLabel);
        mainPanel.add(kursiComboBox);

        add(mainPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBackground(new Color(51, 51, 51)); 
        buttonPanel.add(kembaliButton);
        buttonPanel.add(beliTiketButton);
        add(buttonPanel, BorderLayout.SOUTH); 

        auditoriumComboBox.addActionListener(e -> loadJadwal());

        jadwalComboBox.addActionListener(e -> loadKursi());

        beliTiketButton.addActionListener(e -> confirmPurchase());

        kembaliButton.addActionListener(e -> {
            dispose();
            new halListFilm().setVisible(true);
        });

        setSize(600, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void loadAuditorium() {
        List<Auditorium> auditoriums = new ArrayList<>();
        try {
            Connection conn = Koneksi.connect();
            String query = "SELECT DISTINCT a.* FROM auditorium a "
                    + "JOIN jadwal j ON a.id_auditorium = j.fk_auditorium "
                    + "WHERE j.fk_film = " + film.getId_film();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                auditoriums.add(new Auditorium(rs.getInt("id_auditorium"), rs.getString("nama")));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (Auditorium a : auditoriums) {
            auditoriumComboBox.addItem(a);
        }
    }

    private void loadJadwal() {
        jadwalComboBox.removeAllItems();
        kursiComboBox.removeAllItems();

        List<Jadwal> jadwals = new ArrayList<>();
        Auditorium selectedAuditorium = (Auditorium) auditoriumComboBox.getSelectedItem();

        if (selectedAuditorium != null) {
            System.out.println("Auditorium ID: " + selectedAuditorium.getId_auditorium());
            try {
                Connection conn = Koneksi.connect();
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM jadwal WHERE fk_auditorium = ? AND fk_film = ?");
                ps.setInt(1, selectedAuditorium.getId_auditorium());
                ps.setInt(2, film.getId_film());
                ResultSet rs = ps.executeQuery();

                if (!rs.next()) {
                    System.out.println("Tidak ada jadwal ditemukan untuk auditorium ID: " + selectedAuditorium.getId_auditorium());
                } else {
                    do {
                        Jadwal jadwal = new Jadwal(
                                new Film(rs.getInt("fk_film"), "nama", "gambar", "genre", "durasi", "rating", "sinopsis"),
                                selectedAuditorium,
                                rs.getInt("id_jadwal"),
                                rs.getTime("waktu_mulai").toLocalTime(),
                                rs.getTime("waktu_selesai").toLocalTime()
                        );
                        jadwals.add(jadwal);
                    } while (rs.next());
                }
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            for (Jadwal j : jadwals) {
                jadwalComboBox.addItem(j);
            }

            if (jadwals.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tidak ada jadwal tersedia untuk auditorium yang dipilih.", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void loadKursi() {
        kursiComboBox.removeAllItems();

        List<Kursi> kursis = new ArrayList<>();
        Jadwal selectedJadwal = (Jadwal) jadwalComboBox.getSelectedItem();

        if (selectedJadwal != null) {
            try {
                Connection conn = Koneksi.connect();
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM kursi WHERE fk_auditorium = ? AND fk_jadwal = ? AND status = true");
                ps.setInt(1, selectedJadwal.getAuditorium().getId_auditorium());
                ps.setInt(2, selectedJadwal.getId_jadwal());
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    Kursi kursi = new Kursi(
                            selectedJadwal.getAuditorium(),
                            rs.getInt("id_kursi"),
                            rs.getString("nomor_kursi"),
                            rs.getBoolean("status")
                    );
                    kursis.add(kursi);
                    System.out.println("Kursi: " + kursi.getNomor_kursi() + ", Status: " + (kursi.isStatus() ? "Tersedia" : "Tidak Tersedia"));
                }
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            for (Kursi k : kursis) {
                if (k.isStatus()) {
                    kursiComboBox.addItem(k);
                }
            }

            if (kursis.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tidak ada kursi tersedia untuk jadwal yang dipilih.", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void confirmPurchase() {
        int response = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin membeli tiket?", "Konfirmasi Pembelian", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            try {
                Connection conn = Koneksi.connect();
                PreparedStatement ps = conn.prepareStatement("INSERT INTO tiket (fk_pengguna, fk_jadwal, fk_kursi) VALUES (?, ?, ?)");

                Jadwal selectedJadwal = (Jadwal) jadwalComboBox.getSelectedItem();
                Kursi selectedKursi = (Kursi) kursiComboBox.getSelectedItem();

                ps.setInt(1, Pengguna.penggunaSekarang.getId());
                System.out.println(Pengguna.penggunaSekarang.getId() + "beli tiket pengguna");
                ps.setInt(2, selectedJadwal.getId_jadwal());
                ps.setInt(3, selectedKursi.getId_kursi());

                ps.executeUpdate();

                PreparedStatement updateKursi = conn.prepareStatement("UPDATE kursi SET status = ? WHERE id_kursi = ?");
                updateKursi.setBoolean(1, false);
                updateKursi.setInt(2, selectedKursi.getId_kursi());
                updateKursi.executeUpdate();

                conn.close();
                JOptionPane.showMessageDialog(this, "Pembelian tiket berhasil!", "Berhasil", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Pembelian tiket gagal: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // </editor-fold>
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
