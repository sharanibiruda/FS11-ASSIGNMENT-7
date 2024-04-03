import java.util.*;

public class Weather {
    
    public static void main(String[] args) {
        List<WeatherData> weatherDataList = new ArrayList<>();
        // Populate weather data list (Assuming temperatures are in Celsius and humidity is in percentage)
        weatherDataList.add(new WeatherData(5, 70));
        weatherDataList.add(new WeatherData(12, 65));
        weatherDataList.add(new WeatherData(3, 80));
        weatherDataList.add(new WeatherData(-5, 90));
        weatherDataList.add(new WeatherData(18, 60));
        weatherDataList.add(new WeatherData(7, 75));
        
        Map<String, TemperatureStatistics> temperatureStatisticsMap = analyzeWeatherData(weatherDataList);
        
        // Print the result
        for (Map.Entry<String, TemperatureStatistics> entry : temperatureStatisticsMap.entrySet()) {
            TemperatureStatistics stats = entry.getValue();
            System.out.println("Temperature range: " + entry.getKey());
            System.out.println("Number of days: " + stats.getNumDays());
            System.out.println("Average humidity: " + stats.getAverageHumidity());
            System.out.println();
        }
    }

    public static Map<String, TemperatureStatistics> analyzeWeatherData(List<WeatherData> weatherDataList) {
        Map<String, TemperatureStatistics> result = new HashMap<>();
        // Define temperature ranges and initialize temperature statistics for each range
        int[][] temperatureRanges = {
            {-100, 0}, // <0°C
            {0, 10},   // 0-10°C
            {10, 20},  // 10-20°C
            {20, 30},  // 20-30°C
            {30, 100}  // >=30°C
        };
        
        for (int[] range : temperatureRanges) {
            result.put(getTemperatureRangeLabel(range[0], range[1]), new TemperatureStatistics());
        }
        
        // Iterate through weather data to count days and calculate average humidity for each temperature range
        for (WeatherData weatherData : weatherDataList) {
            for (int[] range : temperatureRanges) {
                if (weatherData.getTemperature() >= range[0] && weatherData.getTemperature() < range[1]) {
                    String rangeLabel = getTemperatureRangeLabel(range[0], range[1]);
                    TemperatureStatistics stats = result.get(rangeLabel);
                    stats.incrementNumDays();
                    stats.addToHumiditySum(weatherData.getHumidity());
                    break;
                }
            }
        }
        
        // Calculate average humidity for each temperature range
        for (TemperatureStatistics stats : result.values()) {
            stats.calculateAverageHumidity();
        }
        
        return result;
    }
    
    public static String getTemperatureRangeLabel(int lowerBound, int upperBound) {
        if (lowerBound == -100) {
            return "<0°C";
        } else if (upperBound == 100) {
            return ">=30°C";
        } else {
            return lowerBound + "-" + upperBound + "°C";
        }
    }
}

class WeatherData {
    private int temperature;
    private int humidity;
    
    public WeatherData(int temperature, int humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
    }
    
    public int getTemperature() {
        return temperature;
    }
    
    public int getHumidity() {
        return humidity;
    }
}

class TemperatureStatistics {
    private int numDays;
    private int humiditySum;
    private double averageHumidity;
    
    public TemperatureStatistics() {
        this.numDays = 0;
        this.humiditySum = 0;
        this.averageHumidity = 0.0;
    }
    
    public void incrementNumDays() {
        numDays++;
    }
    
    public void addToHumiditySum(int humidity) {
        humiditySum += humidity;
    }
    
    public void calculateAverageHumidity() {
        if (numDays > 0) {
            averageHumidity = (double) humiditySum / numDays;
        }
    }
    
    public int getNumDays() {
        return numDays;
    }
    
    public double getAverageHumidity() {
        return averageHumidity;
    }
}
