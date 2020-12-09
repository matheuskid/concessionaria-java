/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import DAO.ClienteDAO;
import Model.Cliente;
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
import javafx.scene.control.Menu;
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
public class FuncionarioClienteController implements Initializable {

    @FXML
    private Button buttonInserir;
    @FXML
    private Button buttonAlterar;
    @FXML
    private Button buttonRemover;
    @FXML
    private Menu menu_veiculos;
    @FXML
    private TableView<Cliente> tbl_clientes;
    @FXML
    private TableColumn<Cliente, String> col_nome;
    @FXML
    private TableColumn<Cliente, String> col_senha;
    @FXML
    private TableColumn<Cliente, String> col_email;
    @FXML
    private TableColumn<Cliente, String> col_telefone;
    @FXML
    private TableColumn<Cliente, String> col_cidade;
    @FXML
    private TableColumn<Cliente, String> col_estado;
    @FXML
    private TableColumn<Cliente, String> col_cnh;
    @FXML
    private TableColumn<Cliente, String> col_id;
    
    private List<Cliente> listClientes;
    
    private ObservableList<Cliente> observableListClientes;
    @FXML
    private Button buttonEscolher;
    
    private Stage dialogStage;
    
    private Funcionario funcionario;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ClienteDAO cliDAO = new ClienteDAO();
        col_id.setCellValueFactory(new PropertyValueFactory<>("id_cliente"));
        col_nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        col_senha.setCellValueFactory(new PropertyValueFactory<>("cod_login"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_telefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        col_cidade.setCellValueFactory(new PropertyValueFactory<>("cidade"));
        col_estado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        col_cnh.setCellValueFactory(new PropertyValueFactory<>("CNH"));
        listClientes = cliDAO.listar();
        observableListClientes = FXCollections.observableArrayList(listClientes);
        tbl_clientes.setItems(observableListClientes);
    }
    public void carregarTableViewClientes(){
        ClienteDAO cliDAO = new ClienteDAO();
        listClientes = cliDAO.listar();
        observableListClientes = FXCollections.observableArrayList(listClientes);
        tbl_clientes.setItems(observableListClientes);
    }
    
    public void setStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    public void setFunc(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
    
    @FXML
    private void handleButtonEscolher(ActionEvent event) throws IOException {
        Cliente cliente = tbl_clientes.getSelectionModel().getSelectedItem();
        if (cliente != null) {
        boolean buttonConfirmarClicked = showVendaDialog(cliente);
        
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um promocao na Tabela!");
            alert.show();
        }
    }

    @FXML
    private void handleButtonInserir(ActionEvent event) throws IOException {
        ClienteDAO cliDAO = new ClienteDAO();
        Cliente cliente = new Cliente();
        boolean buttonConfirmarClicked = showClientesDialog(cliente);
        if (buttonConfirmarClicked) {
            cliDAO.inserir(cliente);
            carregarTableViewClientes();
        }
    }

    @FXML
    private void handleButtonAlterar(ActionEvent event) throws IOException {
        ClienteDAO cliDAO = new ClienteDAO();
        Cliente cliente = tbl_clientes.getSelectionModel().getSelectedItem();
        if (cliente != null) {
            boolean buttonConfirmarClicked = showClientesDialog(cliente);
            if (buttonConfirmarClicked) {
                cliDAO.alterar(cliente);
                carregarTableViewClientes();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um promocao na Tabela!");
            alert.show();
        }
    }

    @FXML
    private void handleButtonRemover(ActionEvent event) {
        ClienteDAO cliDAO = new ClienteDAO();
        Cliente cliente = tbl_clientes.getSelectionModel().getSelectedItem();
        if (cliente != null) {
                cliDAO.apagar(cliente);
                carregarTableViewClientes();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um promocao na Tabela!");
            alert.show();
        }
    }




    public boolean showClientesDialog(Cliente cliente) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ClientesDialogController.class.getResource("/View/ClientesDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Clientes");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Setando o Promoçoes no Controller.
        ClientesDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setCliente(cliente);

        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();

        return controller.isButtonConfirmarClicked();
}
    public boolean showVendaDialog(Cliente cliente) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FuncionarioVendasController.class.getResource("/View/FuncionarioVendas.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Fechar Venda");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Setando o Promoçoes no Controller.
        FuncionarioVendasController controller = loader.getController();
        controller.setStage(dialogStage);
        controller.setCliente(cliente);
        controller.setId(funcionario.getId_func());

        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();

        return controller.isButtonConfirmarClicked();
}
}
