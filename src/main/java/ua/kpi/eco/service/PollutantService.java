package ua.kpi.eco.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kpi.eco.dto.PollutantDto;
import ua.kpi.eco.dto.PollutantResponseDto;
import ua.kpi.eco.exception.PollutantNotFoundException;
import ua.kpi.eco.mapper.PollutantMapper;
import ua.kpi.eco.model.Pollutant;
import ua.kpi.eco.repository.PollutantRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PollutantService {
    private final PollutantRepository pollutantRepository;
    private final PollutantMapper pollutantMapper;
    @Transactional
    public PollutantResponseDto create(PollutantDto pollutantDto) {
        var pollutantToCrete = pollutantMapper.pollutantDtoToPollutant(pollutantDto);
        var createdPollutant = pollutantRepository.save(pollutantToCrete);
        return pollutantMapper.pollutantToPollutantResponseDto(createdPollutant);
    }
    @Transactional
    public PollutantResponseDto update(Long id, PollutantDto pollutantDto) {
        Pollutant pollutant = pollutantRepository.findById(id).orElseThrow(() -> new PollutantNotFoundException("id = " + id));
        pollutant.setName(pollutantDto.name());
        pollutant.setMfr(pollutantDto.mfr());
        pollutant.setElv(pollutantDto.elv());
        pollutant.setTlv(pollutantDto.tlv());
        pollutant.setSf(pollutantDto.sf());
        pollutant.setRfc(pollutantDto.rfc());
        return pollutantMapper.pollutantToPollutantResponseDto(pollutant);
    }
    @Transactional
    public void delete(Long id) {
        pollutantRepository.findById(id).orElseThrow(() -> new PollutantNotFoundException("id = " + id));
        pollutantRepository.deleteById(id);
    }

    public PollutantResponseDto get(Long id) {
        var retrievedPollutant = pollutantRepository.findById(id).orElseThrow(() -> new PollutantNotFoundException("id = " + id));
        return pollutantMapper.pollutantToPollutantResponseDto(retrievedPollutant);
    }

    public List<PollutantResponseDto> getAll() {
        return pollutantRepository.findAllBy(PollutantResponseDto.class);
    }
}
