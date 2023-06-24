/**
 * Aircraft is a plane ticket selling program
 * Aircraft is a class for creating aircraft objects with seats that can be bought
 * 
 * @author Gabriel Bonoto and Juliano Maia
 * @version 2023.06.23
 */

import java.util.Scanner;
import java.time.LocalDate;
import java.io.PrintStream;
import java.io.File;
import java.io.FileInputStream;

// https://www.flyporter.com/en/about-porter/our-fleet/embraer-e195-e2
// https://www.koreanair.com/kr/en/in-flight/aircraft/b747/8i-368/seat-map
// https://www.aircharterservice.co.kr/aircraft-guide/private/cessnaaircraftcompany-usa/cessna-208-grand-caravan

public class Aircraft
{
    // Object variables
    private String destination;
    private String aircraftName;
    private String flightCompany;
    private String flightName;
    private LocalDate date;
    private String time;
    private int numRows;
    private int numColumns;
    private int spaceRows[];
    private int spaceColumns[];
    private Seat[][] seats;
    private int seatsNum;
    private int vacantSeats;

    // Static variables
    private static String planes[] = {"Embraer E195-E2", "Cessna Grand Caravan", "Boeing 747"};
    private static int flightsSeatSize[][] = {{16, 4}, {5, 2}, {25, 10}};
    private static int flightsSeatSpaces[][][] = {{{2}, {13}}, {{1}, {4}}, {{3, 7}, {10, 20}}};
    private static String destinations[] = {"CGH", "GIG", "BSB", "JFK", "CDG", "HND"};
    private static String destinationsNames[] = {"Congonhas, Brasil", "Rio de Janeiro, Brasil", "Brasilia, Brasil", "Nova Iorque, EUA", "Paris, Franca", "Toquio, Japao"};
    private static String flightCompanies[] = {"AD", "G3", "LA"};
    private static String flightCompanyNames[] = {"Azul", "Gol", "Latam"};
    private static String flightTimes[] = {"00:00","3:30", "8:00", "9:45", "11:30", "13:00", "15:45", "17:30", "19:15", "22:30"};

    // Constructor for the Aircraft object
    public Aircraft(int destination, int flightCompany, int plane, int flightTime)
    {
        this.aircraftName = planes[plane];
        this.flightName = flightCompanies[flightCompany] + "-" + flightCompany + String.valueOf(plane) + String.valueOf(destination) + String.valueOf(flightTime);
        this.flightCompany = flightCompanies[flightCompany];
        this.destination = destinations[destination];
        this.date = LocalDate.now();
        this.time = flightTimes[flightTime];
        this.numRows = flightsSeatSize[plane][0];
        this.numColumns = flightsSeatSize[plane][1];
        this.spaceRows = flightsSeatSpaces[plane][0];
        this.spaceColumns = flightsSeatSpaces[plane][1];
        
        seats = new Seat[numRows][numColumns];

        // Populates "Seat" objects in "seats" array, filling the "code" variable and price
        for (int i = 0; i < numRows; i++)
        {
            double price = findPrice(i, destination, plane);
            
            for (int j = 0; j < numColumns; j++)
            {
                seats[i][j] = new Seat((char)(65+j) + String.valueOf(i+1), price);
            }
        }

        seatsNum = seats.length*seats[0].length;
        // Tries to read a CSV file for the object. If it exists, it will update the fiels of the object.
        try
        {
            read();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        // Counts vacant seats
        vacantSeats = seatsNum;
        for (int i = 0; i < numRows; i++)
        {
            for (int j = 0; j < numColumns; j++)
            {
                if(seats[i][j].checkOccupied())
                {
                    vacantSeats--;
                }
            }
        }
    }

    // Getters
    public static int getDestinationsNum()
    {
        return destinations.length;
    }

    public static int getFlightCompaniesNum()
    {
        return flightCompanies.length;
    }

    public static int getPlanesNum()
    {
        return planes.length;
    }

    public static int getFlightTimesNum()
    {
        return flightTimes.length;
    }

    public static String getDestinationName(int num)
    {
        return destinations[num];
    }

    public static String getFlightCompanyName(int num)
    {
        return flightCompanies[num];
    }

    public static String getPlaneName(int num)
    {
        return planes[num];
    }

    public static String getFlightTime(int num)
    {
        return flightTimes[num];
    }

    public String seatPassengerName(int seatRow, int seatColumn)
    {
        return seats[seatRow][seatColumn].getName();
    }

    // Calculates the price based on seat row, destination and type of plane
    public static double findPrice(int row, int destination, int plane)
    {
        double price;
        if(row < 8)
        {
            price = 32486.77;
        }
        else if(row % 16 == 0)
        { 
            price = 7538.90;
        }
        else
        {
            price = 2498.94;
        }

        switch (destination)
        {
            case 3:
                price *= 2;
                break;
            case 4:
                price *= 2.5;
                break;
            case 5:
                price *= 4;
                break;
        }

        switch (plane)
        {
            case 1:
                price *= 0.2;
                break;
            case 2:
                price *= 1.2;
                break;
        }
        
        return price;
    }

    // Method for selling seats
    public void sell(String command, User user)
    {
        Scanner sc = new Scanner(System.in);

        command = command.trim();
        String choice = command.substring(8);
        char letter = Character.toUpperCase(choice.charAt(0));
        int number = Integer.parseInt(choice.substring(1));

        int row = number - 1;
        int column = (int)(letter-65);

        if (seats[row][column].checkOccupied()) {
            System.out.println("Assento OCUPADO!\n");
        }
        else {
            double seatPrice = seats[row][column].getPrice();
            String comando;
            do {
                System.out.println("R$ " + seatPrice);
                System.out.println("Tem certeza?");
                comando = sc.nextLine();
                if (!comando.equals("sim") && !comando.equals("nao")) {
                    System.out.println("Comando invalido");
                }
            }
            while (!comando.equals("sim") && !comando.equals("nao"));

            if (comando.equals("sim")) {
                user.total += seats[row][column].sell(user.getName(), user.getCpf()); //retorna o valor da passagem
                this.vacantSeats--;
                System.out.printf("Valor total da compra: %.2f \n", user.total);
                int next;
                if (column == 0 || column == 2)
                {
                    next = column + 1;
                }
                else
                {
                    next = column - 1;
                }

                // Asks the user to buy the seat next to the first
                if (!seats[row][next].checkOccupied())
                {
                    System.out.println("Deseja reservar o assento ao lado? (sim/nao)");
                    char novaLetra = (char) (64+next);
                    System.out.println("assento do lado: " + novaLetra + row );
                    System.out.printf("R$ %.2f \n", seats[row][next].getPrice());
                    String answer = sc.nextLine();
                    if (answer.equalsIgnoreCase("sim"))
                    {
                        seats[row][next].sell(user.getName(), user.getCpf());
                        user.total += seats[row][column].sell(user.getName(), user.getCpf());
                        this.vacantSeats--;
                    }                      
                }
                System.out.printf("Valor total da compra: %.2f \n", user.total);
            } 
            else if (!comando.equals("sim") && !comando.equals("nao"))
            {
                System.out.println("Comando inválido");
            }

            // Tries to write to the specified seats CSV based on the Aircraft object
            try
            {
                write();
                user.write();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    // Prints the plane seats in the CLI
    public void print()
    {
        System.out.println(aircraftName);
        System.out.println(flightName);
        System.out.println("POA -> " + destination);
        System.out.println(date + " " + time + "\n");
        
        System.out.print("  ");
        for (int i = 0; i < numColumns; i++)
        {
            for(int j = 0; j < spaceRows.length; j++)
            {
                if (i == spaceRows[j] && spaceRows[j] != 0)
                {
                    System.out.print("   ");
                }
            }
            System.out.print("  " + (char)(65+i));
        }
        System.out.println();
        for (int i = 0; i < seats.length; i++)
        {
            System.out.printf("%2d ", i + 1);
            for (int j = 0; j < seats[i].length; j++)
            {   
                for(int k = 0; k < spaceRows.length; k++)
                {
                    if (j == spaceRows[k] && spaceRows[k] != 0)
                    {
                        System.out.print("   ");
                    }
                } 
                if (seats[i][j].checkOccupied())
                {
                    System.out.print("[O]");
                }
                else
                {
                    System.out.print("[ ]");
                }
            }
            System.out.printf(" %2d%n", i + 1);

            for(int j = 0; j < spaceRows.length; j++)
            {
                if (i == spaceColumns[j]-1 && spaceColumns[j] != 0)
                {
                    System.out.println();
                }
            }
        }
        System.out.print("  ");
        for (int i = 0; i < numColumns; i++)
        {
            for(int j = 0; j < spaceRows.length; j++)
            {
                if (i == spaceRows[j] && spaceRows[j] != 0)
                {
                    System.out.print("   ");
                }
            }
            System.out.print("  " + (char)(65+i));
        }
        System.out.println("\n");
        System.out.printf("Assentos: %d     Assentos disponíveis: %d\n", seatsNum, vacantSeats);
    }

    // Writes the seats array to a CSV file
    public void write() throws Exception
    {
        PrintStream file = new PrintStream("flight-" + flightName + "-seats.csv");
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
        File file = new File("flight-" + flightName + "-seats.csv");
        if(file.exists())
        {
            FileInputStream f = new FileInputStream(file);        
            Scanner reader = new Scanner(file);
            reader.useDelimiter(",|;\n");

            for (int i = 0; i < seats.length; i++)
            {
                for (int j = 0; j < seats[i].length; j++)
                {            
                    seats[i][j].read(reader.next(), reader.next(), reader.next(), Double.valueOf(reader.next()), reader.nextBoolean());
                }
            }
            reader.close();
            f.close();
        }        
    }

    public void quit (User user)
    {
        Scanner sc = new Scanner (System.in);
        
        String choice = "";
        /*do
        {
            System.out.println("Finalizar Sessao?");
            choice = sc.nextLine();
            if (!choice.equals("sim") && !choice.equals("nao")) {
                System.out.println("Comando invalido");
            }
            
            //System.out.println(!(choice.equals("sim") || choice.equals("nao")));
        }
        while (!(choice.equals("sim") || choice.equals("nao")));
        
        if (choice.equalsIgnoreCase("sim"))
        {
            printTicket(user);
        }*/
        printTicket(user);
    }

    // Prints specified arrays as tables
    public static String printDestinationTable()
    {
        String table = "";
        for(int i = 0; i < destinations.length; i++)
        {
             table += i+1 + ": " + destinations[i] + " - " + destinationsNames[i] + "\n";
        }
        return table;
    }

    public static String printCompanyTable()
    {
        String table = "";
        for(int i = 0; i < flightCompanies.length; i++)
        {
             table += i+1 + ": " + flightCompanies[i] + " - " + flightCompanyNames[i] + "\n";
        }
        return table;
    }

    public static String printPlanesTable()
    {
        String table = "";
        for(int i = 0; i < planes.length; i++)
        {
             table += i+1 + ": " + planes[i].toString() + "\n";
        }
        return table;
    }

    public static String printTimeTable()
    {
        String table = "";
        for(int i = 0; i < flightTimes.length; i++)
        {
             table += i+1 + ": " + flightTimes[i].toString() + "\n";
        }
        return table;
    }

    // Print users' ticket
    public void printTicket(User user){
        System.out.println("POA -> " + destination);
        System.out.println(date + " " + time + " " + flightName + "\n");
        System.out.println("Seus Assentos:");

        for (int i = 0; i < seats.length; i++)
        {
            for (int j = 0; j < seats[i].length; j++)
            {
                if(user.getCpf().equals(seats[i][j].getCpf()))
                {
                    System.out.print(seats[i][j].printTicket());
                }
            }
        }
        System.out.println();
    }
}