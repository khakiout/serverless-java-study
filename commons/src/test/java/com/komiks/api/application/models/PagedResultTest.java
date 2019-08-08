package com.komiks.api.application.models;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class PagedResultTest {

    @Test
    public void testSampleInstantionOfClass() {
        PagedResult<String> pagedResult = new PagedResult<>();
        String[] contents = new String[] { "A", "B" };
        PagedResult.PageMetadata metadata = pagedResult.getMetadata();
        metadata.currentPage = "1";
        metadata.nextPage = "2";
        metadata.previousPage = "0";
        metadata.resultCount = "2";
        metadata.totalCount = "2";

        pagedResult.setResults(Arrays.asList(contents));

        assertEquals(2, pagedResult.getResults().size());
        assertEquals("1", pagedResult.getMetadata().currentPage);
        assertEquals("2", pagedResult.getMetadata().nextPage);
        assertEquals("0", pagedResult.getMetadata().previousPage);
        assertEquals("2", pagedResult.getMetadata().resultCount);
        assertEquals("2", pagedResult.getMetadata().totalCount);

        pagedResult = new PagedResult<>();
        contents = new String[] { "A", "B", "C", "D" };
        metadata = pagedResult.getMetadata();
        metadata.currentPage = "3";
        metadata.nextPage = "4";
        metadata.previousPage = "2";
        metadata.resultCount = "4";
        metadata.totalCount = "14";

        pagedResult.setResults(Arrays.asList(contents));

        assertEquals(4, pagedResult.getResults().size());
        assertEquals("3", pagedResult.getMetadata().currentPage);
        assertEquals("4", pagedResult.getMetadata().nextPage);
        assertEquals("2", pagedResult.getMetadata().previousPage);
        assertEquals("4", pagedResult.getMetadata().resultCount);
        assertEquals("14", pagedResult.getMetadata().totalCount);
    }

}
