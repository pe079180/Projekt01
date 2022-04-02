package com.engeto.projekt01;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class CountryList {

    ArrayList<Country> countries = new ArrayList<>();

    public ArrayList<Country> getCountries() {
        return countries;
    }

    public void setCountries(ArrayList<Country> countries) {
        this.countries = countries;
    }

    public void addCountry(Country country) {
        countries.add(country);
    }

    public Country getCountry(int index) {
        return countries.get(index);
    }

    public int size() {
        return countries.size();
    }

    public void sortCountries() {
        Collections.sort(countries);
    }


    public void importCountriesFromFile(String fileName, String fileItemDelimiter) throws CountryException {
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)))) {
            int lineNumber = 0;
            while (scanner.hasNextLine()) {
                String record = scanner.nextLine();
                lineNumber++;
                try {
                    this.addCountry(Country.parse(record, fileItemDelimiter));
                } catch (CountryException e) {
                    throw new CountryException("Neplatný vstupní soubor " + fileName + " na řádku " + lineNumber + ":\n\t" + e.getLocalizedMessage());
                }
            }
        } catch (FileNotFoundException e) {
            throw new CountryException("Vstupní soubor " + fileName + " nebyl nalezen: " + e.getLocalizedMessage());
        }
    }

    public void exportToFile(String fileName, int vatLimitParameter) throws CountryException {
        String additionalInfo = "Sazba VAT " + vatLimitParameter + "% nebo nižší nebo používají speciální sazbu: ";
        String lastLineCountryDelimiter = "";
        int lineNumber = 0;
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))) {
            for (Country country : countries) {
                if (country.getVat() > vatLimitParameter && !country.useSpecialVatRate()) {
                    writer.println(country.format2());
                } else {
                    additionalInfo += lastLineCountryDelimiter + country.getCode();
                    lastLineCountryDelimiter = ", ";
                }
                lineNumber++;
            }
            writer.println("=====================================");
            writer.println(additionalInfo);

        } catch (IOException e) {
            throw new CountryException("Error writing to : " + fileName + " line " + lineNumber + ": " + e.getLocalizedMessage());
        }
    }


}
