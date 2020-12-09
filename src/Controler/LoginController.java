
package Controler;

import DAO.FuncionarioDAO;
import Model.Mascaras;
import concessionaria.Login;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mathe
 */
public class LoginController implements Initializable {

    @FXML
    private Button btn_login;
    @FXML
    private Button btn_sair;
    @FXML
    private TextField tf_login;
    @FXML
    private PasswordField tf_senha;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Mascaras.maskString(tf_login);
    }    

    @FXML
    void logar(ActionEvent event) {
        String login = tf_login.getText();
        String senha = tf_senha.getText();
        FuncionarioDAO func = new FuncionarioDAO();
        if(0 == func.logar(login, senha)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Erro! Credenciais inválidas!");
            alert.show();
        }else{
            int id_fun = func.logar(login, senha);
            switch(func.funcao(id_fun)){
                case("gerente"):
                    
                try {
                    
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
                controller.setFunc(func.procurar(id_fun));

        // 
                stage.show();
                Login.getStage().close();
                
                }catch(IOException ex){                
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Erro! Credenciais inválidas!");
                    alert.show();
                }
    
                break;
                case("funcionario"):
                    
                try {
                    
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(FuncionarioClienteController.class.getResource("/View/FuncionarioCliente.fxml"));
                AnchorPane page = (AnchorPane) loader.load();

        // 
                Stage stage = new Stage();
                stage.setTitle("Cliente");
                Scene scene = new Scene(page);
                stage.setScene(scene);

        // 
                FuncionarioClienteController controller = loader.getController();
                controller.setStage(stage);
                controller.setFunc(func.procurar(id_fun));

        // 
                stage.show();
                Login.getStage().close();
                
                }catch(IOException ex){                
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Erro! Credenciais inválidas!");
                    alert.show();
                }
    
                break;
            }
        }
                
    }
            
    @FXML
    void sair(ActionEvent event) {
        Login.getStage().close();
    }
    }
    