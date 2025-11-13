import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


import java.util.LinkedHashMap;

/**
 * Клас BasicDataOperationUsingMap реалізує операції з колекціями типу Map для зберігання пар ключ-значення.
 * 
 * <p>Методи класу:</p>
 * <ul>
 *   <li>{@link #executeDataOperations()} - Виконує комплекс операцій з даними Map.</li>
 *   <li>{@link #findByKey()} - Здійснює пошук елемента за ключем в Map.</li>
 *   <li>{@link #findByValue()} - Здійснює пошук елемента за значенням в Map.</li>
 *   <li>{@link #addEntry()} - Додає новий запис до Map.</li>
 *   <li>{@link #removeByKey()} - Видаляє запис з Map за ключем.</li>
 *   <li>{@link #removeByValue()} - Видаляє записи з Map за значенням.</li>
 *   <li>{@link #sortByKey()} - Сортує Map за ключами.</li>
 *   <li>{@link #sortByValue()} - Сортує Map за значеннями.</li>
 * </ul>
 */
public class BasicDataOperationUsingMap {
    private final Newt KEY_TO_SEARCH_AND_DELETE = new Newt("Луна", "Полярна сова");
    private final Newt KEY_TO_ADD = new Newt("Кір", "Сова вухата");

    private final String VALUE_TO_SEARCH_AND_DELETE = "Олена";
    private final String VALUE_TO_ADD = "Богдан";

    private HashMap<Newt, String> hashtable;
    private LinkedHashMap<Newt, String> treeMap;

    /**
     * Компаратор для сортування Map.Entry за значеннями String.
     * Використовує метод String.compareTo() для порівняння імен власників.
     */
    static class OwnerValueComparator implements Comparator<Map.Entry<Newt, String>> {
        @Override
        public int compare(Map.Entry<Newt, String> e1, Map.Entry<Newt, String> e2) {
            String v1 = e1.getValue();
            String v2 = e2.getValue();
            if (v1 == null && v2 == null) return 0;
            if (v1 == null) return -1;
            if (v2 == null) return 1;
            return v1.compareTo(v2);
        }
    }

    /**
     * Внутрішній клас Newt для зберігання інформації про домашню тварину.
     * 
     * Реалізує Comparable<Newt> для визначення природного порядку сортування.
     * Природний порядок: спочатку за кличкою (nickname) за зростанням, потім за видом (spotsColor) за спаданням.
     */
    public static class Newt implements Comparable<Newt> {
        private final String nickname;
        private final String spotsColor;

        public Newt(String nickname) {
            this.nickname = nickname;
            this.spotsColor = null;
        }

        public Newt(String nickname, String spotsColor) {
            this.nickname = nickname;
            this.spotsColor = spotsColor;
        }

        public String getNickname() { 
            return nickname; 
        }

        public String getSpecies() {
            return spotsColor;
        }

        /**
         * Порівнює цей об'єкт Newt з іншим для визначення порядку сортування.
         * Природний порядок: спочатку за кличкою (nickname) за зростанням, потім за видом (spotsColor) за спаданням.
         * 
         * @param other Newt об'єкт для порівняння
         * @return негативне число, якщо цей Newt < other; 
         *         0, якщо цей Newt == other; 
         *         позитивне число, якщо цей Newt > other
         * 
         * Критерій порівняння: поля nickname (кличка) за зростанням та spotsColor (вид) за спаданням.
         * 
         * Цей метод використовується:
         * - LinkedHashMap для автоматичного сортування ключів Newt за nickname (зростання), потім за spotsColor (спадання)
         * - Collections.sort() для сортування Map.Entry за ключами Newt
         * - Collections.binarySearch() для пошуку в відсортованих колекціях
         */
        @Override
        public int compareTo(Newt other) {
            if (other == null) return 1;

            // 1. Порівнюємо клички (nickname) за спаданням
            int nicknameComparison = other.nickname.compareTo(this.nickname);
            if (nicknameComparison != 0) {
                return nicknameComparison;
            }

            // 2. Якщо клички однакові — порівнюємо колір плям (spotsColor) теж за спаданням
            return other.spotsColor.compareTo(this.spotsColor);
        }


        /**
         * Перевіряє рівність цього Newt з іншим об'єктом.
         * Два Newt вважаються рівними, якщо їх клички (nickname) та види (spotsColor) однакові.
         * 
         * @param obj об'єкт для порівняння
         * @return true, якщо об'єкти рівні; false в іншому випадку
         * 
         * Критерій рівності: поля nickname (кличка) та spotsColor (вид).
         * 
         * Важливо: метод узгоджений з compareTo() - якщо equals() повертає true,
         * то compareTo() повертає 0, оскільки обидва методи порівнюють за nickname та spotsColor.
         */
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Newt other = (Newt) obj;
            return Objects.equals(nickname, other.nickname)
                && Objects.equals(spotsColor, other.spotsColor);
        }


        /**
         * Повертає хеш-код для цього Newt.
         * 
         * @return хеш-код, обчислений на основі nickname та spotsColor
         * 
         * Базується на полях nickname та spotsColor для узгодженості з equals().
         * 
         * Важливо: узгоджений з equals() - якщо два Newt рівні за equals()
         * (мають однакові nickname та spotsColor), вони матимуть однаковий hashCode().
         */
        @Override
        public int hashCode() {
            return Objects.hash(nickname, spotsColor);
        }


        /**
         * Повертає строкове представлення Newt.
         * 
         * @return кличка тварини (nickname), вид (spotsColor) та hashCode
         */
        @Override
        public String toString() {
            return "Newt{nickname='" + nickname + "', spotsColor='" + spotsColor + "'}";
        }

    }

    /**
     * Конструктор, який ініціалізує об'єкт з готовими даними.
     * 
     * @param hashtable HashMap з початковими даними (ключ: Newt, значення: ім'я власника)
     * @param treeMap LinkedHashMap з початковими даними (ключ: Newt, значення: ім'я власника)
     */
    BasicDataOperationUsingMap(HashMap<Newt, String> hashtable, LinkedHashMap<Newt, String> treeMap) {
        this.hashtable = hashtable;
        this.treeMap = treeMap;
    }
    
    /**
     * Виконує комплексні операції з Map.
     * 
     * Метод виконує різноманітні операції з Map: пошук, додавання, видалення та сортування.
     */
    public void executeDataOperations() {
        // Спочатку працюємо з HashMap
        System.out.println("========= Операції з HashMap =========");
        System.out.println("Початковий розмір HashMap: " + hashtable.size());
        
        // Пошук до сортування
        findByKeyInHashMap();
        findByValueInHashMap();

        printHashMap();
        sortHashMap();
        printHashMap();

        // Пошук після сортування
        findByKeyInHashMap();
        findByValueInHashMap();

        addEntryToHashMap();
        
        removeByKeyFromHashMap();
        removeByValueFromHashMap();
               
        System.out.println("Кінцевий розмір HashMap: " + hashtable.size());

        // Потім обробляємо LinkedHashMap
        System.out.println("\n\n========= Операції з LinkedHashMap =========");
        System.out.println("Початковий розмір LinkedHashMap: " + treeMap.size());
        
        findByKeyInLinkedHashMap();
        findByValueInLinkedHashMap();

        printLinkedHashMap();

        addEntryToLinkedHashMap();
        
        removeByKeyFromLinkedHashMap();
        removeByValueFromLinkedHashMap();
        
        System.out.println("Кінцевий розмір LinkedHashMap: " + treeMap.size());
    }


    // ===== Методи для HashMap =====

    /**
     * Виводить вміст HashMap без сортування.
     * HashMap не гарантує жодного порядку елементів.
     */
    private void printHashMap() {
        System.out.println("\n=== Пари ключ-значення в HashMap ===");
        long timeStart = System.nanoTime();

        for (Map.Entry<Newt, String> entry : hashtable.entrySet()) {
            System.out.println("  " + entry.getKey() + " -> " + entry.getValue());
        }

        PerformanceTracker.displayOperationTime(timeStart, "виведення пари ключ-значення в HashMap");
    }

    /**
     * Сортує HashMap за ключами.
     * Використовує Collections.sort() з природним порядком Newt (Newt.compareTo()).
     * Перезаписує hashtable відсортованими даними.
     */
    /**
 * Сортує HashMap за ключами (Newt) у порядку, який визначає compareTo().
 */
    private void sortHashMap() {
        long timeStart = System.nanoTime();

        // Створюємо список записів із HashMap
        List<Map.Entry<Newt, String>> entries = new ArrayList<>(hashtable.entrySet());

        // Сортуємо за ключами (Newt) — compareTo() визначає порядок
        Collections.sort(entries, Map.Entry.comparingByKey());

        // Створюємо нову впорядковану мапу, щоб зберегти порядок
        LinkedHashMap<Newt, String> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<Newt, String> entry : entries) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        // Очищаємо стару HashMap і додаємо відсортовані пари
        hashtable.clear();
        hashtable.putAll(sortedMap);

        PerformanceTracker.displayOperationTime(timeStart, "сортування HashMap за ключами");

        System.out.println("✅ HashMap відсортовано за ключами (Newt)");
    }


    /**
     * Здійснює пошук елемента за ключем в HashMap.
     * Використовує Newt.hashCode() та Newt.equals() для пошуку.
     */
    void findByKeyInHashMap() {
        long timeStart = System.nanoTime();

        boolean found = hashtable.containsKey(KEY_TO_SEARCH_AND_DELETE);

        PerformanceTracker.displayOperationTime(timeStart, "пошук за ключем в HashMap");

        if (found) {
            String value = hashtable.get(KEY_TO_SEARCH_AND_DELETE);
            System.out.println("Елемент з ключем '" + KEY_TO_SEARCH_AND_DELETE + "' знайдено. Власник: " + value);
        } else {
            System.out.println("Елемент з ключем '" + KEY_TO_SEARCH_AND_DELETE + "' відсутній в HashMap.");
        }
    }

    /**
     * Здійснює пошук елемента за значенням в HashMap.
     * Сортує список Map.Entry за значеннями та використовує бінарний пошук.
     */
    void findByValueInHashMap() {
        long timeStart = System.nanoTime();

        // Створюємо список Entry та сортуємо за значеннями
        List<Map.Entry<Newt, String>> entries = new ArrayList<>(hashtable.entrySet());
        OwnerValueComparator comparator = new OwnerValueComparator();
        Collections.sort(entries, comparator);

        // Створюємо тимчасовий Entry для пошуку
        Map.Entry<Newt, String> searchEntry = new Map.Entry<Newt, String>() {
            public Newt getKey() { return null; }
            public String getValue() { return VALUE_TO_SEARCH_AND_DELETE; }
            public String setValue(String value) { return null; }
        };

        int position = Collections.binarySearch(entries, searchEntry, comparator);

        PerformanceTracker.displayOperationTime(timeStart, "бінарний пошук за значенням в HashMap");

        if (position >= 0) {
            Map.Entry<Newt, String> foundEntry = entries.get(position);
            System.out.println("Власника '" + VALUE_TO_SEARCH_AND_DELETE + "' знайдено. Newt: " + foundEntry.getKey());
        } else {
            System.out.println("Власник '" + VALUE_TO_SEARCH_AND_DELETE + "' відсутній в HashMap.");
        }
    }

    /**
     * Додає новий запис до HashMap.
     */
    void addEntryToHashMap() {
        long timeStart = System.nanoTime();

        hashtable.put(KEY_TO_ADD, VALUE_TO_ADD);

        PerformanceTracker.displayOperationTime(timeStart, "додавання запису до HashMap");

        System.out.println("Додано новий запис: Newt='" + KEY_TO_ADD + "', власник='" + VALUE_TO_ADD + "'");
    }

    /**
     * Видаляє запис з HashMap за ключем.
     */
    void removeByKeyFromHashMap() {
        long timeStart = System.nanoTime();

        String removedValue = hashtable.remove(KEY_TO_SEARCH_AND_DELETE);

        PerformanceTracker.displayOperationTime(timeStart, "видалення за ключем з HashMap");

        if (removedValue != null) {
            System.out.println("Видалено запис з ключем '" + KEY_TO_SEARCH_AND_DELETE + "'. Власник був: " + removedValue);
        } else {
            System.out.println("Ключ '" + KEY_TO_SEARCH_AND_DELETE + "' не знайдено для видалення.");
        }
    }

    /**
     * Видаляє записи з HashMap за значенням.
     */
    void removeByValueFromHashMap() {
        long timeStart = System.nanoTime();

        List<Newt> keysToRemove = new ArrayList<>();
        for (Map.Entry<Newt, String> entry : hashtable.entrySet()) {
            if (entry.getValue() != null && entry.getValue().equals(VALUE_TO_SEARCH_AND_DELETE)) {
                keysToRemove.add(entry.getKey());
            }
        }
        
        for (Newt key : keysToRemove) {
            hashtable.remove(key);
        }

        PerformanceTracker.displayOperationTime(timeStart, "видалення за значенням з HashMap");

        System.out.println("Видалено " + keysToRemove.size() + " записів з власником '" + VALUE_TO_SEARCH_AND_DELETE + "'");
    }

    // ===== Методи для LinkedHashMap =====

    /**
     * Виводить вміст LinkedHashMap.
     * LinkedHashMap автоматично відсортована за ключами (Newt nickname за зростанням, spotsColor за спаданням).
     */
    private void printLinkedHashMap() {
        System.out.println("\n=== Пари ключ-значення в LinkedHashMap ===");

        long timeStart = System.nanoTime();
        for (Map.Entry<Newt, String> entry : treeMap.entrySet()) {
            System.out.println("  " + entry.getKey() + " -> " + entry.getValue());
        }

        PerformanceTracker.displayOperationTime(timeStart, "виведення пар ключ-значення в LinkedHashMap");
    }

    /**
     * Здійснює пошук елемента за ключем в LinkedHashMap.
     * Використовує Newt.compareTo() для навігації по дереву.
     */
    void findByKeyInLinkedHashMap() {
        long timeStart = System.nanoTime();

        boolean found = treeMap.containsKey(KEY_TO_SEARCH_AND_DELETE);

        PerformanceTracker.displayOperationTime(timeStart, "пошук за ключем в LinkedHashMap");

        if (found) {
            String value = treeMap.get(KEY_TO_SEARCH_AND_DELETE);
            System.out.println("Елемент з ключем '" + KEY_TO_SEARCH_AND_DELETE + "' знайдено. Власник: " + value);
        } else {
            System.out.println("Елемент з ключем '" + KEY_TO_SEARCH_AND_DELETE + "' відсутній в LinkedHashMap.");
        }
    }

    /**
     * Здійснює пошук елемента за значенням в LinkedHashMap.
     * Сортує список Map.Entry за значеннями та використовує бінарний пошук.
     */
    void findByValueInLinkedHashMap() {
        long timeStart = System.nanoTime();

        // Створюємо список Entry та сортуємо за значеннями
        List<Map.Entry<Newt, String>> entries = new ArrayList<>(treeMap.entrySet());
        OwnerValueComparator comparator = new OwnerValueComparator();
        Collections.sort(entries, comparator);

        // Створюємо тимчасовий Entry для пошуку
        Map.Entry<Newt, String> searchEntry = new Map.Entry<Newt, String>() {
            public Newt getKey() { return null; }
            public String getValue() { return VALUE_TO_SEARCH_AND_DELETE; }
            public String setValue(String value) { return null; }
        };

        int position = Collections.binarySearch(entries, searchEntry, comparator);

        PerformanceTracker.displayOperationTime(timeStart, "бінарний пошук за значенням в LinkedHashMap");

        if (position >= 0) {
            Map.Entry<Newt, String> foundEntry = entries.get(position);
            System.out.println("Власника '" + VALUE_TO_SEARCH_AND_DELETE + "' знайдено. Newt: " + foundEntry.getKey());
        } else {
            System.out.println("Власник '" + VALUE_TO_SEARCH_AND_DELETE + "' відсутній в LinkedHashMap.");
        }
    }

    /**
     * Додає новий запис до LinkedHashMap.
     */
    void addEntryToLinkedHashMap() {
        long timeStart = System.nanoTime();

        treeMap.put(KEY_TO_ADD, VALUE_TO_ADD);

        PerformanceTracker.displayOperationTime(timeStart, "додавання запису до LinkedHashMap");

        System.out.println("Додано новий запис: Newt='" + KEY_TO_ADD + "', власник='" + VALUE_TO_ADD + "'");
    }

    /**
     * Видаляє запис з LinkedHashMap за ключем.
     */
    void removeByKeyFromLinkedHashMap() {
        long timeStart = System.nanoTime();

        String removedValue = treeMap.remove(KEY_TO_SEARCH_AND_DELETE);

        PerformanceTracker.displayOperationTime(timeStart, "видалення за ключем з LinkedHashMap");

        if (removedValue != null) {
            System.out.println("Видалено запис з ключем '" + KEY_TO_SEARCH_AND_DELETE + "'. Власник був: " + removedValue);
        } else {
            System.out.println("Ключ '" + KEY_TO_SEARCH_AND_DELETE + "' не знайдено для видалення.");
        }
    }

    /**
     * Видаляє записи з LinkedHashMap за значенням.
     */
    void removeByValueFromLinkedHashMap() {
        long timeStart = System.nanoTime();

        List<Newt> keysToRemove = new ArrayList<>();
        for (Map.Entry<Newt, String> entry : treeMap.entrySet()) {
            if (entry.getValue() != null && entry.getValue().equals(VALUE_TO_SEARCH_AND_DELETE)) {
                keysToRemove.add(entry.getKey());
            }
        }
        
        for (Newt key : keysToRemove) {
            treeMap.remove(key);
        }

        PerformanceTracker.displayOperationTime(timeStart, "видалення за значенням з LinkedHashMap");

        System.out.println("Видалено " + keysToRemove.size() + " записів з власником '" + VALUE_TO_SEARCH_AND_DELETE + "'");
    }

    /**
     * Головний метод для запуску програми.
     */
    public static void main(String[] args) {
        // Створюємо початкові дані (ключ: Newt, значення: ім'я власника)
        HashMap<Newt, String> hashtable = new HashMap<>();
        hashtable.put(new Newt("Тум", "Сова вухата"), "Андрій");
        hashtable.put(new Newt("Луна", "Полярна сова"), "Ірина");
        hashtable.put(new Newt("Барсик", "Сова сіра"), "Олена");
        hashtable.put(new Newt("Боні", "Сипуха"), "Олена");
        hashtable.put(new Newt("Тайсон", "Сова болотяна"), "Ірина");
        hashtable.put(new Newt("Барсик", "Сичик-горобець"), "Андрій");
        hashtable.put(new Newt("Ґуфі", "Сова болотяна"), "Тимофій");
        hashtable.put(new Newt("Боні", "Сова яструбина"), "Поліна");
        hashtable.put(new Newt("Муся", "Сова білолиця"), "Стефанія");
        hashtable.put(new Newt("Чіпо", "Сичик-хатник"), "Ярослав");

        LinkedHashMap<Newt, String> treeMap = new LinkedHashMap<Newt, String>() {{
            put(new Newt("Тум", "Сова вухата"), "Андрій");
            put(new Newt("Луна", "Полярна сова"), "Ірина");
            put(new Newt("Барсик", "Сова сіра"), "Олена");
            put(new Newt("Боні", "Сипуха"), "Олена");
            put(new Newt("Тайсон", "Сова болотяна"), "Ірина");
            put(new Newt("Барсик", "Сичик-горобець"), "Андрій");
            put(new Newt("Ґуфі", "Сова болотяна"), "Тимофій");
            put(new Newt("Боні", "Сова яструбина"), "Поліна");
            put(new Newt("Муся", "Сова білолиця"), "Стефанія");
            put(new Newt("Чіпо", "Сичик-хатник"), "Ярослав");
        }};

        // Створюємо об'єкт і виконуємо операції
        BasicDataOperationUsingMap operations = new BasicDataOperationUsingMap(hashtable, treeMap);
        operations.executeDataOperations();
    }
}
