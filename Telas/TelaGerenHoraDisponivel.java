import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TelaGerenHoraDisponivel extends JFrame{

    private Administrador admin;
    private Gerente gerente;
    private JPanel contentPane;

    public TelaGerenHoraDisponivel(Administrador admin, Gerente gerente) {
        this.admin = admin;
        this.gerente = gerente;
    }

    public void telaHoraDisponivel() {
        contentPane = new JPanel();
        contentPane.setLayout(null);
        JFrame novaJanela = new JFrame();
        novaJanela.setLayout(null);
        

        // Adicionando título
        JLabel labelTitulo = new JLabel("Hora disponível");
        labelTitulo.setBounds(50, 10, 300, 20);
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);
        Font fonteTitulo = new Font("Arial", Font.BOLD, 20);
        labelTitulo.setFont(fonteTitulo);

        novaJanela.add(labelTitulo);

        JTextArea areaTexto = new JTextArea();
        areaTexto.setBounds(50, 100, 300, 250);
        areaTexto.setEditable(false);

        

        // Adicionar botões com os títulos
        String[] titulos = {
            "Voltar",
            "Sair da conta",
            "Sair da aplicação"
        };        
        
        int espacoEntreBotoes = 10; // Reduzi o espaço entre os botões
        int xInicial = 50; // Ajustei o valor inicial de x
        for (int i = 0; i < titulos.length; i++) {
            JButton botao = new JButton(titulos[i]);
            int x = xInicial + (i * (100 + espacoEntreBotoes));  // Ajustei a fórmula para calcular x
            int y = 400;  // Mantém a mesma coordenada y
            botao.setBounds(x-10, y, 100, 30);  // Define as novas dimensões dos botões
            
            Font fonte = botao.getFont();
            botao.setFont(new Font(fonte.getFontName(), Font.PLAIN, 10));
            novaJanela.add(botao);    

            final int indice = i; // Variável final para ser acessada no ActionListener

            botao.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    switch (indice) {
                        case 0:
                            novaJanela.dispose();
                            TelaGerente telaGerente = new TelaGerente(admin, gerente);
                            telaGerente.telaMenuGerente();
                        break;
                        case 1:
                            // Lógica para excluir gerente
                            novaJanela.dispose();
                            TelaLogin telaLogin = new TelaLogin(admin);
                            telaLogin.janelaLogin();
                        break;
                        case 2:
                            System.exit(0);
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
