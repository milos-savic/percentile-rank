package by.modus.percentilerank;

import by.modus.percentilerank.handler.AppHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class PercentileRankCalcApp implements CommandLineRunner {

    @Autowired
    private AppHandler appHandler;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter student data file path: ");
        String inputFilePath = scanner.next();

        System.out.print("Enter output file path: ");
        String outputFilePath = scanner.next();

        //disabled banner, don't want to see the spring logo
        SpringApplication app = new SpringApplication(PercentileRankCalcApp.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(inputFilePath, outputFilePath);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Milos");
        appHandler.handle(args[0], args[1]);
    }
}
