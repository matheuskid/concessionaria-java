package DAO;

import Conexao.Conexao;
import Model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;




public class ClienteDAO {
    private Connection con = null;
    
    public ClienteDAO(){
        con = Conexao.getConnection();
    
}
    public boolean inserir (Cliente cliente){
        Random random = new Random();
        
        String sql = "INSERT INTO public.tbl_cliente(" +
"	cod_login, nome, email, telefone, cidade, estado, cnh)" +
"	VALUES ("+cliente.getCod_login()+", '"+cliente.getNome()+"', '"+cliente.getEmail()+"', '"+cliente.getTelefone()+"', '"+cliente.getCidade()+"', '"+cliente.getEstado()+"', '"+cliente.getCNH()+"')";
        
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("erro "+ex);
            return false;
        } finally{
        Conexao.closeConnection(con, stmt);
    }
    }
    
    public List<Cliente> listar() {
        String sql = "SELECT * FROM public.tbl_cliente";
        List<Cliente> retorno = new ArrayList<>();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Cliente cliente = new Cliente();
                cliente.setId_cliente(resultado.getInt("id_cliente"));
                cliente.setNome(resultado.getString("nome"));
                cliente.setCod_login(resultado.getInt("cod_login"));
                cliente.setEmail(resultado.getString("email"));
                cliente.setTelefone(resultado.getString("telefone"));
                cliente.setCidade(resultado.getString("cidade"));
                cliente.setEstado(resultado.getString("estado"));
                cliente.setCNH(resultado.getString("cnh"));
                retorno.add(cliente);
            }
        } catch (SQLException ex) {
        System.out.println("erro na conexao: " +ex);
        } finally{
        Conexao.closeConnection(con, stmt);
        }
        return retorno;
    }
    
      public boolean alterar(Cliente cliente){
        String sql = "UPDATE public.tbl_cliente SET nome='"+cliente.getNome()+"', email='"+cliente.getEmail()+"', telefone='"+cliente.getTelefone()+"', cidade='"+cliente.getCidade()+"', estado='"+cliente.getEstado()+"', cod_login='"+cliente.getCod_login()+"', cnh='"+cliente.getCNH()+"'\n" +
"	WHERE id_cliente="+cliente.getId_cliente()+";";
        
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("erro na conexao: " +ex);
            return false;
        } finally{
        Conexao.closeConnection(con, stmt);
        }
    }
    
      public boolean apagar(Cliente cliente){
        String sql = "DELETE FROM public.tbl_cliente WHERE id_cliente= "+cliente.getId_cliente()+";";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("erro na conexao: " +ex);
            return false;
        } finally{
        Conexao.closeConnection(con, stmt);
        }
    }
      
      public ResultSet pesquisar(){
       PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("SELECT nome, email, telefone, cidade, estado, cnh FROM tbl_cliente;");
            ResultSet resultado = stmt.executeQuery();
            return resultado;
        } catch (SQLException ex) {
            System.out.println("erro na conexao: " +ex);
            return null;
        } finally {
        Conexao.closeConnection(con, stmt);
      }
   }

}
