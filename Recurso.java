import java.util.ArrayList;

public class Recurso {
    private String nome;
    private String tipo;
    private boolean interditado;
    private Gerente gerenteCriador;
    private ArrayList<Reserva> reservas = new ArrayList<>();

    public Recurso(String nome, String tipo, boolean interditado, Gerente gerenteCriador) {
        this.nome = nome;
        this.tipo = tipo;
        this.interditado = interditado;
        this.gerenteCriador = gerenteCriador;
        this.reservas = new ArrayList<>();
    }

    public Recurso(String nome, String tipo, boolean interditado) {
        this.nome = nome;
        this.tipo = tipo;
        this.interditado = interditado;
    }

    public ArrayList<Reserva> getReservas() {
        return reservas;
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
    public Gerente getGerenteCriador() {
        return gerenteCriador;
    }
    public void setGerenteCriador(Gerente gerenteCriador) {
        this.gerenteCriador = gerenteCriador;
    }  
    
 
    public void cadastrar(String nome, String tipo) {
        this.nome = nome;
        this.tipo = tipo;
        this.interditado = false;
    }

    public void excluir(ArrayList<Recurso> listaRecursos, String nomeRecurso) {
        for (int i = 0; i < listaRecursos.size(); i++) {
            Recurso recurso = listaRecursos.get(i);
            if (recurso.getNome().equals(nomeRecurso)) {
                listaRecursos.remove(i);
                System.out.println("Recurso removido com sucesso.\n");
                return;
            }
        }
        System.out.println("Recurso não encontrado.\n");
    }


    public void interditar() {
        this.interditado = true;    
    } 

    public void desinterditar() {
        this.interditado = false;    
    }

    public void consultar(Gerente gerente) {
        if (gerente.equals(gerenteCriador)) {
            System.out.println("Nome: " + nome);
            System.out.println("Tipo: " + tipo);
            System.out.println("Interditado: " + interditado);
        } else {
            System.out.println("Você não tem permissão para consultar este recurso.");
        }
    }

    public void alterarGerente() {

    }

    public void verificarHorarioDisponivel() {

    }

    public void marcarReserva(String finalidade, Usuario usuario, Alocacao alocacao, boolean reservado) {
        Reserva novaReserva = new Reserva(finalidade, usuario, alocacao, this, reservado);
        reservas.add(novaReserva);
    }

    public void desmarcarReserva(Reserva reserva) {
        reservas.remove(reserva);
    }

    public void notificarUsuario() {

    }

    public void listarReservas(String usuario, Usuario usuarioExistente, Gerente gerente) {

        System.out.println("\nLista de Reservas do Recurso " + this.getNome());
            for (Reserva reserva : reservas) {
                if(gerente.getLogin().equals(usuario)) {
                    System.out.println("Finalidade: " + reserva.getFinalidade() +
                                    ", Alocação: " + reserva.getAlocacao().getHoraInicial());
                }
            }
            
    }
}