package by.modus.percentilerank.handler;

import by.modus.percentilerank.data.Student;
import by.modus.percentilerank.data.StudentWithPercentileRank;
import by.modus.percentilerank.facade.PercentileRankCalculator;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

class AppHandler {

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

    }
}
