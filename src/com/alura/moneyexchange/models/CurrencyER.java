package com.alura.moneyexchange.models;

import java.util.Map;

public record CurrencyER(String base_code, Map<String, Double> conversion_rates) {
}
