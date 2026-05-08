package data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartiDAO {

    public List<Carte> getAll()
    {
        List<Carte> carti = new ArrayList<>();

        try
        {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT carti.id, carti.nume AS titlu, carti.an_publicare, carti.editura_id, edituri.nume FROM carti"
                                                                + " JOIN edituri on CARTI.editura_id = edituri.id");

            ResultSet rs = stmt.executeQuery();

            while ( rs.next() )
            {
                Carte carte = new Carte(rs.getInt("id"), rs.getString("titlu"), rs.getInt("an_publicare"), rs.getInt("editura_id"), rs.getString("nume"));
                carti.add(carte);
            }

            conn.close();
            return carti;
        }
        catch(Exception ex)
        {
            System.out.println("Eroare " + ex.getMessage());
        }

        return carti;
    }

    public void add ( Carte carte )
    {
        try{
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt =  conn.prepareStatement("INSERT INTO carti (nume, an_publicare, editura_id) values (?, ?, ?)");
            stmt.setString(1, carte.getTitlu());
            stmt.setInt(2, carte.getAn_publicare());
            stmt.setInt(3, carte.getEditura_id());
            stmt.executeUpdate();

            conn.close();
        }
        catch ( SQLException e )
        {
            System.out.println("Eroare " + e.getMessage());
        }
    }

    public void delete ( int id )
    {
        try{
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM carti WHERE id = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();

            conn.close();
        }
        catch (  SQLException e )
        {
            System.out.println("Eroare " + e.getMessage());
        }
    }

    public void update ( Carte carte )
    {
        try{
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("UPDATE carti SET nume = ?, an_publicare = ?, editura_id = ? WHERE id = ?");
            stmt.setString(1, carte.getTitlu());
            stmt.setInt(2, carte.getAn_publicare());
            stmt.setInt(3, carte.getEditura_id());
            stmt.setInt(4, carte.getId());
            stmt.executeUpdate();

            conn.close();

        }
        catch ( SQLException e )
        {
            System.out.println("Eroare " + e.getMessage());
        }
    }

    public List<Carte> getFiltered ( int anDeLa, int anPanaLa, int EdituraId )
    {
        List<Carte> carti = new ArrayList<>();
        try{

            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt;

            if ( EdituraId == 0 )
            {
                stmt = conn.prepareStatement("SELECT carti.id, carti.nume AS titlu, carti.an_publicare, carti.editura_id, edituri.nume AS numeEditura " +
                                                 "FROM carti " +
                                                 "JOIN edituri ON carti.editura_id = edituri.id " +
                                                 "WHERE an_publicare BETWEEN ? AND ? ");

                stmt.setInt(1, anDeLa);
                stmt.setInt(2, anPanaLa);


            }
            else{
                stmt = conn.prepareStatement("SELECT carti.id, carti.nume AS titlu, carti.an_publicare, carti.editura_id, edituri.nume AS numeEditura " +
                                                 "FROM carti " +
                                                 "JOIN edituri ON carti.editura_id = edituri.id " +
                                                 "WHERE an_publicare BETWEEN ? AND ? AND editura_id = ? ");

                stmt.setInt(1, anDeLa);
                stmt.setInt(2, anPanaLa);
                stmt.setInt(3, EdituraId);

            }

            ResultSet rs = stmt.executeQuery();

            while ( rs.next() )
            {
                Carte carte = new Carte(rs.getInt("id"), rs.getString("titlu"), rs.getInt("an_publicare"), rs.getInt("editura_id"), rs.getString("numeEditura"));
                carti.add(carte);
            }

            conn.close();


        }
        catch ( SQLException e )
        {
            System.out.println("Eroare " + e.getMessage());
        }

        return carti;
    }

}
