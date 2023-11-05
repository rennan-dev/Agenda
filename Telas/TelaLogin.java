import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TelaLogin extends JFrame{

    private Administrador admin;

    public TelaLogin(Administrador admin) {
        this.admin = admin;
    }

    public void janelaLogin() {
        Container janela = getContentPane();
        setLayout(null);
    
        //Adicionando titulo
        JLabel labelTitulo = new JLabel("Agenda de Recursos");
        labelTitulo.setBounds(50,10,300,20);
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);
        Font fonteTitulo = new Font("Arial", Font.BOLD, 20);
        labelTitulo.setFont(fonteTitulo);

        // Define os rótulos dos botões
        JLabel labelLogin = new JLabel("Login: ");
        JLabel labelSenha = new JLabel("Senha: ");
        labelLogin.setBounds(50, 80, 100, 20);
        labelSenha.setBounds(50, 120, 100, 20);
    
        // Cria o campo de texto para o login
        JTextField textFieldLogin = new JTextField();
        textFieldLogin.setBounds(130, 80, 120, 20);
    
        // Cria o campo de texto para a senha
        JPasswordField passwordFieldSenha = new JPasswordField();
        passwordFieldSenha.setBounds(130, 120, 120, 20);

        // Adiciona um botão de login
        JButton botaoLogin = new JButton("Login");
        botaoLogin.setBounds(100, 180, 80, 30); // Define a posição e tamanho do botão

        JButton botaoFechar = new JButton("Fechar");
        botaoFechar.setBounds(200, 180, 80, 30); // Define a posição e tamanho do botão
        
        JLabel mensagemLabel = new JLabel("");
        mensagemLabel.setBounds(80, 220, 300, 20);
        mensagemLabel.setForeground(Color.RED);
        janela.add(mensagemLabel);

        // Adiciona um ActionListener ao botão (para lidar com o evento de clique)
        botaoLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = textFieldLogin.getText();
                String senha = new String(passwordFieldSenha.getPassword());
        
                if(admin.autenticarAdmin(login, senha)) {
                    dispose();
                    TelaAdmin telaAdmin = new TelaAdmin(admin);
                    telaAdmin.telaMenuAdmin();
                }else {
                    // O login e a senha não correspondem a um administrador
                    // Agora, verifica se são de um gerente
                    Gerente gerente = Gerente.autenticarGerente(admin, login, senha);
                    if(gerente != null) {
                        // O login e a senha correspondem a um gerente
                        dispose();
                        TelaGerente telaGerente = new TelaGerente(admin, gerente);
                        telaGerente.telaMenuGerente();
                    }else {
                        Usuario usuario = Usuario.autenticarUsuario(admin.getGerentes(), login, senha);
                        
                        if(usuario != null) {
                            gerente = usuario.getGerenteCriador();
                            dispose();
                            TelaUsuario telaUsuario = new TelaUsuario(admin,gerente,usuario);
                            telaUsuario.telaMenuUsuario();
                        }else {
                            mensagemLabel.setText("O login e a senha não estão cadastrados.");
                        }
                    }
                }
            }
        });
        

        botaoFechar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a janela
            }
        });
        

        // Adiciona os rótulos e os campos de textos na tela
        janela.add(labelTitulo);
        janela.add(labelLogin);
        janela.add(labelSenha);
        janela.add(textFieldLogin);
        janela.add(passwordFieldSenha);
        janela.add(botaoLogin);
        janela.add(botaoFechar);

        setTitle("Agenda de Recursos");
        setSize(400, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
