/**
 * Aircraft is a plane ticket selling program
 * Seat is a class for airplane seats objects
 * 
 * @author Gabriel Bonoto and Juliano Maia
 * @version 2023.06.23
 */

public class Seat
{
    // Object variables
    private String code;
    private String name;
    private String cpf;
    private boolean occupied;
    private double price;
    
    // Empty constructor
    public Seat()
    {
        code = "";
        name = "";
        cpf = "";
        occupied = false;
        price = 0.0;
    }

    // Constructor with seat code and price
    public Seat(String code, double price)
    {
        this.code = code;
        name = "";
        cpf = "";
        occupied = false;
        this.price = price;
    }

    // Constructor with all fields
    public Seat(String code, String name, String cpf, double price, boolean occupied)
    {
        this.code = code;
        this.name = name;
        this.cpf = cpf;
        this.price = price;
        this.occupied = occupied;
    }
    
    // Method to sell a seat object and fill the name and cpf fields, returning the price
    public double sell(String name, String cpf)
    {
        this.name = name;
        this.cpf = cpf;
        occupied = true;
        return price;
    }

    // Makes a Seat object vacant
    public void vacant()
    {
        name = "";
        cpf = "";
        occupied = false;
    }

    // Getter for the "occupied" boolean variable
    public boolean checkOccupied()
    {
        return occupied;
    }

    // Setter for "code" variable
    public void setCode(String code)
    {
        this.code = code;
    }

    public String getName()
    {
        return name;
    }

    public String getCpf()
    {
        return cpf;
    }

    public double getPrice()
    {
        return price;
    }
    
    // Prints the Seat object in CSV format
    public String print()
    {
        return code+","+name+","+cpf+","+price+","+occupied+";\n";
    }

    // Prints the Seat object for the user
    public String printTicket()
    {
        return code+"\n    Nome: "+name+", CPF: "+cpf+"\n";
    }

    // Reads CSV format into a Seat object
    public void read(String code, String name, String cpf, double Price, boolean occupied)
    {
        this.code = code;
        this.name = name;
        this.cpf = cpf;
        this.price = price;
        this.occupied = occupied;
    }
}