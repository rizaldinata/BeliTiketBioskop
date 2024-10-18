
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author rizal
 */
public class halListFilm extends javax.swing.JFrame {

    private ListFilm listFilm;

    /**
     * Creates new form testing
     */
    public halListFilm() {
        if (Pengguna.penggunaSekarang == null) {
            System.out.println("Pengguna belum login. Mengarahkan ke halaman login.");
            dispose(); 
            new halLogin().setVisible(true);  
            return; 
        }

        setLayout(new BorderLayout());

        listFilm = new ListFilm();

        JPanel panel = new JPanel();
        panel.setBackground(new Color(51, 51, 51));
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));

        try {
            Connection conn = Koneksi.connect();

            String query = "SELECT * FROM film";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            //cek
            System.out.println("Berhasil");

            while (rs.next()) {
                int id_film = rs.getInt("id_film");
                String judul = rs.getString("nama");
                String gambar = ("asset/" + rs.getString("gambar"));
                String genre = rs.getString("genre");
                String durasi = rs.getString("durasi");
                String rating = rs.getString("rating");
                String sinopsis = rs.getString("sinopsis");
                
                //cek
                System.out.println(id_film + judul + gambar + genre + durasi + rating + sinopsis);

                Film film = new Film(id_film, judul, gambar, genre, durasi, rating, sinopsis);
                listFilm.addFilm(film);

                ImageIcon icon = new ImageIcon(gambar);
                Image img = icon.getImage();
                Image scaledImg = img.getScaledInstance(150, 200, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImg);

                JLabel labelGambar = new JLabel(scaledIcon);
                labelGambar.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

                JLabel labelJudul = new JLabel(judul, SwingConstants.CENTER);
                labelJudul.setBorder(BorderFactory.createEmptyBorder(1, 0, 1, 0));
                labelJudul.setForeground(Color.WHITE);

                JLabel labelGenre = new JLabel(genre, SwingConstants.CENTER);
                labelGenre.setBorder(BorderFactory.createEmptyBorder(1, 0, 1, 0));
                labelGenre.setForeground(Color.WHITE);

                JLabel labelDurasi = new JLabel(durasi, SwingConstants.CENTER);
                labelDurasi.setBorder(BorderFactory.createEmptyBorder(1, 0, 1, 0));
                labelDurasi.setForeground(Color.WHITE);

                JPanel filmPanel = new JPanel();
                filmPanel.setLayout(new BoxLayout(filmPanel, BoxLayout.Y_AXIS));
                filmPanel.setBorder(BorderFactory.createEmptyBorder(6, 1, 0, 5));

                filmPanel.add(labelGambar);
                filmPanel.add(labelJudul);
                filmPanel.add(Box.createRigidArea(new Dimension(0, 2)));
                filmPanel.add(labelGenre);
                filmPanel.add(Box.createRigidArea(new Dimension(0, 2)));
                filmPanel.add(labelDurasi);
                filmPanel.setBackground(new Color(51, 51, 51));

                filmPanel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        
                        //cek
                        System.out.println("Membuka detail film: " + film.getJudul());
                        
                        dispose();
                        new halDetailFilm(film).setVisible(true);
                    }
                });

                panel.add(filmPanel);

            }

            panel.revalidate();
            panel.repaint();

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        add(panel, BorderLayout.CENTER);

        JButton kembaliButton = new JButton("Kembali");
        kembaliButton.setPreferredSize(new Dimension(100, 30));
        kembaliButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new halUtama().setVisible(true); 
            }
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(51, 51, 51));
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(kembaliButton);

        add(bottomPanel, BorderLayout.SOUTH);

        setTitle("Film list");
        setSize(600, 350);
        setPreferredSize(new Dimension(600, 350));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
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
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(halListFilm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(halListFilm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(halListFilm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(halListFilm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        SwingUtilities.invokeLater(() -> new halListFilm());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
