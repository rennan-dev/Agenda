import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TelaSolicitarRecurso extends JFrame {

    private Administrador admin;
    private Gerente gerente;
    private Usuario usuario;

    public TelaSolicitarRecurso(Administrador admin, Gerente gerente, Usuario usuario) {
        this.admin = admin;
        this.gerente = gerente;
        this.usuario = usuario;
    }

    public void telaSolicitarRecurso() {
        JFrame novaJanela = new JFrame();
        novaJanela.setLayout(null);

        // Adicionando título
        JLabel labelTitulo = new JLabel("Solicitar Recurso");
        labelTitulo.setBounds(50, 10, 300, 20);
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);
        Font fonteTitulo = new Font("Arial", Font.BOLD, 20);
        labelTitulo.setFont(fonteTitulo);

        novaJanela.add(labelTitulo);

        // Adicionando campo para finalidade
        JLabel labelFinalidade = new JLabel("Finalidade:");
        labelFinalidade.setBounds(50, 50, 100, 20);
        novaJanela.add(labelFinalidade);

        JTextField campoFinalidade = new JTextField();
        campoFinalidade.setBounds(120, 50, 230, 20);
        novaJanela.add(campoFinalidade);

        // Adicionando campo para seleção de data
        JLabel labelData = new JLabel("Data:");
        labelData.setBounds(50, 80, 100, 20);
        novaJanela.add(labelData);

        // JComboBox para o ano
        String[] anos = {"2023", "2024", "2025"}; // Substitua com os anos desejados
        JComboBox<String> comboBoxAno = new JComboBox<>(anos);
        comboBoxAno.setBounds(120, 80, 60, 20);
        novaJanela.add(comboBoxAno);

        // JComboBox para o mês
        String[] meses = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        JComboBox<String> comboBoxMes = new JComboBox<>(meses);
        comboBoxMes.setBounds(190, 80, 100, 20);
        novaJanela.add(comboBoxMes);

        // JComboBox para o dia
        int numDias = 31; // Inicialmente, definimos 31 dias
        String[] dias = getDias(numDias);
        JComboBox<String> comboBoxDia = new JComboBox<>(dias);
        comboBoxDia.setBounds(300, 80, 60, 20);
        novaJanela.add(comboBoxDia);

        // ActionListener para o JComboBox do mês
        comboBoxMes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int novoNumDias = getDiasNoMes(comboBoxMes.getSelectedIndex() + 1); // +1 porque os meses começam do índice 0
                String[] novosDias = getDias(novoNumDias);
                comboBoxDia.setModel(new DefaultComboBoxModel<>(novosDias));
            }
        });

        // Adicionar botões com os títulos
        String[] titulos = {
                "Solicitar",
                "Cancelar",
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
                            // Lógica para lidar com o botão "Solicitar"
                            break;
                        case 1:
                            novaJanela.dispose();
                            TelaUsuario telaUsuario = new TelaUsuario(admin, gerente, usuario);
                            telaUsuario.telaMenuUsuario();
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

    private int getDiasNoMes(int mes) {
        switch (mes) {
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                return 28; // Considerando que não é ano bissexto
            default:
                return 31;
        }
    }

    private String[] getDias(int numDias) {
        String[] dias = new String[numDias];
        for (int i = 1; i <= numDias; i++) {
            dias[i-1] = String.valueOf(i);
        }
        return dias;
    }
}
