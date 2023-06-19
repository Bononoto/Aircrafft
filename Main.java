import java.util.Scanner;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        Scanner sc = new Scanner(System.in);

        Aircraft aircraft1 = new Aircraft("1", 16, 4);
        Aircraft aircraft2 = new Aircraft("2", 16, 4);
        Aircraft aircraft3 = new Aircraft("3", 16, 4);

        // aircraft1.sell("sell A1"); // simula uma venda anterior

        System.out.println("Venda de passagens");
        System.out.println("Crie sua conta");
        Comprador comprador = pegarDadosComprador();

        System.out.println("Insira o comando desejado");
        String command;
        do {
            exibirMenu();
            command = sc.nextLine();
            if (command.startsWith("show"))
                aircraft1.print();
            else if (command.startsWith("sell"))
                aircraft1.sell(command, comprador);
            else if (command.startsWith("write"))
                aircraft1.write();
            else if (command.startsWith("read"))
                aircraft1.read();
            else
                System.out.println("Comando invalido!");
        } while (!command.startsWith("quit"));

        sc.close();
    }

    public static void exibirMenu () {
        System.out.println("Opcoes de comandos:");
        System.out.println("show: Exibe os Lugares do avião");
        System.out.println("sell: venda de passagens, insira o comando espaco a letra e o numero referido");
        System.out.println("write: escreve no arquivo csv ");
        System.out.println("read: le do arquivo ");
        System.out.println("quit: sair do sistema");
    }

    public static Comprador pegarDadosComprador () {
        Scanner scanner = new Scanner (System.in);
        System.out.println("Insira seu nome");
        String nome = scanner.nextLine();
        System.out.println("Insira seu cpf");
        String cpf = scanner.nextLine();
        return new Comprador(nome, cpf);
    }
}
