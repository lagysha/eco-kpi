package ua.kpi.eco.dto;

public record PollutionDto(
        String objectName,
        String pollutantName,
        int year,
        double valuePollution
) {}
