package ua.kpi.eco.dto;

import jakarta.validation.constraints.NotNull;

public record PollutionDto(
        Long id,
        @NotNull
        String objectName,
        @NotNull
        String objectDescription,
        @NotNull
        String pollutantName,
        @NotNull
        int year,
        @NotNull
        double valuePollution,
        @NotNull
        double pollutionConcentration,
        @NotNull
        double hq,
        @NotNull
        double cr
) {
    public PollutionDto(String objectName, String objectDescription, String pollutantName,
                        int year, double valuePollution, double pollutionConcentration, double hq, double cr) {
        this(null, objectName, objectDescription, pollutantName, year, valuePollution, pollutionConcentration,hq,cr);
    }
}
