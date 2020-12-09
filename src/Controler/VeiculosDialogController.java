package Controler;

import Model.Mascaras;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Model.Veiculo;

public class VeiculosDialogController implements Initializable {

    @FXML
    private Button buttonConfirmar;
    @FXML
    private Button buttonCancelar;

    private Stage dialogStage;
    
    private boolean buttonConfirmarClicked = false;
    
    private Veiculo veiculo;
    @FXML
    private TextField tf_chassi;
    @FXML
    private TextField tf_modelo;
    @FXML
    private TextField tf_fabricante;
    @FXML
    private TextField tf_cor;
    @FXML
    private TextField tf_ano;
    @FXML
    private TextField tf_preco;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Mascaras.maskString(tf_cor);
        Mascaras.maskString(tf_fabricante);
        Mascaras.maskNumeros(tf_preco);
        Mascaras.maskNumeros(tf_ano);
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

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
        this.tf_chassi.setText(veiculo.getChassi());
        this.tf_modelo.setText(veiculo.getModelo());
        this.tf_fabricante.setText(veiculo.getFabricante());
        this.tf_cor.setText(veiculo.getCor());
        this.tf_ano.setText(String.valueOf(veiculo.getAno()));
        this.tf_preco.setText(String.valueOf(veiculo.getPreco()));
    }
    

    @FXML
    public void handleButtonConfirmar() {

        if (validarEntradaDeDados()) {

            veiculo.setChassi(tf_chassi.getText());
            veiculo.setModelo(tf_modelo.getText());
            veiculo.setFabricante(tf_fabricante.getText());
            veiculo.setCor(tf_cor.getText());
            veiculo.setAno(Integer.parseInt(tf_ano.getText()));
            veiculo.setPreco(Float.parseFloat(tf_preco.getText()));

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

        if (tf_chassi.getText() == null || tf_chassi.getText().length() == 0) {
            errorMessage += "Chassi inválido!\n";
        }
        if (tf_modelo.getText() == null || tf_modelo.getText().length() == 0) {
            errorMessage += "Modelo inválido!\n";
        }
        if (tf_fabricante.getText() == null || tf_fabricante.getText().length() == 0) {
            errorMessage += "Fabricante inválido!\n";
        }
        if (tf_cor.getText() == null || tf_cor.getText().length() == 0) {
            errorMessage += "Cor inválido!\n";
        }
        if (tf_ano.getText() == null || tf_ano.getText().length() == 0) {
            errorMessage += "Ano inválido!\n";
        }
        if (tf_preco.getText() == null || tf_preco.getText().length() == 0) {
            errorMessage += "Preço inválido!\n";
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
