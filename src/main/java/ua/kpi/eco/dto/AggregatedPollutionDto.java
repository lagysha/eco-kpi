package ua.kpi.eco.dto;

public record AggregatedPollutionDto(
        Long id,
        String objectName,
        String objectDescription,
        String pollutantName,
        double valuePollution,
        Long mfr,
        Long tlv,
        int year
) {}
