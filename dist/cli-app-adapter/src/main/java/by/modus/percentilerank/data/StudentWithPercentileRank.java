package by.modus.percentilerank.data;

import by.modus.percentilerank.dto.PercentileRank;
import by.modus.percentilerank.dto.Score;

public class StudentWithPercentileRank implements PercentileRank{

    private final Student student;
    private final double percentileRank;

    public StudentWithPercentileRank(Student student, double percentileRank) {
        this.student = student;
        this.percentileRank = percentileRank;
    }

    @Override
    public Score getScore() {
        return student;
    }

    @Override
    public double getPercentileRank() {
        return percentileRank;
    }
}
