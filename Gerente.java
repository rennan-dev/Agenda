import java.util.ArrayList;
import java.util.Scanner;

public class Gerente extends Usuario {

    private ArrayList<Usuario> usuarios;

    public Gerente(String login, String senha) {
        super(login, senha);
        this.usuarios = new ArrayList<>();
    }

    public ArrayList<Usuario> getUsuario() {
        return usuarios;
    }

    public void setUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    //imprime todos os usuários
    public void listarUsuarios() {
        System.out.println("\nLista de Usuários:");
        for(Usuario usuario : usuarios) {
            System.out.println("Login: " + usuario.getLogin() + ", Senha: " + usuario.getSenha());
        }
    }

    public void cadastraUsuario(Scanner ler) {
        String login_usuario, senha_usuario;

        System.out.println("\nLogin usuário: ");
        login_usuario = ler.nextLine();
        ler = new Scanner(System.in);
        System.out.println("Senha usuário: ");
        senha_usuario = ler.nextLine();
        ler = new Scanner(System.in);

        Gerente novoGerente = new Gerente(login_usuario, senha_usuario);
        this.setUsuario(novoGerente);

        System.out.println("Cadastrado com sucesso.\n");
    }

    public void excluiUsuario(String loginUsuario) {
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario usuario = usuarios.get(i);
            if (usuario.getLogin().equals(loginUsuario)) {
                usuarios.remove(i);
                System.out.println("Usuário removido com sucesso.\n");
                return;
            }
        }

        System.out.println("Usuário não encontrado.\n");
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
}

