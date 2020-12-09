
package Controler;

import DAO.FuncionarioDAO;
import Model.Funcionario;
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
public class GerenteFuncController implements Initializable {
    
    @FXML
    private TableView<Funcionario> tbl_funcionarios;
    @FXML
    private TableColumn<Funcionario, String> col_id;
    @FXML
    private TableColumn<Funcionario, String> col_nome;
    @FXML
    private TableColumn<Funcionario, String> col_email;
    @FXML
    private TableColumn<Funcionario, String> col_telefone;
    @FXML
    private TableColumn<Funcionario, String> col_cpf;
    @FXML
    private TableColumn<Funcionario, String> col_login;
    @FXML
    private TableColumn<Funcionario, String> col_senha;
    @FXML
    private TableColumn<Funcionario, String> col_funcao;
    @FXML
    private TableColumn<Funcionario, String> col_pis;
    @FXML
    private TableColumn<Funcionario, String> col_rg;
    @FXML
    private TableColumn<Funcionario, String> col_cidade;
    @FXML
    private TableColumn<Funcionario, String> col_estado;
    @FXML
    private Button buttonInserir;
    @FXML
    private Button buttonAlterar;
    @FXML
    private Button buttonRemover;
    
    private List<Funcionario> listFunc;
    
    private ObservableList<Funcionario> observableListVeiculos;
    
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
        FuncionarioDAO funcDAO = new FuncionarioDAO();
        col_id.setCellValueFactory(new PropertyValueFactory<>("id_func"));
        col_nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_telefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        col_cpf.setCellValueFactory(new PropertyValueFactory<>("CPF"));
        col_login.setCellValueFactory(new PropertyValueFactory<>("login"));
        col_senha.setCellValueFactory(new PropertyValueFactory<>("password"));
        col_funcao.setCellValueFactory(new PropertyValueFactory<>("funcao"));
        col_pis.setCellValueFactory(new PropertyValueFactory<>("numeroPIS"));
        col_rg.setCellValueFactory(new PropertyValueFactory<>("rg"));
        col_cidade.setCellValueFactory(new PropertyValueFactory<>("cidade"));
        col_estado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        listFunc = funcDAO.listar();
        observableListVeiculos = FXCollections.observableArrayList(listFunc);
        tbl_funcionarios.setItems(observableListVeiculos);
    }
    
    public void carregarTableViewFuncionarios(){
        FuncionarioDAO funcDAO = new FuncionarioDAO();
        listFunc = funcDAO.listar();
        observableListVeiculos = FXCollections.observableArrayList(listFunc);
        tbl_funcionarios.setItems(observableListVeiculos);
    }
    
    public void setStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
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
    private void handleButtonInserir(ActionEvent event) throws IOException {
        FuncionarioDAO funcDAO = new FuncionarioDAO();
        Funcionario func = new Funcionario();
        boolean buttonConfirmarClicked = showFuncsDialog(func);
        if (buttonConfirmarClicked) {
            funcDAO.inserir(func);
            carregarTableViewFuncionarios();
        }
    }

    @FXML
    private void handleButtonAlterar(ActionEvent event) throws IOException {
        FuncionarioDAO funcDAO = new FuncionarioDAO();
       Funcionario func = tbl_funcionarios.getSelectionModel().getSelectedItem();
        if (func != null) {
            boolean buttonConfirmarClicked = showFuncsDialog(func);
            if (buttonConfirmarClicked) {
                funcDAO.alterar(func);
                carregarTableViewFuncionarios();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um funcionario na Tabela!");
            alert.show();
        }
    }

    @FXML
    private void handleButtonRemover(ActionEvent event) throws IOException {
        FuncionarioDAO funcDAO = new FuncionarioDAO();
        Funcionario func = tbl_funcionarios.getSelectionModel().getSelectedItem();
        if (func != null) {
                funcDAO.apagar(func);
                carregarTableViewFuncionarios();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um funcionario na Tabela!");
            alert.show();
        }
        
        
    }
    public boolean showFuncsDialog(Funcionario func) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FuncionariosDialogController.class.getResource("/View/FuncionariosDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Funcionarios");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Setando o Funcionario no Controller.
        FuncionariosDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setFunc(func);

        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();

        return controller.isButtonConfirmarClicked();

    }

    @FXML
    private void tela_funcs(ActionEvent event) {
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
