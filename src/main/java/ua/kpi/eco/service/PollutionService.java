package ua.kpi.eco.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kpi.eco.dto.AggregatedPollutionDto;
import ua.kpi.eco.dto.PollutionDto;
import ua.kpi.eco.dto.PollutionResponseDto;
import ua.kpi.eco.exception.ObjectNotFoundException;
import ua.kpi.eco.exception.PollutantNotFoundException;
import ua.kpi.eco.exception.PollutionNotFoundException;
import ua.kpi.eco.mapper.PollutionMapper;
import ua.kpi.eco.model.Object;
import ua.kpi.eco.model.Pollutant;
import ua.kpi.eco.model.Pollution;
import ua.kpi.eco.repository.ObjectRepository;
import ua.kpi.eco.repository.PollutantRepository;
import ua.kpi.eco.repository.PollutionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PollutionService {

    private final PollutionRepository pollutionRepository;
    private final PollutantRepository pollutantRepository;
    private final ObjectRepository objectRepository;
    private final PollutionMapper pollutionMapper;

    public List<AggregatedPollutionDto> getAll() {
        return pollutionRepository.findAllByOrderById(AggregatedPollutionDto.class);
    }

    @Transactional
    public PollutionResponseDto create(PollutionDto pollutionDto) {
        Pollutant pollutant = pollutantRepository.findByNameIgnoreCase(pollutionDto.pollutantName())
                .orElseThrow(() -> new PollutantNotFoundException("name = " + pollutionDto.pollutantName()));
        Object object = objectRepository.findByNameIgnoreCase(pollutionDto.objectName())
                .orElse(new Object(pollutionDto.objectName()));

        Pollution pollution = Pollution.builder()
                .pollutant(pollutant)
                .object(object)
                .valuePollution(pollutionDto.valuePollution())
                .year(pollutionDto.year())
                .pollutionConcentration(pollutionDto.pollutionConcentration())
                .addLadd(Pollution.calculateAddLadd(pollutionDto.pollutionConcentration()))
                .build();
        pollutionRepository.save(pollution);

        return pollutionMapper.entityToPollutionResponse(pollution);
    }

    @Transactional
    public void delete(Long id) {
        pollutionRepository.findById(id)
                .orElseThrow(() -> new PollutionNotFoundException("id = " + id));
        pollutionRepository.deleteById(id);
    }

    @Transactional
    public PollutionResponseDto update(Long id, PollutionDto pollutionDto) {
        Pollution pollution = pollutionRepository.findById(id)
                .orElseThrow(() -> new PollutionNotFoundException("id = " + id));
        Object object = objectRepository.findByNameIgnoreCase(pollutionDto.objectName())
                .orElseThrow(() -> new ObjectNotFoundException("name = " + pollutionDto.objectName()));
        Pollutant pollutant = pollutantRepository.findByNameIgnoreCase(pollutionDto.pollutantName())
                .orElseThrow(() -> new PollutantNotFoundException("name = " +pollutionDto.pollutantName()));

        object.setDescription(pollutionDto.objectDescription());
        pollution.setObject(object);
        pollution.setPollutant(pollutant);
        pollution.setValuePollution(pollutionDto.valuePollution());
        pollution.setYear(pollutionDto.year());
        pollution.setAddLadd(Pollution.calculateAddLadd(pollutionDto.pollutionConcentration()));
        pollution.setPollutionConcentration(pollutionDto.pollutionConcentration());

        return pollutionMapper.entityToPollutionResponse(pollution);
    }

    public PollutionResponseDto read(Long id) {
        return pollutionRepository.findPollutionById(id, PollutionResponseDto.class)
                .orElseThrow(() -> new PollutionNotFoundException("id = " + id));
    }
}
