
package Model;

import javafx.application.Platform;
import javafx.scene.control.TextField;

/**
 *
 * @author mathe
 */
public class Mascaras {

 private static void limitarTamanho(TextField textField, int tamanho){
 textField.textProperty().addListener((observable, oldValue, newValue)->{
 if(newValue==null || newValue.length()>tamanho){
 textField.setText(oldValue);
 }
 });
// O método acima, limitarTamanho, apresenta um nome bem sugestivo, e serve para
//limitar o tamanho do valor do campo, sendo usado por outros métodos a seguir.
 }
 private static void posicionarCursor(TextField textField){
 Platform.runLater(()->{
 if(textField.getText().length()!=0){
 textField.positionCaret(textField.getText().length());
 }
 });
 }
 public static void maskCpf(TextField textFielf){
 limitarTamanho(textFielf, 14); //limita em 14 o tamanho do cpf
 // a linha a baixo escuta o que esta sendo digitado no campo
 textFielf.lengthProperty().addListener((observable, oldValue, newValue)->{
 String textoDigitado=textFielf.getText(); //pega o valor que foi digitado
 textoDigitado=textoDigitado.replaceAll("[^0-9]",""); // Só permite números
 textoDigitado=textoDigitado.replaceFirst("(\\d{3})(\\d)", "$1.$2"); //coloca o pontodepois de 3 primeiros dígitos.
 textoDigitado=textoDigitado.replaceFirst("(\\d{3})(\\d)", "$1.$2");//coloca outroponto depois de 3 outros dígitos.
 textoDigitado=textoDigitado.replaceFirst("(\\d{3})(\\d)", "$1-$2");// coloca o traçodepois de mais 3 dígitos.
 textFielf.setText(textoDigitado); //seta o valor no textfield formatado
 posicionarCursor(textFielf); //posiciona o cursor no final do valor digitado
 });
 }
 
 public static void maskTel(TextField textFielf){
 limitarTamanho(textFielf, 15); //limita em 14 o tamanho do cpf
 // a linha a baixo escuta o que esta sendo digitado no campo
 textFielf.lengthProperty().addListener((observable, oldValue, newValue)->{
 String textoDigitado=textFielf.getText(); //pega o valor que foi digitado
 textoDigitado=textoDigitado.replaceAll("[^0-9]",""); // Só permite números
 textoDigitado=textoDigitado.replaceFirst("(\\d{0})(\\d)", "$1($2");
 textoDigitado=textoDigitado.replaceFirst("(\\d{2})(\\d)", "$1) $2");
 textoDigitado=textoDigitado.replaceFirst("(\\d{5})(\\d)", "$1-$2"); //coloca o pontodepois de 3 primeiros dígitos.
 textFielf.setText(textoDigitado); //seta o valor no textfield formatado
 posicionarCursor(textFielf); //posiciona o cursor no final do valor digitado
 });
 }

// o método abaixo só permite letras e espaços em branco. Ele não deixa você digitar um número.
 public static void maskString(TextField textField){
 textField.lengthProperty().addListener((observable, oldValue, newValue)->{
 String textoDigitado=textField.getText();
 textoDigitado=textoDigitado.replaceAll("[^A-Za-z\\s]","");
 textField.setText(textoDigitado);

 });
 }
 
 public static void maskNumeros(TextField textField){
 textField.lengthProperty().addListener((observable, oldValue, newValue)->{
 String textoDigitado=textField.getText();
 textoDigitado=textoDigitado.replaceAll("[^0-9]","");
 textField.setText(textoDigitado);

 });
 }
 
 public static void maskRg(TextField textField){
 limitarTamanho(textField, 13);
 textField.lengthProperty().addListener((observable, oldValue, newValue)->{
 String textoDigitado=textField.getText();
 textoDigitado=textoDigitado.replaceAll("[^0-9]","");
 textoDigitado=textoDigitado.replaceFirst("(\\d{2})(\\d)", "$1 $2");
 textoDigitado=textoDigitado.replaceFirst("(\\d{3})(\\d)", "$1 $2");
 textoDigitado=textoDigitado.replaceFirst("(\\d{3})(\\d)", "$1-$2");
 textField.setText(textoDigitado);
 posicionarCursor(textField);
 });
 }
 
 
 //máscara para cep
 public static void maskCep(TextField textField){
 limitarTamanho(textField, 10);
 textField.lengthProperty().addListener((observable, oldValue, newValue)->{
 String textoDigitado=textField.getText();
 textoDigitado=textoDigitado.replaceAll("[^0-9]","");
 textoDigitado=textoDigitado.replaceFirst("(\\d{2})(\\d)", "$1.$2");
 textoDigitado=textoDigitado.replaceFirst("(\\d{3})(\\d)", "$1-$2");
 textField.setText(textoDigitado);
 posicionarCursor(textField);
 });
 }
 //Máscara para Data
 public static void maskData(TextField textField){

 limitarTamanho(textField, 10);
 textField.lengthProperty().addListener((observable, oldValue, newValue)->{
 String textoDigitado=textField.getText();
 textoDigitado=textoDigitado.replaceAll("[^0-9]","");
 textoDigitado=textoDigitado.replaceFirst("(\\d{2})(\\d)", "$1/$2");
 textoDigitado=textoDigitado.replaceFirst("(\\d{2})(\\d)", "$1/$2");
 textField.setText(textoDigitado);
 posicionarCursor(textField);
 });

}
 //Máscara para dinheiro
 public static void maskDinheiro(TextField textField){
 limitarTamanho(textField, 12);
 textField.lengthProperty().addListener((observable, oldValue, newValue)->{
 String textoDigitado=textField.getText();
 textoDigitado=textoDigitado.replaceAll("[^0-9]","");
 //textoDigitado=textoDigitado.replaceFirst("(\\d{1})(\\d)", "$1,$2");
 textoDigitado=textoDigitado.replaceFirst("(\\d{3})(\\d)", "$1 $2");
 textoDigitado=textoDigitado.replaceFirst("(\\d{3})(\\d)", "$1 $2");
 textoDigitado=textoDigitado.replaceFirst("(\\d{3})(\\d)", "$1 $2");
 textoDigitado=textoDigitado.replaceFirst("(\\d{3})(\\d)", "$1 $2");
 textoDigitado=textoDigitado.replaceFirst("(\\d{3})(\\d)", "$1 $2");
 textField.setText(textoDigitado);
 posicionarCursor(textField);
 });
 }

}
