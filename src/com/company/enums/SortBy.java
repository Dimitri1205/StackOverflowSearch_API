package com.company.enums;

public enum SortBy {
    RELEVANCE	("relevance"),
    VOTES		("votes"),
    CREATION	("creation"),
    ACTIVITY	("activity");

    private final String key;
    SortBy(String key) { this.key = key; }

    public String toString() { return key; }
}