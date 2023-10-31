import java.util.*;

public class AppAgendaRecurso {
    public static void main(String[] args) {
        Administrador admin = new Administrador();

        Scanner ler = new Scanner(System.in);
        int aux = 0; // verifica o fim do programa

        while(aux != 1) {
            autenticarAdmin(admin, ler);
        }
    }

    public static void autenticarAdmin(Administrador admin, Scanner ler) {
        System.out.print("Login: ");
        String verifica_login = ler.nextLine();
        System.out.print("Senha: ");
        String verifica_senha = ler.nextLine();

        if(verifica_login.equals(admin.getLogin()) && verifica_senha.equals(admin.getSenha())) {
            System.out.println("Bem vindo admin!");
        }else {
            System.out.println("Login e senha incorretos");
        }
    }
}
