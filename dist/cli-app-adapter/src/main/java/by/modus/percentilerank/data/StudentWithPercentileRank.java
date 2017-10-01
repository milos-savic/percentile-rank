package by.modus.percentilerank.data;

import com.opencsv.bean.CsvBindByPosition;

public class StudentWithPercentileRank{

    @CsvBindByPosition(position = 0, required = true)
    private final String name;
    @CsvBindByPosition(position = 1, required = true)
    private final double gpa;
    @CsvBindByPosition(position = 2, required = true)
    private final double percentileRank;

    public StudentWithPercentileRank(String name, double gpa, double percentileRank) {
        this.name = name;
        this.gpa = gpa;
        this.percentileRank = percentileRank;
    }

    public String getName() {
        return name;
    }

    public double getGpa() {
        return gpa;
    }

    public double getPercentileRank() {
        return percentileRank;
    }
}
