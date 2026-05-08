package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarteAutorDAO {

    public void add ( int carteID, int autorID )
    {

        try{
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt =  conn.prepareStatement("INSERT INTO carte_autor (id_carte, id_autor) values (?, ?)");
            stmt.setInt(1, carteID);
            stmt.setInt(2, autorID);
            stmt.executeUpdate();

            conn.close();
        }
        catch ( SQLException e )
        {
            System.out.println("Eroare " + e.getMessage());
        }
    }

    public void delete ( int carteID )
    {
        try{
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM carte_autor WHERE id_carte = ?");
            stmt.setInt(1, carteID);
            stmt.executeUpdate();

            conn.close();
        }
        catch (  SQLException e )
        {
            System.out.println("Eroare " + e.getMessage());
        }
    }

    public List<Autor> getAutoriForCarte(int carteId) {
        List<Autor> autori = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT autori.id, autori.nume FROM autori " +
                            "JOIN carte_autor ON autori.id = carte_autor.id_autor " +
                            "WHERE carte_autor.id_carte = ?");
            stmt.setInt(1, carteId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                autori.add(new Autor(rs.getString("nume"), rs.getInt("id")));
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("Eroare " + e.getMessage());
        }

        return autori;
    }
}
