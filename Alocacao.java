<<<<<<< HEAD

public class Alocacao {
    private String data;
    private int horaInicial;
    private int horaFinal;
    
    public Alocacao(String data, int horaInicial, int horaFinal) {
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
    public int getHoraInicial() {
        return horaInicial;
    }
    public void setHoraInicial(int horaInicial) {
        this.horaInicial = horaInicial;
    }
    public int getHoraFinal() {
        return horaFinal;
    }
    public void setHoraFinal(int horaFinal) {
        this.horaFinal = horaFinal;
    }

}
=======
package Agenda.Java;

public class Alocacao extends Reserva {
    private String data;
    private int horaInicial;
    private int horaFinal;
       
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
    public int getHoraInicial() {
        return horaInicial;
    }
    public void setHoraInicial(int horaInicial) {
        this.horaInicial = horaInicial;
    }
    public int getHoraFinal() {
        return horaFinal;
    }
    public void setHoraFinal(int horaFinal) {
        this.horaFinal = horaFinal;
    }

}
>>>>>>> da00b5c971063b806408fe25d14c8f80581f9253
