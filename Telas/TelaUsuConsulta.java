import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TelaUsuConsulta extends JFrame{

    private Administrador admin;
    private Gerente gerente;
    private Usuario usuario;
    private JPanel contentPane;


    public TelaUsuConsulta(Administrador admin, Gerente gerente, Usuario usuario) {
        this.admin = admin;
        this.gerente = gerente;
        this.usuario = usuario;
    }

    public void telaConsulta() {
        contentPane = new JPanel();
        contentPane.setLayout(null);
        JFrame novaJanela = new JFrame();
        novaJanela.setLayout(null);
        

        JLabel labelTitulo = new JLabel("Consultar Reservas");
        labelTitulo.setBounds(50, 10, 300, 20);
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);
        Font fonteTitulo = new Font("Arial", Font.BOLD, 20);
        labelTitulo.setFont(fonteTitulo);

        novaJanela.add(labelTitulo);

        JTextArea areaTexto = new JTextArea();
        areaTexto.setBounds(50, 50, 300, 250);
        areaTexto.setEditable(false);
        novaJanela.add(areaTexto);

        for (Reserva reserva : gerente.getReservas()) {
            areaTexto.append("Finalidade: " + reserva.getFinalidade() + "\n");
            areaTexto.append("Data: " + reserva.getAlocacao().getData() + "\n");
            areaTexto.append("Hora Inicial: " + reserva.getAlocacao().getHoraInicial() + "\n");
            areaTexto.append("Hora Final: " + reserva.getAlocacao().getHoraFinal() + "\n");
            areaTexto.append("Recurso: " + reserva.getRecurso().getNome() + "\n");
            areaTexto.append("---------------------\n");
        }
        
        // Adicionar botões com os títulos
        String[] titulos = {
            "Voltar",
        };

        int espacoEntreBotoes = 50; // Reduzi o espaço entre os botões
        int xInicial = 140; // Ajustei o valor inicial de x
        for (int i = 0; i < titulos.length; i++) {
            JButton botao = new JButton(titulos[i]);
            int x = xInicial + (i * (100 + espacoEntreBotoes));  // Ajustei a fórmula para calcular x
            int y = 320;  // Mantém a mesma coordenada y
            botao.setBounds(x, y, 120, 30);  // Define as novas dimensões dos botões
            
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
                           TelaUsuario telaUsuario = new TelaUsuario(admin, gerente, usuario);
                           telaUsuario.telaMenuUsuario();
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
