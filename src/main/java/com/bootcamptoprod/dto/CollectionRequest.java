package com.bootcamptoprod.dto;

/**
 * For operations that need dbName + collectionName (e.g., listIndexes).
 */
public record CollectionRequest(String dbName, String collectionName) {
}