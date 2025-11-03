package com.vetclinic;
package vetclinic.src.main.java.com.vetclinic;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Клас BasicDataOperationUsingSet реалізує операції з множиною LinkedHashSet для Integer.
 * 
 * <p>Методи класу:</p>
 * <ul>
 *   <li>{@link #executeDataAnalysis()} - Запускає аналіз даних.</li>
 *   <li>{@link #performArraySorting()} - Упорядковує масив Integer.</li>
 *   <li>{@link #findInArray()} - Пошук значення в масиві Integer.</li>
 *   <li>{@link #locateMinMaxInArray()} - Знаходить граничні значення в масиві.</li>
 *   <li>{@link #findInSet()} - Пошук значення в множині Integer.</li>
 *   <li>{@link #locateMinMaxInSet()} - Знаходить мінімальне і максимальне значення в множині.</li>
 *   <li>{@link #analyzeArrayAndSet()} - Аналізує елементи масиву та множини.</li>
 * </ul>
 */
public class BasicDataOperationUsingSet {
    Integer integerValueToSearch;
    Integer[] intArray;
    Set<Integer> dateTimeSet = new LinkedHashSet<>();

    /**
     * Конструктор, який iнiцiалiзує об'єкт з готовими даними.
     * 
     * @param integerValueToSearch Значення для пошуку
     * @param intArray Масив Integer
     */
    BasicDataOperationUsingSet(Integer integerValueToSearch, Integer[] intArray) {
        this.integerValueToSearch = integerValueToSearch;
        this.intArray = intArray;
        this.dateTimeSet = new LinkedHashSet<>(Arrays.asList(intArray));
    }
    
    /**
     * Запускає комплексний аналіз даних з використанням множини LinkedHashSet.
     * 
     * Метод завантажує дані, виконує операції з множиною та масивом Integer.
     */
    public void executeDataAnalysis() {
        // спочатку аналізуємо множину дати та часу
        findInSet();
        locateMinMaxInSet();
        analyzeArrayAndSet();

        // потім обробляємо масив
        findInArray();
        locateMinMaxInArray();

        performArraySorting();

        findInArray();
        locateMinMaxInArray();

        // зберігаємо відсортований масив до файлу
        DataFileHandler.writeArrayToFile(intArray, BasicDataOperation.PATH_TO_DATA_FILE + ".sorted");
    }

    /**
     * Упорядковує масив об'єктів Integer за зростанням.
     * Фіксує та виводить тривалість операції сортування в наносекундах.
     */
    private void performArraySorting() {
        long timeStart = System.nanoTime();

        Arrays.sort(intArray);

        PerformanceTracker.displayOperationTime(timeStart, "упорядкування масиву дати i часу");
    }

    /**
     * Здійснює пошук конкретного значення в масиві дати та часу.
     */
    private void findInArray() {
        long timeStart = System.nanoTime();

        int position = Arrays.binarySearch(this.intArray, integerValueToSearch);

        PerformanceTracker.displayOperationTime(timeStart, "пошук елемента в масивi дати i часу");

        if (position >= 0) {
            System.out.println("Елемент '" + integerValueToSearch + "' знайдено в масивi за позицією: " + position);
        } else {
            System.out.println("Елемент '" + integerValueToSearch + "' відсутній в масиві.");
        }
    }

    /**
     * Визначає найменше та найбільше значення в масиві Integer.
     */
    private void locateMinMaxInArray() {
        if (intArray == null || intArray.length == 0) {
            System.out.println("Масив є пустим або не ініціалізованим.");
            return;
        }

        long timeStart = System.nanoTime();

        Integer minValue = intArray[0];
        Integer maxValue = intArray[0];

        for (Integer currentInteger : intArray) {
            if (currentInteger < minValue) {
                minValue = currentInteger;
            }
            if (currentInteger > maxValue) {
                maxValue = currentInteger;
            }
        }

        PerformanceTracker.displayOperationTime(timeStart, "визначення мiнiмальної i максимальної дати в масивi");

        System.out.println("Найменше значення в масивi: " + minValue);
        System.out.println("Найбільше значення в масивi: " + maxValue);
    }

    /**
     * Здійснює пошук конкретного значення в множині дати та часу.
     */
    private void findInSet() {
        long timeStart = System.nanoTime();

        boolean elementExists = this.dateTimeSet.contains(integerValueToSearch);

        PerformanceTracker.displayOperationTime(timeStart, "пошук елемента в LinkedHashSet дати i часу");

        if (elementExists) {
            System.out.println("Елемент '" + integerValueToSearch + "' знайдено в LinkedHashSet");
        } else {
            System.out.println("Елемент '" + integerValueToSearch + "' відсутній в LinkedHashSet.");
        }
    }

    /**
     * Визначає найменше та найбільше значення в множині Integer.
     */
    private void locateMinMaxInSet() {
        if (dateTimeSet == null || dateTimeSet.isEmpty()) {
            System.out.println("LinkedHashSet є пустим або не ініціалізованим.");
            return;
        }

        long timeStart = System.nanoTime();

        Integer minValue = Collections.min(dateTimeSet);
        Integer maxValue = Collections.max(dateTimeSet);

        PerformanceTracker.displayOperationTime(timeStart, "визначення мiнiмальної i максимальної дати в LinkedHashSet");

        System.out.println("Найменше значення в LinkedHashSet: " + minValue);
        System.out.println("Найбільше значення в LinkedHashSet: " + maxValue);
    }

    /**
     * Аналізує та порівнює елементи масиву та множини.
     */
    private void analyzeArrayAndSet() {
        System.out.println("Кiлькiсть елементiв в масивi: " + intArray.length);
        System.out.println("Кiлькiсть елементiв в LinkedHashSet: " + dateTimeSet.size());

        boolean allElementsPresent = true;
        for (Integer dateTimeElement : intArray) {
            if (!dateTimeSet.contains(dateTimeElement)) {
                allElementsPresent = false;
                break;
            }
        }

        if (allElementsPresent) {
            System.out.println("Всi елементи масиву наявні в LinkedHashSet.");
        } else {
            System.out.println("Не всi елементи масиву наявні в LinkedHashSet.");
        }
    }
}