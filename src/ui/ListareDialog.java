package ui;

import business.CarteAutorService;
import business.CartiService;
import business.EdituriService;
import data.Autor;
import data.Carte;
import data.Editura;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListareDialog extends JDialog {

    CartiService cartiService = new CartiService();
    CarteAutorService carteAutor = new CarteAutorService();
    public ListareDialog()
    {
        setSize(600, 400);
        setTitle("Listare");
        setLocationRelativeTo(getParent());
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        JTextField anDeLa = new JTextField();
        JTextField anPanaLa = new JTextField();

        JComboBox<Editura> edituraComboBox = new JComboBox<>();
        JButton okButton = new JButton("Tipareste");

        EdituriService edituriService = new EdituriService();
        for ( Editura e : edituriService.getAll() )
        {
            edituraComboBox.addItem(e);
        }
        edituraComboBox.insertItemAt(new Editura(0, "Toate"), 0);

        panel.add(anDeLa);
        panel.add(anPanaLa);
        panel.add(edituraComboBox);
        panel.add(okButton);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Titlu");
        model.addColumn("Autor");
        model.addColumn("An Publicare");
        model.addColumn("Editura");

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER );
        add(panel, BorderLayout.NORTH);



        okButton.addActionListener( e-> {
            int anDL = Integer.parseInt(anDeLa.getText());
            int anPAN = Integer.parseInt(anPanaLa.getText());

            Editura edituraSelectata = (Editura) edituraComboBox.getSelectedItem();

            List<Carte> rezultate = cartiService.getFiltered(anDL, anPAN, edituraSelectata.getId());
            model.setRowCount(0);
            for (Carte c : rezultate) {

                List<Autor> autori = carteAutor.getAutoriForCarte(c.getId());
                String autoriStr = autori.stream().map(Autor::getNume).collect(java.util.stream.Collectors.joining(", "));
                model.addRow(new Object[]{c.getId(), c.getTitlu(), autoriStr, c.getAn_publicare(), c.getNumeEditura()});
            }
        });

        setVisible(true);
    }
}
