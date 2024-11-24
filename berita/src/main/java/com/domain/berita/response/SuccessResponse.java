package com.domain.berita.response;

import java.util.Map;

public class SuccessResponse<T> {
    private Meta meta;
    private T data;
    private Map<String, Boolean> permission;

    public SuccessResponse(int status, String message, T data, Map<String, Boolean> permission) {
        this.meta = new Meta(status, message);
        this.data = data;
        this.permission = permission;
    }

    // Getters and Setters
    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Map<String, Boolean> getPermission() {
        return permission;
    }

    public void setPermission(Map<String, Boolean> permission) {
        this.permission = permission;
    }

    // Inner class for meta
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
}
