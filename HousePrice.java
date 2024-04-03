import java.util.*;

public class HousePrice {

    public static void main(String[] args) {
        List<House> houses = new ArrayList<>();
        houses.add(new House(180000, 1800));
        houses.add(new House(200000, 1700));
        houses.add(new House(150000, 1200));
        houses.add(new House(330000, 2400));
        houses.add(new House(280000, 1500));
        houses.add(new House(450000, 1900));

        Map<String, Integer> priceRangesCount = new LinkedHashMap<>();
        Map<String, Double> priceRangesSquareFootage = new LinkedHashMap<>();

        priceRangesCount.put("$100,000-200,000", 0);
        priceRangesCount.put("$200,000-300,000", 0);
        priceRangesCount.put("$300,000-400,000", 0);
        priceRangesCount.put("$400,000+", 0);

        for (House house : houses) {
            double price = house.getPrice();
            String range = getPriceRange(price);
            priceRangesCount.put(range, priceRangesCount.get(range) + 1);
            priceRangesSquareFootage.put(range, priceRangesSquareFootage.getOrDefault(range, 0.0) + house.getSquareFootage());
        }

        for (Map.Entry<String, Integer> entry : priceRangesCount.entrySet()) {
            int count = entry.getValue();
            double averageSquareFootage = priceRangesSquareFootage.get(entry.getKey()) / count;
            System.out.println("Number of houses sold within " + entry.getKey() + " range: " + count);
            System.out.println("Average square footage for " + entry.getKey() + ": " + averageSquareFootage);
            System.out.println();
        }
    }

    public static String getPriceRange(double price) {
        if (price >= 100000 && price < 200000) {
            return "$100,000-200,000";
        } else if (price >= 200000 && price < 300000) {
            return "$200,000-300,000";
        } else if (price >= 300000 && price < 400000) {
            return "$300,000-400,000";
        } else {
            return "$400,000+";
        }
    }
}

class House {
    private double price;
    private double squareFootage;

    public House(double price, double squareFootage) {
        this.price = price;
        this.squareFootage = squareFootage;
    }

    public double getPrice() {
        return price;
    }

    public double getSquareFootage() {
        return squareFootage;
    }
}
