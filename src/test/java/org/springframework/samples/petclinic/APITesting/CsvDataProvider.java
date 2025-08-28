package org.springframework.samples.petclinic.APITesting;
import com.opencsv.CSVReader;
import org.testng.annotations.DataProvider;

import java.io.FileReader;
import java.util.Iterator;

public class CsvDataProvider {
    @DataProvider(name = "ownersData")
    public Iterator<Object[]> readOwners() throws Exception {
        CSVReader reader = new CSVReader(new FileReader("src/test/resources/datasets/owners.csv"));
        return reader.readAll().stream()
                .skip(1)
                .map(line -> new Object[]{ line[0], line[1], line[2], line[3], line[4] })
                .iterator();
    }

    @DataProvider(name = "petsData")
    public Iterator<Object[]> readPets() throws Exception {
        CSVReader reader = new CSVReader(new FileReader("src/test/resources/datasets/pets.csv"));
        return reader.readAll().stream()
                .skip(1)
                .map(line -> new Object[]{ line[0], line[1], line[2], line[3] })
                .iterator();
    }

     @DataProvider(name = "visitsData")
    public Iterator<Object[]> readVisits() throws Exception {
        CSVReader reader = new CSVReader(new FileReader("src/test/resources/datasets/visits.csv"));
        return reader.readAll().stream()
                .skip(1)
                .map(line -> new Object[]{ line[0], line[1], line[2], line[3] })
                .iterator();
    }
}