package by.modus.percentilerank.data;

import com.opencsv.bean.CsvBindByPosition;

public class StudentWithPercentileRank{

    @CsvBindByPosition(position = 0, required = true)
    private final String name;
    @CsvBindByPosition(position = 1, required = true)
    private final double gpa;
    @CsvBindByPosition(position = 2, required = true)
    private final int percentileRank;

    public StudentWithPercentileRank(String name, double gpa, int percentileRank) {
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

    public int getPercentileRank() {
        return percentileRank;
    }
}
