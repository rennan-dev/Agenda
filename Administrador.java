import java.util.ArrayList;

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
}
