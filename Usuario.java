package Java;

public class Usuario {
    private String login;
    private String senha;
    private String email;
    private int sala;
    private String ramal;
    private int telefone;
    
    
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getSala() {
        return sala;
    }
    public void setSala(int sala) {
        this.sala = sala;
    }
    public String getRamal() {
        return ramal;
    }
    public void setRamal(String ramal) {
        this.ramal = ramal;
    }
    public int getTelefone() {
        return telefone;
    }
    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }
}
