
public class Reserva {
    private String finalidade;
    private Usuario usuario;
    private Alocacao alocacao;
    private Recurso recurso;
    
    public Reserva(String finalidade, Usuario usuario, Alocacao alocacao, Recurso recurso) {
        this.finalidade = finalidade;
        this.usuario = usuario;
        this.alocacao = alocacao;
        this.recurso = recurso;
    }

    public String getFinalidade() {
        return finalidade;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }
    
    public Alocacao getAlocacao() {
        return alocacao;
    }
    
    public Recurso getRecurso() {
        return recurso;
    }

    public void setFinalidade(String finalidade) {
        this.finalidade = finalidade;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public void setAlocacao(Alocacao alocacao) {
        this.alocacao = alocacao;
    }
    
    public void setRecurso(Recurso recurso) {
        this.recurso = recurso;
    }
    
}
