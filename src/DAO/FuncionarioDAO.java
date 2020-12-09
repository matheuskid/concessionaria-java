package DAO;

import Conexao.Conexao;
import Model.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




public class FuncionarioDAO {
    private Connection con = null;
    
    public FuncionarioDAO(){
        con = Conexao.getConnection();
    }
    public boolean inserir(Funcionario fun){
        String sql = "INSERT INTO public.tbl_funcionario (nome, email, telefone, CPF, rg, cidade, estado, login, senha, funcao, numeroPIS)" +
" VALUES ('"+fun.getNome()+"','"+fun.getEmail()+"','"+fun.getTelefone()+"','"+fun.getCPF()+"','"+fun.getRg()+"','"+fun.getCidade()+"','"+fun.getEstado()+"'" +
",'"+fun.getLogin()+"','"+fun.getPassword()+"','"+fun.getFuncao()+"','"+fun.getNumeroPIS()+"')";
        
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("erro na conexao "+ex);
            return false;
        } finally{
        Conexao.closeConnection(con, stmt);
        }
    }
    
    public int logar(String login, String senha){
        String sql = "SELECT id_fun FROM public.tbl_funcionario WHERE login = '"+login+"' AND senha = '"+senha+"'";
        PreparedStatement stmt = null;
       
        try {
            stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return rs.getInt("id_fun");
        } catch (SQLException ex) {
            System.out.println("erro na conexao: " +ex);
            return 0;
        } finally {
        Conexao.closeConnection(con, stmt);
        }
    }
    
    public Funcionario procurar(int id_fun){
        String sql = "SELECT * FROM public.tbl_funcionario WHERE id_fun = "+id_fun+";";
        PreparedStatement stmt = null;
        Funcionario funcionario = new Funcionario();
        try {
            stmt = con.prepareStatement(sql);
            ResultSet resu = stmt.executeQuery();
            
            while (resu.next()) {
                
                funcionario.setId_func(resu.getInt("id_fun"));
                funcionario.setNome(resu.getString("nome"));
                funcionario.setEmail(resu.getString("email"));
                funcionario.setTelefone(resu.getString("telefone"));
                funcionario.setCPF(resu.getString("cpf"));
                funcionario.setRg(resu.getString("rg"));
                funcionario.setCidade(resu.getString("cidade"));
                funcionario.setEstado(resu.getString("estado"));
                funcionario.setFuncao(resu.getString("funcao"));
                funcionario.setLogin(resu.getString("login"));
                funcionario.setPassword(resu.getString("senha"));
                funcionario.setNumeroPIS(resu.getString("numeropis"));
                
            }
        } catch (SQLException ex) {
            System.out.println("erro na conexao: " +ex);
            return null;
        } finally {
        Conexao.closeConnection(con, stmt);
        }
        return funcionario;
    }
    
    public String funcao (int id_fun){
        String sql = "SELECT funcao FROM public.tbl_funcionario WHERE id_fun = "+id_fun+";";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            ResultSet resu = stmt.executeQuery();
            resu.next();
            return resu.getString("funcao");
        } catch (SQLException ex) {
            System.out.println("erro na conexao: " +ex);
            return null;
        } finally{
        Conexao.closeConnection(con, stmt);
        }
        
    }
    
    public List<Funcionario> listar() {
        String sql = "SELECT * FROM public.tbl_funcionario";
        List<Funcionario> retorno = new ArrayList<>();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            
            while (resultado.next()) {
                
                Funcionario funcionario = new Funcionario();
                funcionario.setId_func(resultado.getInt("id_fun"));
                funcionario.setNome(resultado.getString("nome"));
                funcionario.setEmail(resultado.getString("email"));
                funcionario.setTelefone(resultado.getString("telefone"));
                funcionario.setCPF(resultado.getString("cpf"));
                funcionario.setRg(resultado.getString("rg"));
                funcionario.setCidade(resultado.getString("cidade"));
                funcionario.setEstado(resultado.getString("estado"));
                funcionario.setFuncao(resultado.getString("funcao"));
                funcionario.setLogin(resultado.getString("login"));
                funcionario.setPassword(resultado.getString("senha"));
                funcionario.setNumeroPIS(resultado.getString("numeropis"));
                retorno.add(funcionario);
            }
        } catch (SQLException ex) {
            System.out.println("erro na conexao: " +ex);
        } finally{
        Conexao.closeConnection(con, stmt);
        }
        return retorno;
    }
    
    public boolean alterar(Funcionario funcionario){
        String sql = "UPDATE public.tbl_funcionario SET nome='"+funcionario.getNome()+"', email='"+funcionario.getEmail()+"', telefone='"+funcionario.getTelefone()+"', cpf='"+funcionario.getCPF()+"', rg='"+funcionario.getRg()+"', cidade='"+funcionario.getCidade()+"', estado='"+funcionario.getEstado()+"', login='"+funcionario.getLogin()+"', senha='"+funcionario.getPassword()+"', funcao='"+funcionario.getFuncao()+"', numeropis='"+funcionario.getNumeroPIS()+"'\n" +
"	WHERE id_fun="+funcionario.getId_func()+";";
        
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
    
    public boolean apagar(Funcionario func){
        String sql = "DELETE FROM public.tbl_funcionario WHERE id_fun = "+func.getId_func()+";";
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
    
}
