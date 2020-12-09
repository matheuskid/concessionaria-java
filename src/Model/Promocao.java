
package Model;





public class Promocao {

    private int id_prom;
    private String id_veiculo;
    private double desconto;
    private String data_exp;

    public Promocao() {
    }

    public Promocao(int id_prom, String id_veiculo, Double desconto, String data_exp) {
        this.desconto = desconto;
        this.data_exp = data_exp;
        this.id_prom = id_prom;
        this.id_veiculo = id_veiculo;
    }
    public int getId_prom() {
        return id_prom;
    }

    public void setId_prom(int id_prom) {
        this.id_prom = id_prom;
    }

    public String getId_veiculo() {
        return id_veiculo;
    }

    public void setId_veiculo(String id_veiculo) {
        this.id_veiculo = id_veiculo;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public String getData_exp() {
        return data_exp;
    }

    public void setData_exp(String data_exp) {
        this.data_exp = data_exp;
    }


}