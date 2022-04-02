package com.engeto.projekt01;

import java.util.Objects;

public class Country implements Comparable<Country> {
    private String code;
    private String name;
    private Double vat;
    private Double vatDecreased;
    private boolean hasSpecialVatRate;

    public Country(String code, String name, Double vat, Double vatDecreased, boolean hasSpecialVatRate) {
        this.code = code;
        this.name = name;
        this.vat = vat;
        this.vatDecreased = vatDecreased;
        this.hasSpecialVatRate = hasSpecialVatRate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getVat() {
        return vat;
    }

    public void setVat(Double vat) {
        this.vat = vat;
    }

    public Double getVatDecreased() {
        return vatDecreased;
    }

    public void setVatDecreased(Double vatDecreased) {
        this.vatDecreased = vatDecreased;
    }

    public boolean useSpecialVatRate() {
        return hasSpecialVatRate;
    }

    public void setUseSpecialRate(boolean useSpecialRate) {
        this.hasSpecialVatRate = useSpecialRate;
    }

    public String format1() {
        return (this.name + " (" + this.code + "): " + this.vat + " %");
    }

    public String format2() {
        return (this.name + " (" + this.code + "): " + this.vat + "%" + " (" + this.vatDecreased + " %) ");
    }

    @Override
    public String toString() {
        return "Country{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", vat=" + vat +
                ", vatDecreased=" + vatDecreased +
                ", hasSpecialVatRate=" + hasSpecialVatRate +
                '}';
    }

    @Override
    public int compareTo(Country o) {
        return vat.compareTo(o.getVat());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return hasSpecialVatRate == country.hasSpecialVatRate && Objects.equals(getCode(), country.getCode()) && Objects.equals(getName(), country.getName()) && Objects.equals(getVat(), country.getVat()) && Objects.equals(getVatDecreased(), country.getVatDecreased());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode());
    }

    public static Country parse(String text, String delimiter) throws CountryException {
        String[] items = text.split(delimiter);

        int numberOfItems = items.length;
        if (numberOfItems != 5)
            throw new CountryException("Nesprávný počet položek na řádku! Očekáváme 5 položek, místo " + numberOfItems + " položek na řádku: " + text);

        String code = items[0];
        String name = items[1];
        //String vatStr = items[2].replace(",", ".");
        //String vatSpecialStr = items[3].replace(",", ".");

        try {
            Double vat = Double.parseDouble(items[2].replace(",", "."));
            Double vatSpecial = Double.parseDouble(items[3].replace(",", "."));
            Boolean hasSpecialVatRate = Boolean.parseBoolean(items[4]);
            return new Country(code, name, vat, vatSpecial, hasSpecialVatRate);
        } catch (NumberFormatException ex) {
            throw new CountryException("Špatně zadané číslo na řádku: \"" + text + "\"\n\t" + ex.getLocalizedMessage());
        }
//        catch (BooleanFormatException ex) { throw new CountryException("Špatně zadaná 5.položka hasSpecialVatRate na řádku: \""+text+"\"\n\t"+ex.getLocalizedMessage()); }

    }


}
