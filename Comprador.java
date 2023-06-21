import java.util.Scanner;

public class Comprador {
    private String name;
    private String cpf;
    private Seat seat;
    public double total;

    public String getName() {
        return name;
    }


    public String getCpf() {
        return cpf;
    }


    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Comprador(String name, String cpf) {
        this.name = name;
        this.cpf = cpf;
        this.total = 0;
    }
    
    public double getValor() {
        return this.total;
    }
    
    
}
