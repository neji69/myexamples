package ru.example.autoanswer;

import org.apache.log4j.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyLogger {

    static Logger logger = Logger.getLogger(AutoAnswer.class);

    static {
        initLogger();
    }

    private static void initLogger() {

        PatternLayout layout = new PatternLayout("%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy-HH-mm");
        String dateAndTime = simpleDateFormat.format(new Date());
        String logFileName = ".\\Logs\\log-" + dateAndTime + ".log";

        RollingFileAppender fileAppender = null;

        try {
            fileAppender = new RollingFileAppender(layout, logFileName, true);
            logger.addAppender(fileAppender);
        } catch (IOException exception) {
            exception.printStackTrace();

        }
    }
}
