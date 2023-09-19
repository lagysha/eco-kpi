package ua.kpi.eco.service;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ua.kpi.eco.dto.PollutionDto;

import java.io.IOException;


@Service
@RequiredArgsConstructor
public class FileUploadService {

    private final PollutionService pollutionService;

    @Transactional
    public void parseAndSave(MultipartFile file) throws IOException {
        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0); // assuming the data is in the first sheet
            for (Row row : sheet) {
                PollutionDto pollutionRow = parseRowToPollutionDto(row);
                pollutionService.create(pollutionRow);
            }
        }
    }

    private PollutionDto parseRowToPollutionDto(Row row) {
        Cell objectNameCell = row.getCell(0);
        Cell pollutantNameCell = row.getCell(1);
        Cell valuePollutionCell = row.getCell(2);
        Cell yearCell = row.getCell(3);

        String objectName = objectNameCell.getStringCellValue().trim();
        String pollutantName = pollutantNameCell.getStringCellValue().trim();
        double valuePollution = getValuePollution(valuePollutionCell);
        int year = (int) yearCell.getNumericCellValue();

        return new PollutionDto(objectName,"No Description provided", pollutantName, year, valuePollution);
    }

    private double getValuePollution(Cell valuePollutionCell) {
        if (valuePollutionCell.getCellType().equals(CellType.NUMERIC)) {
            return valuePollutionCell.getNumericCellValue();
        }
        String formattedValue = valuePollutionCell.getStringCellValue().replace(",", ".");
        return Double.parseDouble(formattedValue);
    }
}
