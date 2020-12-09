
package DAO;

import Conexao.Conexao;
import Model.Veiculo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;




public class VeiculoDAO {
    private Connection con = null;
    
    public VeiculoDAO(){
        con = Conexao.getConnection();
    }
    
    public boolean inserir (Veiculo veiculo){
        String sql = "INSERT INTO public.tbl_veiculo (chassi, modelo, fabricante, cor, ano, preco) VALUES "
+"('"+veiculo.getChassi()+"','"+veiculo.getModelo()+"','"+veiculo.getFabricante()+"','"+veiculo.getCor()+"',"+veiculo.getAno()+",'"+veiculo.getPreco()+"')";
        
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("erro na conexao: "+ex);
            return false;
        } finally{
        Conexao.closeConnection(con, stmt);
    }
}
    
    public boolean apagar(Veiculo veiculo){
        Statement stmt = null;
        try {
            String sql2 = "DELETE FROM public.tbl_veiculo WHERE chassi = '"+veiculo.getChassi()+"';";
            stmt = con.createStatement();
            stmt.executeUpdate(sql2);
            return true;
        } catch (SQLException ex) {
            System.out.println("erro na conexao: "+ex);
            return false;
        }finally{
        Conexao.closeConnection(con, stmt);
    }
}
    
    public boolean vender(Veiculo veiculo){
        String sql = "SELECT * FROM public.tbl_veiculo WHERE chassi = '"+veiculo.getChassi()+"';";
        PreparedStatement stmt = null, stmt2 = null;
        try{
            stmt = con.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            
            resultado.next();
            
                Veiculo vei = new Veiculo();
                vei.setChassi(resultado.getString("chassi"));
                vei.setModelo(resultado.getString("modelo"));
                vei.setFabricante(resultado.getString("fabricante"));
                vei.setCor(resultado.getString("cor"));
                vei.setAno(resultado.getInt("ano"));
                vei.setPreco(resultado.getFloat("preco"));
                String id_prom;
                if(resultado.getInt("id_prom") == 0){
                    id_prom = null;
                }else{
                    id_prom = String.valueOf(resultado.getInt("id_prom"));
                } 
                
                String sql2 = "INSERT INTO public.tbl_vendido (chassi, id_prom, modelo, fabricante, cor, ano, preco) VALUES "
        +"('"+vei.getChassi()+"', "+id_prom+", '"+vei.getModelo()+"','"+vei.getFabricante()+"','"+vei.getCor()+"',"+vei.getAno()+",'"+vei.getPreco()+"'); "
        + "DELETE FROM public.tbl_veiculo WHERE chassi = '"+vei.getChassi()+"'";
        
        
            stmt2 = con.prepareStatement(sql2);
            stmt2.executeUpdate();
            return true;
        
        
        } catch (SQLException ex) {
            System.out.println("erro na conexao: "+ex);
            return false;
        } finally{
        Conexao.closeConnection(con, stmt, stmt2);
        }
}
    
    public boolean alterar(Veiculo veiculo){
        Statement stmt = null;
        try {
            String sql = "UPDATE public.tbl_veiculo SET modelo = '"+veiculo.getModelo()+"', fabricante = '"+veiculo.getFabricante()+"', cor = '"+veiculo.getCor()+"', ano = "+veiculo.getAno()+", preco = "+veiculo.getPreco()+" WHERE chassi = '"+veiculo.getChassi()+"'";
            stmt = con.createStatement();
            stmt.executeUpdate(sql);
            return true;
        } catch (SQLException ex) {
            System.out.println("erro "+ex);
            return false;
        }finally{
        Conexao.closeConnection(con, stmt);
    }
}
    
    public List<Veiculo> listar() {
        String sql = "SELECT * FROM public.tbl_veiculo";
        List<Veiculo> retorno = new ArrayList<>();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            
            while (resultado.next()) {
                
                Veiculo veiculo = new Veiculo();
                veiculo.setChassi(resultado.getString("chassi"));
                veiculo.setModelo(resultado.getString("modelo"));
                veiculo.setFabricante(resultado.getString("fabricante"));
                veiculo.setCor(resultado.getString("cor"));
                veiculo.setAno(resultado.getInt("ano"));
                veiculo.setPreco(resultado.getFloat("preco"));
                retorno.add(veiculo);
            }
        } catch (SQLException ex) {
            System.out.println("erro na conexao: "+ex);
        } finally{
        Conexao.closeConnection(con, stmt);
        }
        return retorno;
    }
    
     public List<Veiculo> listar2() {
        String sql = "SELECT chassi, id_prom, modelo, preco FROM public.tbl_veiculo";
        List<Veiculo> retorno = new ArrayList<>();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            
            while (resultado.next()) {
                
                Veiculo veiculo = new Veiculo();
                veiculo.setChassi(resultado.getString("chassi"));
                veiculo.setModelo(resultado.getString("modelo"));
                veiculo.setPreco(resultado.getFloat("preco"));
                if(resultado.getInt("id_prom")==0){
                    System.out.print(resultado.getInt("id_prom"));
                    veiculo.setDesc(0);
                } else {
                    int id = resultado.getInt("id_prom");
                    System.out.print(id);
                    PreparedStatement stmt2 = con.prepareStatement("SELECT desconto FROM public.tbl_promocao WHERE id_prom = "+id+";");
                    ResultSet resultado2 = stmt2.executeQuery();
                    resultado2.next();
                    veiculo.setDesc(resultado2.getDouble("desconto"));
                }
                retorno.add(veiculo);
            }
        } catch (SQLException ex) {
            System.out.println("erro na conexao: "+ex);
        } finally{
        Conexao.closeConnection(con, stmt);
        }
        return retorno;
    }
     
     public ResultSet pesquisar(){
       PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("SELECT chassi, modelo, fabricante, cor, ano, preco FROM tbl_veiculo;");
            ResultSet resultado = stmt.executeQuery();
            return resultado;
        } catch (SQLException ex) {
            System.out.println("erro na conexao: "+ex);
            return null;
        } finally{
        Conexao.closeConnection(con, stmt);
        }
   }
    
}


