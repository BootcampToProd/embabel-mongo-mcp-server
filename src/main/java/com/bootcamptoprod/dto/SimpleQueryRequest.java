package com.bootcamptoprod.dto;

/**
 * Simple equality query: field = value
 * value is typed as Object to allow numbers, strings, booleans, etc.
 */
public record SimpleQueryRequest(String dbName, String collectionName, String field, Object value) {
}