package com.vetclinic;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Клас BasicDataOperationUsingList реалізує операції з колекціями типу ArrayList для даних Integer.
 * 
 * <p>Методи класу:</p>
 * <ul>
 *   <li>{@link #executeDataOperations()} - Виконує комплекс операцій з даними.</li>
 *   <li>{@link #performArraySorting()} - Упорядковує масив елементів Integer.</li>
 *   <li>{@link #findInArray()} - Здійснює пошук елемента в масиві Integer.</li>
 *   <li>{@link #locateMinMaxInArray()} - Визначає найменше і найбільше значення в масиві.</li>
 *   <li>{@link #sortList()} - Сортує колекцію List з Integer.</li>
 *   <li>{@link #findInList()} - Пошук конкретного значення в списку.</li>
 *   <li>{@link #locateMinMaxInList()} - Пошук мінімального і максимального значення в списку.</li>
 * </ul>
 */
public class BasicDataOperationUsingList {
    private Integer integerValueToSearch;
    private Integer[] intArray;
    private List<Integer> dateTimeList;

    /**
     * Конструктор, який iнiцiалiзує об'єкт з готовими даними.
     * 
     * @param integerValueToSearch Значення для пошуку
     * @param intArray Масив Integer
     */
    BasicDataOperationUsingList(Integer integerValueToSearch, Integer[] intArray) {
        this.integerValueToSearch = integerValueToSearch;
        this.intArray = intArray;
        this.dateTimeList = new ArrayList<>(Arrays.asList(intArray));
    }
    
    /**
     * Виконує комплексні операції з структурами даних.
     * 
     * Метод завантажує масив і список об'єктів Integer, 
     * здійснює сортування та пошукові операції.
     */
    public void executeDataOperations() {
        // спочатку працюємо з колекцією List
        findInList();
        locateMinMaxInList();
        
        sortList();
        
        findInList();
        locateMinMaxInList();

        // потім обробляємо масив дати та часу
        findInArray();
        locateMinMaxInArray();

        performArraySorting();
        
        findInArray();
        locateMinMaxInArray();

        // зберігаємо відсортований масив до окремого файлу
        DataFileHandler.writeArrayToFile(intArray, BasicDataOperation.PATH_TO_DATA_FILE + ".sorted");
    }

    /**
     * Упорядковує масив об'єктів Integer за зростанням.
     * Фіксує та виводить тривалість операції сортування в наносекундах.
     */
    void performArraySorting() {
        long timeStart = System.nanoTime();

        intArray = Arrays.stream(intArray)
        .sorted()
        .toArray(Integer[]::new);


        PerformanceTracker.displayOperationTime(timeStart, "упорядкування масиву дати i часу");
    }

    /**
     * Здійснює пошук конкретного значення в масиві дати та часу.
     */
    void findInArray() {
        long timeStart = System.nanoTime();

        int index = IntStream.range(0, dateTimeList.size())
        .filter(i -> dateTimeList.get(i) == integerValueToSearch)
        .findFirst()
        .orElse(-1);


        PerformanceTracker.displayOperationTime(timeStart, "пошук заданого типу даних");

        if (position >= 0) {
            System.out.println("Елемент '" + integerValueToSearch + "' знайдено в масивi за позицією: " + position);
        } else {
            System.out.println("Елемент '" + integerValueToSearch + "' відсутній в масиві.");
        }
    }

    /**
     * Визначає найменше та найбільше значення в масиві дати та часу.
     */
    void locateMinMaxInArray() {
    if (intArray == null || intArray.length == 0) {
        System.out.println("Масив є пустим або не ініціалізованим.");
        return;
    }

    long timeStart = System.nanoTime();

    int minValue = Arrays.stream(intArray).min().orElse(0);
    int maxValue = Arrays.stream(intArray).max().orElse(0);

    PerformanceTracker.displayOperationTime(timeStart, "визначення мiнiмальної i максимальної дати в масивi");

    System.out.println("Найменше значення в масивi: " + minValue);
    System.out.println("Найбільше значення в масивi: " + maxValue);
    }


    /**
     * Шукає конкретне значення дати та часу в колекції ArrayList.
     */
    void findInList() {
        long timeStart = System.nanoTime();

        boolean found = Arrays.stream(intArray)
        .anyMatch(value -> value == integerValueToSearch);

Utils.printOperationDuration(startTime, "пошук в масиві цілих чисел");

System.out.println("Значення " + integerValueToSearch + (found ? " знайдено" : " не знайдено") + " в масиві.");

    }

    /**
     * Визначає найменше і найбільше значення в колекції ArrayList з датами.
     */
    void locateMinMaxInList() {
        if (dateTimeList == null || dateTimeList.isEmpty()) {
            System.out.println("Колекція ArrayList є пустою або не ініціалізованою.");
            return;
        }

        long timeStart = System.nanoTime();

        Integer minValue = Collections.min(dateTimeList);
        Integer maxValue = Collections.max(dateTimeList);

        PerformanceTracker.displayOperationTime(timeStart, "визначення мiнiмальної i максимальної дати в List");

        System.out.println("Найменше значення в List: " + minValue);
        System.out.println("Найбільше значення в List: " + maxValue);
    }

    /**
     * Упорядковує колекцію List з об'єктами Integer за зростанням.
     * Відстежує та виводить час виконання операції сортування.
     */
    void sortList() {
    long timeStart = System.nanoTime();

    // Сортування списку з Integer як примітивів int
    dateTimeList = dateTimeList.stream()
            .mapToInt(Integer::intValue)          // перетворюємо на IntStream
            .sorted()                             // сортування за зростанням
            .boxed()                              // перетворюємо назад на Stream<Integer>
            .collect(Collectors.toCollection(ArrayList::new)); // збираємо в ArrayList

    PerformanceTracker.displayOperationTime(timeStart, "упорядкування ArrayList цілих чисел");
}

}