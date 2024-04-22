package com.alura.moneyexchange.conexions;

import com.alura.moneyexchange.models.Currency;
import com.alura.moneyexchange.models.CurrencyER;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class Conexion {

    private String baseCurrency;
    private static final String API_KEY = "ed9c35d26151a9b3b33d8e0e";


    //si não passan a baseCurrency é USD por  default

    public Conexion(){
        this.baseCurrency = "USD";
    }

    Conexion(String baseCurrency){
        this.baseCurrency = baseCurrency;
    }

    public  void setBaseCurrency(String baseCurrency){
        this.baseCurrency = baseCurrency;
    }

    public Currency getData(String baseCurrency) throws IOException, InterruptedException{

        String address = "https://v6.exchangerate-api.com/v6/ed9c35d26151a9b3b33d8e0e/latest/" + baseCurrency ;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(address))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String json = response.body();
        Gson gson = new Gson();
        CurrencyER currencyER = gson.fromJson(json, CurrencyER.class);
        return new Currency(currencyER);
    }
}
