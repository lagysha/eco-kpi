package ua.kpi.eco.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kpi.eco.dto.PollutionDto;
import ua.kpi.eco.exception.PollutantNotFoundException;
import ua.kpi.eco.model.Object;
import ua.kpi.eco.model.Pollutant;
import ua.kpi.eco.model.Pollution;
import ua.kpi.eco.repository.ObjectRepository;
import ua.kpi.eco.repository.PollutantRepository;
import ua.kpi.eco.repository.PollutionRepository;

@Service
@RequiredArgsConstructor
public class PollutionService {

    private final PollutionRepository pollutionRepository;
    private final PollutantRepository pollutantRepository;
    private final ObjectRepository objectRepository;

    @Transactional
    public void create(PollutionDto pollutionDto) {
        Pollutant pollutant = pollutantRepository.findByName(pollutionDto.pollutantName())
                .orElseThrow(() -> new PollutantNotFoundException(pollutionDto.pollutantName()));
        Object object = objectRepository.findByName(pollutionDto.objectName())
                .orElse(new Object(pollutionDto.objectName()));

        Pollution pollution = Pollution.builder()
                .pollutant(pollutant)
                .object(object)
                .valuePollution(pollutionDto.valuePollution())
                .year(pollutionDto.year())
                .build();
        pollutionRepository.save(pollution);
    }
}
