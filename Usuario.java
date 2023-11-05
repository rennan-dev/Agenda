import java.util.ArrayList;

import javax.swing.JTextArea;

public class Usuario {
    private String login;
    private String senha;
    private String email;
    private int sala;
    private String ramal;
    private int telefone;
    private Gerente gerenteCriador;
    private ArrayList<Recurso> recursos = new ArrayList<>();

    public Usuario(String login, String senha, String email, int sala, String ramal, int telefone) {
        this.login = login;
        this.senha = senha;
        this.email = email;
        this.sala = sala;
        this.ramal = ramal;
        this.telefone = telefone;
    }

    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Usuario(String login, String senha, Gerente gerenteCriador) {
        this.login = login;
        this.senha = senha;
        this.gerenteCriador = gerenteCriador;
    }

    public Reserva solicitarReserva(String finalidade, Alocacao alocacao, Recurso recurso, boolean reservado) {
        Reserva reserva = new Reserva(finalidade, this, alocacao, recurso, reservado);
        return reserva;
    }
    
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
    public Gerente getGerenteCriador() {
        return gerenteCriador;
    }
    public void setGerenteCriador(Gerente gerenteCriador) {
        this.gerenteCriador = gerenteCriador;
    }

    public boolean autenticarUsuario(String login, String senha) {
        return login.equals(this.getLogin()) && senha.equals(this.getSenha());
    }
         
    public static Usuario autenticarUsuario(ArrayList<Gerente> gerentes, String login, String senha) {
        for (Gerente gerente : gerentes) {
            for (Usuario usuario : gerente.getUsuarios()) {
                if (usuario.autenticarUsuario(login, senha)) {
                    return usuario;
                }
            }
        }
        return null;
    }

    // Adicione um recurso ao usuário
    public void adicionarRecurso(Recurso recurso) {
        recursos.add(recurso);
    }

    // Remova um recurso do usuário
    public void removerRecurso(Recurso recurso) {
        recursos.remove(recurso);
    }

    // Obtenha a lista de recursos associados ao usuário
    public ArrayList<Recurso> getRecursos() {
        return recursos;
    }

    // Método para acessar os recursos de um gerente
    public void acessarRecursos(Gerente gerente, JTextArea areaTexto) {
        ArrayList<Recurso> recursos = gerente.getRecursos();

        for (Recurso recurso : recursos) {
            String detalhesRecurso = "Nome: " + recurso.getNome() +
                    ", Tipo: " + recurso.getTipo() +
                    ", Disponível: " + (!recurso.isInterditado() ? "Sim" : "Não") + "\n";

            areaTexto.append(detalhesRecurso);
        }
    }
    public ArrayList<Reserva> getReservasSolicitadas(ArrayList<Reserva> reservasPendentes) {
        ArrayList<Reserva> reservasSolicitadas = new ArrayList<>();
        for (Reserva reserva : reservasPendentes) {
            if (reserva.getUsuario().equals(this)) {
                reservasSolicitadas.add(reserva);
                System.out.println("Finalidade: " + reserva.getFinalidade() +
                                   ", Alocação: " + reserva.getAlocacao().getHoraInicial());
            }
        }
        return reservasSolicitadas;
    }
}
