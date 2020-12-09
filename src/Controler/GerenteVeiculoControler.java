
package Controler;



import DAO.VeiculoDAO;
import Model.Funcionario;
import Model.Veiculo;
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
import javafx.scene.control.Label;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author pedro
 */
public class GerenteVeiculoControler implements Initializable {
    

    @FXML
    private Label label;
    
    @FXML
    private TableView<Veiculo> tbl_veiculo;
    @FXML
    private TableColumn<Veiculo, String> col_chassi;
    @FXML
    private TableColumn<Veiculo, String> col_modelo2;
    @FXML
    private TableColumn<Veiculo, String> col_fabricante;
    @FXML
    private TableColumn<Veiculo, String> col_cor;
    @FXML
    private TableColumn<Veiculo, String> col_ano;
    @FXML
    private TableColumn<Veiculo, String> col_preco;
    
    private List<Veiculo> listVeiculos;
    
    private ObservableList<Veiculo> observableListVeiculos;
    
    @FXML
    private Button buttonInserir;
    @FXML
    private Button buttonAlterar;
    @FXML
    private Button buttonRemover;
    
    private Stage dialogStage;
    
    private Funcionario gerente;
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
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        col_chassi.setCellValueFactory(new PropertyValueFactory<>("chassi"));
        col_modelo2.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        col_fabricante.setCellValueFactory(new PropertyValueFactory<>("fabricante"));
        col_cor.setCellValueFactory(new PropertyValueFactory<>("cor"));
        col_ano.setCellValueFactory(new PropertyValueFactory<>("ano"));
        col_preco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        //col_desc.setCellValueFactory(new PropertyValueFactory<>("desconto"));
        listVeiculos = veiculoDAO.listar();
        observableListVeiculos = FXCollections.observableArrayList(listVeiculos);
        tbl_veiculo.setItems(observableListVeiculos);
    }
    
    
    public void setStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    public void setFunc(Funcionario gerente) {
        this.gerente = gerente;
    }

    
    public void carregarTableViewVeiculo() {
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        col_chassi.setCellValueFactory(new PropertyValueFactory<>("chassi"));
        col_modelo2.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        col_fabricante.setCellValueFactory(new PropertyValueFactory<>("fabricante"));
        col_cor.setCellValueFactory(new PropertyValueFactory<>("cor"));
        col_ano.setCellValueFactory(new PropertyValueFactory<>("ano"));
        col_preco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        listVeiculos = veiculoDAO.listar();
        observableListVeiculos = FXCollections.observableArrayList(listVeiculos);
        tbl_veiculo.setItems(observableListVeiculos);
    }

    @FXML
    private void tela_veiculos(ActionEvent event) {
        
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
        dialogStage.close();
        
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
        dialogStage.close();
        
        }catch(IOException ex){                
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Erro!");
            alert.show();
        }
    }
    @FXML
    public void handleButtonInserir() throws IOException {
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        Veiculo veiculo = new Veiculo();
        boolean buttonConfirmarClicked = showVeiculosDialog(veiculo);
        if (buttonConfirmarClicked) {
            veiculoDAO.inserir(veiculo);
            carregarTableViewVeiculo();
        }
    }
    @FXML
    public void handleButtonAlterar() throws IOException {
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        Veiculo veiculo = tbl_veiculo.getSelectionModel().getSelectedItem();
        if (veiculo != null) {
            boolean buttonConfirmarClicked = showVeiculosDialog(veiculo);
            if (buttonConfirmarClicked) {
                veiculoDAO.alterar(veiculo);
                carregarTableViewVeiculo();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um veiculo na Tabela!");
            alert.show();
        }
    }

    @FXML
    private void handleButtonRemover(ActionEvent event) throws IOException {
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        Veiculo veiculo = tbl_veiculo.getSelectionModel().getSelectedItem();
        if (veiculo != null) {
                veiculoDAO.apagar(veiculo);
                carregarTableViewVeiculo();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um veiculo na Tabela!");
            alert.show();
        }
    }
    
    public boolean showVeiculosDialog(Veiculo veiculo) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(VeiculosDialogController.class.getResource("/View/VeiculosDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Veiculos");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Setando o cliente no Controller.
        VeiculosDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setVeiculo(veiculo);

        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();

        return controller.isButtonConfirmarClicked();

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
    
