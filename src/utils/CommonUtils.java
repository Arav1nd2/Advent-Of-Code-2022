package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class CommonUtils {
    private String convertToNumber(Integer x) {
        String result = x.toString();
        if(result.length() == 1) {
            return "0" + result;
        }
        return result;
    }

    public String readFile(Integer dayNo, String fileName) {
        try {
            String newFileName = "./src/Day" + convertToNumber(dayNo) + "/" + fileName;
            File inputFile = new File(newFileName);
            FileReader fr = new FileReader(inputFile);
            BufferedReader bufferedReader = new BufferedReader(fr);
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
                stringBuffer.append("\n");
            }
            fr.close();
            return stringBuffer.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
