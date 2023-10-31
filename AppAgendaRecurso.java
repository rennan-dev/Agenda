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
            menuAdmin(admin,ler);
        }else {
            System.out.println("Login e senha incorretos");
        }
    }

    public static void menuAdmin(Administrador admin, Scanner ler) {
        System.out.println("\nO que voce deseja fazer?");
        System.out.println("Cadastrar gerente[1]");
        System.out.println("Excluir gerente[2]");
        System.out.println("Imprimir gerentes[3]");
        System.out.println("Sair da conta[4]");
        
        int res;
        while(true) {
            res = ler.nextInt();

            switch(res) {
                case 1: cadastraGerente(admin,ler);
                break;

                //case 2: excluiGerente(ler);
                //break;

                case 3: admin.listarGerentes(); menuAdmin(admin, ler);
                break;

                case 4: autenticarAdmin(admin, ler); 
                break;

                default: System.out.println("\nEscolha uma opção válida.\n");
                break;
            }
        }
    }

    public static void cadastraGerente(Administrador admin, Scanner ler) {
        String login_gerente, senha_gerente;

        System.out.println("\nLogin gerente: ");
        login_gerente = ler.nextLine();
        ler.nextLine(); //consumir a nova linha restante
        System.out.println("Senha gerente: ");
        senha_gerente = ler.nextLine();

        Gerente novoGerente = new Gerente(login_gerente, senha_gerente);
        admin.setGerente(novoGerente);

        System.out.println("Cadastrado com sucesso.\n");

        menuAdmin(admin, ler);
    }
}
