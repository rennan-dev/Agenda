import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TelaAdminExcluir extends JFrame{

    private Administrador admin;

    public TelaAdminExcluir(Administrador admin) {
        this.admin = admin;
    }

    public void telaExcluirGerente() {
        JFrame novaJanela = new JFrame();
        novaJanela.setLayout(null);

        // Adicionando título
        JLabel labelTitulo = new JLabel("Excluir Gerente");
        labelTitulo.setBounds(50, 10, 300, 20);
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);
        Font fonteTitulo = new Font("Arial", Font.BOLD, 20);
        labelTitulo.setFont(fonteTitulo);

        novaJanela.add(labelTitulo);

        // Define os rótulos dos botões
        JLabel labelLogin = new JLabel("Nome de login: ");
        labelLogin.setBounds(20, 80, 100, 20);
    
        // Cria o campo de texto para o login
        JTextField textFieldLogin = new JTextField();
        textFieldLogin.setBounds(130, 80, 120, 20);

        // Adiciona um botão de login
        JButton botaoExcluirGerente = new JButton("Excluir");
        botaoExcluirGerente.setBounds(95, 180, 95, 30);
        botaoExcluirGerente.setFont(new Font("Arial", Font.PLAIN, 12)); 
        
        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.setBounds(200, 180, 95, 30); // Define a posição e tamanho do botão
        botaoVoltar.setFont(new Font("Arial", Font.PLAIN, 12)); 
        
        JLabel mensagemLabel = new JLabel("");
        mensagemLabel.setBounds(100, 220, 300, 20);
        mensagemLabel.setForeground(Color.RED);
        novaJanela.add(mensagemLabel);

        botaoExcluirGerente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = textFieldLogin.getText();

                boolean exluirGer = admin.excluiGerente(login);

                if(exluirGer) {
                    admin.excluiGerente(login);
                    novaJanela.dispose();
                    TelaAdmin telaAdmin = new TelaAdmin(admin);
                    telaAdmin.telaMenuAdmin();
                }else {
                    mensagemLabel.setText("Usuário não existe no sistema.");
                }
            }
        });

        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                novaJanela.dispose();
                TelaAdmin telaAdmin = new TelaAdmin(admin);
                telaAdmin.telaMenuAdmin();
            }
        });

        novaJanela.add(labelLogin);
        novaJanela.add(textFieldLogin);
        novaJanela.add(botaoExcluirGerente);
        novaJanela.add(botaoVoltar);

        novaJanela.setTitle("Agenda de Recursos");
        novaJanela.setSize(400, 600);
        novaJanela.setLocationRelativeTo(null);
        novaJanela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        novaJanela.setVisible(true);
    }
}