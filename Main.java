import java.util.Scanner;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        Scanner sc = new Scanner(System.in);

        Aircraft aircraft1 = new Aircraft("1", 40, 4);
        Aircraft aircraft2 = new Aircraft("2", 50, 4);
        Aircraft aircraft3 = new Aircraft("3", 20, 4);

        // aircraft1.sell("sell A1"); // simula uma venda anterior

        System.out.println("Venda de passagens");
        System.out.println("Crie sua conta");
        Comprador comprador = pegarDadosComprador();

        System.out.println("Insira o comando desejado");
        String command;

        do {
            exibirMenu();
            command = sc.nextLine();
            if (command.startsWith("lugares")){
                aircraft1.print();
            }else if (command.startsWith("comprar")){
                aircraft1.comprar(command, comprador);
            }else if (command.startsWith("salvar")){
                aircraft1.salvar();
            }else if (command.startsWith("historico")){
                aircraft1.historico();
            }else if (command.startsWith("passagem")) {
                aircraft1.imprimePassagem(comprador);
            } else if (command.startsWith("sair")) {
                aircraft1.sair(comprador);
            } else {
                System.out.println("Comando invalido!");
            }
        } while (!command.startsWith("sair"));
        /*case "lugares": aircraft1.print();
            break;
        case "comprar": aircraft1.comprar(command, comprador);
            break;
        case "salvar": aircraft1.salvar();
            break;
        case "historico": aircraft1.historico();
            break;
        case "passagem": aircraft1.imprimePassagem(comprador);
            break;
        default: System.out.println("Comando invalido!\n");
            break;*/
        //if (command.startsWith("fim")){
        //aircraft1.fimSessao(comprador);
        //}
        //System.out.println("Comando invalido!");
        //} 

        sc.close();
    }

    public static void exibirMenu () {
        System.out.println("Opcoes de comandos:");
        System.out.println("lugares: Exibe os Lugares do avião");
        System.out.println("comprar: para comprar digite: comprar + o assento que deseja");
        System.out.println("salvar: salvar no avião");
        System.out.println("passagem: para ver sua passagem");
        System.out.println("historico: ultima compra");
        System.out.println("sair: sair do sistema");
    }

    public static Comprador pegarDadosComprador () {
        Scanner scanner = new Scanner (System.in);
        System.out.println("Insira seu nome");
        String nome = scanner.nextLine();
        System.out.println("Insira seu cpf");
        String cpf = scanner.nextLine();

        if(cpf.length() != 11  ){
            System.out.println("Comando Inválido!");
            return pegarDadosComprador();
        }
        return new Comprador(nome, cpf);    
    }
}
