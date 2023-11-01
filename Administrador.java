import java.util.ArrayList;
import java.util.Scanner;

public class Administrador extends Usuario{
    
    private ArrayList<Gerente> gerentes;

    public Administrador() {
        super("admin", "12345");
        this.gerentes = new ArrayList<>();
    }

    public ArrayList<Gerente> getGerentes() {
        return gerentes;
    }

    public void setGerente(Gerente gerente) {
        gerentes.add(gerente);
    }

    //imprime todos os gerentes
    public void listarGerentes() {
        System.out.println("\nLista de Gerentes:");
        for (Gerente gerente : gerentes) {
            System.out.println("Login: " + gerente.getLogin() + ", Senha: " + gerente.getSenha());
        }
    }

    public boolean autenticarAdmin(String login, String senha) {
        return login.equals(this.getLogin()) && senha.equals(this.getSenha());
    }

    public void cadastraGerente(Scanner ler) {
        String login_gerente, senha_gerente;

        System.out.println("\nLogin gerente: ");
        login_gerente = ler.nextLine();
        ler = new Scanner(System.in);
        System.out.println("Senha gerente: ");
        senha_gerente = ler.nextLine();
        ler = new Scanner(System.in);

        Gerente novoGerente = new Gerente(login_gerente, senha_gerente);
        this.setGerente(novoGerente);

        System.out.println("Cadastrado com sucesso.\n");
    }

    public void excluiGerente(String loginGerente) {
        for (int i = 0; i < gerentes.size(); i++) {
            Gerente gerente = gerentes.get(i);
            if (gerente.getLogin().equals(loginGerente)) {
                gerentes.remove(i);
                System.out.println("Gerente removido com sucesso.\n");
                return;
            }
        }

        System.out.println("Gerente não encontrado.\n");
    }
}
