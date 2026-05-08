package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import business.EdituriService;
import data.Editura;

import java.awt.*;
import java.util.List;

public class EdituriPanel extends JPanel{

    public EdituriPanel ()
    {
        EdituriService edituriService = new EdituriService();
        List<Editura> edituri = edituriService.getAll();

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("NUME");

        for ( Editura e: edituri )
        {
            model.addRow(new Object[]{e.getId(), e.getNume()});
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

        add.addActionListener(e -> {
            String nume = id.getText();

            Editura edit = new Editura( 0, nume);
            edituriService.add(edit);

            List<Editura> updated = edituriService.getAll();
            Editura last = updated.get(updated.size() - 1);
            model.addRow(new Object[]{last.getId(), last.getNume()});

            id.setText("");
        });

        delete.addActionListener(e->{
            int selectedRow = table.getSelectedRow();

            if ( selectedRow != -1 )
            {
                int i = (int) model.getValueAt(selectedRow, 0);
                edituriService.delete(i);
                model.removeRow(selectedRow);
            }
            else{
                JOptionPane.showMessageDialog(this, "Selecteaza o editura pentru eliminare.", "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        });

        update.addActionListener(e->{
            int selectedRow = table.getSelectedRow();

            if ( selectedRow != -1 )
            {
                int i = (int) model.getValueAt(selectedRow, 0);

                String numeNou = JOptionPane.showInputDialog(this, "Nume nou", "Modificare editura", JOptionPane.PLAIN_MESSAGE);

                edituriService.update(i, numeNou);
                model.setValueAt(numeNou, selectedRow, 1);


            }
            else{
                JOptionPane.showMessageDialog(this, "Selecteaza o editura pentru modificare.", "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
