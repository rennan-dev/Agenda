import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class TelaGerenConsulta extends JFrame{

    private Administrador admin;
    private Gerente gerente;
    private JPanel contentPane;

    public TelaGerenConsulta(Administrador admin, Gerente gerente) {
        this.admin = admin;
        this.gerente = gerente;
    }

    public void telaConsultaRecursos() {
        contentPane = new JPanel();
        contentPane.setLayout(null);
        JFrame novaJanela = new JFrame();
        novaJanela.setLayout(null);
        

        // Adicionando título
        JLabel labelTitulo = new JLabel("Consultar Recursos");
        labelTitulo.setBounds(50, 10, 300, 20);
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);
        Font fonteTitulo = new Font("Arial", Font.BOLD, 20);
        labelTitulo.setFont(fonteTitulo);

        novaJanela.add(labelTitulo);

        // Adicionar botões com os títulos
        String[] titulos = {
            "Voltar",
            "Sair da conta",
            "Sair da aplicação"
        };


        JTextArea areaTexto = new JTextArea();
        areaTexto.setBounds(50, 100, 300, 250);
        areaTexto.setEditable(false);
        novaJanela.add(areaTexto);

        ArrayList<Recurso> recursos = gerente.getRecursos();

        for (Recurso recurso : recursos) {
            String detalhesRecurso = "Nome: " + recurso.getNome() + "\n" +
                                     "Tipo: " + recurso.getTipo() + "\n" +
                                     "Disponível: " + (!recurso.isInterditado() ? "Sim" : "Não") + "\n\n";
    
            areaTexto.append(detalhesRecurso);
        }
        JScrollPane scrollPane = new JScrollPane(areaTexto); // Adiciona a JTextArea a um JScrollPane

        scrollPane.setBounds(50, 100, 300, 250); // Define as dimensões do JScrollPane

        novaJanela.add(scrollPane); // Adiciona o JScrollPane à novaJanela

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
