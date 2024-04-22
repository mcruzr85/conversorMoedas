package com.alura.moneyexchange.models;

import java.util.ArrayList;
import java.util.List;

public class RateCenter {

    private List<Currency> currencies;

    public RateCenter(){

        currencies = new ArrayList<Currency>();
    }

    public void insertCurrency(Currency currency){
        currencies.add(currency);

    }

    //devolve true se essa moeda ja foi consultada e por tanto esta salva no arrayList de moedas
    public boolean isConsultedToday(String baseCurrency){
        return this.currencies.stream().anyMatch(currency -> currency.getAcronym().equals(baseCurrency) );
    }

    //Pego a moeda se ja foi consultada ou um optional vazio
    public double getExchangeRateFromBaseCurrency(String baseCurrency, String quoteCurency) {
    return 0.5;/*this.currencies.stream()
                 .filter(currency -> currency.getAcronym().equals(baseCurrency))
                 .forEach(e -> e.getRateOfAQuotedMoney(quoteCurency));*/
      }
      @Override
      public String toString() {

          return currencies.toString();
      }
}
