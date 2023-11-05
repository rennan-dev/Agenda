public class AppAgendaRecurso {
    public static void main(String[] args) {
        Administrador admin = new Administrador();
        TelaLogin telaLogin = new TelaLogin(admin);

        telaLogin.janelaLogin();

    }
}
