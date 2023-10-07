package az.ingress.service;

import az.ingress.client.ProductClient;
import az.ingress.model.client.Product;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

import static az.ingress.model.constant.ProductHeader.ALL_HEADERS;
import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class ExcelGeneratorService {
    ProductClient productClient;

    public byte[] generateXlsx() {
        var products = productClient.getAllProducts();
        var workbook = new XSSFWorkbook();
        return generateReport(workbook, products);
    }

    public byte[] generateXls() {
        List<Product> products = productClient.getAllProducts();
        var workbook = new HSSFWorkbook();
        return generateReport(workbook, products);
    }


    @SneakyThrows
    private byte[] generateReport(Workbook workbook, List<Product> products) {
        Sheet sheet = workbook.createSheet();
        setColumnWidth(sheet);
        createHeaderRow(sheet);
        createDataRows(sheet, products);
        var out = new ByteArrayOutputStream();
        workbook.write(out);
        out.close();
        workbook.close();
        return out.toByteArray();
    }

    private void setColumnWidth(Sheet sheet) {
        for (int columnIndex = 0; columnIndex < ALL_HEADERS.length; columnIndex++) {
            sheet.setColumnWidth(columnIndex, 256 * 20);
        }
    }

    private void createHeaderRow(Sheet sheet) {
        var row = sheet.createRow(0);
        for (int columnNumber = 0; columnNumber < ALL_HEADERS.length; columnNumber++) {
            var cell = row.createCell(columnNumber);
            cell.setCellValue(ALL_HEADERS[columnNumber]);
        }
    }

    private void createDataRows(Sheet sheet, List<Product> products) {
        int rowNumber = 1;
        for (Product product : products) {
            Row row = sheet.createRow(rowNumber++);
            int cellNumber = 0;
            row.createCell(cellNumber++).setCellValue(product.id());
            row.createCell(cellNumber++).setCellValue(product.name());
            row.createCell(cellNumber).setCellValue(product.stock());
        }
    }
}