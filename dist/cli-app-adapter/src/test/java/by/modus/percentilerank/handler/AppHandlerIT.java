package by.modus.percentilerank.handler;

import by.modus.percentilerank.service.PercentileRankCalculatorService;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.*;
import java.util.Arrays;
import java.util.List;


public class AppHandlerIT {

    @Test
    public void handleIT() {
        String inputFilePath = "intput.csv";
        String outputFilePath = "output.csv";
        createInputFile(inputFilePath);
        List<String[]> expectedOutputFileContent = Arrays.asList(new String[]{"John", "3.5", "60"},
                new String[]{"Don", "2.3", "30"}, new String[]{"James", "4.3", "90"},
                new String[]{"Bond", "1.3", "10"}, new String[]{"Chris", "3.5", "60"});

        AppHandler appHandler = new AppHandler();
        appHandler.setPercentileRankCalculator(new PercentileRankCalculatorService());
        appHandler.handle(inputFilePath, outputFilePath);

        List<String[]> actualOutputFileContent = readOutputFile(outputFilePath);

        Assert.assertEquals(expectedOutputFileContent.size(), actualOutputFileContent.size());
        for (int i = 0; i < expectedOutputFileContent.size(); i++) {
            String[] expectedLine = expectedOutputFileContent.get(i);
            String[] actualLine = actualOutputFileContent.get(i);
            Assert.assertEquals(expectedLine, actualLine);
        }

        housekeeping(inputFilePath, outputFilePath);

    }

    private void createInputFile(String inputFilePath) {
        try (Writer fr = new FileWriter(inputFilePath)) {
            CSVWriter writer = new CSVWriter(fr, ',', Character.MIN_VALUE, '\'', System.getProperty("line.separator"));
            // feed in your array (or convert your data to an array)
            String[] line1 = "1#John#3.5".split("#");
            writer.writeNext(line1);
            String[] line2 = "2#Don#2.3".split("#");
            writer.writeNext(line2);
            String[] line3 = "3#James#4.3".split("#");
            writer.writeNext(line3);
            String[] line4 = "4#Bond#1.3".split("#");
            writer.writeNext(line4);
            String[] line5 = "5#Chris#3.5".split("#");
            writer.writeNext(line5);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<String[]> readOutputFile(String outputFilePath) {
        try (CSVReader reader = new CSVReader(new FileReader(outputFilePath))) {
            return reader.readAll();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void housekeeping(String inputFilePath, String outputFilePath) {
        File inputF = new File(inputFilePath);
        inputF.delete();

        File outputF = new File(inputFilePath);
        outputF.delete();
    }
}
