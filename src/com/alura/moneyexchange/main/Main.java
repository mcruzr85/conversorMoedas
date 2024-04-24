package com.alura.moneyexchange.main;

import com.alura.moneyexchange.conexions.Conexion;
import com.alura.moneyexchange.models.Currency;
import com.alura.moneyexchange.models.RateCenter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static <IOException extends Throwable> void main(String[] args) throws IOException, InterruptedException, java.io.IOException {
        Conexion con = new Conexion();
        RateCenter center = new RateCenter();

        System.out.println( "***********************************************************");
        System.out.println( "       Seja Bem-vindo(a) ao Conversor de Moedas ☻");

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


        while(flag){

            System.out.println(message);
            option = scanner.nextInt();

            switch(option){
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
                    baseCurrency  = scanner.nextLine();

                    System.out.println("Escreva a moeda destino ");
                    quoteCurrency = scanner.nextLine();
                    break;
                default:
                    if(!( option > 0 && option < 9 )){
                        System.out.println("Escolha não válida - Selecione um número entre 1 e 8");
                    }else{
                        System.out.println("Muito obrigada pela visita!! Até mais!");
                        flag = false;
                    }
                    break;

            } //swith


            if(( option > 0 && option < 8 )){
                System.out.println("Opção escolhida: " + option + ") " + baseCurrency + " => " + quoteCurrency);

                System.out.println("Digite o valor que deseja converter");
                valor = scanner.nextDouble();

                //verificar si esta ja descargadas las rates para la baseCurrency
                if(!center.isConsultedToday(baseCurrency)){

                    //obtengo los datos dessa moeda na API e la guardo en la lista de moedas
                    Currency currency = con.getData(baseCurrency);
                    center.insertCurrency(currency);
                }
                //obtengo el rate para calcular el valor de la conversion

                Double rate = 0.0;

                Optional<Double> optRate= center.getExchangeRate(baseCurrency,quoteCurrency);
                if(optRate.isPresent()){
                    rate = optRate.get();

                    if(!(rate == -1.0)){System.out.println("A taxa de hoje é: " + rate);}
                    else{
                        System.out.println("imposivle taxa para esa moeda");
                    }

                }else {
                    System.out.println("Não foi possivel achar a taxa para essa moeda");
                }

                System.out.println(center);

                //***********
                System.out.println("======================================================================");
                System.out.println("Total a converter: " + valor + " " + baseCurrency + ", corresponde ao valor final => " + valor * rate + " " +  quoteCurrency);
                System.out.println("======================================================================");
            }
        }
    }
}
