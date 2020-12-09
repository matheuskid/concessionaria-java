
package Controler;

import Model.Cliente;
import Model.Mascaras;
import Model.Promocao;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pedro
 */
public class ClientesDialogController implements Initializable {

    @FXML
    private TextField tf_nome;
    @FXML
    private TextField tf_email;
    @FXML
    private TextField tf_telefone;
    @FXML
    private TextField tf_cidade;
    @FXML
    private TextField tf_estado;
    @FXML
    private TextField tf_senha;
    @FXML
    private TextField tf_cnh;
    @FXML
    private Button buttonCancelar;
    @FXML
    private Button buttonConfirmar;

    private Stage dialogStage;
    
    private boolean buttonConfirmarClicked = false;
    
    private Cliente cliente;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Mascaras.maskString(tf_nome);
        Mascaras.maskString(tf_cidade);
        Mascaras.maskTel(tf_telefone);
        Mascaras.maskString(tf_estado);
        Mascaras.maskNumeros(tf_cnh);
    }    
    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
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
        this.tf_nome.setText(cliente.getNome());
        this.tf_senha.setText(String.valueOf(cliente.getCod_login()));
        this.tf_email.setText(cliente.getEmail());
        this.tf_telefone.setText(cliente.getTelefone());
        this.tf_cidade.setText(cliente.getCidade());
        this.tf_estado.setText(cliente.getEstado());
        this.tf_cnh.setText(cliente.getCNH());
        

    }
    @FXML
    private void handleButtonConfirmar(ActionEvent event) {
        
        if (validarEntradaDeDados()) {
            
            cliente.setNome(tf_nome.getText());
            cliente.setCod_login(Integer.parseInt(tf_senha.getText()));
            cliente.setEmail(tf_email.getText());
            cliente.setTelefone(tf_telefone.getText());
            cliente.setCidade(tf_cidade.getText());
            cliente.setEstado(tf_estado.getText());
            cliente.setCNH(tf_cnh.getText());

            
            buttonConfirmarClicked = true;
            dialogStage.close();
       }
    }

    @FXML
    private void handleButtonCancelar(ActionEvent event) {
        dialogStage.close();
    }
    
    private boolean validarEntradaDeDados() {
        String errorMessage = "";

        if (tf_nome.getText() == null || tf_nome.getText().length() == 0) {
            errorMessage += "Nome inválido!\n";
        }
        if (tf_senha.getText() == null || tf_senha.getText().length() == 0) {
            errorMessage += "Senha inválida!\n";
        }
        if (tf_email.getText() == null || tf_email.getText().length() == 0){
            errorMessage += "Email inválido!\n";
        }
        if (tf_telefone.getText() == null || tf_telefone.getText().length() == 0){
            errorMessage += "Telefone inválido!\n";
        }
        if (tf_cidade.getText() == null || tf_cidade.getText().length() == 0){
            errorMessage += "Cidade inválida!\n";
        }
        if (tf_estado.getText() == null || tf_estado.getText().length() == 0){
            errorMessage += "Estado inválido!\n";
        }
        if (tf_cnh.getText() == null || tf_cnh.getText().length() == 0){
            errorMessage += "CNH inválida!\n";
        }
        
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Mostrando a mensagem de erro
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro no cadastro");
            alert.setHeaderText("Campos inválidos, por favor, corrija...");
            alert.setContentText(errorMessage);
            alert.show();
            return false;
        }
    }


    
    
}
