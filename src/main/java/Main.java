public class Main {

    private static String pathMovementsCsv = "BankStatement/movementList.csv";

    public static void main(String[] args) {

        Movements test = new Movements(pathMovementsCsv);
        test.getExpenseSum();
        test.getIncomeSum();
        test.printMap();
    }
}













