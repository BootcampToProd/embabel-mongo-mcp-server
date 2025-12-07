package com.bootcamptoprod.dto;

/**
 * jsonQuery expects a JSON string representing the Mongo query document (e.g. {"age": {"$gt": 30}})
 */
public record ComplexQueryRequest(String dbName, String collectionName, String jsonQuery) {
}