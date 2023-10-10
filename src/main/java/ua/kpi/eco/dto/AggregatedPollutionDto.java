package ua.kpi.eco.dto;

public record AggregatedPollutionDto(
        Long id,
        String objectName,
        String objectDescription,
        String pollutantName,
        double valuePollution,
        Long pollutantMfr,
        Long pollutantElv,
        double pollutantTlv,
        double pollutionConcentration,
        double addLadd,
        int year
) {}
