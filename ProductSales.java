import java.util.*;

public class ProductSales {
    
    public static void main(String[] args) {
        List<Double> sales = Arrays.asList(20.0, 45.5, 60.0, 75.5, 100.0, 150.0, 175.0, 200.0, 250.0);
        Map<String, Double> priceRanges = new LinkedHashMap<>();

        priceRanges.put("$0-50", 0.0);
        priceRanges.put("$50-100", 0.0);
        priceRanges.put("$100-200", 0.0);
        priceRanges.put("$200+", 0.0);

        for (Double sale : sales) {
            if (sale <= 50.0) {
                priceRanges.put("$0-50", priceRanges.get("$0-50") + sale);
            } else if (sale <= 100.0) {
                priceRanges.put("$50-100", priceRanges.get("$50-100") + sale);
            } else if (sale <= 200.0) {
                priceRanges.put("$100-200", priceRanges.get("$100-200") + sale);
            } else {
                priceRanges.put("$200+", priceRanges.get("$200+") + sale);
            }
        }

        for (Map.Entry<String, Double> entry : priceRanges.entrySet()) {
            System.out.println("Number of products sold in " + entry.getKey() + ": " + countProductsSold(entry.getKey(), sales));
            System.out.println("Total revenue generated in " + entry.getKey() + ": $" + entry.getValue());
            System.out.println();
        }
    }

    public static long countProductsSold(String priceRange, List<Double> sales) {
        double lowerBound = getLowerBound(priceRange);
        double upperBound = getUpperBound(priceRange);
        return sales.stream().filter(sale -> sale >= lowerBound && sale <= upperBound).count();
    }

    public static double getLowerBound(String priceRange) {
        switch (priceRange) {
            case "$0-50":
                return 0.0;
            case "$50-100":
                return 50.0;
            case "$100-200":
                return 100.0;
            case "$200+":
                return 200.0;
            default:
                return 0.0;
        }
    }

    public static double getUpperBound(String priceRange) {
        switch (priceRange) {
            case "$0-50":
                return 50.0;
            case "$50-100":
                return 100.0;
            case "$100-200":
                return 200.0;
            case "$200+":
                return Double.MAX_VALUE;
            default:
                return 0.0;
        }
    }
}
