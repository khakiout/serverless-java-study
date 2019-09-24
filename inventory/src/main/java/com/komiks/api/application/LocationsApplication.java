package com.komiks.api.application;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class LocationsApplication {

    String fileName = "/Users/ruenzapanta/Downloads/barangays-ncr-reduced.csv";

    public Map<String, Map<String, List<String>>> getDirectory() throws IOException {
        Path path = Paths.get(fileName);

        Map<String, List<String>> cities = new HashMap<>();
        try (
            Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                .withHeader("id", "name", "urban_rural", "population", "city", "province", "region")
                .withIgnoreHeaderCase(true)
                .withSkipHeaderRecord(true)
                .withTrim());
        ) {
            for (CSVRecord csvRecord : csvParser) {
                // Accessing values by the names assigned to each column
                String barangay = csvRecord.get("name");
                String city = csvRecord.get("city");

                List<String> barangays = cities.getOrDefault(city, new ArrayList<>());
                barangays.add(barangay);
                cities.put(city, barangays);
            }
        }

        Map<String, Map<String, List<String>>> directory = new HashMap<>();
        directory.put("NCR - National Capital Region", cities);

        return directory;
    }
}
