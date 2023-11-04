import java.util.ArrayList;

public class Administrador extends Usuario{
    
    private ArrayList<Gerente> gerentes;

    public Administrador() {
        super("admin", "12345");
        this.gerentes = new ArrayList<>();
    }

    public void testeAdicionaGerente(Gerente gerente) {
        this.gerentes.add(gerente);
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

    public boolean cadastraGerente(String login_gerente, String senha_gerente) {

        //verificando se já existe logins semelhantes
        if(login_gerente.equals("admin")) {
            System.out.println("Login já existe. Cadastro não permitido.\n");
            return false;
        }
        for(Gerente gerente : gerentes) {
            if (gerente.getLogin().equals(login_gerente)) {
                System.out.println("Login já existe. Cadastro não permitido.\n");
                return false;
            }
        }
        for(Gerente gerente : gerentes) {
            for(Usuario usuario : gerente.getUsuarios()) {
                if (usuario.getLogin().equals(login_gerente)) {
                    System.out.println("Login já existe entre os usuários associados a um gerente. Cadastro não permitido.\n");
                    return false;
                }
            }
        }

        Gerente novoGerente = new Gerente(login_gerente, senha_gerente);
        this.gerentes.add(novoGerente);
        System.out.println(novoGerente.getLogin() + " cadastrado com sucesso.\n");
        return true;
    }

    public boolean excluiGerente(String loginGerente) {
        for (int i = 0; i < gerentes.size(); i++) {
            Gerente gerente = gerentes.get(i);
            if (gerente.getLogin().equals(loginGerente)) {
                gerentes.remove(i);
                System.out.println("Gerente removido com sucesso.\n");
                return true;
            }
        }

        System.out.println("Gerente não encontrado.\n");
        return false;
    }
}
