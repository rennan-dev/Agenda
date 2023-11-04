import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TelaUsuario extends JFrame{

    private Administrador admin;
    private Gerente gerente;
    private Usuario usuario;

    public TelaUsuario(Administrador admin, Gerente gerente, Usuario usuario) {
        this.admin = admin;
        this.gerente = gerente;
        this.usuario = usuario;
    }

    public void telaMenuUsuario() {
        JFrame novaJanela = new JFrame();
        novaJanela.setLayout(null);

        // Adicionando título
        JLabel labelTitulo = new JLabel("Menu Usuário");
        labelTitulo.setBounds(50, 10, 300, 20);
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);
        Font fonteTitulo = new Font("Arial", Font.BOLD, 20);
        labelTitulo.setFont(fonteTitulo);

        novaJanela.add(labelTitulo);

        // Adicionar botões com os títulos
        String[] titulos = {
            "Solicitar Reserva",
            "Desmarcar Reserva",
            "Visualizar Reservas",
            "Sair da conta",
            "Sair da aplicação"
        };

        JTextArea areaTexto = new JTextArea();
        areaTexto.setBounds(50, 330, 300, 150);
        areaTexto.setEditable(false);
        novaJanela.add(areaTexto);

        for (int i = 0; i < titulos.length; i++) {
            JButton botao = new JButton(titulos[i]);
            botao.setBounds(50, 50 + 50 * i, 300, 30);
            novaJanela.add(botao);

            final int indice = i; // Variável final para ser acessada no ActionListener

            botao.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    switch (indice) {
                        case 0:
                            //lógica para solicitar reserva
                            novaJanela.dispose();
                            TelaUsuSolicitarRecur telaUsuSolicitarRecur = new TelaUsuSolicitarRecur(admin,gerente,usuario);
                            telaUsuSolicitarRecur.telaSolicitarRecursos();
                        break;
                        case 1:
                            //lógica para desmarcar reserva
                        break;
                        case 2:
                            //lógica para visualizar reservas
                        break;
                        case 3:
                            novaJanela.dispose();
                            TelaLogin telaLogin = new TelaLogin(admin);
                            telaLogin.janelaLogin();
                        break;
                        case 4:
                            System.exit(0); // Sair da aplicação
                        break;
                        default:
                        break;
                    }
                }
            });
        }

        novaJanela.setTitle("Agenda de Recursos");
        novaJanela.setSize(400, 600);
        novaJanela.setLocationRelativeTo(null);
        novaJanela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        novaJanela.setVisible(true);
    }
}
