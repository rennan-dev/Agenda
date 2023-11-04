import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class TelaGerenCadastro extends JFrame{

    private Administrador admin;
    private Gerente gerente;

    public TelaGerenCadastro(Administrador admin, Gerente gerente) {
        this.admin = admin;
        this.gerente = gerente;
    }

    public void telaCadastroGerente() {
        JFrame novaJanela = new JFrame();
        novaJanela.setLayout(null);

        // Adicionando título
        JLabel labelTitulo = new JLabel("Cadastrar Usuário");
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
        JButton botaoCriarUsuario = new JButton("Cadastrar");
        botaoCriarUsuario.setBounds(95, 180, 95, 30);
        botaoCriarUsuario.setFont(new Font("Arial", Font.PLAIN, 12)); 
        
        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.setBounds(200, 180, 95, 30); // Define a posição e tamanho do botão
        botaoVoltar.setFont(new Font("Arial", Font.PLAIN, 12)); // Altere o valor de 12 para o tamanho desejado

        JLabel mensagemLabel = new JLabel("");
        mensagemLabel.setBounds(100, 220, 300, 20);
        mensagemLabel.setForeground(Color.RED);
        novaJanela.add(mensagemLabel);

        botaoCriarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = textFieldLogin.getText();
                String senha = new String(passwordFieldSenha.getPassword());
        
                //Gerente gerente = Gerente.autenticarGerente(admin, login, senha);
                boolean cadastroSucesso = gerente.cadastraUsuario(admin.getGerentes(), login, senha);

                //if (gerente != null) {
                    //boolean cadastroSucesso = gerente.cadastraUsuario(admin.getGerentes(), login, senha);
        
                    if (cadastroSucesso) {
                        novaJanela.dispose();
                        TelaGerente telaGerente = new TelaGerente(admin, gerente);
                        telaGerente.telaMenuGerente();
                    } else {
                        mensagemLabel.setText("Usuário já existe no sistema.");
                    }
                //}// else {
                //    mensagemLabel.setText("Login e/ou senha incorretos.");
               // }
            }
        });
        
        
        

        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                novaJanela.dispose();
                TelaGerente telaGerente = new TelaGerente(admin,gerente);
                telaGerente.telaMenuGerente();
            }
        });

        novaJanela.add(labelLogin);
        novaJanela.add(textFieldLogin);
        novaJanela.add(labelSenha);
        novaJanela.add(passwordFieldSenha);
        novaJanela.add(botaoCriarUsuario);
        novaJanela.add(botaoVoltar);

        novaJanela.setTitle("Agenda de Recursos");
        novaJanela.setSize(400, 600);
        novaJanela.setLocationRelativeTo(null);
        novaJanela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        novaJanela.setVisible(true);
    }
}