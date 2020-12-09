package Conexao;

import java.sql.*;



public class Conexao {
    private static final String URL = "jdbc:postgresql://localhost:5432/";
    private static final String USER = "postgres";
    private static final String PASS = "1213";
    
    public static Connection getConnection(){

        try {
            Class.forName("org.postgresql.Driver");
            
            return DriverManager.getConnection(URL, USER, PASS);
            
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("erro na conexao", ex);
        }
            

    }
            
    public static void closeConnection(Connection con){
        if(con != null){
            try {
                con.close();
            } catch (SQLException ex) {
                System.err.println("Erro "+ex);
            }
        }
    }
    
    public static void closeConnection(Connection con, Statement stmt){
        if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException ex) {
                System.err.println("Erro "+ex);
            }
        }
        closeConnection(con);
    }
    
    public static void closeConnection(Connection con, Statement stmt, Statement stmt2){
        if(stmt != null){
            try {
                stmt.close();
                stmt2.close();
            } catch (SQLException ex) {
                System.err.println("Erro "+ex);
            }
        }
        closeConnection(con);
    }
    
}
