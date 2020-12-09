
package DAO;

import Conexao.Conexao;
import Model.Venda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;




public class VendaDAO {
    private Connection con = null;

    public VendaDAO() {
        con = Conexao.getConnection();
    }
    
    public boolean inserir(Venda venda){
        String sql = "INSERT INTO public.tbl_venda (id_fun, id_cliente, chassi, form_pag, serv_adi, valor_tot, desconto, data, status) "
        + "VALUES ("+venda.getId_fun()+", "+venda.getId_cliente()+", '"+venda.getChassi()+"', '"+venda.getFormaPagam()+"', '"+venda.getServAdi()+"', "+venda.getValorTotal()+", "+venda.getDesconto()+", '"+venda.getData()+"', '"+venda.getStatus()+"');";
        
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
    
    public ResultSet pesquisar(){
       PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("SELECT chassi, form_pag, serv_adi, valor_tot, data FROM tbl_venda;");
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
