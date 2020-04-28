package com.example.deneme.exception;

public class MetricNotFoundException extends Exception {
    private Integer id;

    public MetricNotFoundException(Integer id) {
        super(String.format("MetricEntity is not found with id : '%d'", id));

    }
}
