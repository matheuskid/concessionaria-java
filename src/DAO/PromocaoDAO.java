package DAO;

import Conexao.Conexao;
import Model.Promocao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;




public class PromocaoDAO {
    
    private Connection con = null;
    
    public PromocaoDAO(){
        con = Conexao.getConnection();
    }
    
    public boolean inserir (Promocao promocao){
        String sql = "INSERT INTO public.tbl_promocao (desconto, data_exp) VALUES ('"+promocao.getDesconto()+"',\'"+promocao.getData_exp()+"\')"
        + "RETURNING id_prom";
        
        Statement stmt = null, stmt2 = null;
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            int id_prom = rs.getInt("id_prom");
            
            String sql2 = "UPDATE public.tbl_veiculo SET id_prom = '"+id_prom+"' WHERE chassi = '"+promocao.getId_veiculo()+"'";
            stmt2 = con.createStatement();
            stmt2.executeUpdate(sql2);
            return true;
        } catch (SQLException ex) {
            System.out.println("erro na conexao: "+ex);
            return false;
        } finally{
        Conexao.closeConnection(con, stmt, stmt2);
        }
    }
    
   public boolean apagar(Promocao prom){
       String sql = "UPDATE public.tbl_veiculo SET id_prom = null WHERE id_prom = "+prom.getId_prom()+";"
       + "DELETE FROM public.tbl_promocao WHERE id_prom = "+prom.getId_prom()+";";
       PreparedStatement stmt = null;
        try {
           stmt = con.prepareStatement(sql);
           stmt.executeUpdate();
           System.out.println("Salvo");
           return true;
        } catch(SQLException ex){
           System.out.println("Erro na conexao: "+ex);
           return false;
        } finally{ 
        Conexao.closeConnection(con, stmt);
        }
    }
   
   public boolean alterar(Promocao prom){
       String sql = "UPDATE public.tbl_promocao SET desconto = "+prom.getDesconto()+", data_exp = \'"+prom.getData_exp()+"\' WHERE id_prom = "+prom.getId_prom()+";";
       PreparedStatement stmt = null;
        try {
           stmt = con.prepareStatement(sql);
           stmt.executeUpdate();
           System.out.println("Salvo");
           return true;
        } catch(SQLException ex){
           System.out.println("Erro na conexao: "+ex);
           return false;
        } finally{
        Conexao.closeConnection(con, stmt);
        }
    }
   
   public List<Promocao> listar(){
       String sql = "SELECT tbl_veiculo.chassi, tbl_promocao.id_prom, tbl_promocao.desconto, tbl_promocao.data_exp\n" +
"FROM public.tbl_veiculo\n" +
"INNER JOIN tbl_promocao ON tbl_veiculo.id_prom = tbl_promocao.id_prom;";
       List<Promocao> retorno = new ArrayList<>();
       PreparedStatement stmt = null;
       try {
            stmt = con.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            
            while (resultado.next()) {
                
                Promocao promocao = new Promocao();
                promocao.setId_prom(resultado.getInt("id_prom"));
                promocao.setId_veiculo(resultado.getString("chassi"));
                promocao.setDesconto(resultado.getDouble("desconto"));
                promocao.setData_exp(resultado.getString("data_exp"));
                retorno.add(promocao);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
        Conexao.closeConnection(con, stmt);
        }
        return retorno;
    }
   
   public ResultSet pesquisar(){
       PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("SELECT id_prom, desconto, data_exp FROM tbl_promocao;");
            ResultSet resultado = stmt.executeQuery();
            return resultado;
        } catch (SQLException ex) {
            Logger.getLogger(PromocaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally{
        Conexao.closeConnection(con, stmt);
        }
    }

}
