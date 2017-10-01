package by.modus.percentilerank.data;

import by.modus.percentilerank.dto.Scorable;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvCustomBindByPosition;

public class Student implements Scorable {
    @CsvBindByPosition(position = 0, required = true)
    private String id;
    @CsvBindByPosition(position = 1, required = true)
    private String name;
    @CsvCustomBindByPosition(position = 2, required = true, converter = GpaConverter.class)
    private double gpa;

    @Override
    public double getScore() {
        return gpa;
    }

//    public String getId() {
//        return id;
//    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public double getGpa() {
//        return gpa;
//    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
}
