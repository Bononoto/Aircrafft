import java.util.Scanner;
public class Main
{
    public static void main(String[] args) throws Exception
    {
        // Populates the 4D flights array destination*planetypes*flightcompanies*flighttimes
        Aircraft flights[][][][] = new Aircraft[Aircraft.getDestinationsNum()][Aircraft.getPlanesNum()][Aircraft.getFlightCompaniesNum()][Aircraft.getFlightTimesNum()];

        for(int i = 0; i < flights.length; i++)
        {
            for(int j = 0; j < flights[i].length; j++)
            {
                for(int k = 0; k < flights[i][j].length; k++)
                {
                    for(int l = 0; l < flights[i][j][k].length; l++)
                    {
                        flights[i][j][k][l] = new Aircraft(i, j, k, l);
                    }
                }
            }
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("Venda de passagens\n");

        // Creates an User object with an input from user data
        User user = getUserData();
        // User user = new User("Joao", "012345678912");

        // If user doesn't have any flights on his account
        if(!user.checkFlights())
        {
            System.out.println("Usuario registrado com sucesso.\n");

            // Goes to the chooseFlight menu for the user to find the aircraft they want to buy seats from
            user.addFlight(chooseFlight());
        }
        else
        {
            System.out.println("Usuario ja esta registrado no sistema.\nBem vindo de volta " + user.getName() +"!\n");
            String choice = "";

            // If user has already purchased seats on any plane, it will ask to edit the last flight
            do
            {
                System.out.println("Digite (1) mudar seus assentos da ultima compra.\nDigite (2) para nova compra. ");
                choice = sc.nextLine();
                if (!choice.equals("1") && !choice.equals("2")) {
                    System.out.println("Comando invalido\n");
                }
            }
            while (!(choice.equals("1") || choice.equals("2")));

            // If they haven't purchased any seats yet
            if (choice.equalsIgnoreCase("2"))
            {
                System.out.println();
                user.addFlight(chooseFlight());
            }            
        }

        // Sets the variables from the received array to better visualize it
        int[] lastFlight = user.lastFlight();
        int userFlightDestination = lastFlight[0];
        int userFlightCompany = lastFlight[1];
        int userFlightPlane = lastFlight[2];
        int userFlightTime = lastFlight[3];

        String command;

        // Command menu loop
        do
        {
            // Prints command menu
            showMenu();
            command = sc.nextLine();
            if (command.startsWith("1"))
            {
                flights[userFlightDestination][userFlightCompany][userFlightPlane][userFlightTime].print();
            }
            else if (command.startsWith("comprar"))
            {
                flights[userFlightDestination][userFlightCompany][userFlightPlane][userFlightTime].sell(command, user);
            }
            else if (command.startsWith("3"))
            {
                flights[userFlightDestination][userFlightCompany][userFlightPlane][userFlightTime].printTicket(user);
            }
            else if (command.startsWith("4"))
            {
                flights[userFlightDestination][userFlightCompany][userFlightPlane][userFlightTime].quit(user);
            }
            else
            {
                //System.out.println("Comando invalido!");
            }
        } while (!command.startsWith("4"));

        sc.close();   
    }

    // Method to print command menu
    public static void showMenu () {
        System.out.println("Opções:");
        System.out.println("Digite (1) para exibir os lugares do aviao");
        System.out.println("Para comprar  um assento digite: comprar + o assento que deseja");
        System.out.println("Digite (3) para ver sua passagem");
        System.out.println("Digite (4) para sair do sistema\n");
    }

    // Method to get user data and return as an User object
    public static User getUserData ()
    {
        Scanner sc = new Scanner(System.in);
        String username = "";
        String cpf = "";

        System.out.println("Crie ou faca login na sua conta");

        do
        {
            System.out.print("Insira seu nome: ");
            username = sc.nextLine();
        }
        while(username.isEmpty());

        while(true)
        {
            System.out.print("Insira seu cpf: ");
            cpf = sc.nextLine();
            if (!Cpf.checkValid(cpf))
            {
                System.out.println("CPF invalido!");
                continue;
            }
            cpf = Cpf.formatCpf(cpf);
            System.out.println("");
            break;
        }

        return new User(username, cpf);
    }

    // Method to return an array of the flight chosen by the user
    public static int[] chooseFlight()
    {
        Scanner sc = new Scanner(System.in);

        int[] userFlight = new int[4];
        try {
            do
            {
                System.out.println("Destinos disponíveis:");
                System.out.println(Aircraft.printDestinationTable());
                System.out.println("Escolha seu destino: ");
                userFlight[0] = (sc.nextInt() - 1);
            }
            while (userFlight[0] < 0 || userFlight[0] > 5);

            do
            {
                System.out.println("Companhias disponíveis:");
                System.out.println(Aircraft.printCompanyTable());
                System.out.println("Escolha sua companhia: ");
                userFlight[1] = (sc.nextInt() - 1);
            }
            while (userFlight[1] < 0 || userFlight[1] > 2);

            do
            {
                System.out.println("Avioes disponíveis:");
                System.out.println(Aircraft.printPlanesTable());
                System.out.println("Escolha seu aviao: ");
                userFlight[2] = (sc.nextInt() - 1);
            }
            while (userFlight[2] < 0 || userFlight[2] > 2);

            do
            {
                System.out.println("Horarios disponíveis:");
                System.out.println(Aircraft.printTimeTable());
                System.out.println("Escolha o horario: ");
                userFlight[3] = (sc.nextInt() - 1);
                System.out.println(" ");
            }
            while (userFlight[3] < 0 || userFlight[3] > 9);

            //return userFlight;
        } catch (Exception e){
            System.out.println("Comando invalido1");
        }
        return userFlight;
    }
}