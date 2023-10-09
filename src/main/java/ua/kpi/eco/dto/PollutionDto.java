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
        double valuePollution,
        @NotEmpty
        double pollutionConcentration
) {
    public PollutionDto(String objectName, String objectDescription, String pollutantName,
                        int year, double valuePollution, double pollutionConcentration) {
        this(null, objectName, objectDescription, pollutantName, year, valuePollution, pollutionConcentration);
    }
}
