package ui;

import business.CarteAutorService;
import business.CartiService;
import business.EdituriService;
import data.Autor;
import data.Carte;
import data.CarteAutorDAO;
import data.Editura;

import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;

public class CartiPanel extends JPanel{

   public CartiPanel()
   {
       CartiService carte = new CartiService();
       CarteAutorService carteAutor = new CarteAutorService();
       List<Carte> carti = carte.getAll();

       DefaultTableModel model = new DefaultTableModel();
       model.addColumn("ID");
       model.addColumn("Titlu");
       model.addColumn("Autor");
       model.addColumn("An publicare");
       model.addColumn("Editura");


       for ( Carte c : carti )
       {
           List<Autor> autori = carteAutor.getAutoriForCarte(c.getId());
           String autoriStr = autori.stream().map(Autor::getNume).collect(java.util.stream.Collectors.joining(", "));
           model.addRow(new Object[]{c.getId(), c.getTitlu(), autoriStr, c.getAn_publicare(), c.getNumeEditura()});
       }

       JTable table = new JTable(model);

       JScrollPane scrollPane = new JScrollPane(table);


       JButton adauga = new JButton("Adauga");
       adauga.setPreferredSize(new Dimension(100, 30));
       JButton delete = new JButton("Eliminare");
       JButton update = new JButton("Modificare");
       JButton filtrare =  new JButton("Filtrare");
       JPanel panel = new JPanel();

       panel.add(adauga);
       panel.add(delete);
       panel.add(update);
       panel.add(filtrare);

       setLayout(new BorderLayout());
       add(scrollPane, BorderLayout.CENTER);
       add(panel, BorderLayout.SOUTH);

       adauga.addActionListener(e -> {
           new CarteFormDialog((JFrame) SwingUtilities.getWindowAncestor(this));

           model.setRowCount(0);
           for (Carte c : carte.getAll()) {
               List<Autor> autori = carteAutor.getAutoriForCarte(c.getId());
               String autoriStr = autori.stream().map(Autor::getNume).collect(java.util.stream.Collectors.joining(", "));
               model.addRow(new Object[]{c.getId(), c.getTitlu(), autoriStr, c.getAn_publicare(), c.getNumeEditura()});
           }
       });

       delete.addActionListener(e -> {
           int selectedRow = table.getSelectedRow();
           if (selectedRow != -1) {
               int id = (int) model.getValueAt(selectedRow, 0);
               carte.delete(id);
               model.removeRow(selectedRow);
           } else {
               JOptionPane.showMessageDialog(this, "Selecteaza o carte pentru eliminare.", "Eroare", JOptionPane.ERROR_MESSAGE);
           }

       });

       update.addActionListener(e -> {
           int selectedRow = table.getSelectedRow();

           if (selectedRow != -1) {
               int id = (int) model.getValueAt(selectedRow, 0);
               Carte carteSelectata = carte.getAll().stream()
                       .filter(c -> c.getId() == id)
                       .findFirst().orElse(null);

               new CarteFormDialog((JFrame) SwingUtilities.getWindowAncestor(this), carteSelectata);


               model.setRowCount(0);
               for (Carte c : carte.getAll()) {
                   List<Autor> autori = carteAutor.getAutoriForCarte(c.getId());
                   String autoriStr = autori.stream().map(Autor::getNume).collect(java.util.stream.Collectors.joining(", "));
                   model.addRow(new Object[]{c.getId(), c.getTitlu(), autoriStr, c.getAn_publicare(), c.getNumeEditura()});
               }
           } else {
               JOptionPane.showMessageDialog(this, "Selecteaza o carte pentru modificare.", "Eroare", JOptionPane.ERROR_MESSAGE);
           }
       });

       filtrare.addActionListener(e -> {
           ListareDialog filtru = new ListareDialog();


       });
   }
}
