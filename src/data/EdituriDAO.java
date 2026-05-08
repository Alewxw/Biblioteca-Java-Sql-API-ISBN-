package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;

public class EdituriDAO {

    public List<Editura> getAll()
    {
        List<Editura> list = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM edituri");

            ResultSet rs = stmt.executeQuery();

            while ( rs.next() )
            {

                Editura e = new Editura(rs.getInt("id"), rs.getString("nume"));

                list.add(e);
            }

            conn.close();
            return list;
        }
        catch (SQLException ex) {
            System.out.println("Eroare " + ex.getMessage());
        }

        return list;
    }

    public void add ( Editura e )
    {
        try{
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO edituri (nume) VALUES (?)");
            stmt.setString(1, e.getNume());
            stmt.executeUpdate();

            conn.close();

        }
        catch ( SQLException ex ) {
            System.out.println("Eroare " + ex.getMessage());
        }
    }

    public void delete ( int id )
    {
        try{
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM edituri WHERE id = ?" );

            stmt.setInt(1, id);
            stmt.executeUpdate();

            conn.close();
        }
        catch ( SQLException ex )
        {
            System.out.println("Eroare " + ex.getMessage());
        }
    }

    public void update ( int id, String nume )
    {
        try{
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("UPDATE edituri SET nume = ? WHERE id = ?");
            stmt.setString(1, nume);
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
