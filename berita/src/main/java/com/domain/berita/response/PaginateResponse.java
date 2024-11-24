package com.domain.berita.response;

public class PaginateResponse<T> {
    private Meta meta;
    private Pagination paginate;
    private T data;

    public PaginateResponse(int status, String message, Pagination paginate, T data) {
        this.meta = new Meta(status, message);
        this.paginate = paginate;
        this.data = data;

    }

    // Getters and Setters
    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Pagination getPaginate() {
        return paginate;
    }

    public void setPaginate(Pagination paginate) {
        this.paginate = paginate;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    // Inner class for Meta
    public static class Meta {
        private int status;
        private String message;

        public Meta(int status, String message) {
            this.status = status;
            this.message = message;
        }

        // Getters and Setters
        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    // Inner class for Pagination
    public static class Pagination {
        private int page;
        private int perPage;
        private int nextPage;
        private int totalPage;
        private int totalData;
        private String nextUrl;
        private String prevUrl;

        public Pagination(int page, int perPage, int nextPage, int totalPage, int totalData, String nextUrl,
                String prevUrl) {
            this.page = page;
            this.perPage = perPage;
            this.nextPage = nextPage;
            this.totalPage = totalPage;
            this.totalData = totalData;
            this.nextUrl = nextUrl;
            this.prevUrl = prevUrl;
        }

        // Getters and Setters
        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getPerPage() { // Getter untuk perPage
            return perPage;
        }

        public void setPerPage(int perPage) { // Setter untuk perPage
            this.perPage = perPage;
        }

        public int getNextPage() {
            return nextPage;
        }

        public void setNextPage(int nextPage) {
            this.nextPage = nextPage;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public int getTotalData() {
            return totalData;
        }

        public void setTotalData(int totalData) {
            this.totalData = totalData;
        }

        public String getNextUrl() {
            return nextUrl;
        }

        public void setNextUrl(String nextUrl) {
            this.nextUrl = nextUrl;
        }

        public String getPrevUrl() {
            return prevUrl;
        }

        public void setPrevUrl(String prevUrl) {
            this.prevUrl = prevUrl;
        }
    }
}
