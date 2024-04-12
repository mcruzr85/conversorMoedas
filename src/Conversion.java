public class Conversion {
    String baseCurrency;
    String destinyCurrency;
    double rate;

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getDestinyCurrency() {
        return destinyCurrency;
    }

    public void setDestinyCurrency(String destinyCurrency) {
        this.destinyCurrency = destinyCurrency;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    Conversion(){
    }

    Conversion(String baseCurrency, String destinyCurrency, double rate){
        this.baseCurrency = baseCurrency;
        this.destinyCurrency = destinyCurrency;
        this.rate = rate;
    }
}
