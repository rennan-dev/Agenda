import java.util.*;

public class AppAgendaRecurso {
    public static void main(String[] args) {
        Administrador admin = new Administrador();

        Scanner ler = new Scanner(System.in);
        int aux = 0; // verifica o fim do programa

        //login
        String login, senha;

        while(aux != 1) {
            System.out.print("Login: ");
            login = ler.nextLine();
            ler = new Scanner(System.in);
            System.out.print("Senha: ");
            senha = ler.nextLine();
            ler = new Scanner(System.in);
            if(admin.autenticarAdmin(login,senha)) {
                System.out.println("Bem vindo admin! Essas são suas funcionalidades:");
                menuAdmin(admin,ler);
            } else {
                System.out.println("Login e senha incorretos");
            }
        }
    }

    public static void menuAdmin(Administrador admin, Scanner ler) {
        System.out.println("\n\nO que voce deseja fazer?");
        System.out.println("Cadastrar gerente[1]");
        System.out.println("Excluir gerente[2]");
        System.out.println("Imprimir gerentes[3]");
        System.out.println("Sair da conta[4]");
        System.out.print("Sair da aplicação[5]\nR: ");
        
        int res;
        while(true) {
            res = ler.nextInt();
            ler = new Scanner(System.in);
            switch(res) {
                case 1: 
                    admin.cadastraGerente(ler);
                    menuAdmin(admin, ler);
                break;

                case 2:
                    System.out.println("\nDigite o login do gerente que deseja excluir: ");
                    String loginGerente = ler.nextLine();
                    admin.excluiGerente(loginGerente);
                    menuAdmin(admin, ler);
                break;

                case 3: admin.listarGerentes(); menuAdmin(admin, ler);
                break;

                case 4:
                    int aux2=0;
                    while(aux2==0) {
                        //leitura do login e senha
                        System.out.print("\nLogin: ");
                        String login = ler.nextLine();
                        ler = new Scanner(System.in);
                        System.out.print("Senha: ");
                        String senha = ler.nextLine();
                        ler = new Scanner(System.in);

                        if(admin.autenticarAdmin(login,senha)) {
                            System.out.println("Bem vindo admin!");
                            menuAdmin(admin,ler);
                            aux2=1;
                        } else {
                            Gerente gerente = Gerente.autenticarGerente(admin, login, senha);
                            if (gerente != null) {
                                System.out.println("Bem vindo Gerente!");
                                menuGerente(admin,gerente,ler);
                                aux2=1;
                            } else {
                                System.out.println("\nLogin e senha incorretos");
                            }
                        }
                    } 

                case 5: System.exit(0);
                break;

                default: 
                    System.out.println("\nEscolha uma opção válida.\n"); 
                    menuAdmin(admin, ler);
                break;
            }
        }
    }

    public static void menuGerente(Administrador admin, Gerente gerente, Scanner ler) {
        System.out.println("\n\nO que voce deseja fazer?");
        System.out.println("Cadastrar usuário[1]");
        System.out.println("Excluir usuário[2]");
        System.out.println("Imprimir usuários[3]");
        System.out.println("Sair da conta[4]");
        System.out.print("Sair da aplicação[5]\nR: ");
        
        int res;
        while(true) {
            res = ler.nextInt();
            ler = new Scanner(System.in);
            switch(res) {
                case 1: 
                    gerente.cadastraUsuario(ler);
                    menuGerente(admin,gerente, ler);
                break;

                case 2:
                    System.out.println("\nDigite o login do usuário que deseja excluir: ");
                    String loginUsuario = ler.nextLine();
                    gerente.excluiUsuario(loginUsuario);
                    menuGerente(admin,gerente, ler);
                break;

                case 3: 
                    gerente.listarUsuarios();
                    menuGerente(admin,gerente, ler);
                break;

                case 4:
                    boolean loginSucesso = false;
                    while (!loginSucesso) {
                        // leitura do login e senha
                        System.out.print("\nLogin: ");
                        String login = ler.nextLine();
                        System.out.print("Senha: ");
                        String senha = ler.nextLine();

                        if (admin.autenticarAdmin(login, senha)) {
                            System.out.println("Bem vindo admin!");
                            menuAdmin(admin, ler);
                            loginSucesso = true;
                        } else {
                            Gerente gerente2 = gerente.autenticarGerente(admin,login, senha);
                            if (gerente2 != null) {
                                System.out.println("Bem vindo Gerente!");
                                menuGerente(admin,gerente, ler);
                                loginSucesso = true;
                            } else {
                                System.out.println("\nLogin e senha incorretos");
                            }
                        }
                    }
                break;


                case 5: System.exit(0);
                break;

                default: 
                    System.out.println("\nEscolha uma opção válida.\n"); 
                    menuGerente(admin,gerente, ler);
                break;
            }
        }
    }
}
