package ru.example.autoanswer;

import java.util.Scanner;

import static ru.example.autoanswer.MyLogger.logger;

public class AutoAnswer {

    private static final String commandStopTalking = "«Stop talking»";
    private static final String commandStartTalking = "«Start talking»";
    private static final String commandGoodbye = "«Goodbye»";
    private static final String commandUseAnotherFile = "«Use another file:";

    FileManager fileManager = new FileManager();

    public void startProgram() {
        boolean commandStartTalkingFlag = false;
        boolean commandGoodbyeFlag = false;

        helloCommand();

        try (Scanner scanner = new Scanner(System.in)) {
            do {
                String consoleInputLine = consoleLineRead(scanner);

                if (consoleInputLine.contains(commandUseAnotherFile)) {
                    fileManager.useAnotherFileForAnswers(consoleInputLine);
                } else {
                    switch (consoleInputLine) {
                        case (commandStopTalking):
                            do {
                                if (consoleLineRead(scanner).equals(commandStartTalking)) {
                                    commandStartTalkingFlag = true;
                                }
                            } while (!commandStartTalkingFlag);
                            break;

                        case (commandGoodbye):
                            commandGoodbyeFlag = true;
                            break;
                        default:
                            getRandomAnswerCommand();
                            break;
                    }
                }
            } while (!commandGoodbyeFlag);
        }
    }

    // Команда приветствия.
    private void helloCommand() {
        String hello = fileManager.getFirstLine();
        programOutPrintln(hello);
    }

    // Выводит строку в консоль.
    private void programOutPrintln(String programOut) {
        System.out.println(programOut);
        logger.info("[Program]: " + programOut);
    }

    // Считываем строку пользователя из консоли.
    private String consoleLineRead(Scanner scanner) {
        String consoleLine = scanner.nextLine();
        logger.info("[User]: " + consoleLine);
        return consoleLine;
    }

    // Получаем рандомный ответ и выводим его в консоль.
    private void getRandomAnswerCommand() {
        String answer = fileManager.getRandomElement();
        programOutPrintln(answer);
    }
}
