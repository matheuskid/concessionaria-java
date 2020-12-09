
package Controler;

import DAO.PromocaoDAO;
import Model.Promocao;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pedro
 */
public class GerentePromController implements Initializable {

    @FXML
    private TableView<Promocao> tbl_promocao;
    @FXML
    private TableColumn<Promocao, String> col_idProm;
    @FXML
    private TableColumn<Promocao, String> col_chassi;
    @FXML
    private TableColumn<Promocao, String> col_desconto;
    @FXML
    private TableColumn<Promocao, String> col_expira;

    @FXML
    private Button buttonInserir;
    @FXML
    private Button buttonAlterar;
    @FXML
    private Button buttonRemover;
    
    private List<Promocao> listProm;
    
    private ObservableList<Promocao> observableListPromocao;
    
    private Stage dialogStage;
    @FXML
    private Button tela_veiculos;
    @FXML
    private Button tela_func;
    @FXML
    private Button tela_prom;
    @FXML
    private Button tela_rela;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PromocaoDAO promDAO = new PromocaoDAO();
        col_chassi.setCellValueFactory(new PropertyValueFactory<>("id_veiculo"));
        col_desconto.setCellValueFactory(new PropertyValueFactory<>("desconto"));
        col_expira.setCellValueFactory(new PropertyValueFactory<>("data_exp"));
        col_idProm.setCellValueFactory(new PropertyValueFactory<>("id_prom"));
        listProm = promDAO.listar();
        observableListPromocao = FXCollections.observableArrayList(listProm);
        tbl_promocao.setItems(observableListPromocao);
    }

    public void setStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }    

    public void carregarTableViewProm(){
        PromocaoDAO promDAO = new PromocaoDAO();
        listProm = promDAO.listar();
        observableListPromocao = FXCollections.observableArrayList(listProm);
        tbl_promocao.setItems(observableListPromocao);
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
        dialogStage.close();
        
        }catch(IOException ex){                
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Erro!");
            alert.show();
        }
    }
    @FXML
    private void tela_funcs(ActionEvent event){
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
        dialogStage.close();
        
        }catch(IOException ex){                
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Erro!");
            alert.show();
        }
    }

    @FXML
    private void handleButtonInserir(ActionEvent event) throws IOException {
        PromocaoDAO promDAO = new PromocaoDAO();
        Promocao prom = new Promocao();
        boolean buttonConfirmarClicked = showPromDialog(prom);
        if (buttonConfirmarClicked) {
            promDAO.inserir(prom);
            carregarTableViewProm();
        }
    }

    @FXML
    private void handleButtonAlterar(ActionEvent event) throws IOException {
        PromocaoDAO promDAO = new PromocaoDAO();
        Promocao prom = tbl_promocao.getSelectionModel().getSelectedItem();
        if (prom != null) {
            boolean buttonConfirmarClicked = showPromDialog(prom);
            if (buttonConfirmarClicked) {
                promDAO.alterar(prom);
                carregarTableViewProm();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um promocao na Tabela!");
            alert.show();
        }
    }

    @FXML
    private void handleButtonRemover(ActionEvent event) {
        PromocaoDAO promDAO = new PromocaoDAO();
        Promocao prom = tbl_promocao.getSelectionModel().getSelectedItem();
        if (prom != null) {
                promDAO.apagar(prom);
                carregarTableViewProm();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um promocao na Tabela!");
            alert.show();
        }
        
    }
 
    public boolean showPromDialog(Promocao prom) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(PromDialogController.class.getResource("/View/PromDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Promoções");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Setando o Promoçoes no Controller.
        PromDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setProm(prom);

        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();

        return controller.isButtonConfirmarClicked();
}

    @FXML
    private void tela_proms(ActionEvent event) {
    }

    @FXML
    private void tela_rela(ActionEvent event) {
        try{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(GerenteRelaController.class.getResource("/View/GerenteRela.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // 
        Stage stage = new Stage();
        stage.setTitle("Relatórios");
        Scene scene = new Scene(page);
        stage.setScene(scene);

        // 
        GerenteRelaController controller = loader.getController();
        controller.setStage(stage);

        // 
        stage.show();
        dialogStage.close();
        
        }catch(IOException ex){                
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Erro!");
            alert.show();
        }
    }
}
