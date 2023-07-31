package exceltokakao;

import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SavetoExcel {

    // 엑셀 파일 생성 및 데이터 저장 메서드
    public void saveToExcelSheet(String[] data) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("DataSheet");

        Row row = sheet.createRow(0);
        for (int i = 0; i < data.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(data[i]);
        }

        try {
            FileOutputStream fileOut = new FileOutputStream("data.xlsx");
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
            System.out.println("데이터가 엑셀 파일에 저장되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
