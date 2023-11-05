import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TelaGerenDesin extends JFrame{

    private Administrador admin;
    private Gerente gerente;
    private JPanel contentPane;

    public TelaGerenDesin(Administrador admin, Gerente gerente) {
        this.admin = admin;
        this.gerente = gerente;
    }

    public void telaDesinterditaRecursos() {
        contentPane = new JPanel();
        contentPane.setLayout(null);
        JFrame novaJanela = new JFrame();
        novaJanela.setLayout(null);
        

        // Adicionando título
        JLabel labelTitulo = new JLabel("Desinterditar Recurso");
        labelTitulo.setBounds(50, 10, 300, 20);
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);
        Font fonteTitulo = new Font("Arial", Font.BOLD, 20);
        labelTitulo.setFont(fonteTitulo);

        novaJanela.add(labelTitulo);

        JLabel labelRecurso = new JLabel("Nome do Recurso: ");
        labelRecurso.setBounds(20, 80, 120, 20);
        
        // Cria o campo de texto para o login
        JTextField textRecurso = new JTextField();
        textRecurso.setBounds(150, 80, 140, 20);
        novaJanela.add(labelRecurso);
        novaJanela.add(textRecurso);

        // Adicionar botões com os títulos
        String[] titulos = {
            "Desinterditar",
            "Voltar",
        };

        JLabel mensagemLabel = new JLabel("");
        mensagemLabel.setBounds(100, 220, 300, 20);
        mensagemLabel.setForeground(Color.RED);
        novaJanela.add(mensagemLabel);

        int espacoEntreBotoes = 10; // Reduzi o espaço entre os botões
        int xInicial = 50; // Ajustei o valor inicial de x
        for (int i = 0; i < titulos.length; i++) {
            JButton botao = new JButton(titulos[i]);
            int x = xInicial + (i * (100 + espacoEntreBotoes));  // Ajustei a fórmula para calcular x
            int y = 120;  // Mantém a mesma coordenada y
            botao.setBounds(x+20, y, 100, 30);  // Define as novas dimensões dos botões
            
            Font fonte = botao.getFont();
            botao.setFont(new Font(fonte.getFontName(), Font.PLAIN, 10));
            novaJanela.add(botao);    

            final int indice = i; // Variável final para ser acessada no ActionListener

            botao.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    switch (indice) {
                        case 0:
                            //lógica para interditar
                            String desinter = textRecurso.getText();
                            boolean desinterditaSucesso = gerente.desinterditarRecursos(desinter);
                            
                            if(desinterditaSucesso) {
                                TelaGerente telaGerente2 = new TelaGerente(admin, gerente);
                                telaGerente2.telaMenuGerente();
                                dispose();
                            }else {
                                mensagemLabel.setText("Recurso não existe no sistema.");
                            }
                        break;
                        case 1:
                            novaJanela.dispose();
                            TelaGerente telaGerente = new TelaGerente(admin, gerente);
                            telaGerente.telaMenuGerente();
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
