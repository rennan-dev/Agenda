import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TelaCadastraRecurso extends JFrame{

    private Administrador admin;
    private Gerente gerente;

    public TelaCadastraRecurso(Administrador admin, Gerente gerente) {
        this.admin = admin;
        this.gerente = gerente;
    }

    public void telaCadastraRecurso() {
        JFrame novaJanela = new JFrame();
        novaJanela.setLayout(null);

        // Adicionando título
        JLabel labelTitulo = new JLabel("Cadastrar Recurso");
        labelTitulo.setBounds(50, 10, 300, 20);
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);
        Font fonteTitulo = new Font("Arial", Font.BOLD, 20);
        labelTitulo.setFont(fonteTitulo);

        novaJanela.add(labelTitulo);

        // Define os rótulos dos botões
        JLabel labelRecurso = new JLabel("Nome do Recurso: ");
        labelRecurso.setBounds(20, 80, 100, 20);
        JLabel labelTipo = new JLabel("Tipo do Recurso: ");
        labelTipo.setBounds(20, 120, 100, 20);
    
        // Cria o campo de texto para o login
        JTextField textRecurso = new JTextField();
        textRecurso.setBounds(160, 80, 140, 20);
        JTextField textTipo = new JTextField();
        textTipo.setBounds(160, 120, 140, 20);

        // Adiciona um botão de login
        JButton botaoCadastraRecurso = new JButton("Cadastrar");
        botaoCadastraRecurso.setBounds(95, 180, 95, 30);
        botaoCadastraRecurso.setFont(new Font("Arial", Font.PLAIN, 12)); 
        
        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.setBounds(200, 180, 95, 30); // Define a posição e tamanho do botão
        botaoVoltar.setFont(new Font("Arial", Font.PLAIN, 12)); // Altere o valor de 12 para o tamanho desejado

        JLabel mensagemLabel = new JLabel("");
        mensagemLabel.setBounds(100, 220, 300, 20);
        mensagemLabel.setForeground(Color.RED);
        novaJanela.add(mensagemLabel);

        botaoCadastraRecurso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String recurso = textRecurso.getText();
                String tipo = textTipo.getText();

                boolean cadastroSucesso = gerente.cadastraRecurso(recurso, tipo);

                if(cadastroSucesso) {
                    novaJanela.dispose();
                    TelaGerente telaGerente = new TelaGerente(admin, gerente);
                    telaGerente.telaMenuGerente();
                }else {
                    mensagemLabel.setText("Recurso já existe no sistema.");
                }
            }
        });

        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                novaJanela.dispose();
                TelaGerente telaGerente = new TelaGerente(admin, gerente);
                telaGerente.telaMenuGerente();
            }
        });

        novaJanela.add(labelRecurso);
        novaJanela.add(textRecurso);
        novaJanela.add(labelTipo);
        novaJanela.add(textTipo);
        novaJanela.add(botaoCadastraRecurso);
        novaJanela.add(botaoVoltar);

        novaJanela.setTitle("Agenda de Recursos");
        novaJanela.setSize(400, 600);
        novaJanela.setLocationRelativeTo(null);
        novaJanela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        novaJanela.setVisible(true);
    }
}