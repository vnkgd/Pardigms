import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        List<Laptop> laptops = FileLoader.loadLaptops("laptops.txt");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Выберите характеристики ноутбука:");

        // Получение уникальных значений для каждой характеристики
        Set<Integer> uniqueRamValues = FileLoader.getUniqueRamValues(laptops);
        Set<Integer> uniqueStorageValues = FileLoader.getUniqueStorageValues(laptops);
        Set<String> uniqueOsValues = FileLoader.getUniqueOsValues(laptops);
        Set<String> uniqueColorValues = FileLoader.getUniqueColorValues(laptops);

        // Вывод уникальных значений с пунктами
        System.out.println("Оперативная память (GB):");
        printOptions(uniqueRamValues);

        System.out.println("Объем жесткого диска (GB, TB):");
        printOptions(uniqueStorageValues);

        System.out.println("Операционная система:");
        printOptions(uniqueOsValues);

        System.out.println("Цвет:");
        printOptions(uniqueColorValues);

        // Ввод пользовательских параметров
        System.out.println("Выберите опцию для оперативной памяти:");
        int ram = getUserInput(uniqueRamValues);

        System.out.println("Выберите опцию для объема жесткого диска:");
        int storage = getUserInput(uniqueStorageValues);

        System.out.println("Выберите опцию для операционной системы:");
        String os = getUserInput(uniqueOsValues);

        System.out.println("Выберите опцию для цвета:");
        String color = getUserInput(uniqueColorValues);

        // Выбор подходящих ноутбуков
        LaptopSelector laptopSelector = new LaptopSelector(laptops);
        List<Laptop> selectedLaptops = laptopSelector.selectLaptops(ram, storage, os, color);
        laptopSelector.displayResults(selectedLaptops);
    }

    private static void printOptions(Set<?> options) {
        int optionNumber = 1;
        for (Object option : options) {
            System.out.println(optionNumber + " - " + option);
            optionNumber++;
        }
    }

    private static <T> T getUserInput(Set<T> options) {
        Scanner scanner = new Scanner(System.in);
        int selectedOption;
        do {
            System.out.print("Введите номер выбранной опции: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Некорректный ввод. Попробуйте снова.");
                scanner.next(); // Очистка буфера
            }
            selectedOption = scanner.nextInt();
        } while (selectedOption < 1 || selectedOption > options.size());

        // Преобразование выбранного номера в соответствующую опцию
        int optionIndex = selectedOption - 1;
        return options.toArray((T[]) new Object[0])[optionIndex];
    }
}
