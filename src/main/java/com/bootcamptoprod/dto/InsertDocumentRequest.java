package com.bootcamptoprod.dto;

/**
 * jsonDocument is a JSON string representing the document to insert.
 */
public record InsertDocumentRequest(String dbName, String collectionName, String jsonDocument) {
}