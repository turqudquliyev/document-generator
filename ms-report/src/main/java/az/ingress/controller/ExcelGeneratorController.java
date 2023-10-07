package az.ingress.controller;

import az.ingress.service.ExcelGeneratorService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;
import static org.springframework.http.MediaType.APPLICATION_OCTET_STREAM;

@RestController
@RequestMapping("/v1/excel")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class ExcelGeneratorController {
    ExcelGeneratorService excelGeneratorService;

    @PostMapping("/xlsx")
    public ResponseEntity<byte[]> generateXlsxReport() {
        var report = excelGeneratorService.generateXlsx();
        return createResponseEntity(report, "report.xlsx");
    }

    @PostMapping("/xls")
    public ResponseEntity<byte[]> generateXlsReport() {
        var report = excelGeneratorService.generateXls();
        return createResponseEntity(report, "report.xls");
    }

    private ResponseEntity<byte[]> createResponseEntity(byte[] report, String filename) {
        return ResponseEntity.ok()
                .contentType(APPLICATION_OCTET_STREAM)
                .header(CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .body(report);
    }
}