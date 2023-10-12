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
public class PollutionFileUploadService {

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
        Cell concentrationCell = row.getCell(4);

        String objectName = objectNameCell.getStringCellValue().trim();
        String pollutantName = pollutantNameCell.getStringCellValue().trim();
        double valuePollution = getDoubleFromCell(valuePollutionCell);
        int year = (int) yearCell.getNumericCellValue();
        double pollutionConcentration = getDoubleFromCell(concentrationCell);

        return new PollutionDto(objectName,"No Description provided",
                pollutantName, year, valuePollution, pollutionConcentration,0,0);
    }

    private double getDoubleFromCell(Cell numericCell) {
        if (numericCell == null) {
            return 0;
        }
        if (numericCell.getCellType().equals(CellType.NUMERIC)) {
            return numericCell.getNumericCellValue();
        }
        String formattedValue = numericCell.getStringCellValue().replace(",", ".");
        return Double.parseDouble(formattedValue);
    }
}
