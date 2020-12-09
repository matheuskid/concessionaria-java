
package Controler;

import DAO.ClienteDAO;
import DAO.PromocaoDAO;
import DAO.VeiculoDAO;
import DAO.VendaDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
/**
 * FXML Controller class
 *
 * @author mathe
 */
public class GerenteRelaController implements Initializable {

    @FXML
    private Button tela_veiculos1;
    @FXML
    private Button tela_func1;
    @FXML
    private Button tela_prom1;
    @FXML
    private Button clientes_rela;
    @FXML
    private Button veiculos_rela;
    @FXML
    private Button prom_rela;
    @FXML
    private Button venda_rela;
    
    private Stage stagio;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }
    
    public void setStage(Stage dialogStage) {
        this.stagio = dialogStage;
    }       

    @FXML
    private void tela_veiculos(ActionEvent event) {
        try{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(GerenteVeiculoControler.class.getResource("/View/GerenteVeiculo.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // 
        Stage stage = new Stage();
        stage.setTitle("Veiculos");
        Scene scene = new Scene(page);
        stage.setScene(scene);

        // 
        GerenteVeiculoControler controller = loader.getController();
        controller.setStage(stage);

        // 
        stage.show();
        stagio.close();
        
        }catch(IOException ex){                
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Erro!");
            alert.show();
        }
    }

    @FXML
    private void tela_funcs(ActionEvent event) {
        try{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(GerenteFuncController.class.getResource("/View/GerenteFunc.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // 
        Stage stage = new Stage();
        stage.setTitle("Funcionarios");
        Scene scene = new Scene(page);
        stage.setScene(scene);

        // 
        GerenteFuncController controller = loader.getController();
        controller.setStage(stage);

        // 
        stage.show();
        stagio.close();
        
        }catch(IOException ex){                
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Erro!");
            alert.show();
        }
    }

    @FXML
    private void tela_proms(ActionEvent event) {
        try{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(GerentePromController.class.getResource("/View/GerenteProm.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // 
        Stage stage = new Stage();
        stage.setTitle("Promoções");
        Scene scene = new Scene(page);
        stage.setScene(scene);

        // 
        GerentePromController controller = loader.getController();
        controller.setStage(stage);

        // 
        stage.show();
        stagio.close();
        
        }catch(IOException ex){                
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Erro!");
            alert.show();
        }
    }

    @FXML
    private void rela_clientes(ActionEvent event){
        ClienteDAO cliDAO = new ClienteDAO();
        ResultSet rs = cliDAO.pesquisar();
        JasperDesign rela;
        try {
            rela = JRXmlLoader.load("Relatorio_clientes.jrxml");
            JasperReport rela_compi = JasperCompileManager.compileReport(rela);
            JRResultSetDataSource dados = new JRResultSetDataSource(rs);
            JasperPrint jPrint = JasperFillManager.fillReport(rela_compi,null, dados);
            JasperViewer jv = new JasperViewer(jPrint, false);
            jv.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(GerenteRelaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void rela_veiculos(ActionEvent event) throws JRException {
        VeiculoDAO veiDAO = new VeiculoDAO();
        ResultSet rs = veiDAO.pesquisar();
        JasperDesign rela = JRXmlLoader.load("relatorio_veiculos.jrxml");
        JasperReport rela_compi = JasperCompileManager.compileReport(rela);
        JRResultSetDataSource dados = new JRResultSetDataSource(rs);
        JasperPrint jPrint = JasperFillManager.fillReport(rela_compi,null, dados);
        JasperViewer jv = new JasperViewer(jPrint, false);
        jv.setVisible(true);
    }

    @FXML
    private void rela_proms(ActionEvent event) throws JRException {
        PromocaoDAO promDAO = new PromocaoDAO();
        ResultSet rs = promDAO.pesquisar();
        JasperDesign rela = JRXmlLoader.load("relatorio_promocoes.jrxml");
        JasperReport rela_compi = JasperCompileManager.compileReport(rela);
        JRResultSetDataSource dados = new JRResultSetDataSource(rs);
        JasperPrint jPrint = JasperFillManager.fillReport(rela_compi,null, dados);
        JasperViewer jv = new JasperViewer(jPrint, false);
        jv.setVisible(true);
    }

    @FXML
    private void rela_vendas(ActionEvent event) {
        VendaDAO vendaDAO = new VendaDAO();
        ResultSet rs = vendaDAO.pesquisar();
        JasperDesign rela;
        try {
        rela = JRXmlLoader.load("Relatorio_vendas.jrxml");
        JasperReport rela_compi = JasperCompileManager.compileReport(rela);
        JRResultSetDataSource dados = new JRResultSetDataSource(rs);
        JasperPrint jPrint = JasperFillManager.fillReport(rela_compi,null, dados);
        JasperViewer jv = new JasperViewer(jPrint, false);
        jv.setVisible(true);
        } catch (JRException ex) {
            System.out.println("erro: "+ex);
        }
        
    }
    
}
