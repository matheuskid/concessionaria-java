
package Model;


public class Funcionario {

    private int id_func;
    private String nome;
    private String email;
    private String telefone;
    private String CPF;
    private String rg;
    private String cidade;
    private String estado;
    private String login;
    private String password;
    private String funcao;
    private String numeroPIS;

        public Funcionario() {
        }

        public Funcionario(int id_fun, String nome, String email, String telefone, String CPF, String rg, String cidade, String estado, String login, String password, String funcao, String numeroPIS) {
            this.id_func = id_fun;
            this.nome = nome;
            this.email = email;
            this.telefone = telefone;
            this.CPF = CPF;
            this.rg = rg;
            this.cidade = cidade;
            this.estado = estado;
            this.login = login;
            this.password = password;
            this.funcao = funcao;
            this.numeroPIS = numeroPIS;
        }

    public Funcionario(String nome, String email, String telefone, String CPF, String rg, String cidade, String estado, String login, String password, String funcao, String numeroPIS) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.CPF = CPF;
        this.rg = rg;
        this.cidade = cidade;
        this.estado = estado;
        this.login = login;
        this.password = password;
        this.funcao = funcao;
        this.numeroPIS = numeroPIS;
    }
        
        
        public int getId_func() {
            return id_func;
        }

        public void setId_func(int id_func) {
            this.id_func = id_func;
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

        public String getCPF() {
            return CPF;
        }

        public void setCPF(String CPF) {
            this.CPF = CPF;
        }

        public String getRg() {
            return rg;
        }

        public void setRg(String rg) {
            this.rg = rg;
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

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getFuncao() {
            return funcao;
        }

        public void setFuncao(String funcao) {
            this.funcao = funcao;
        }

        public String getNumeroPIS() {
            return numeroPIS;
        }

        public void setNumeroPIS(String numeroPIS) {
            this.numeroPIS = numeroPIS;
        }

    }