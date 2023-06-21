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
    //private double valor;

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
                double valor;
                if(i < 8) {
                    valor = 32486.77;
                } else if(i % 16 == 0) { 
                    valor = 7538.90;
                } else {
                    valor = 2498.94;
                }
                seats[i][j] = new Seat((char)(65+j) + String.valueOf(i+1), valor);
            }
        }
    }

    // Method for selling seats
    public void comprar(String command, Comprador comprador)
    {
        Scanner sc = new Scanner(System.in);

        command = command.trim();
        String choice = command.substring(8);
        char letter = Character.toUpperCase(choice.charAt(0));
        int number = Integer.parseInt(choice.substring(1));

        int line = number - 1;
        int column = (int)(letter-65); //ASCII

        if (seats[line][column].checkOccupied()) {
            System.out.println("Assento OCUPADO!");
        }
        else {
            double valorAssento = seats[line][column].valor;
            String comando;
            do {
                System.out.println("R$ " + valorAssento);
                System.out.println("Tem certeza?");
                comando = sc.nextLine();
                if (!comando.equals("sim") && !comando.equals("nao")) {
                    System.out.println("Comando inválido num1");
                }
            } while (!comando.equals("sim") && !comando.equals("nao"));

            if (comando.equals("sim")) {
                comprador.total += seats[line][column].sell(seats, line, column, comprador.getName(), comprador.getCpf()); //retorna o valor da passagem
                System.out.println("Valor total da compra: " + comprador.total);
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
                    System.out.println("Deseja reservar o assento ao lado? (sim/nao)");
                    String resposta = sc.nextLine();
                    if (resposta.equalsIgnoreCase("sim")){
                        char novaLetra = (char) (64+next);

                        System.out.println("assento do lado: " + novaLetra + line );
                        System.out.println("R$ " + seats[line][next].valor);
                        seats[line][next].sell(seats, line, next, comprador.getName(), comprador.getCpf() );
                        comprador.total += seats[line][column].sell(seats, line, column, comprador.getName(), comprador.getCpf());
                    }                      
                }

                System.out.println("Valor total da compra: " + comprador.total);
            }  else if (!comando.equals("sim") && !comando.equals("nao")) {
                System.out.println("Comando inválido num2");
            }

        }

        //System.out.println(seats[line][column].checkOccupied());
    }

    
    public void sair (Comprador comprador) {
        Scanner sc1 = new Scanner (System.in);
        
        String comando1 = "";
        do {
            System.out.println("Finalizar Sessão?");
            comando1 = sc1.nextLine();
            if (!comando1.equals("sim") && !comando1.equals("nao")) {
                System.out.println("Comando inválido");
            }
            
            System.out.println(!(comando1.equals("sim") || comando1.equals("nao")));
        } while (!(comando1.equals("sim") || comando1.equals("nao")));
        
        if (comando1.equalsIgnoreCase("sim")){
            imprimePassagem(comprador);
        }
    }

    public void imprimePassagem(Comprador comprador){
        System.out.println("POA -> MCO");
        System.out.println("03/06/2023 6h LATAM AIRLINES 3434\n");
        System.out.println("Seus Assentos:");

        for (int i = 0; i < seats.length; i++)
        {
            for (int j = 0; j < seats[i].length; j++)
            {
                if(comprador.getCpf().equals(seats[i][j].getCpf()))
                    System.out.print(seats[i][j].print());
            }
        }
    }

    // Prints the plane seats in the CLI
    public void print()
    {
        System.out.println("POA -> MCO");
        System.out.println("03/06/2023 6h LATAM AIRLINES 3434\n");

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
                    System.out.print("[X]");
                }
                else
                {
                    System.out.print("[_]");
                }

                if(j == 1)
                {
                    System.out.print("  ");
                }

            }
            System.out.printf(" %2d%n", i + 1);
            if (i % 15 == 0 && i != 0)
            {
                System.out.println();
            }
        }
        System.out.println("    A  B     C  D");

    }

    // Writes the seats array to a CSV file
    public void salvar() throws Exception
    {
        PrintStream file = new PrintStream("aviao.csv");
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
    public void historico() throws Exception
    {
        FileInputStream file = new FileInputStream("aviao.csv");
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

