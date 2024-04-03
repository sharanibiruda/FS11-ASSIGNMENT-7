import java.util.*;

public class MedicalTest {

    public static void main(String[] args) {
        List<MedicalTestResult> testResults = new ArrayList<>();
        testResults.add(new MedicalTestResult("Maha", 60.0));
        testResults.add(new MedicalTestResult("Kamala", 120.0));
        testResults.add(new MedicalTestResult("Swathi", 160.0));
        testResults.add(new MedicalTestResult("Seetha", 105.0));
        testResults.add(new MedicalTestResult("Valli", 150.0));

        Map<String, Integer> resultRangesCount = new LinkedHashMap<>();
        resultRangesCount.put("Normal", 0);
        resultRangesCount.put("Borderline", 0);
        resultRangesCount.put("High", 0);

        Map<String, Double> resultRangesAverage = new LinkedHashMap<>();

        for (MedicalTestResult result : testResults) {
            String range = getResultRange(result.getResultValue());
            resultRangesCount.put(range, resultRangesCount.get(range) + 1);
            resultRangesAverage.put(range, resultRangesAverage.getOrDefault(range, 0.0) + result.getResultValue());
        }

        for (Map.Entry<String, Integer> entry : resultRangesCount.entrySet()) {
            int count = entry.getValue();
            double averageValue = resultRangesAverage.get(entry.getKey()) / count;
            System.out.println("Number of patients with results in " + entry.getKey() + " range: " + count);
            System.out.println("Average value for " + entry.getKey() + " range: " + averageValue);
            System.out.println();
        }
    }

    public static String getResultRange(double resultValue) {
        if (resultValue <= 100) {
            return "Normal";
        } else if (resultValue > 100 && resultValue <= 150) {
            return "Borderline";
        } else {
            return "High";
        }
    }
}

class MedicalTestResult {
    private String patientName;
    private double resultValue;

    public MedicalTestResult(String patientName, double resultValue) {
        this.patientName = patientName;
        this.resultValue = resultValue;
    }

    public String getPatientName() {
        return patientName;
    }

    public double getResultValue() {
        return resultValue;
    }
}