package com.engeto.projekt01;

import java.util.Collections;

public class Main {

    private static final String INPUT_FILE = "vat_eu.csv";
    private static final String FILE_ITEM_DELIMITER = "\t";
    public static Double limitParameter = 20.0;
    public static String lastLineCountryDelimiter = "";

    public static void main(String[] args) {
        // task 1 načtení dat ze souboru
        CountryList countries = new CountryList();
        try {
            countries.importCountriesFromFile(INPUT_FILE, FILE_ITEM_DELIMITER);
        } catch (CountryException e) {
            System.err.println(e.getLocalizedMessage());
        }

        //  task 2: print in format:  Název země (zkratka): základní sazba %
        System.out.println("task 2: print in format:  Název země (zkratka): základní sazba %");
        for (int i = 0; i < countries.size(); i++) {
            System.out.println(countries.getCountry(i).format2());
        }

        System.out.println();
        System.out.println("task 3: print only specified countries where vat > 20% with hasSpecialVatRate false");
        for (int i = 0; i < countries.size(); i++) {
            if (countries.getCountry(i).getVat() > 20 && countries.getCountry(i).useSpecialVatRate() == false)
                System.out.println(countries.getCountry(i).format1());
        }

        countries.sortCountries();

        System.out.println();
        System.out.println("task 4: print list from task 3 ordered by vat descending ");
        for (int i = countries.size()-1; i >= 0 ; i--) {
            if (countries.getCountry(i).getVat() > 20 && countries.getCountry(i).useSpecialVatRate() == false)
                System.out.println(countries.getCountry(i).format1());
        }

        System.out.println();
        System.out.println("task 5: add to task3 info about other countries ");

        String additionalInfo = "Sazba VAT " + limitParameter +"% nebo nižší nebo používají speciální sazbu: ";

        for (Country c : countries.getCountries()
        ) {
            if (c.getVat() > limitParameter && c.useSpecialVatRate() == false) {
                System.out.println(c.format2());
            } else {
                additionalInfo += lastLineCountryDelimiter + c.getCode() ;
                lastLineCountryDelimiter = ", ";
            }

        }
        System.out.println("=====================================");
        System.out.println(additionalInfo);


        System.out.println("-- konec :-)");


//        System.out.println();
//        System.out.println("pokus1");
//        countries.countries.forEach(i -> {
//            System.out.println(i);
//        });
//
//        System.out.println();
//        System.out.println("pokus2");
//        countries.countries.forEach(System.out::println);
//
//        System.out.println();
//        System.out.println("pokus3");
//        for (Country c : countries.countries
//        ) {
//            System.out.println(c);
//
//        }
//        System.out.println("pokus4");
//        for (Country country : countries.countries
//        ) {
//            System.out.println(country);
//
//        }

    }
}
