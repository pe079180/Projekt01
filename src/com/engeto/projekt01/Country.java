package com.engeto.projekt01;

public class Country {
    private String code;
    private String name;
    private Double vat;
    private Double vatDecreased;
    private boolean hasSpecialVatRate;

//    public Country(String code, String name, Double vat, Double vatDecreased, boolean hasSpecialVatRate) {
//        this.code = code;
//        this.name = name;
//        this.vat = vat;
//        this.vatDecreased = vatDecreased;
//        this.hasSpecialVatRate = hasSpecialVatRate;
//    }

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

    public boolean isUseSpecialRate() {
        return hasSpecialVatRate;
    }

    public void setUseSpecialRate(boolean useSpecialRate) {
        this.hasSpecialVatRate = useSpecialRate;
    }

    public static Country parse(String text, String delimiter) throws CountryException {
        String[] items = text.split(delimiter);

        int numberOfItems = items.length;
//        if (numberOfItems != 5) throw new PlantException("Nesprávný počet položek na řádku! Očekáváme 5 položek, místo "+numberOfItems+" položek na řádku: "+text);
//
//        String name = items[0];
//        String notes = items[1];
//        try {
//            int frequencyOfWateringInDays = Integer.parseInt(items[2]);
//            LocalDate lastWatering = LocalDate.parse(items[3]);
//            LocalDate planted = LocalDate.parse(items[4]);
//
//            return new Plant(name, notes, frequencyOfWateringInDays, lastWatering, planted);
//        }
//        catch (DateTimeParseException ex) { throw new PlantException("Špatně zadané datum na řádku: \""+text+"\"\n\t"+ex.getLocalizedMessage()); }
//        catch (NumberFormatException ex) { throw new PlantException("Špatně zadané číslo na řádku: \""+text+"\"\n\t"+ex.getLocalizedMessage()); }

        return new Country(); //todo delme
    }


}
