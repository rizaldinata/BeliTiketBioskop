
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author rizal
 */
public class halDetailFilm extends javax.swing.JFrame {

    private Film film;
    private JButton beliTiketButton;

    public halDetailFilm(Film film) {
        if (Pengguna.penggunaSekarang == null) {
            System.out.println("Pengguna belum login. Mengarahkan ke halaman login.");
            dispose();  // Menutup frame ini
            new halLogin().setVisible(true);  // Membuka halaman login
            return;  // Menghentikan eksekusi lebih lanjut
        }

        this.film = film;

        setLayout(new BorderLayout());

        ImageIcon icon = new ImageIcon(film.getGambar());
        Image img = icon.getImage().getScaledInstance(421, 562, Image.SCALE_SMOOTH);
        JLabel labelGambar = new JLabel(new ImageIcon(img));

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(5, 1, 0, 5));
        infoPanel.setBorder(new EmptyBorder(2, 2, 2, 2));

        JLabel labelJudul = new JLabel("Judul: " + film.getJudul());
        labelJudul.setForeground(Color.WHITE);

        JTextArea textAreaSinopsis = new JTextArea(film.getSinopsis());
        textAreaSinopsis.setLineWrap(true);
        textAreaSinopsis.setWrapStyleWord(true);  
        textAreaSinopsis.setForeground(Color.WHITE);
        textAreaSinopsis.setBorder(null);
        textAreaSinopsis.setBackground(new Color(51, 51, 51));

        JLabel labelGenre = new JLabel("Genre: " + film.getGenre());
        labelGenre.setForeground(Color.WHITE);

        JLabel labelDurasi = new JLabel("Durasi: " + film.getDurasi());
        labelDurasi.setForeground(Color.WHITE);

        infoPanel.add(labelJudul);
        infoPanel.add(textAreaSinopsis);
        infoPanel.add(labelGenre);
        infoPanel.add(labelDurasi);

        beliTiketButton = new JButton("Beli Tiket");
        beliTiketButton.addActionListener(e -> {
            // Pastikan film tidak null
            if (film != null) {
                halBeliTiket beliTiket = new halBeliTiket(film);
                beliTiket.setVisible(true);
                this.dispose();
            } else {
                System.out.println("Error: film tidak terdefinisi");
            }
        });

        infoPanel.add(beliTiketButton);
        infoPanel.setBackground(new Color(51, 51, 51));

        add(labelGambar, BorderLayout.WEST);
        add(infoPanel, BorderLayout.CENTER);

        setTitle("Detail Film");
        setSize(750, 562);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Creates new form halDetailFilm
     */
    public halDetailFilm() {
        initComponents();
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
            .addGap(0, 600, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
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
            java.util.logging.Logger.getLogger(halDetailFilm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(halDetailFilm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(halDetailFilm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(halDetailFilm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new halDetailFilm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
