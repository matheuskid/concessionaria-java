
package Controler;



import Model.Mascaras;
import Model.Promocao;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author alves
 */
public class PromDialogController implements Initializable {

    @FXML
    private TextField tf_chassi;
    @FXML
    private TextField tf_desc;
    @FXML
    private DatePicker dp_expira;
    @FXML
    private Button buttonConfirmar;
    @FXML
    private Button buttonCancelar;

    private Stage dialogStage;
    
    private boolean buttonConfirmarClicked = false;
    
    private Promocao prom;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Mascaras.maskNumeros(tf_desc);
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

    public Promocao getProm() {
        return prom;
    }
    
    public void setProm(Promocao prom) {
        this.prom = prom;
        this.tf_chassi.setText(prom.getId_veiculo());
        this.tf_desc.setText(String.valueOf(prom.getDesconto()*100)+"%");
        
        //LocalDate data = LocalDate.parse(prom.getData_exp());
        this.dp_expira.setValue(null);
    }
    @FXML
    private void handleButtonConfirmar(ActionEvent event) {
        
        if (validarEntradaDeDados()) {
            
            prom.setId_veiculo(tf_chassi.getText());
            prom.setDesconto(Double.parseDouble(tf_desc.getText())/100);
            
            //String text = dp_expira.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            prom.setData_exp(dp_expira.getValue().toString());
            
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

        if (tf_chassi.getText() == null || tf_chassi.getText().length() == 0) {
            errorMessage += "Chassi inválido!\n";
        }
        if (tf_desc.getText() == null || tf_desc.getText().length() == 0) {
            errorMessage += "Desconto inválido!\n";
        }
        if (dp_expira.getValue() == null ){
            errorMessage += "Data inválida!\n";
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
