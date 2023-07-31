package exceltokakao;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.logging.log4j.message.Message;
import org.apache.poi.ss.usermodel.*;

public class ExcelReaderBasic {

    public static void main(String[] args) {
        try {
            // 엑셀 파일 경로
            String filePath = "고객정보.xlsx";

            FileInputStream file = new FileInputStream(filePath);

            // 워크북 열기
            Workbook workbook = WorkbookFactory.create(file);

            // 첫 번째 시트 선택
            Sheet sheet = workbook.getSheetAt(0);

            // 각 행(iterate rows)
            for (Row row : sheet) {
                // 첫 번째 열의 데이터 읽기 (고객 이름)
                Cell nameCell = row.getCell(0);
                if (nameCell != null) {
                    String customerName = "";
                    switch (nameCell.getCellType()) {
                        case STRING:
                            customerName = nameCell.getStringCellValue();
                            break;
                        default:
                            // 처리하지 않는 셀 타입에 대한 기본 처리
                            break;
                    }

                    // 두 번째 열의 데이터 읽기 (전화번호)
                    Cell phoneCell = row.getCell(1);
                    if (phoneCell != null) {
                        String phoneNumber = "";
                        switch (phoneCell.getCellType()) {
                            case STRING:
                                phoneNumber = phoneCell.getStringCellValue();
                                break;
                            default:
                                // 처리하지 않는 셀 타입에 대한 기본 처리
                                break;
                        }

                        // 고객 정보를 기반으로 카카오톡 메시지 발송
                        try {
                            sendKakaoTalkMessage(phoneNumber, customerName);
                        } catch (Exception e) {

                            e.printStackTrace();
                        }
                    }
                }
            }

            // 워크북 닫기
            workbook.close();

            // 파일 닫기
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendKakaoTalkMessage(String phoneNumber, String name) throws Exception {
        // API 요청 URL
        String url = "https://kapi.kakao.com/v2/api/talk/memo/send";

        // 카카오톡 API 토큰
        String accessToken = "G1N1jc7xiRxHJ_WZfKFnNIMVKBWkUquiCE0f98WaCj11GgAAAYj2OPJA";
        String message = name + "님, 안녕하세요. 주문이 완료되었습니다.";

        // 요청 헤더 설정
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "Bearer " + accessToken);
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        // conn.setRequestProperty("template_id", "95312");
        // ,\"template_id\":\"95312\"
        // \"button_title\":\" 1 \"

        // 요청 바디 설정

        String requestBody = "template_id=" + URLEncoder.encode("95312", "UTF-8");

        conn.setDoOutput(true);
        OutputStream outputStream = conn.getOutputStream();
        outputStream.write(requestBody.getBytes());
        outputStream.flush();
        outputStream.close();

        // 응답 처리
        int responseCode = conn.getResponseCode();
        BufferedReader reader;
        if (responseCode == HttpURLConnection.HTTP_OK) {
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        System.out.println(response.toString());
    }
}