import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class TelaGerente extends JFrame{

    private Administrador admin;
    private Gerente gerente;
    private JPanel contentPane;


    public TelaGerente(Administrador admin, Gerente gerente) {
        this.admin = admin;
        this.gerente = gerente;
    }

    public void telaMenuGerente() {
        contentPane = new JPanel();
        contentPane.setLayout(null);
        JFrame novaJanela = new JFrame();
        novaJanela.setLayout(null);
        

        // Adicionando título
        JLabel labelTitulo = new JLabel("Menu Gerente");
        labelTitulo.setBounds(50, 10, 300, 20);
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);
        Font fonteTitulo = new Font("Arial", Font.BOLD, 20);
        labelTitulo.setFont(fonteTitulo);

        novaJanela.add(labelTitulo);

        // Adicionar botões com os títulos
        String[] titulos = {
            "Cadastrar usuário",
            "Excluir usuário",
            "Imprimir usuário(s)",
            "Cadastrar recurso",
            "Excluir recurso",
            "Interditar",
            "Desinterditar",
            "Consultar",
            "Notificar usuários",
            "Verificar horário disponível",
            "Marcar reserva",
            "Desmarcar reserva",
            "Sair da conta",
            "Sair da aplicação"
        };

        JTextArea areaTexto = new JTextArea();
        areaTexto.setBounds(50, 410, 300, 150);
        areaTexto.setEditable(false);
        
        JScrollPane scrollPane = new JScrollPane(areaTexto); // Adiciona a JTextArea a um JScrollPane

        scrollPane.setBounds(50, 410, 300, 150); // Define as dimensões do JScrollPane
    
        novaJanela.add(scrollPane); // Adiciona o JScrollPane à novaJanela
        

        for (int i = 0; i < titulos.length; i++) {
            JButton botao = new JButton(titulos[i]);
            int x = 50 + (i % 2) * 165;  // Calcula a posição x (alternando entre 50 e 225)
            int y = 100 + (i / 2) * 50;  // Calcula a posição y (a cada 2 botões, aumenta em 50)
            botao.setBounds(x, y-30, 135, 30);  // Define as novas dimensões dos botões
            
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
                            TelaGerenCadastro telaGerenCadastro = new TelaGerenCadastro(admin,gerente);
                            telaGerenCadastro.telaCadastroGerente();
                        break;
                        case 1:
                            // Lógica para excluir gerente
                            novaJanela.dispose();
                            TelaGerenExcluir telaGerenExcluir = new TelaGerenExcluir(admin,gerente);
                            telaGerenExcluir.telaExcluirGerente();
                        break;
                        case 2:
                            // Obtenha a lista de usuários associados ao gerente atual
                            ArrayList<Usuario> usuariosAssociados = gerente.getUsuarios();
                        
                            // Crie uma string formatada com a lista de usuários
                            StringBuilder textoUsuarios = new StringBuilder("Lista de Usuários cadastrados pelo gerente " + gerente.getLogin() + ":\n");
                            for (Usuario usuario : usuariosAssociados) {
                                textoUsuarios.append("Login: ").append(usuario.getLogin()).append(", Senha: ").append(usuario.getSenha()).append("\n");
                            }
                        
                            // Defina o texto do JTextArea
                            areaTexto.setVisible(true);
                            areaTexto.setText(textoUsuarios.toString());
                        break;
                        case 3:
                            //lógica para cadastrar recursos
                            novaJanela.dispose();
                            TelaCadastraRecurso telaCadastraRecurso = new TelaCadastraRecurso(admin, gerente);
                            telaCadastraRecurso.telaCadastraRecurso();
                        break;
                        case 4:
                            novaJanela.dispose();
                            TelaExcluiRecurso telaExcluiRecurso = new TelaExcluiRecurso(admin,gerente);
                            telaExcluiRecurso.telaExcluiRecurso();
                        break;
                        case 5:
                            novaJanela.dispose();
                            TelaGerenInter telaGerenInter = new TelaGerenInter(admin, gerente);
                            telaGerenInter.telaInterditaRecursos();
                        break;
                        case 6:
                            novaJanela.dispose();
                            TelaGerenDesin telaGerenDesin = new TelaGerenDesin(admin, gerente);
                            telaGerenDesin.telaDesinterditaRecursos();
                        break;
                        case 7:
                            novaJanela.dispose();
                            TelaGerenConsulta telaGerenConsulta = new TelaGerenConsulta(admin, gerente);
                            telaGerenConsulta.telaConsultaRecursos();
                        break;
                        case 9:
                            novaJanela.dispose();
                            TelaGerenHoraDisponivel telaGerenHoraDisponivel = new TelaGerenHoraDisponivel(admin, gerente);
                            telaGerenHoraDisponivel.telaHoraDisponivel();
                        break;
                        case 10:
                            novaJanela.dispose();
                            TelaGerenMarcarReserva telaGerenMarcarReserva = new TelaGerenMarcarReserva(admin, gerente, admin, gerente.getReservas());
                            telaGerenMarcarReserva.telaGerenMarcarReserva();
                        break;
                        case 11:
                            novaJanela.dispose();
                            TelaGerenApagaReser telaGerenApagaReser = new TelaGerenApagaReser(admin, gerente, admin, gerente.getReservas());
                            telaGerenApagaReser.telaGerenApagaReser();
                        break;
                        case 12:
                            novaJanela.dispose();
                            TelaLogin telaLogin = new TelaLogin(admin);
                            telaLogin.janelaLogin();
                        break;
                        case 13:
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
}
