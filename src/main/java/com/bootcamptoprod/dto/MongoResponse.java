package com.bootcamptoprod.dto;

/**
 * Generic wrapper response used by the agent exports.
 * T can be List<String>, List<Document>, String, etc.
 */
public record MongoResponse<T>(T data) {
}