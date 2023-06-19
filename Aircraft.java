import java.util.Scanner;
import java.io.PrintStream;
import java.io.FileInputStream;

// https://www.flyporter.com/en/about-porter/our-fleet/embraer-e195-e2
public class Aircraft
{
    private String name;
    private int rows;
    private int columns;
    private Seat[][] seats;

    public Aircraft(String name, int rows, int columns)
    {
        this.name = name;
        this.rows = rows;
        this.columns = columns;
        seats = new Seat[rows][columns];

        // Populates "Seat" objects in "seats" array, filling the "code" variable
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                seats[i][j] = new Seat((char)(65+j) + String.valueOf(i+1));
            }
        }
    }


    // Method for selling seats
    public void sell(String command, Comprador comprador)
    {
        Scanner scanner = new Scanner(System.in);
        String choice = command.substring(5);
        char letter = Character.toUpperCase(choice.charAt(0));
        int number = Integer.parseInt(choice.substring(1));

        int line = number - 1;
        int column = (int)(letter-65); //ASCII

        if (seats[line][column].checkOccupied()) {
            System.out.println("Assento OCUPADO!");
        }
        else {
            seats[line][column].sell(seats, line, column, comprador.getName(), comprador.getCpf()); //inserir dados usuario
            System.out.println(seats[line][column].checkOccupied());
            int next;
            if (column == 0 || column == 2)
            {
                next = column + 1;
            }
            else
            {
                next = column - 1;
            }

            // (WIP) Asks the user to buy the seat next to the first
            if (!seats[line][next].checkOccupied()) {
                System.out.println("Deseja reservar o assento ao lado? (S/n)");
                String resposta = scanner.nextLine();
                if (resposta.equalsIgnoreCase("S")){
                    seats[line][next].sell(seats, line, next,  comprador.getName(), comprador.getCpf());
                }
            }
        }
    }

    // Prints the plane seats in the CLI
    public void print()
    {
        System.out.println("POA -> CGH");
        System.out.println("03/06/2023 6h TAM 3434\n");

        System.out.println("    A  B     C  D");
        // percorre cada linha
        for (int i = 0; i < seats.length; i++)
        {
            // mostra uma linha matriz
            System.out.printf("%2d ", i + 1);
            for (int j = 0; j < seats[i].length; j++)
            {
                if (seats[i][j].checkOccupied())
                {
                    System.out.print("[O]");
                }
                else
                {
                    System.out.print("[ ]");
                }
                if (j == 1)
                {
                    System.out.print("   ");
                }
            }
            System.out.printf(" %2d%n", i + 1);
            if (i == 11 || i == 12)
            {
                System.out.println();
            }
        }
        System.out.println("    A  B     C  D");
    }

    // Writes the seats array to a CSV file
    public void write() throws Exception
    {
        PrintStream file = new PrintStream("seats.csv");
        for (int i = 0; i < seats.length; i++)
        {
            for (int j = 0; j < seats[i].length; j++)
            {
                file.print(seats[i][j].print());
            }
        }
        file.close();
    }

    // Reads from a CSV file to the seats array
    public void read() throws Exception
    {
        FileInputStream file = new FileInputStream("seats.csv");
        Scanner reader = new Scanner(file);
        reader.useDelimiter(",|;\n");
        for (int i = 0; i < seats.length; i++)
        {
            for (int j = 0; j < seats[i].length; j++)
            {
                seats[i][j].read(reader.next(), reader.next(), reader.next(), reader.nextBoolean());
            }
        }
        reader.close();
        file.close();
    }
}
