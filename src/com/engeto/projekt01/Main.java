package com.engeto.projekt01;

public class Main {

    private static final String INPUT_FILE = "vat_eu.csv";
    private static final String FILE_ITEM_DELIMITER = "\t";
    public static final int DEFAULT_VAT_LIMIT_VALUE = 20;
    public static int vatLimitParameter = DEFAULT_VAT_LIMIT_VALUE;

    public static int readOneIntFromInput(int defaultVatLimitValue) {
        System.out.println("Please enter VAT limit parameter or press <enter> twice to leave default setting: ");
        int input = Support.safeReadInt(defaultVatLimitValue);
        return input;
    }

    public static void main(String[] args) {
        System.out.println("task 1: read input date from file ...");
        // task 1 načtení dat ze souboru
        CountryList countries = new CountryList();
        try {
            countries.importCountriesFromFile(INPUT_FILE, FILE_ITEM_DELIMITER);
        } catch (CountryException e) {
            System.err.println(e.getLocalizedMessage());
        }

        System.out.println();
        System.out.println("task 7: read limit parameter from imput default value=" + DEFAULT_VAT_LIMIT_VALUE + "%");
        vatLimitParameter = readOneIntFromInput(DEFAULT_VAT_LIMIT_VALUE);
        System.out.println("VAT limit parameter value=" + vatLimitParameter);

        System.out.println();
        System.out.println("task 2: print in format:  Název země (zkratka): základní sazba %");
        for (int i = 0; i < countries.size(); i++) {
            System.out.println(countries.getCountry(i).format1());
        }

        System.out.println();
        System.out.println("task 3: print only specified countries where vat > " + vatLimitParameter + "% with hasSpecialVatRate false");
        for (int i = 0; i < countries.size(); i++) {
            if (countries.getCountry(i).getVat() > vatLimitParameter && !countries.getCountry(i).useSpecialVatRate())
                System.out.println(countries.getCountry(i).format1());
        }

        countries.sortCountries();

        System.out.println();
        System.out.println("task 4: print list from task 3 ordered by vat descending ");
        for (int i = countries.size() - 1; i >= 0; i--) {
            if (countries.getCountry(i).getVat() > vatLimitParameter && !countries.getCountry(i).useSpecialVatRate())
                System.out.println(countries.getCountry(i).format1());
        }

        System.out.println();
        System.out.println("task 5: add to task3 info about other countries ");

        String additionalInfo = "Sazba VAT " + vatLimitParameter + "% nebo nižší nebo používají speciální sazbu: ";
        String lastLineCountryDelimiter = "";

        for (Country c : countries.getCountries()
        ) {
            if (c.getVat() > vatLimitParameter && !c.useSpecialVatRate()) {
                System.out.println(c.format2());
            } else {
                additionalInfo += lastLineCountryDelimiter + c.getCode();
                lastLineCountryDelimiter = ", ";
            }

        }

        System.out.println("=====================================");
        System.out.println(additionalInfo);

        System.out.println();
        System.out.println("task 6: store output from task 5 into a file");
        String outputFile = "vat-over-" + vatLimitParameter + ".txt";
        try {
            countries.exportToFile(outputFile, vatLimitParameter);
        } catch (CountryException e) {
            System.err.println(e.getLocalizedMessage());
        }
        System.out.println("Data exported into " + outputFile + " file ...");

        System.out.println("-- a to je konec :-)");


    }
}
