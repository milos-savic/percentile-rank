package by.modus.percentilerank;

import by.modus.percentilerank.handler.AppHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;

@SpringBootApplication
public class PercentileRankCalcApp implements CommandLineRunner {

    private final static Logger log = LoggerFactory.getLogger(PercentileRankCalcApp.class);

    @Autowired
    private AppHandler appHandler;

    public static void main(String[] args) {
        validateCliArgs(args);
        //disabled banner, don't want to see the spring logo
        SpringApplication app = new SpringApplication(PercentileRankCalcApp.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args[0], args[1]);
    }

    @Override
    public void run(String... args) throws Exception {
        appHandler.handle(args[0], args[1]);
    }

    private static void validateCliArgs(String[] args) {
        if (args.length != 2) {
            log.error("Invalid argument number. 2 arguments are expected: student data file path as the first and output file path as the second.");
            System.exit(1);
        }

        File f = new File(args[0]);
        if (!f.exists() || f.isDirectory()) {
            log.error("Specified input file {} doesn't exist.", args[0]);
            System.exit(1);
        }
    }
}
