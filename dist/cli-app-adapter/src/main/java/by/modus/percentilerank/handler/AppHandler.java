package by.modus.percentilerank.handler;

import by.modus.percentilerank.data.Student;
import by.modus.percentilerank.data.StudentWithPercentileRank;
import by.modus.percentilerank.facade.PercentileRankCalculator;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Component
public class AppHandler {

    @Autowired
    private PercentileRankCalculator<Student, StudentWithPercentileRank> percentileRankCalculator;

    public void handle(String inputFilePath, String outputFilePath) {
        List<Student> input = readInputData(inputFilePath);
        List<StudentWithPercentileRank> processedData = percentileRankCalculator.calculatePercentileRanks(input);
        serveResponse(processedData, outputFilePath);
    }

    private List<Student> readInputData(String inputFilePath) {
        try (FileReader fr = new FileReader(inputFilePath)) {
            return new CsvToBeanBuilder<Student>(fr)
                    .withType(Student.class).build().parse();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void serveResponse(List<StudentWithPercentileRank> processedData, String outputFilePath) {
        try (Writer writer = new FileWriter(outputFilePath)) {
            StatefulBeanToCsv<StudentWithPercentileRank> beanToCsv = new StatefulBeanToCsvBuilder<StudentWithPercentileRank>(writer).build();
            beanToCsv.write(processedData);
            writer.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
