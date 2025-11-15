package com.vetclinic;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Клас DataFileHandler управляє роботою з файлами даних Integer.
 */
public class DataFileHandler {
    /**
     * Завантажує масив об'єктів Integer з файлу.
     * 
     * @param filePath Шлях до файлу з даними.
     * @return Масив об'єктів Integer.
     */
    public static Integer[] loadArrayFromFile(String filePath) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_DATE_TIME;
        Integer[] temporaryArray = new Integer[1000];
        int currentIndex = 0;

        try (BufferedReader fileReader = new BufferedReader(new FileReader(filePath))) {
    return fileReader.lines()
            .map(currentLine -> currentLine.trim().replaceAll("^\\uFEFF", ""))
            .filter(currentLine -> !currentLine.isEmpty())
            .map(currentLine -> Integer.parseInt(currentLine))
            .toArray(Integer[]::new);
} catch (IOException ioException) {
    throw new RuntimeException("Помилка читання даних з файлу: " + filePath, ioException);
}


        Integer[] resultArray = new Integer[currentIndex];
        System.arraycopy(temporaryArray, 0, resultArray, 0, currentIndex);

        return resultArray;
    }

    /**
     * Зберігає масив об'єктів Integer у файл.
     * 
     * @param intArray Масив об'єктів Integer.
     * @param filePath Шлях до файлу для збереження.
     */
    public static void writeArrayToFile(Integer[] intArray, String filePath) {
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(filePath))) {
            String content = Arrays.stream(integerArray)
        .map(Object::toString)
        .collect(Collectors.joining(System.lineSeparator()));

fileWriter.write(content);

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
