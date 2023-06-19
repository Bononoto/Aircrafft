public class Seat
{
    private String code;
    private String name;
    private String cpf;
    public boolean occupied;

    // Empty constructor
    public Seat()
    {
        code = "";
        name = "";
        cpf = "";
        occupied = false;
    }

    // Constructor with seat code
    public Seat(String code)
    {
        this.code = code;
        name = "";
        cpf = "";
        occupied = false;
    }

    // Constructor with all fields
    public Seat(String code, String name, String cpf, boolean occupied)
    {
        this.code = code;
        this.name = name;
        this.cpf = cpf;
        this.occupied = occupied;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    private void setName(String name) {
        this.name = name;
    }


    // Method to sell a seat object and fill the name and cpf fields
    public void sell(Seat [][] seats, int line, int column, String name, String cpf)
    {
        seats[line][column].name = name;
        seats[line][column].cpf = cpf;
        seats[line][column].occupied = true;
    }



    // Makes a Seat object vacant
    public void vacant(Seat [][] seats, int line, int column)
    {
        seats[line][column].name = "";
        seats[line][column].cpf = "";
        seats[line][column].occupied = false;
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

    // Prints the Seat object in CSV format
    public String print()
    {
        return code+","+name+","+cpf+","+occupied+";\n";
    }

    // Reads CSV format into a Seat object
    public void read(String code, String name, String cpf, boolean occupied)
    {
        this.code = code;
        this.name = name;
        this.cpf = cpf;
        this.occupied = occupied;
    }
}