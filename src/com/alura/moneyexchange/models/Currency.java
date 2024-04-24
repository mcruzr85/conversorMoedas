package com.alura.moneyexchange.models;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

public class Currency {

    private String acronym;
    private Map<String,Double> rates;

    Currency(){}

    Currency(String acronym, String name, String country){
        this.acronym = acronym;
        this.rates = new HashMap<String,Double>();
    }

    public Currency(CurrencyER currencyER){
        this.acronym = currencyER.base_code();
        this.rates = currencyER.conversion_rates();
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getAcronym(){
        return this.acronym;
    }


    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    @Override
    public String toString() {
        return  acronym;
    }

    public Double getRateOfAQuotedMoney(String aQuoteMoney){
        String quoteMoney = aQuoteMoney.toUpperCase();
        if(this.rates.containsKey(quoteMoney)){
            return this.rates.get(quoteMoney);
       }else return (double) -1;
    }
    }