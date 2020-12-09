
package Controler;

import DAO.VeiculoDAO;
import DAO.VendaDAO;
import Model.Cliente;

import Model.Veiculo;
import Model.Venda;

import java.net.URL;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author pedro
 */
public class FuncionarioVendasController implements Initializable {

    @FXML
    private TableView<Veiculo> tbl_veiculos;
    @FXML
    private TableColumn<Veiculo, String> col_chassi;
    @FXML
    private TableColumn<Veiculo, String> col_modelo;
    @FXML
    private TableColumn<Veiculo, String> col_preco;
    @FXML
    private TableColumn<Veiculo, String> col_desconto;
    
    private List<Veiculo> listVeiculos;
    
    private ObservableList<Veiculo> observableListVeiculos;
    
    private Stage stage;
    
    private boolean buttonConfirmarClicked = false;
    
    private int id_fun;
    
    private Cliente cliente;
    @FXML
    private TextField tf_formaPagam;
    @FXML
    private TextField tf_servAdi;
    @FXML
    private TextField tf_desconto;
    @FXML
    private TextField tf_total;
    @FXML
    private TextField tf_id;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        VeiculoDAO veiDAO = new VeiculoDAO();
        col_chassi.setCellValueFactory(new PropertyValueFactory<>("chassi"));
        col_modelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        col_preco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        col_desconto.setCellValueFactory(new PropertyValueFactory<>("desc"));
        listVeiculos = veiDAO.listar2();
        observableListVeiculos = FXCollections.observableArrayList(listVeiculos);
        tbl_veiculos.setItems(observableListVeiculos);
        
    }
    public void carregarTableViewVeiculos(){
        VeiculoDAO veiDAO = new VeiculoDAO();
        listVeiculos = veiDAO.listar2();
        observableListVeiculos = FXCollections.observableArrayList(listVeiculos);
        tbl_veiculos.setItems(observableListVeiculos);
    }
    public void setId(int id){
        this.id_fun = id;
        tf_id.setText(String.valueOf(id_fun));
    }
    
    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public boolean isButtonConfirmarClicked() {
        return buttonConfirmarClicked;
    }

    public void setButtonConfirmarClicked(boolean buttonConfirmarClicked) {
        this.buttonConfirmarClicked = buttonConfirmarClicked;
    }

    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    @FXML
    private void FecharVenda(ActionEvent event) {
        Veiculo veiculo = tbl_veiculos.getSelectionModel().getSelectedItem();
        Venda venda = new Venda();
        Date d = new Date();
        String data = java.text.DateFormat.getDateInstance(DateFormat.MEDIUM).format(d);
        
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        veiculoDAO.vender(veiculo);
        
        
        venda.setId_fun(Integer.parseInt(tf_id.getText()));
        venda.setId_cliente(cliente.getId_cliente());
        venda.setChassi(veiculo.getChassi());
        venda.setFormaPagam(tf_formaPagam.getText());
        venda.setServAdi(tf_servAdi.getText());
        venda.setValorTotal(Double.parseDouble(tf_total.getText()));
        venda.setDesconto(Double.parseDouble(tf_desconto.getText()));
        venda.setData(data);
        venda.setStatus("Andamento");
        VendaDAO vDAO = new VendaDAO();
        vDAO.inserir(venda);
        

        
        System.out.println("Deu crt garoto");
        carregarTableViewVeiculos();
    }

    }
