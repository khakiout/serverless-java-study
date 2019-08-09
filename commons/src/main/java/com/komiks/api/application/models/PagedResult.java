package com.komiks.api.application.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Paged Result model.
 * @param <T> the class of the results.
 */
public class PagedResult<T> {

    List<T> results;
    PageMetadata metadata;

    public PagedResult() {
        this.results = new ArrayList<>();
        this.metadata = this.new PageMetadata();
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public PageMetadata getMetadata() {
        return metadata;
    }

    /**
     * The metadata contents of a paged result.
     */
    public class PageMetadata {

        String currentPage;
        String nextPage;
        String previousPage;
        String totalCount;
        String resultCount;

        public String getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(String currentPage) {
            this.currentPage = currentPage;
        }

        public String getNextPage() {
            return nextPage;
        }

        public void setNextPage(String nextPage) {
            this.nextPage = nextPage;
        }

        public String getPreviousPage() {
            return previousPage;
        }

        public void setPreviousPage(String previousPage) {
            this.previousPage = previousPage;
        }

        public String getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(String totalCount) {
            this.totalCount = totalCount;
        }

        public String getResultCount() {
            return resultCount;
        }

        public void setResultCount(String resultCount) {
            this.resultCount = resultCount;
        }

    }

}
