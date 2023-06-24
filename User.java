/**
 * Aircraft is a plane ticket selling program
 * User is a class for user account objects
 *  
 * @author Gabriel Bonoto and Juliano Maia
 * @version 2023.06.23
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class User {
    // Object variables
    private String name;
    private String cpf;
    public double total;
    int flights[][];

    // Getters
    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    // User constructor with name and cpf arguments
    public User(String name, String cpf) {
        this.name = name;
        this.cpf = cpf;
        this.total = 0;

        this.flights = new int[10][4];

        // Sets every first element to -1 to be used as a false boolean
        for (int i = 0; i < flights.length; i++)
        {
            flights[i][0] = -1;
        }

        // Tries to read user CSV file
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
    
    // Method to read a CSV file for specified user
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

    // Method to write CSV file for specified user
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

    // Checks if user has any flights on his array
    public boolean checkFlights()
    {
        for (int i = 0; i < flights.length; i++)
        {
            // If first element is not -1, then it is a valid flight
            if (flights[i][0] != -1)
            {
                return true;
            }
        }
        return false;
    }

    // Finds the index of the last flight
    public int[] lastFlight()
    {
        int index = 0;
        // The last flight will be the first where a -1 is found
        for (int i = 0; i < flights.length; i++)
        {            
            if (flights[i][0] != -1)
            {
                index = i;
            }
        }
        return flights[index];
    }

    // Returns the number of flights the user has on his account
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

    // Method to add a flight array to User fligths array
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
}