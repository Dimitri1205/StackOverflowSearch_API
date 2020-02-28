package com.company.enums;

public enum SortOrder {
    ASCENDING   ("asc"),
    DESCENDING  ("desc");

    private final String key;
    SortOrder(String key) { this.key = key; }

    public String toString() { return key; }
}
