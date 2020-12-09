
package Model;

/**
 *
 * @author pedro
 */
public class Venda {
    int id_fun;
    int id_cliente;
    String chassi;
    String formaPagam;
    String servAdi;
    Double desconto;
    Double valorTotal;
    String data;
    String status;

    public Venda() {
    }
    
    public int getId_fun() {
        return id_fun;
    }

    public void setId_fun(int id_fun) {
        this.id_fun = id_fun;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getChassi() {
        return chassi;
    }

    public void setChassi(String chassi) {
        this.chassi = chassi;
    }

    public String getFormaPagam() {
        return formaPagam;
    }

    public void setFormaPagam(String formaPagam) {
        this.formaPagam = formaPagam;
    }

    public String getServAdi() {
        return servAdi;
    }

    public void setServAdi(String servAdi) {
        this.servAdi = servAdi;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
    
    
}
