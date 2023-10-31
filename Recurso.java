import java.util.*;

public class Recurso {
    private String nome;
    private String tipo;
    private boolean interditado;

    Scanner ler = new Scanner(System.in);

    public Recurso(String nome, String tipo, boolean interditado) {
        this.nome = nome;
        this.tipo = tipo;
        this.interditado = interditado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isInterditado() {
        return interditado;
    }

    public void setInterditado(boolean interditado) {
        this.interditado = interditado;
    }
 
    public void cadastrar() {
        
    }

    public void excluir() {

    }

    public void interditar(boolean interditado) {
        if(!interditado) {
            this.interditado = true;
        }
    } 

    public void desinterditar() {
        this.interditado = false;
    }

    public void consultar() {

    } 

    public void alterarGerente() {

    }

    public void verificarHorarioDisponivel() {

    }

    public void marcarReserva() {

    }

    public void desmarcarReserva() {

    } 

    public void notificarUsuario() {

    }  
}