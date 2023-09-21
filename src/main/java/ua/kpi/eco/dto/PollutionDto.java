package ua.kpi.eco.dto;

public record PollutionDto(
        Long id,
        String objectName,
        String objectDescription,
        String pollutantName,
        int year,
        double valuePollution
) {
    public PollutionDto(String objectName, String objectDescription, String pollutantName, int year, double valuePollution) {
        this(null, objectName, objectDescription, pollutantName, year, valuePollution);
    }
}
