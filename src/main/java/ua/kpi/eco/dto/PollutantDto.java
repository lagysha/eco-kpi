package ua.kpi.eco.dto;

import jakarta.validation.constraints.NotEmpty;

public record PollutantDto (
    @NotEmpty
    String name,
    @NotEmpty
    Long elv,
    @NotEmpty
    Long mfr,
    @NotEmpty
    double tlv
) {}
