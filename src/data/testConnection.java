package data;

import java.sql.Connection;
import java.sql.SQLException;

public class testConnection {
    public static void main ( String[] args )
    {
        try{
            Connection conn = DatabaseConnection.getConnection();
            System.out.println("Conexiune reusita");
            conn.close();
        }
        catch (SQLException ex)
        {
            System.out.println("Eroare la conectare: " + ex.getMessage());
        }
    }
}

