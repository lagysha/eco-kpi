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

    @Override
    public Long id() {
        return id;
    }

    @Override
    public String objectName() {
        return objectName;
    }

    @Override
    public String objectDescription() {
        return objectDescription;
    }

    @Override
    public String pollutantName() {
        return pollutantName;
    }

    @Override
    public int year() {
        return year;
    }

    @Override
    public double valuePollution() {
        return valuePollution;
    }
}
