package ui;

import javax.swing.*;
import business.AutoriService;
import data.Autor;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class AutoriPanel extends JPanel
{
    public AutoriPanel()
    {
        AutoriService a = new AutoriService();
        List<Autor> aList = a.getAll();

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("NUME");

        for ( Autor autor : aList )
        {
            model.addRow(new Object[]{autor.getId(), autor.getNume()});
        }

        JTable table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);

        JTextField id = new JTextField();
        JButton add = new JButton("Adauga");
        JButton delete = new JButton("Eliminare");
        JButton update = new JButton("Modificare");
        JPanel panel = new JPanel();

        panel.add(id);
        panel.add(add);
        panel.add(delete);
        panel.add(update);
        id.setPreferredSize(new Dimension(200, 30));

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        add.addActionListener( e ->{
            String nume = id.getText();

            Autor autor = new Autor( nume, 0);
            a.add(autor);

            List<Autor> updated = a.getAll();
            Autor last = updated.get(updated.size() - 1);

            model.addRow(new Object[]{last.getId(), last.getNume()});

            id.setText("");
        });

        delete.addActionListener(e ->{
            int selectedRow = table.getSelectedRow();

            if ( selectedRow != -1 )
            {
                int i = (int) model.getValueAt(selectedRow, 0);
                a.delete(i);
                model.removeRow(selectedRow);
            }
            else{
                JOptionPane.showMessageDialog(this, "Selecteaza un autor pentru eliminare.", "Eroare", JOptionPane.ERROR_MESSAGE);
            }

        });

        update.addActionListener(e ->{
           int selectedRow = table.getSelectedRow();

           if ( selectedRow != -1 )
           {
               int i  = (int) model.getValueAt(selectedRow, 0);
               String numeNou = JOptionPane.showInputDialog(this, "Nume Nou", "Modificare nume", JOptionPane.PLAIN_MESSAGE );

               a.update(i, numeNou);
               model.setValueAt(numeNou, selectedRow, 1);
           }
           else{
               JOptionPane.showMessageDialog(this, "Selecteaza un autor pentru modificare.", "Eroare", JOptionPane.ERROR_MESSAGE);
           }
        });

    }
}
