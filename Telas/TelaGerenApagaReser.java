import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class TelaGerenApagaReser extends JFrame {

    private Administrador admin;
    private Gerente gerente;
    private Usuario usuario;
    private ArrayList<Reserva> reservasPendentes;

    public TelaGerenApagaReser(Administrador admin, Gerente gerente, Usuario usuario, ArrayList<Reserva> reservasPendentes) {
        this.admin = admin;
        this.gerente = gerente;
        this.usuario = usuario;
        this.reservasPendentes = reservasPendentes;
    }

    public void telaGerenApagaReser() {
        JFrame novaJanela = new JFrame();
        novaJanela.setLayout(null);

        // Adicionando título
        JLabel labelTitulo = new JLabel("Desmarcar Reserva");
        labelTitulo.setBounds(50, 10, 300, 20);
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);
        Font fonteTitulo = new Font("Arial", Font.BOLD, 20);
        labelTitulo.setFont(fonteTitulo);

        novaJanela.add(labelTitulo);

        // Adicionando campo para finalidade
        JLabel labelUsuario = new JLabel("Desmarcar para usuário: ");
        labelUsuario.setBounds(20, 50, 190, 20);
        novaJanela.add(labelUsuario);

        JTextField campoUsuario = new JTextField();
        campoUsuario.setBounds(200, 50, 200, 20);
        novaJanela.add(campoUsuario);

        // Adicionando campo para finalidade
        JLabel labelReserva = new JLabel("Recurso para ser desmarcado: ");
        labelReserva.setBounds(20, 80, 190, 20);
        novaJanela.add(labelReserva);

        JTextField campoReserva = new JTextField();
        campoReserva.setBounds(200, 80, 200, 20);
        novaJanela.add(campoReserva);

        ArrayList<Reserva> reservasUsuarios = gerente.obterReservasUsuarios();
        visualizarReservasUsuarios(reservasUsuarios, novaJanela);
        
        // Adicionar botões com os títulos
        String[] titulos = {
                "Desmarcar Reserva",
                "Voltar",
                "Sair da aplicação"
        };

        for (int i = 0; i < titulos.length; i++) {
            JButton botao = new JButton(titulos[i]);
            botao.setBounds(50, 300 + 50 * i, 300, 30);
            novaJanela.add(botao);

            final int indice = i; // Variável final para ser acessada no ActionListener

            botao.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    switch (indice) {
                        case 0:
                            String usuarioReserva = campoUsuario.getText();
                            String recursoReserva = campoReserva.getText();
                            Usuario usuarioExistente = verificarUsuarioExistente(admin.getGerentes(), usuarioReserva);

                            if (usuarioExistente != null) {
                                for (Reserva reserva : gerente.getReservas()) {
                                    if (reserva.getUsuario().equals(usuarioExistente) && reserva.getRecurso().getNome().equalsIgnoreCase(recursoReserva)) {
                                        gerente.getReservas().remove(reserva);
                                        JOptionPane.showMessageDialog(null, "Reserva desmarcada com sucesso.");
                                        visualizarReservasUsuarios(gerente.obterReservasUsuarios(), novaJanela); 
                                        novaJanela.dispose();
                                        TelaGerente telaGerente = new TelaGerente(admin, gerente);
                                        telaGerente.telaMenuGerente();
                                        return; // Encerra o loop após encontrar e cancelar a reserva
                                    }
                                }
                                JOptionPane.showMessageDialog(null, "Não foi encontrada uma reserva para o usuário associada ao recurso especificado.");
                            } else {
                                JOptionPane.showMessageDialog(null, "Não foi encontrada uma reserva para o usuário associada ao recurso especificado.");
                            }
                        break;
                        case 1:
                            novaJanela.dispose();
                            TelaGerente telaGereTelaGerente = new TelaGerente(admin, gerente);
                            telaGereTelaGerente.telaMenuGerente();
                            break;
                        case 2:
                            System.exit(0); // Sair da aplicação
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
    
    public static Usuario verificarUsuarioExistente(ArrayList<Gerente> gerentes, String login) {
        for (Gerente gerente : gerentes) {
            for (Usuario usuario : gerente.getUsuarios()) {
                if (usuario.getLogin().equals(login)) {
                    return usuario; // Retorna o usuário se encontrado
                }
            }
        }
        return null; // Retorna null se o usuário não for encontrado
    }
    public void visualizarReservasUsuarios(ArrayList<Reserva> reservasUsuarios, JFrame novaJanela) {
        JTextArea areaTexto = new JTextArea();
        areaTexto.setBounds(20, 150, 360, 120);
        areaTexto.setEditable(false);
        novaJanela.add(areaTexto);
    
        if (reservasUsuarios.isEmpty()) {
            areaTexto.setText("Nenhum usuário possui reservas.");
        } else {
            for (Reserva reserva : reservasUsuarios) {
                areaTexto.append("Usuário: " + reserva.getUsuario().getLogin() +
                                 "\nRecurso: " + reserva.getRecurso().getNome() +
                                 "\nFinalidade: " + reserva.getFinalidade() +
                                 "\nData: " + reserva.getAlocacao().getData() +
                                 "\nHora inicial: " + reserva.getAlocacao().getHoraInicial() + 
                                 "\tHora inicial: " + reserva.getAlocacao().getHoraFinal() + 
                                 "\n--------------------------------------------------------\n");
            }
        }

        JScrollPane scrollPane = new JScrollPane(areaTexto);
        scrollPane.setBounds(20, 150, 360, 120); 
        novaJanela.add(scrollPane);
    
        novaJanela.revalidate();
        novaJanela.repaint();
    }
    
}
