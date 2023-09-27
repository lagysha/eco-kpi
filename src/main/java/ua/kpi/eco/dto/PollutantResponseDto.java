package ua.kpi.eco.dto;

public record PollutantResponseDto(
        Long id,
        String name,
        Long tlv,
        Long mfr
) {}
