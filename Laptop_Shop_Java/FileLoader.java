import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FileLoader {
    public static List<Laptop> loadLaptops(String filePath) {
        List<Laptop> laptops = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                laptops.add(parseLaptop(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return laptops;
    }

    public static Set<Integer> getUniqueRamValues(List<Laptop> laptops) {
        Set<Integer> uniqueValues = new HashSet<>();
        for (Laptop laptop : laptops) {
            uniqueValues.add(laptop.getRam());
        }
        return uniqueValues;
    }

    public static Set<Integer> getUniqueStorageValues(List<Laptop> laptops) {
        Set<Integer> uniqueValues = new HashSet<>();
        for (Laptop laptop : laptops) {
            uniqueValues.add(laptop.getStorage());
        }
        return uniqueValues;
    }

    public static Set<String> getUniqueOsValues(List<Laptop> laptops) {
        Set<String> uniqueValues = new HashSet<>();
        for (Laptop laptop : laptops) {
            uniqueValues.add(laptop.getOs());
        }
        return uniqueValues;
    }

    public static Set<String> getUniqueColorValues(List<Laptop> laptops) {
        Set<String> uniqueValues = new HashSet<>();
        for (Laptop laptop : laptops) {
            uniqueValues.add(laptop.getColor());
        }
        return uniqueValues;
    }

    private static Laptop parseLaptop(String line) {
        String[] parts = line.split(", ");
        String brand = getValue(parts[0]);
        int ram = getIntValue(parts[1]);
        int storage = parseStorage(parts[2]);
        String os = getValue(parts[3]);
        String color = getValue(parts[4]);

        return new Laptop(brand, ram, storage, os, color);
    }

    private static int parseStorage(String storageString) {
        // Разделение строки по пробелу
        String[] storageParts = storageString.split(" ");

        // Пробегаемся по частям и ищем числовую часть
        for (String part : storageParts) {
            try {
                return Integer.parseInt(part.replaceAll("[^0-9]", ""));
            } catch (NumberFormatException ignored) {
            }
        }

        // Если не найдено числовое значение, возвращаем 0
        System.out.println("Ошибка парсинга объема диска: " + storageString);
        return 0;
    }

    private static String getValue(String part) {
        return part.split(": ")[1];
    }

    private static int getIntValue(String part) {
        return Integer.parseInt(part.split(": ")[1].replace("GB", ""));
    }
}
