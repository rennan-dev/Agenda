public class Gerente extends Usuario {

    private String nome_gerente;
    private String senha_gerente;

    public Gerente(String nome_gerente, String senha_gerente) {
        super(nome_gerente, senha_gerente);
        this.nome_gerente = nome_gerente;
        this.senha_gerente = senha_gerente;
    }
}
