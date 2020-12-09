package Controler;

import Model.Funcionario;
import Model.Mascaras;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FuncionariosDialogController implements Initializable {

    @FXML
    private Button buttonConfirmar;
    @FXML
    private Button buttonCancelar;
    @FXML
    private TextField tf_nome;
    @FXML
    private TextField tf_email;
    @FXML
    private TextField tf_telefone;
    @FXML
    private TextField tf_cpf;
    @FXML
    private TextField tf_rg;
    @FXML
    private TextField tf_cidade;
    @FXML
    private TextField tf_estado;
    @FXML
    private TextField tf_login;
    @FXML
    private TextField tf_senha;
    @FXML
    private TextField tf_funcao;
    @FXML
    private TextField tf_pis;
    
    //
    
    private Stage dialogStage;
    
    private boolean buttonConfirmarClicked = false;
    
    private Funcionario funcionario;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Mascaras.maskCpf(tf_cpf);
        Mascaras.maskRg(tf_rg);
        Mascaras.maskNumeros(tf_pis);
        Mascaras.maskString(tf_nome);
        Mascaras.maskString(tf_cidade);
        Mascaras.maskTel(tf_telefone);
        Mascaras.maskString(tf_estado);
        Mascaras.maskString(tf_funcao);
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

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFunc(Funcionario funcionario) {
        this.funcionario = funcionario;
        this.tf_nome.setText(funcionario.getNome());
        this.tf_email.setText(funcionario.getEmail());
        this.tf_telefone.setText(funcionario.getTelefone());
        this.tf_cpf.setText(funcionario.getCPF());
        this.tf_rg.setText(funcionario.getRg());
        this.tf_cidade.setText(funcionario.getCidade());
        this.tf_estado.setText(funcionario.getEstado());
        this.tf_login.setText(funcionario.getLogin());
        this.tf_senha.setText(funcionario.getPassword());
        this.tf_funcao.setText(funcionario.getFuncao());
        this.tf_pis.setText(funcionario.getNumeroPIS());
    }
    

    @FXML
    public void handleButtonConfirmar() {

        if (validarEntradaDeDados()) {

            funcionario.setNome(tf_nome.getText());
            funcionario.setEmail(tf_email.getText());
            funcionario.setTelefone(tf_telefone.getText());
            funcionario.setCPF(tf_cpf.getText());
            funcionario.setRg(tf_rg.getText());
            funcionario.setCidade(tf_cidade.getText());
            funcionario.setEstado(tf_estado.getText());
            funcionario.setLogin(tf_login.getText());
            funcionario.setPassword(tf_senha.getText());
            funcionario.setFuncao(tf_funcao.getText());
            funcionario.setNumeroPIS(tf_pis.getText());
            
            
            buttonConfirmarClicked = true;
            dialogStage.close();
       }

    }

    @FXML
    public void handleButtonCancelar() {
        dialogStage.close();
    }

    //Validar entrada de dados para o cadastro
    private boolean validarEntradaDeDados() {
        String errorMessage = "";

        if (tf_nome.getText() == null || tf_nome.getText().length() == 0) {
            errorMessage += "Nome inválido!\n";
        }
        if (tf_email.getText() == null || tf_email.getText().length() == 0) {
            errorMessage += "Email inválido!\n";
        }
        if (tf_telefone.getText() == null || tf_telefone.getText().length() == 0) {
            errorMessage += "Telefone inválido!\n";
        }
        if (tf_cpf.getText() == null || tf_cpf.getText().length() == 0) {
            errorMessage += "CPF inválido!\n";
        }
        if (tf_rg.getText() == null || tf_rg.getText().length() == 0) {
            errorMessage += "RG inválido!\n";
        }
        if (tf_cidade.getText() == null || tf_cidade.getText().length() == 0) {
            errorMessage += "Cidade inválida!\n";
        }
        if (tf_estado.getText() == null || tf_estado.getText().length() == 0) {
            errorMessage += "Estado inválido!\n";
        }
        if (tf_login.getText() == null || tf_login.getText().length() == 0) {
            errorMessage += "Login inválido!\n";
        }
        if (tf_senha.getText() == null || tf_senha.getText().length() == 0) {
            errorMessage += "Senha inválida!\n";
        }
        if (tf_funcao.getText() == null || tf_funcao.getText().length() == 0) {
            errorMessage += "Função inválida!\n";
        }
        if (tf_pis.getText() == null || tf_pis.getText().length() == 0) {
            errorMessage += "PIS inválido!\n";
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
