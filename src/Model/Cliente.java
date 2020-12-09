package Model;

public class Cliente {
    private int id_cliente;
    private int cod_login;
    private String nome;
    private String email;
    private String telefone;
    private String cidade;
    private String estado;
    private String CNH;

    public Cliente() {
    }

    public Cliente(String nome, int cod_login, String email, String telefone, String cidade, String estado, String CNH) {
        this.nome = nome;
        this.cod_login = cod_login;
        this.email = email;
        this.telefone = telefone;
        this.cidade = cidade;
        this.estado = estado;
        this.CNH = CNH;
    }
    
    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getCod_login() {
        return cod_login;
    }

    public void setCod_login(int cod_login) {
        this.cod_login = cod_login;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCNH() {
        return CNH;
    }

    public void setCNH(String CNH) {
        this.CNH = CNH;
    }
    
    
}
