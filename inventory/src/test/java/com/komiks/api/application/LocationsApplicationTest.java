package com.komiks.api.application;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;
import org.junit.Test;

public class LocationsApplicationTest {

    private LocationsApplication locationsApplication = new LocationsApplication();

    @Test
    public void testDirectory() throws Exception {
        Map<String, Map<String, List<String>>> directory = locationsApplication.getDirectory();

        assertNotNull(directory);
        assertEquals(1, directory.size());

        Map<String, List<String>> cities = directory.get("NCR - National Capital Region");
        assertEquals(14, cities.size());
        assertEquals(33, cities.get("CITY OF MAKATI").size());
        assertEquals(21, cities.get("CITY OF MALABON").size());
        assertEquals(10, cities.get("PATEROS").size());
        assertEquals(30, cities.get("CITY OF PASIG").size());
        assertEquals(9, cities.get("CITY OF MUNTINLUPA").size());
        assertEquals(20, cities.get("CITY OF LAS PIÑAS").size());
        assertEquals(142, cities.get("QUEZON CITY").size());
        assertEquals(16, cities.get("CITY OF MARIKINA").size());
        assertEquals(28, cities.get("TAGUIG CITY").size());
        assertEquals(16, cities.get("CITY OF PARAÑAQUE").size());
        assertEquals(33, cities.get("CITY OF VALENZUELA").size());
        assertEquals(27, cities.get("CITY OF MANDALUYONG").size());
        assertEquals(14, cities.get("CITY OF NAVOTAS").size());
        assertEquals(21, cities.get("CITY OF SAN JUAN").size());

        int barangayCount = cities.values()
            .stream()
            .map(List::size)
            .reduce(Integer::sum)
            .orElse(0);

        assertEquals(420, barangayCount);

    }

}
