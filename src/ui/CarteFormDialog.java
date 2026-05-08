package ui;

import business.AutoriService;
import business.CarteAutorService;
import business.CartiService;
import business.EdituriService;
import data.Autor;
import data.Carte;
import data.Editura;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CarteFormDialog extends JDialog {

    public CarteFormDialog(JFrame parent) {
        super(parent, "Adauga carte", true);
        CartiService cartiService = new CartiService();

        setSize(400, 300);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        JTextField titlu = new JTextField();
        JTextField an_publicare = new JTextField();
        JComboBox<Editura> editura = new JComboBox<>();
        JList<Autor> autor = new  JList<Autor>();

        EdituriService edits = new EdituriService();
        List<Editura> edituri = edits.getAll();

        for ( Editura e : edituri )
            {
                editura.addItem(e);
            }

        DefaultListModel<Autor> model = new DefaultListModel<>();
        AutoriService autors = new AutoriService();

        for ( Autor a : autors.getAll() )
        {
            model.addElement(a);
        }

        autor.setModel(model);
        autor.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        formPanel.add(new JLabel("Titlu:"));
        formPanel.add(titlu);
        formPanel.add(new JLabel("An publicare:"));
        formPanel.add(an_publicare);
        formPanel.add(new JLabel("Editura:"));
        formPanel.add(editura);
        formPanel.add(new JLabel("Autori:"));
        formPanel.add(new JScrollPane(autor));

        add(formPanel, BorderLayout.CENTER);

        JButton salveaza = new JButton("Salveaza");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(salveaza);
        add(buttonPanel, BorderLayout.SOUTH);

        salveaza.addActionListener( e-> {
            Carte carte = new Carte(0, titlu.getText(), Integer.parseInt(an_publicare.getText()), ((Editura) editura.getSelectedItem()).getId());

            cartiService.add(carte);

            List<Carte> updated = cartiService.getAll();
            Carte last = updated.get(updated.size() - 1);


            last.getId();

            List<Autor> autoriSelectati = autor.getSelectedValuesList();
            CarteAutorService carteAutorService = new CarteAutorService();


            for ( Autor a : autoriSelectati ) {

                carteAutorService.add(last.getId(), a.getId());
            }
        });
        setVisible(true);
    }

    public CarteFormDialog(JFrame parent, Carte carteDeEditat) {
        super(parent, "Adauga carte", true);
        CartiService cartiService = new CartiService();

        setSize(400, 300);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        JTextField titlu = new JTextField();
        JTextField an_publicare = new JTextField();
        JComboBox<Editura> editura = new JComboBox<>();
        JList<Autor> autor = new  JList<Autor>();

        EdituriService edits = new EdituriService();
        List<Editura> edituri = edits.getAll();

        if (carteDeEditat != null) {
            titlu.setText(carteDeEditat.getTitlu());
            an_publicare.setText(String.valueOf(carteDeEditat.getAn_publicare()));
        }

        for ( Editura e : edituri )
        {
            editura.addItem(e);
        }

        DefaultListModel<Autor> model = new DefaultListModel<>();
        AutoriService autors = new AutoriService();

        for ( Autor a : autors.getAll() )
        {
            model.addElement(a);
        }

        autor.setModel(model);
        autor.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        formPanel.add(new JLabel("Titlu:"));
        formPanel.add(titlu);
        formPanel.add(new JLabel("An publicare:"));
        formPanel.add(an_publicare);
        formPanel.add(new JLabel("Editura:"));
        formPanel.add(editura);
        formPanel.add(new JLabel("Autori:"));
        formPanel.add(new JScrollPane(autor));

        add(formPanel, BorderLayout.CENTER);

        JButton salveaza = new JButton("Salveaza");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(salveaza);
        add(buttonPanel, BorderLayout.SOUTH);

        salveaza.addActionListener( e-> {

            if ( carteDeEditat != null ) {
                Carte carte = new Carte(carteDeEditat.getId(), titlu.getText(), Integer.parseInt(an_publicare.getText()), ((Editura) editura.getSelectedItem()).getId());
                List<Autor> autoriSelectati = autor.getSelectedValuesList();
                cartiService.update(carte, autoriSelectati);
            }
            else {
                Carte carte = new Carte(0, titlu.getText(), Integer.parseInt(an_publicare.getText()), ((Editura) editura.getSelectedItem()).getId());

                cartiService.add(carte);

                List<Carte> updated = cartiService.getAll();
                Carte last = updated.get(updated.size() - 1);


                last.getId();

                List<Autor> autoriSelectati = autor.getSelectedValuesList();
                CarteAutorService carteAutorService = new CarteAutorService();


                for (Autor a : autoriSelectati) {

                    carteAutorService.add(last.getId(), a.getId());
                }
            }
        });
        setVisible(true);
    }
}
