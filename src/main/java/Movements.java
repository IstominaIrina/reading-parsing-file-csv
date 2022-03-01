import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Movements {

    ArrayList<Double> income = new ArrayList<>();
    ArrayList<Double> expense = new ArrayList<>();
    TreeMap<String, Double> infoExpense = new TreeMap<>();

    double sumExpense;
    double sumIncome;
    String infoTekst2;

    public Movements(String pathMovementsCsv) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(pathMovementsCsv));
            boolean isHeader = true;
            for (String line : lines) {
                ArrayList<String> testing = new ArrayList<>();
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                String[] fragments = line.split("\"");
                for (String fragment : fragments) {
                    Pattern p = Pattern.compile("^[0-9]+[\\.,\\,][0-9]+$");
                    Matcher m = p.matcher(fragment);
                    if (m.find() == false) {
                        String[] fragments2 = fragment.split(",");
                        this.income.add(Double.parseDouble(fragments2[6]));
                        for (int i = 0; i < fragments2.length; i++) {
                            testing.add(fragments2[i]);
                        }
                    } else
                        testing.add(fragment.replace(",", "."));
                }

                String[] infoTekst = testing.get(5).split("\\s{4,}\\w*");
                this.infoTekst2 = infoTekst[1].replace("\\", " ").replace("/", " ").trim();

                if (Double.parseDouble(testing.get(7)) != 0) {
                    updateValue(infoExpense, infoTekst2, Double.parseDouble(testing.get(7)));
                }
                this.expense.add(Double.parseDouble(testing.get(7)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateValue(Map<String, Double> infoExpense, String key, Double value) {
        if (infoExpense.containsKey(key)) {
            infoExpense.put(key, infoExpense.get(key) + value);
        } else {
            infoExpense.put(key, value);
        }
    }

    public void printMap() {
        System.out.println("\nСуммы расходов по организациям:");
        for (String key : infoExpense.keySet()) {
            System.out.println(key + "    " + infoExpense.get(key) + " руб.");
        }
    }

    public double getExpenseSum() {
        this.sumExpense = 0;
        for (int i = 0; i < expense.size(); i++) {
            sumExpense += expense.get(i);
        }
        System.out.println("Сумма расходов: " + sumExpense + " руб.");
        return sumExpense;
    }

    public double getIncomeSum() {
        this.sumIncome = 0;
        for (int i = 0; i < income.size(); i++) {
            sumIncome += income.get(i);
        }
        System.out.println("Сумма доходов: " + sumIncome + " руб.");
        return sumIncome;
    }
}
