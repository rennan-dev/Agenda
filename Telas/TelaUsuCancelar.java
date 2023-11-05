import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class TelaUsuCancelar extends JFrame{

    private Administrador admin;
    private Gerente gerente;
    private Usuario usuario;
    private JPanel contentPane;


    public TelaUsuCancelar(Administrador admin, Gerente gerente, Usuario usuario) {
        this.admin = admin;
        this.gerente = gerente;
        this.usuario = usuario;
    }
    //cancelar reserva
    public void telaCancelar() {
        contentPane = new JPanel();
        contentPane.setLayout(null);
        JFrame novaJanela = new JFrame();
        novaJanela.setLayout(null);
        

        // Adicionando título
        JLabel labelTitulo = new JLabel("Cancelar Reserva");
        labelTitulo.setBounds(50, 10, 300, 20);
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);
        Font fonteTitulo = new Font("Arial", Font.BOLD, 20);
        labelTitulo.setFont(fonteTitulo);

        novaJanela.add(labelTitulo);
        

        JLabel labelLogin = new JLabel("Nome do Recurso: ");
        labelLogin.setBounds(50, 80, 150, 20);
        JTextField textSolicita = new JTextField();
        textSolicita.setBounds(160, 80, 140, 20);
        novaJanela.add(labelLogin);
        novaJanela.add(textSolicita);

        novaJanela.add(labelTitulo);

        JTextArea areaTexto = new JTextArea();
        areaTexto.setBounds(50, 250, 300, 250);
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

        scrollPane.setBounds(50, 250, 300, 250); // Define as dimensões do JScrollPane

        novaJanela.add(scrollPane); // Adiciona o JScrollPane à novaJanela


        // Adicionar botões com os títulos
        String[] titulos = {
            "Cancelar Recurso",
            "Voltar",
        };

        int espacoEntreBotoes = 50; // Reduzi o espaço entre os botões
        int xInicial = 50; // Ajustei o valor inicial de x
        for (int i = 0; i < titulos.length; i++) {
            JButton botao = new JButton(titulos[i]);
            int x = xInicial + (i * (100 + espacoEntreBotoes));  // Ajustei a fórmula para calcular x
            int y = 150;  // Mantém a mesma coordenada y
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
                            String nomeRecursoCancelar = textSolicita.getText();
                            cancelarReserva(usuario, nomeRecursoCancelar);
                            TelaUsuario telaUsuario2 = new TelaUsuario(admin, gerente, usuario);
                            telaUsuario2.telaMenuUsuario();
                        break;

                        case 1:
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
    
    public void cancelarReserva(Usuario usuario, String nomeRecursoCancelar) {
        for (Reserva reserva : gerente.getReservas()) {
            if (reserva.getUsuario().equals(usuario) && reserva.getRecurso().getNome().equalsIgnoreCase(nomeRecursoCancelar)) {
                reserva.setReservado(false);
                gerente.getReservas().remove(reserva);
                System.out.println("Reserva cancelada com sucesso.");
                JOptionPane.showMessageDialog(null, "Reserva cancelada com sucesso.");
                return; // Encerra o loop após encontrar e cancelar a reserva
            }
        }
        JOptionPane.showMessageDialog(null, "Não foi encontrada uma reserva para o usuário associada ao recurso especificado.");
        System.out.println("Não foi encontrada uma reserva para o usuário associada ao recurso especificado.");
    }
    
}
