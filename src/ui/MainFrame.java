package ui;


import javax.swing.*;
import java.awt.*;

public class MainFrame extends javax.swing.JFrame {

    public MainFrame()
    {
        setTitle("Biblioteca");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Arial", Font.PLAIN, 16));

        JPanel cartiPanel = new CartiPanel();
        JPanel autoriPanel = new AutoriPanel();
        JPanel edituriPanel = new EdituriPanel();

        tabbedPane.addTab("Carti", cartiPanel);
        tabbedPane.addTab("Autori", autoriPanel);
        tabbedPane.addTab("Edituri", edituriPanel);

        setLayout(new BorderLayout());
        add(tabbedPane, BorderLayout.CENTER);

        setVisible(true);
    }
}
