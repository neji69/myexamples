package ru.example.autoanswer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class FileManager {

    private static final String defaultFileForAnswers = "answers.txt"; // Посмотреть ресурс лоудер. как читать из ресурсов.

    private List<String> linesFromFile;

    public FileManager() {
        String defaultPath = Objects.requireNonNull(getClass().getClassLoader().getResource(defaultFileForAnswers)).getPath();
        readLinesFromFile(defaultPath);
    }

    // Получаем первую строчку из листа ответов в качестве приветствия.
    public String getFirstLine() {
        return getLineByIndex(0);
    }

    // Получаем строку из листа ответов по индексу.
    public String getLineByIndex(int index) {
        return linesFromFile.get(index);
    }

    //Получаем рандомную строку из файла для ответа
    public String getRandomElement() {
        if (linesFromFile.isEmpty()) {
            throw new IllegalArgumentException("File is Empty");
        }
        return getRandomElementWithoutFirstLine();
    }

    //Получаем рандомную строку из файла для ответа исключая первую строку приветствия
    private String getRandomElementWithoutFirstLine() {
        int randomNumber = (new Random()).ints(1, linesFromFile.size()).findFirst().getAsInt();
        return linesFromFile.get(randomNumber);
    }

    //Меняем путь до файла из которого получаем ответы.
    public void useAnotherFileForAnswers(String consoleInputLine) {
        String newFilePath = consoleInputLine.replaceAll("^«Use another file:|»", "").trim();
        readLinesFromFile(newFilePath);
    }

    // Читаем ответы из файла и помещаем их в List<String>
    private void readLinesFromFile(String filePath) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            while (bufferedReader.ready()) {
                lines.add(bufferedReader.readLine());
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        this.linesFromFile = lines;
    }
}
