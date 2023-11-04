import java.util.ArrayList;
import java.util.Scanner;

public class Gerente extends Usuario {

    private ArrayList<Usuario> usuarios;
    private ArrayList<Recurso> recursos = new ArrayList<>();

    public Gerente(String login, String senha) {
        super(login, senha);
        this.usuarios = new ArrayList<>();
        this.recursos = new ArrayList<>();
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

    public int gerenciaRecurso(Scanner ler) {
        int aux = 0;
        do {
            System.out.println("\nMenu gerencia recursos:");
            System.out.println("Cadastrar recurso[1]");
            System.out.println("Excluir recurso[2]");
            System.out.println("Imprimir recursos[3]");
            System.out.println("Voltar para o menu principal[4]");
            System.out.print("Sair da aplicação[5]\nR: ");
    
            String input = ler.nextLine();
    
            try {
                aux = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido.");
                continue;
            }
    
            switch(aux) {
                case 1:
                    System.out.print("Digite o nome do recurso: ");
                    String nomeRecurso = ler.nextLine();
                    ler = new Scanner(System.in);

                    System.out.print("Digite o tipo do recurso: ");
                    String tipoRecurso = ler.nextLine();

                    Recurso novoRecurso = new Recurso(nomeRecurso, tipoRecurso, false, this);
                    System.out.println("Recurso cadastrado com sucesso.");
                    gerenciaRecurso(ler);
                break;
    
                case 2:
                     System.out.println("Digite o nome do recurso para ser excluído: ");
                     String nomeRecursoExcluir = ler.nextLine();
                     ler = new Scanner(System.in);
                     //Recurso recursoExcluir = new Recurso();
                     //recursoExcluir.excluir(recursos, nomeRecursoExcluir);
                break;
                case 4: 
                    return 4;
    
                default: 
                    System.out.println("Digite uma opção válida.");
                    break;
            }
        } while (true);
    }
    

    /*public int gerenciaRecurso(Scanner ler) {
        System.out.println("\nMenu gerencia recursos:");
        System.out.println("Cadastrar recurso[1]");
        System.out.println("Excluir recurso[2]");
        System.out.println("Imprimir recursos[3]");
        System.out.println("Voltar para o menu principal[4]");
        System.out.println("Sair da aplicação[5]\nR: ");
        
        int aux=0;
        while(true) {
            String input = ler.nextLine();

            try {
                aux = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido.");
                continue;
            }

            switch(aux) {
                case 1:
                    System.out.print("Digite o nome do recurso: ");
                    String nomeRecurso = ler.nextLine();
                    ler = new Scanner(System.in);
                    
                    System.out.print("Digite o tipo do recurso: ");
                    String tipoRecurso = ler.nextLine();
                    ler = new Scanner(System.in);

                    Recurso novoRecurso = new Recurso(nomeRecurso, tipoRecurso, false, this);
                    System.out.println("Recurso cadastrado com sucesso.");
                    gerenciaRecurso(ler);    
                break;

                case 4: 
                return 4; //retorna para o menu principal

                default: 
                    System.out.println("Digite uma opção válida.");
                    gerenciaRecurso(ler);
                break;
            }
        }
    }*/
}
