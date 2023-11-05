import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class TelaAdmin extends JFrame{

    private Administrador admin;

    public TelaAdmin(Administrador admin) {
        this.admin = admin;
    }

    public void telaMenuAdmin() {
        JFrame novaJanela = new JFrame();
        novaJanela.setLayout(null);

        // Adicionando título
        JLabel labelTitulo = new JLabel("Menu Administrador");
        labelTitulo.setBounds(50, 10, 300, 20);
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);
        Font fonteTitulo = new Font("Arial", Font.BOLD, 20);
        labelTitulo.setFont(fonteTitulo);

        novaJanela.add(labelTitulo);

        // Adicionar botões com os títulos
        String[] titulos = {
            "Cadastrar gerente",
            "Excluir gerente",
            "Imprimir gerentes",
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
                            novaJanela.dispose();
                            TelaAdminCadastro telaAdminCadastro = new TelaAdminCadastro(admin);
                            telaAdminCadastro.telaCadastroGerente();
                            break;
                        case 1:
                            novaJanela.dispose();
                            TelaAdminExcluir telaAdminExcluir = new TelaAdminExcluir(admin);
                            telaAdminExcluir.telaExcluirGerente();
                            break;
                        case 2:
                            
                            ArrayList<Gerente> gerentes = admin.getGerentes();
                            // Crie uma string formatada com a lista de gerentes
                            StringBuilder textoGerentes = new StringBuilder("Lista de Gerentes:\n");
                            for (Gerente gerente : gerentes) {
                                textoGerentes.append("Login: ").append(gerente.getLogin()).append(", Senha: ").append(gerente.getSenha()).append("\n");
                            }
                            admin.listarGerentes();
                            // Defina o texto do JTextArea
                            areaTexto.setVisible(true);
                            areaTexto.setText(textoGerentes.toString());
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
