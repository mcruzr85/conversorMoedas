package com.alura.moneyexchange.main;

import com.alura.moneyexchange.conexions.Conexion;
import com.alura.moneyexchange.models.Currency;
import com.alura.moneyexchange.models.RateCenter;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Conexion con = new Conexion();
        RateCenter center = new RateCenter();
        Scanner scanner = new Scanner(System.in);

        String baseCurrency = "";
        String quoteCurrency = "";

        boolean flag = true;
        int option;
        double valor;

        String message = """       
                ***********************************************************
                Conversor de Moedas ☻ - Escolha a opção da sua preferencia:

                 1) Dólar americano => Real brasileiro
                 2) Real brasileiro => Dólar americano
                 3) Dólar americano => Euro
                 4) Euro => Dólar americano
                 5) Real brasileiro => Euro
                 6) Euro => Real brasileiro
                 7) Otras moedas
                 8) sair
                 Escolha uma opção entre as anteriores
                 **********************************************************
                 """;

        System.out.println("***********************************************************");
        System.out.println("       Seja Bem-vindo(a) ao Conversor de Moedas ☻");


        while (flag) {

            try {

                System.out.println(message);
                option = scanner.nextInt();


                switch (option) {
                    case 1:
                        baseCurrency = "USD";
                        quoteCurrency = "BRL";
                        break;
                    case 2:
                        baseCurrency = "BRL";
                        quoteCurrency = "USD";
                        break;
                    case 3:
                        baseCurrency = "USD";
                        quoteCurrency = "EUR";
                        break;
                    case 4:
                        baseCurrency = "EUR";
                        quoteCurrency = "USD";
                        break;
                    case 5:
                        baseCurrency = "BRL";
                        quoteCurrency = "EUR";
                        break;
                    case 6:
                        baseCurrency = "EUR";
                        quoteCurrency = "BRL";
                        break;
                    case 7:
                        scanner.nextLine(); //limpando o buffer do scanner
                        System.out.println("Escreva a moeda base");
                        baseCurrency = scanner.nextLine().toUpperCase();

                        System.out.println("Escreva a moeda destino ");
                        quoteCurrency = scanner.nextLine().toUpperCase();
                        break;
                    default:
                        if (!((option > 0) && (option < 9))) {
                            System.out.println("Escolha não válida - Selecione um número entre 1 e 8");
                        } else {
                            System.out.println("Muito obrigado por usar o conversor de moedas!! Até mais!");
                            flag = false;
                            break;
                        }


                } //swith

                if(option > 0 && option < 8){

                    System.out.println("Opção escolhida: " + option + ") " + baseCurrency + " => " + quoteCurrency);

                    System.out.println("Digite o valor que deseja converter: ");
                    valor = scanner.nextDouble();

                    //verificar si esta ja descargadas las rates para la baseCurrency
                    if (!center.isConsultedToday(baseCurrency)) {

                        //obtenho os dados dessa moeda na API e os salvo na lista de moedas
                        Optional<Currency> optCurrency = con.getData(baseCurrency);


                        //se tem dados
                        if (optCurrency.isPresent()){
                            center.insertCurrency(optCurrency.get());

                        } else {
                            System.out.println("Não foi possivel achar a taxa de conversão para essa moeda");
                            System.out.println("Favor confira se " + baseCurrency + " é uma moeda válida");
                        }

                    }
                    if (center.isConsultedToday(baseCurrency)){

                        //obtenho o valor dessa quoteMoney no Hash
                        Double rate = 0.0;

                        Optional<Double> optRate = center.getExchangeRate(baseCurrency, quoteCurrency);

                        //se tem valor para essa moeda
                        if (optRate.isPresent()) {

                            //obtengo el valor
                            rate = optRate.get();

                            if (rate != -1.0) {
                                Double resultado = Math.round(valor * rate * 100.0) / 100.0;

                                System.out.println("A taxa de hoje é: " + rate);
                                System.out.println("===================================================================================");
                                System.out.println("Total a converter: " + valor + " " + baseCurrency + ", corresponde ao valor final => " + resultado + " " + quoteCurrency);
                                System.out.println("===================================================================================");
                                System.out.println("\n");
                            } else {
                                //se não tem rate para essa quotedMoney
                                System.out.println("Não foi possivel achar a taxa de conversão de " + baseCurrency + " para essa moeda");
                                System.out.println("Favor confira se " + quoteCurrency + " é uma moeda válida");
                            }
                        }
                    }
                }

            }catch(InputMismatchException e){
                System.out.println("Por favor escreva uma opção válida");
                flag = false;

            }//catch
        }//while
    }//main
}//class
