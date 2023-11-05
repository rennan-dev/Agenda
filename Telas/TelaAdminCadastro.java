import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TelaAdminCadastro extends JFrame{

    private Administrador admin;

    public TelaAdminCadastro(Administrador admin) {
        this.admin = admin;
    }

    public void telaCadastroGerente() {
        JFrame novaJanela = new JFrame();
        novaJanela.setLayout(null);

        // Adicionando título
        JLabel labelTitulo = new JLabel("Cadastrar Gerente");
        labelTitulo.setBounds(50, 10, 300, 20);
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);
        Font fonteTitulo = new Font("Arial", Font.BOLD, 20);
        labelTitulo.setFont(fonteTitulo);

        novaJanela.add(labelTitulo);

        // Define os rótulos dos botões
        JLabel labelLogin = new JLabel("Nome de login: ");
        JLabel labelSenha = new JLabel("Crie uma senha: ");
        labelLogin.setBounds(20, 80, 100, 20);
        labelSenha.setBounds(20, 120, 100, 20);
    
        // Cria o campo de texto para o login
        JTextField textFieldLogin = new JTextField();
        textFieldLogin.setBounds(130, 80, 120, 20);
    
        // Cria o campo de texto para a senha
        JPasswordField passwordFieldSenha = new JPasswordField();
        passwordFieldSenha.setBounds(130, 120, 120, 20);

        // Adiciona um botão de login
        JButton botaoCriarGerente = new JButton("Cadastrar");
        botaoCriarGerente.setBounds(95, 180, 95, 30);
        botaoCriarGerente.setFont(new Font("Arial", Font.PLAIN, 12)); 
        
        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.setBounds(200, 180, 95, 30); // Define a posição e tamanho do botão
        botaoVoltar.setFont(new Font("Arial", Font.PLAIN, 12)); 

        JLabel mensagemLabel = new JLabel("");
        mensagemLabel.setBounds(100, 220, 300, 20);
        mensagemLabel.setForeground(Color.RED);
        novaJanela.add(mensagemLabel);

        JLabel mensagemLabel2 = new JLabel("");
        mensagemLabel2.setBounds(100, 240, 300, 20);
        mensagemLabel2.setForeground(Color.RED);
        novaJanela.add(mensagemLabel2);

        JLabel mensagemLabel3 = new JLabel("");
        mensagemLabel3.setBounds(100, 260, 300, 20);
        mensagemLabel3.setForeground(Color.RED);
        novaJanela.add(mensagemLabel3);

        botaoCriarGerente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = textFieldLogin.getText();
                String senha = new String(passwordFieldSenha.getPassword());

                if (login.isEmpty() || senha.isEmpty() || login.length() < 3 || senha.length() < 3 || login.contains(" ") || senha.contains(" ")) {
                    mensagemLabel.setText("Login e/ou senha inválidos.");
                    mensagemLabel2.setText("\nNão podem ser brancos, conter espaços");
                    mensagemLabel3.setText("ou ter menos de 3 digitos");
                    return; // Sai do método se os dados forem inválidos
                }

                boolean cadastroSucesso = admin.cadastraGerente(login, senha);

                if(cadastroSucesso) {
                    admin.cadastraGerente(login,senha);
                    novaJanela.dispose();
                    TelaAdmin telaAdmin = new TelaAdmin(admin);
                    telaAdmin.telaMenuAdmin();
                }else {
                    mensagemLabel.setText("Usuário já existe no sistema.");
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
        novaJanela.add(labelSenha);
        novaJanela.add(passwordFieldSenha);
        novaJanela.add(botaoCriarGerente);
        novaJanela.add(botaoVoltar);

        novaJanela.setTitle("Agenda de Recursos");
        novaJanela.setSize(400, 600);
        novaJanela.setLocationRelativeTo(null);
        novaJanela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        novaJanela.setVisible(true);
    }
}