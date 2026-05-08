package business;

import data.Autor;
import data.CarteAutorDAO;

import java.util.List;

public class CarteAutorService {
    private CarteAutorDAO carteDAO = new CarteAutorDAO();

    public void add ( int carteID, int autorID )
    {
        carteDAO.add(carteID, autorID);
    }

    public List<Autor> getAutoriForCarte(int carteId) {
        return carteDAO.getAutoriForCarte(carteId);
    }
}
