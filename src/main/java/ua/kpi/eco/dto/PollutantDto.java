package ua.kpi.eco.dto;

public record PollutantDto (
    Long id,
    String name,
    Long tlv,
    Long mfr
) {}
