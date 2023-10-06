package ua.kpi.eco.dto;

import jakarta.validation.constraints.NotEmpty;

public record PollutionDto(
        Long id,
        @NotEmpty
        String objectName,
        @NotEmpty
        String objectDescription,
        @NotEmpty
        String pollutantName,
        @NotEmpty
        int year,
        @NotEmpty
        double valuePollution
) {
    public PollutionDto(String objectName, String objectDescription, String pollutantName, int year, double valuePollution) {
        this(null, objectName, objectDescription, pollutantName, year, valuePollution);
    }
}
