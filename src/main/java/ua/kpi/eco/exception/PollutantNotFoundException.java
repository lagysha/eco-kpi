package ua.kpi.eco.exception;

public class PollutantNotFoundException extends RuntimeException {

    public PollutantNotFoundException(String name) {
        super(String.format("Pollutant with %s doesn't exist in the database", name));
    }
}
