package business;

import data.AutoriDAO;
import java.util.List;
import data.Autor;

public class AutoriService {
    AutoriDAO autoriDAO = new AutoriDAO();

    public List<Autor> getAll()
    {
        List<Autor> list = autoriDAO.getAll();
        return list;
    }

    public void add ( Autor autor )
    {
        autoriDAO.add(autor);
    }

    public void delete ( int id )
    {
        autoriDAO.delete(id);
    }

    public void update ( int id, String nume )
    {
        autoriDAO.update(id, nume);
    }
}
