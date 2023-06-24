/**
 * Aircraft is a plane ticket selling program
 * Cpf is a class for checking cpf numbers
 * 
 * @author Gabriel Bonoto and Juliano Maia
 * @version 2023.06.23
 */

public class Cpf {
    // Checks if cpf is valid
    public static boolean checkValid(String cpf)
    {
        if(cpf.length() == 14)
        {
            for (int i = 0; i < cpf.length(); i++)
            {
                if (i != 3 && i != 7 && i != 11)
                {
                    if(!(Character.isDigit(cpf.charAt(i))))
                    {
                        return false;
                    }
                }
                else if (!((i == 11 && cpf.charAt(i) == '-') || (cpf.charAt(i) == '.')))
                {
                    return false;
                }
            }
            return true;
        }
        else if(cpf.length() == 11)
        {
            for (int i = 0; i < cpf.length(); i++)
            {
                if(!(Character.isDigit(cpf.charAt(i))))
                {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    // If only numbers, formats cpf to the 14 String with periods and hyphen
    public static String formatCpf(String cpf)
    {
        String formattedCpf = "";
        if(cpf.length() == 11)
        {
            formattedCpf += cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9);
        }

        return formattedCpf;
    }
}