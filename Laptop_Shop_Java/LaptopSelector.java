import java.util.List;
import java.util.stream.Collectors;

public class LaptopSelector {
    private List<Laptop> laptops;

    public LaptopSelector(List<Laptop> laptops) {
        this.laptops = laptops;
    }

    public List<Laptop> selectLaptops(int ram, int storage, String os, String color) {
        return laptops.stream()
                .filter(laptop -> laptop.getRam() == ram && laptop.getStorage() == storage
                        && laptop.getOs().equalsIgnoreCase(os) && laptop.getColor().equalsIgnoreCase(color))
                .collect(Collectors.toList());
    }

    public void displayResults(List<Laptop> selectedLaptops) {
        if (selectedLaptops.isEmpty()) {
            System.out.println("К сожалению, ноутбуки с такими характеристиками не найдены.");
        } else {
            System.out.println("Подходящие ноутбуки:");
            selectedLaptops.forEach(System.out::println);
        }
    }
}
