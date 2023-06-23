import java.io.File;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class User {
    private String name;
    private String cpf;
    public double total;
    int flights[][];

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public User(String name, String cpf) {
        this.name = name;
        this.cpf = cpf;
        this.total = 0;

        this.flights = new int[10][4];
        for (int i = 0; i < flights.length; i++)
        {
            flights[i][0] = -1;
        }

        try
        {
            read();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public double getValor() {
        return this.total;
    }
    
    public void read() throws Exception
    {
        File file = new File("user-" + cpf);
        if(file.exists())
        {
            FileInputStream f = new FileInputStream(file);        
            Scanner reader = new Scanner(file);

            reader.useDelimiter(",|;\n");

            this.total = Double.valueOf(reader.next());       

            for (int i = 0; i < flights.length; i++)
            {
                for (int j = 0; j < flights[i].length; j++)
                {
                    flights[i][j] = Integer.valueOf(reader.next());
                }
            }
            reader.close();
            f.close();
        }        
    }

    public void write() throws Exception
    {
        PrintStream file = new PrintStream("user-" + cpf);
        file.print(total + ";\n");

        for (int i = 0; i < flights.length; i++)
        {

            for (int j = 0; j < flights[i].length; j++)
            {
                file.print(flights[i][j]);
                if (j >= (flights[i].length-1))
                {
                    file.print(";\n");
                }
                else
                {
                    file.print(",");
                }
            }
        }
        file.close();
    }

    public boolean checkFlights()
    {
        for (int i = 0; i < flights.length; i++)
        {
            if (flights[i][0] != -1)
            {
                return true;
            }
        }
        return false;
    }

    public int[] lastFlight()
    {
        int index = 0;
        for (int i = 0; i < flights.length; i++)
        {            
            if (flights[i][0] != -1)
            {
                index = i;
            }
        }
        return flights[index];
    }

    public int flightsNum()
    {
        int lastFlight = 0;
        for (int i = 0; i < flights.length; i++)
        {            
            if (flights[i][0] != -1)
            {
                lastFlight = i;
            }
        }
        return lastFlight;
    }

    public void addFlight(int[] flight)
    {
        int newFlightIndex = 0;
        for (int i = 0; i < flights.length; i++)
        {
            if (flights[i][0] != -1)
            {
                newFlightIndex = i+1;
            }
        }
        if(newFlightIndex < flights.length) 
        {
            flights[newFlightIndex] = flight;
        }
        else
        {
            throw new IllegalArgumentException();
        }
    }

    // public String printFlights()
    // {
    //     String userFlightTable = "";
    //     for (int i = 0; i < flights.length; i++)
    //     {            
    //         if (flights[i][0] != -1)
    //         {
    //             userFlightTable += i + "\n  Destination: " + Aircraft.getDestinationName(flights[i][0])
    //                                 + "Plane: " + Aircraft.getPlaneName(flights[i][1])
    //                                 + "Company: " + Aircraft.getFlightCompanyName(flights[i][2])
    //                                 + "Time: " + Aircraft.getFlightTime(flights[i][3]) +"\n";
    //         }
    //     }
    //     return userFlightTable;
    // }
}