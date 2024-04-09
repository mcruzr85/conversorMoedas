import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    //API address:  GET https://v6.exchangerate-api.com/v6/YOUR-API-KEY/latest/USD

    public static final String API_KEY = "ed9c35d26151a9b3b33d8e0e";
    public static void main(String[] args) {

        System.out.println( "***********************************************************");
        System.out.println( "       Seja Bem-vindo(a) ao Conversor de Moedas ☻");

        Scanner scanner = new Scanner(System.in);

        String baseCurrency = "";
        String destinyCurrency = "";

        boolean flag = true;
        int option = 0;
        double valor = 0.0;

        String message = """       
        ***********************************************************
        Conversor de Moedas ☻ - Escolha a opção da sua preferencia:

         1) Dólar americano => Real brasileiro
         2) Real brasileiro => Dólar americano
         3) Dólar americano => Euro
         4) Euro => Dólar americano
         5) Dólar americano => Dólar canadiense
         6) Dólar canadiense => Dólar americano
         7) Sair
         Escolha uma opção entre as anteriores
         **********************************************************
         """;

        while(flag){

            System.out.println(message);
            option = scanner.nextInt();


            switch(option){
                case 1:
                    baseCurrency = "USD";
                    destinyCurrency = "BRA";

                    break;
                case 2:
                    baseCurrency = "BRA";
                    destinyCurrency = "USD";
                    break;
                case 3:
                    baseCurrency = "USD";
                    destinyCurrency = "EUR";
                    break;
                case 4:
                    baseCurrency = "EUR";
                    destinyCurrency = "USD";
                    break;
                case 5:
                    baseCurrency = "EUR";
                    destinyCurrency = "BRA";
                    break;
                case 6:
                    baseCurrency = "BRA";
                    destinyCurrency = "EUR";
                    break;
                default:
                    if(!( option > 0 && option < 8 )){
                        System.out.println("Escolha não válida - Selecione um número entre 1 e 7");
                        break;
                    }else{
                        System.out.println("Muito obrigada pela visita!! Até mais!");
                        flag = false;
                    }

            } //swith


            if(( option > 0 && option < 7 )){
                System.out.println("Opção escolhida: " + option + ") " + baseCurrency + " => " + destinyCurrency);

                System.out.println("Digite o valor que deseja converter");
                valor = scanner.nextDouble();
                System.out.println("======================================================================");
                System.out.println("Total a converter: " + valor + " " + baseCurrency + ", corresponde ao valor final => " + valor * 2.5 + " " +  destinyCurrency);
                System.out.println("======================================================================");
            }
        }
    }
}