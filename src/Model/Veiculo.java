
package Model;


public class Veiculo {
    private String chassi;
    private String modelo;
    private String fabricante;
    private String cor;
    private int ano;
    private float preco;
    private double desc;

    public Veiculo(){
        
    }
    
    public Veiculo(String chassi, String modelo, float preco, double desc) {
        this.chassi = chassi;
        this.modelo = modelo;
        this.preco = preco;
        this.desc = desc;
    }

    public Veiculo(String chassi, String modelo, String fabricante, String cor, int ano, float preco) {
        this.chassi = chassi;
        this.modelo = modelo;
        this.fabricante = fabricante;
        this.cor = cor;
        this.ano = ano;
        this.preco = preco;
    }

    public Veiculo(String chassi, String modelo, String fabricante, String cor, int ano, float preco, double desc) {
        this.chassi = chassi;
        this.modelo = modelo;
        this.fabricante = fabricante;
        this.cor = cor;
        this.ano = ano;
        this.preco = preco;
        this.desc = desc;
    }

    public double getDesc() {
        return desc;
    }

    public void setDesc(double desc) {
        this.desc = desc;
    }

    public String getChassi() {
        return chassi;
    }

    public void setChassi(String chassi) {
        this.chassi = chassi;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

}