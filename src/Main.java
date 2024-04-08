import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    //API address:  GET https://v6.exchangerate-api.com/v6/YOUR-API-KEY/latest/USD
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
String message = """       
        ************************************************
        Seja Bem-vindo(a) ao Conversor de Moedas ☻

         1) Dólar americano => Real brasileiro
         2) Real brasileiro => Dólar americano
         3) Dólar americano => Euro
         4) Euro => Dólar americano
         5) Dólar americano => Dólar canadiense
         6) Dólar canadiense => Dólar americano
         7) Sair
         Escolha uma opção entre as anteriores
         *************************************************
         """;
        System.out.println(message);
        int option = scanner.nextInt();
        System.out.println(option);
    }
}