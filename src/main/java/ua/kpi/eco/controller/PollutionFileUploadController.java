package ua.kpi.eco.controller;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.kpi.eco.dto.AggregatedPollutionDto;
import ua.kpi.eco.service.PollutionFileUploadService;
import ua.kpi.eco.service.PollutionService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/file")
@RequiredArgsConstructor
public class PollutionFileUploadController {

    private final PollutionFileUploadService pollutionFileUploadService;
    private final PollutionService pollutionService;

    @PostMapping
    public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        pollutionFileUploadService.parseAndSave(file);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportToExcel() throws IOException {
        // Create a list of ExportDto data (replace with your actual data source)
        List<AggregatedPollutionDto> data = pollutionService.getAll();

        // Create a workbook and sheet
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Exported Data");

            // Create a header row
            Row headerRow = sheet.createRow(0);
            String[] headers = {
                    "ID", "Object Name", "Object Description", "Pollutant Name",
                    "Value Pollution", "Pollutant Mfr", "Pollutant Elv",
                    "Pollutant TLV", "Pollution Concentration", "Add Ladd", "Year"
            };

            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            // Add data rows
            int rowNum = 1;
            for (AggregatedPollutionDto exportDto : data) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(exportDto.id());
                row.createCell(1).setCellValue(exportDto.objectName());
                row.createCell(2).setCellValue(exportDto.objectDescription());
                row.createCell(3).setCellValue(exportDto.pollutantName());
                row.createCell(4).setCellValue(exportDto.valuePollution());
                row.createCell(5).setCellValue(exportDto.pollutantMfr());
                row.createCell(6).setCellValue(exportDto.pollutantElv());
                row.createCell(7).setCellValue(exportDto.pollutantTlv());
                row.createCell(8).setCellValue(exportDto.pollutionConcentration());
                row.createCell(9).setCellValue(exportDto.addLadd());
                row.createCell(10).setCellValue(exportDto.year());
            }

            // Write the workbook to a byte array
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            byte[] excelBytes = outputStream.toByteArray();

            // Set appropriate HTTP headers
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            httpHeaders.setContentDispositionFormData("attachment", "exported_data.xlsx");
            httpHeaders.setContentLength(excelBytes.length);

            return ResponseEntity.ok().headers(httpHeaders).body(excelBytes);
        }
    }
}
