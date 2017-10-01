package by.modus.percentilerank.handler;

import by.modus.percentilerank.data.Student;
import by.modus.percentilerank.data.StudentWithPercentileRank;
import by.modus.percentilerank.dto.PercentileRank;
import by.modus.percentilerank.dto.Scorable;
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
import java.util.stream.Collectors;

@Component
public class AppHandler {

    @Autowired
    private PercentileRankCalculator<Scorable, PercentileRank> percentileRankCalculator;

    public void handle(String inputFilePath, String outputFilePath) {
        List<Student> input = readInputData(inputFilePath);
        List<PercentileRank> processedData = percentileRankCalculator.calculatePercentileRanks(input);
        List<StudentWithPercentileRank> studentsWithPercentileRank = processedData.stream().
                map(pd -> new StudentWithPercentileRank(pd.getScorable().getName(), pd.getScorable().getScore(), pd.getPercentileRank())).
                collect(Collectors.toList());
        serveResponse(studentsWithPercentileRank, outputFilePath);
    }

    private List<Student> readInputData(String inputFilePath) {
        try (FileReader fr = new FileReader(inputFilePath)) {
            return new CsvToBeanBuilder<Student>(fr).withIgnoreQuotations(true).withKeepCarriageReturn(true)
                    .withType(Student.class).build().parse();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void serveResponse(List<StudentWithPercentileRank> studentsWithPercentileRank, String outputFilePath) {
        try (Writer writer = new FileWriter(outputFilePath)) {
            StatefulBeanToCsv<StudentWithPercentileRank> beanToCsv = new StatefulBeanToCsvBuilder<StudentWithPercentileRank>(writer).
                    withLineEnd(System.getProperty("line.separator")).
                    build();
            beanToCsv.write(studentsWithPercentileRank);
            writer.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
