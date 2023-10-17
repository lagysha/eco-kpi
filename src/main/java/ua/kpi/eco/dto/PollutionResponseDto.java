package ua.kpi.eco.dto;

import jakarta.validation.constraints.NotEmpty;

public record PollutionResponseDto(
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
        double pollutionConcentration,
        @NotEmpty
        double hq,
        @NotEmpty
        double cr,
        @NotEmpty
        double fee
) {
    public PollutionResponseDto(String objectName, String objectDescription, String pollutantName,
                        int year, double valuePollution, double pollutionConcentration,double hq,double cr,double fee) {
        this(null, objectName, objectDescription, pollutantName, year, valuePollution, pollutionConcentration,hq,cr,fee);
    }
}
