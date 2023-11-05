
public class Alocacao {
    private String data;
    private String horaInicial;
    private String horaFinal;
    
    public Alocacao(String data, String horaInicial, String horaFinal) {
        this.data = data;
        this.horaInicial = horaInicial;
        this.horaFinal = horaFinal;
    }
       
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
    public String getHoraInicial() {
        return horaInicial;
    }
    public void setHoraInicial(String horaInicial) {
        this.horaInicial = horaInicial;
    }
    public String getHoraFinal() {
        return horaFinal;
    }
    public void setHoraFinal(String horaFinal) {
        this.horaFinal = horaFinal;
    }

}
