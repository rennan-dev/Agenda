import java.util.ArrayList;

public class Gerente extends Usuario {

    private ArrayList<Usuario> usuarios;
    private ArrayList<Recurso> recursos = new ArrayList<>();
    private ArrayList<Reserva> reserva = new ArrayList<>();

    public Gerente(String login, String senha) {
        super(login, senha);
        this.usuarios = new ArrayList<>();
        this.recursos = new ArrayList<>();
        this.reserva = new ArrayList<>();
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }
    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    public ArrayList<Recurso> getRecursos() {
        return recursos;
    }
    public void setRecursos(ArrayList<Recurso> recursos) {
        this.recursos = recursos;
    }
    public ArrayList<Reserva> getReservas() {
        return reserva;
    }
    public void setReservas(ArrayList<Reserva> reservas) {
        this.reserva = reservas;
    }

    public void listarUsuarios() {
        System.out.println("\nLista de Usuários criados por " + this.getLogin() + ":");
        for (Usuario usuario : usuarios) {
            if (usuario.getGerenteCriador().equals(this)) {
                System.out.println("Login: " + usuario.getLogin() + ", Senha: " + usuario.getSenha());
            }
        }
    }

    public boolean cadastraUsuario(ArrayList<Gerente> gerentes, String login_usuario, String senha_usuario) {

        //verificando se já existe logins semelhantes
        if(login_usuario.equals("admin")) {
            System.out.println("Login já existe. Cadastro não permitido.");
            return false;
        }
        for(Usuario usuario: usuarios) {
            if(usuario.getLogin().equals(login_usuario)) {
                System.out.println("Login já existe. Cadastro não permitido.");
                return false;
            }
        }
        for(Gerente gerente : gerentes) {
            if(gerente.getLogin().equals(login_usuario)) {
                System.out.println("Login já existe. Cadastro não permitido.");
                return false;
            }
        }

        Usuario novoUsuario = new Usuario(login_usuario, senha_usuario, this);
        this.usuarios.add(novoUsuario);

        System.out.println("Cadastrado com sucesso.\n");
        return true;
    }

    public boolean excluiUsuario(String loginUsuario) {
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario usuario = usuarios.get(i);
            if (usuario.getLogin().equals(loginUsuario) && usuario.getGerenteCriador().equals(this)) {
                usuarios.remove(i);
                System.out.println("Usuário removido com sucesso.\n");
                return true;
            }
        }

        System.out.println("Usuário não encontrado ou não criado por este gerente.\n");
        return false;
    }

    public boolean autenticarGerente(String login, String senha) {
        return login.equals(this.getLogin()) && senha.equals(this.getSenha());
    }

    public static Gerente autenticarGerente(Administrador admin, String login, String senha) {

        for (Gerente gerente : admin.getGerentes()) {
            if (gerente.autenticarGerente(login, senha)) {
                return gerente;
            }
        }

        return null;
    }

    public boolean cadastraRecurso(String nome, String tipo) {
        for (Recurso recurso : recursos) {
            if (recurso.getNome().equals(nome)) {
                System.out.println("Já existe um recurso com esse nome.");
                return false; // Sai do método se já existir um recurso com o mesmo nome
            }
        }

        // Se não existe, cria e cadastra o novo recurso
        Recurso novoRecurso = new Recurso(nome, tipo, false);
        recursos.add(novoRecurso);
        System.out.println("Recurso cadastrado com sucesso.");
        return true;
    }
    
    public boolean interditarRecursos(String interdita) {
        for (Recurso recurso : recursos) {
            if (recurso.getNome().equals(interdita) && !recurso.isInterditado()) {
                recurso.interditar();
                System.out.println("Recurso " + recurso.getNome() + " interditado com sucesso.");
                return true;
            }
        }
        return false;
    }

    public boolean desinterditarRecursos(String desinterdita) {
        for (Recurso recurso : recursos) {
            if (recurso.getNome().equals(desinterdita) && recurso.isInterditado()) {
                recurso.desinterditar();
                System.out.println("Recurso " + recurso.getNome() + " desinterditado com sucesso.");
                return true;
            }
        }
        return false;
    }

    public boolean excluirRecurso(String nomeRecurso) {
        for (int i = 0; i < recursos.size(); i++) {
            Recurso recurso = recursos.get(i);
            if (recurso.getNome().equals(nomeRecurso)) {
                recursos.remove(i);
                System.out.println("Recurso removido com sucesso.\n");
                return true;
            }
        }
        System.out.println("Recurso não encontrado.\n");
        return false;
    }
    
    public boolean criarReserva(String finalidade, Usuario usuario, Alocacao alocacao, Recurso recurso) {
        // Verificar se o recurso está disponível para reserva
        if (recurso.isInterditado()) {
            System.out.println("O recurso está interditado e não pode ser reservado.");
            return false;
        }

        // Verificar se o recurso já está reservado
        for (Reserva reservaExistente : this.getReservas()) {
            if (reservaExistente.getRecurso().equals(recurso) && reservaExistente.getReservado()) {
                System.out.println("O recurso já está reservado.");
                return false;
            }
        }

        // Verificar se o usuário já possui uma reserva para o mesmo recurso
        for (Reserva reservaExistente : usuario.getReservasSolicitadas(this.getReservas())) {
            if (reservaExistente.getRecurso().equals(recurso)) {
                System.out.println("O usuário já possui uma reserva para o mesmo recurso.");
                return false;
            }
        }

        // Criar uma nova reserva
        Reserva novaReserva = new Reserva(finalidade, usuario, alocacao, recurso, true);
        this.getReservas().add(novaReserva);

        System.out.println("Reserva criada com sucesso.");
        return true;
    }


    public void listarReservas() {
        System.out.println("\nLista de Reservas criadas por " + this.getLogin() + ":");
        for (Reserva r : reserva) {
            if (r.getUsuario().getGerenteCriador().equals(this)) {
                System.out.println("Finalidade: " + r.getFinalidade());
                System.out.println("Data: " + r.getAlocacao().getData());
                System.out.println("Hora Inicial: " + r.getAlocacao().getHoraInicial());
                System.out.println("Hora Final: " + r.getAlocacao().getHoraFinal());
                System.out.println("Recurso: " + r.getRecurso().getNome());
                System.out.println("---------------------");
            }
        }
    }
    
    //obter todos os usuarios com reserva: 
    public ArrayList<Reserva> obterReservasUsuarios() {
        ArrayList<Reserva> reservasUsuarios = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            reservasUsuarios.addAll(usuario.getReservasSolicitadas(reserva));
        }
        return reservasUsuarios;
    }
    
    public boolean interditarRecurso(String usuarioReserva, String recursoReserva) {
        for (Usuario usuario : this.getUsuarios()) {
            if (usuario.getLogin().equals(usuarioReserva)) {
                for (Reserva reserva : usuario.getReservasSolicitadas(this.getReservas())) {
                    if (reserva.getRecurso().getNome().equals(recursoReserva)) {
                        reserva.getRecurso().interditar();
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
}
