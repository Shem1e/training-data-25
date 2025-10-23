import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

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
            String currentLine;
            while ((currentLine = fileReader.readLine()) != null) {
                // Видаляємо можливі невидимі символи та BOM
                currentLine = currentLine.trim().replaceAll("^\\uFEFF", "");
                if (!currentLine.isEmpty()) {
                    Integer parsedDateTime = Integer.parseInt(currentLine);
                    temporaryArray[currentIndex++] = parsedDateTime;
                }
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
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
            for (Integer dateTimeElement : intArray) {
                fileWriter.write(dateTimeElement.toString());
                fileWriter.newLine();
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
