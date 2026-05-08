package business;

import data.Editura;
import data.EdituriDAO;

import java.util.List;

public class EdituriService {

    private EdituriDAO edituriDAO = new EdituriDAO();

    public List<Editura> getAll()
    {
        List<Editura> list = edituriDAO.getAll();
        return list;
    }

    public void add ( Editura e )
    {
        edituriDAO.add(e);
    }

    public void delete ( int id )
    {
        edituriDAO.delete(id);
    }

    public void update ( int id, String nume )
    {
        edituriDAO.update(id, nume);
    }
}
