package business;

import data.Autor;
import data.Carte;
import data.CarteAutorDAO;
import data.CartiDAO;

import java.util.ArrayList;
import java.util.List;

public class CartiService {

    private CartiDAO cartiDAO = new CartiDAO();
    private CarteAutorDAO carteAutorDAO = new CarteAutorDAO();

    public List<Carte> getAll()
    {
        List<Carte> list = cartiDAO.getAll();
        return list;
    }

    public void add ( Carte carte )
    {
        cartiDAO.add(carte);
    }

    public void delete ( int id )
    {
        carteAutorDAO.delete(id);
        cartiDAO.delete(id);
    }

    public void update(Carte carte, List<Autor> autori) {
        cartiDAO.update(carte);
        if (!autori.isEmpty()) {
            carteAutorDAO.delete(carte.getId());
            for (Autor a : autori) {
                carteAutorDAO.add(carte.getId(), a.getId());
            }
        }
    }

    public List<Carte> getFiltered ( int anDeLa, int anPanaLa, int EdituraId )
    {
        return cartiDAO.getFiltered(anDeLa, anPanaLa, EdituraId);
    }
}
