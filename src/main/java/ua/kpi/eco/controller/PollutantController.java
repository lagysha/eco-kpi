package ua.kpi.eco.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.kpi.eco.dto.PollutantDto;
import ua.kpi.eco.dto.PollutantResponseDto;
import ua.kpi.eco.service.PollutantService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/pollutions")
public class PollutantController {

    private final PollutantService pollutantService;

    @PostMapping
    public ResponseEntity<PollutantResponseDto> create(@RequestBody PollutantDto pollutantDto) {
        var pollutant = pollutantService.create(pollutantDto);
        return new ResponseEntity<>(pollutant, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PollutantResponseDto> update(@PathVariable Long id, @RequestBody PollutantDto pollutantDto) {
        var pollutant = pollutantService.update(id, pollutantDto);
        return ResponseEntity.ok(pollutant);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PollutantResponseDto> delete(@PathVariable Long id) {
        var pollutant = pollutantService.delete(id);
        return ResponseEntity.ok(pollutant);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PollutantResponseDto> get(@PathVariable Long id) {
        var pollutant = pollutantService.get(id);
        return ResponseEntity.ok(pollutant);
    }
}
