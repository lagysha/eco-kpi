package ua.kpi.eco.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kpi.eco.dto.AggregatedPollutionDto;
import ua.kpi.eco.dto.PollutionDto;
import ua.kpi.eco.exception.PollutantNotFoundException;
import ua.kpi.eco.exception.PollutionNotFoundException;
import ua.kpi.eco.model.Object;
import ua.kpi.eco.model.Pollutant;
import ua.kpi.eco.model.Pollution;
import ua.kpi.eco.repository.ObjectRepository;
import ua.kpi.eco.repository.PollutantRepository;
import ua.kpi.eco.repository.PollutionRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PollutionService {

    private final PollutionRepository pollutionRepository;
    private final PollutantRepository pollutantRepository;
    private final ObjectRepository objectRepository;


    public List<AggregatedPollutionDto> getAll() {
        List<Pollution> pollutions = pollutionRepository.findAll();
        return pollutions.stream()
                .map(this::convertPollutionToAggregatedPollutionDto)
                .collect(Collectors.toList());
    }

    private AggregatedPollutionDto convertPollutionToAggregatedPollutionDto(Pollution pollution){
        return new AggregatedPollutionDto(
                pollution.getId(),
                pollution.getObject().getName(),
                pollution.getObject().getDescription(),
                pollution.getPollutant().getName(),
                pollution.getValuePollution(),
                pollution.getPollutant().getMfr(),
                pollution.getPollutant().getTlv(),
                pollution.getYear()
        );
    }

    @Transactional
    public PollutionDto create(PollutionDto pollutionDto) {
        Pollutant pollutant = pollutantRepository.findByNameIgnoreCase(pollutionDto.pollutantName())
                .orElseThrow(() -> new PollutantNotFoundException(pollutionDto.pollutantName()));
        Object object = objectRepository.findByNameIgnoreCase(pollutionDto.objectName())
                .orElse(new Object(pollutionDto.objectName(),pollutionDto.objectDescription()));

        Pollution pollution = Pollution.builder()
                .pollutant(pollutant)
                .object(object)
                .valuePollution(pollutionDto.valuePollution())
                .year(pollutionDto.year())
                .build();
        pollutionRepository.save(pollution);
        return new PollutionDto(object.getName(),
                object.getDescription(),
                pollutant.getName(),
                pollution.getYear(),
                pollution.getValuePollution());
    }


    @Transactional
    public void delete(Long id) {
        pollutionRepository.findById(id)
                .orElseThrow(() -> new PollutionNotFoundException("id = " + id));
        pollutionRepository.deleteById(id);
    }


    @Transactional
    public PollutionDto update(Long id,PollutionDto pollutionDto) {
        Pollution pollution = pollutionRepository.findById(id)
                .orElseThrow(() -> new PollutionNotFoundException("id = " + id));
        Object object = objectRepository.findByNameIgnoreCase(pollutionDto.objectName())
                .orElse(new Object(pollutionDto.objectName(),pollutionDto.objectDescription()));
        Pollutant pollutant = pollutantRepository.findByNameIgnoreCase(pollutionDto.pollutantName())
                .orElseThrow(() -> new PollutantNotFoundException(pollutionDto.pollutantName()));
        object.setDescription(pollutionDto.objectDescription());
        pollution.setObject(object);
        pollution.setPollutant(pollutant);
        pollution.setValuePollution(pollutionDto.valuePollution());
        pollution.setYear(pollutionDto.year());

        return new PollutionDto(object.getName(),
                object.getDescription(),
                pollutant.getName(),
                pollution.getYear(),
                pollution.getValuePollution());
    }

    public PollutionDto read(Long id) {
        Pollution pollution = pollutionRepository.findById(id)
                .orElseThrow(() -> new PollutionNotFoundException("id = " + id));
        Object object = pollution.getObject();
        Pollutant pollutant = pollution.getPollutant();
        return new PollutionDto(
                object.getName(),
                object.getDescription(),
                pollutant.getName(),
                pollution.getYear(),
                pollution.getValuePollution());
    }
}
