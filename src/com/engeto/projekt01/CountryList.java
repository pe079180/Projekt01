package com.engeto.projekt01;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class CountryList {
    private static final String FILE_ITEM_DELIMITER = "\t";

    ArrayList<Country> countries = new ArrayList<>();

    public void addCountry(Country country) {
        countries.add(country);
    }


    public void importPlantsFromFile(String fileName) throws CountryException {
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)))) {
            int lineNumber = 0;
            while (scanner.hasNextLine()) {
                String record = scanner.nextLine();
                lineNumber++;
                try {
                    this.addCountry(Country.parse(record, FILE_ITEM_DELIMITER));
                } catch (CountryException e) {
                    throw new CountryException("Neplatný vstupní soubor "+fileName+" na řádku "+lineNumber+":\n\t"+e.getLocalizedMessage());
                }
            }
        } catch (FileNotFoundException e) {
            throw new CountryException("Vstupní soubor "+fileName+" nebyl nalezen: "+e.getLocalizedMessage());
        }
    }


}
