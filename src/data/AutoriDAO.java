package data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutoriDAO
{
    public List<Autor> getAll()
    {
         List<Autor> list = new ArrayList<>();

         try
         {
             Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM autori");

             ResultSet rs = stmt.executeQuery();

             while ( rs.next() )
             {
                 Autor autor = new Autor(rs.getString("nume"), rs.getInt("id"));

                 list.add(autor);
             }

             conn.close();
             return list;
         }
         catch ( SQLException ex )
         {
             System.out.println("Eroare " + ex.getMessage());
         }

         return list;
    }

    public void add ( Autor autor )
    {
        try{
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO autori (nume) values (?)");
            stmt.setString(1, autor.getNume());
            stmt.executeUpdate();

            conn.close();
        }
        catch( SQLException ex )
        {
            System.out.println("Eroare " + ex.getMessage());
        }
    }

    public void delete ( int id )
    {
        try{
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM autori WHERE id=?");
            stmt.setInt(1, id);
            stmt.executeUpdate();

            conn.close();
        }
        catch ( SQLException ex )
            {
            System.out.println("Eroare " + ex.getMessage());
            }
    }

    public void update ( int id, String numeNou )
    {
        try{
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("UPDATE autori SET nume=? WHERE id=?");

            stmt.setString(1, numeNou);
            stmt.setInt(2, id);
            stmt.executeUpdate();

            conn.close();
        }
        catch ( SQLException ex )
        {
            System.out.println("Eroare " + ex.getMessage());
        }
    }
}
