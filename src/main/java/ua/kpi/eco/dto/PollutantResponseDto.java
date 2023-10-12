package ua.kpi.eco.dto;

public record PollutantResponseDto(
        Long id,
        String name,
        Long elv,
        Long mfr,
        double tlv,
        double sf,
        double rfc
) {}
