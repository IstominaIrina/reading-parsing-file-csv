import junit.framework.TestCase;

import java.io.File;
import java.io.PrintWriter;

public class MovementsTest extends TestCase {

    private String testPathMovementsCsv = "testing/movementsTest.csv";
    private Movements test2;


    @Override
    protected void setUp() throws Exception {

        File root = new File("testing");
        root.mkdir();
        PrintWriter movTestCsv = new PrintWriter("testing/movementsTest.csv");

        movTestCsv.write("Тип счёта,Номер счета,Валюта,Дата операции,Референс проводки,Описание операции,Приход,Расход");
        movTestCsv.write('\n');

        movTestCsv.write("Текущий счёт,40817813206170024534,RUR,31.05.17,CRD_1U34U7,548673++++++1028    809216  /RU/поступление зп         31.05.17 31.05.17 35000.00       RUR MCC6536,35000,0\n");
        movTestCsv.write("Текущий счёт,40817813206170024534,RUR,31.05.17,CRD_5XK5TM,548673++++++1028    21708201/RUS/мороженное              31.05.17 29.05.17       300.00  RUR (Apple Pay-7666) MCC5814,0,300\n");
        movTestCsv.write("Текущий счёт,40817813206170024534,RUR,28.05.17,CRD_7HN8BF,548673++++++1028    809216  /RU/поступление премии          28.05.17 28.05.17 5000.00       RUR MCC6536,5000,0\n");
        movTestCsv.write("Текущий счёт,40817813206170024534,RUR,20.05.17,CRD_96C6Y4,548673++++++1028      210106/643/расходы в ТРЦ                      20.05.17 19.05.17       900.00  RUR MCC6011,0,900\n");
        movTestCsv.write("Текущий счёт,40817813206170024534,RUR,20.05.17,CRD_24Y3Y4,548673++++++1028      210102/643/покупка курса                 20.05.17 19.05.17     33000.90  RUR MCC6011,0,\"33000,90\"\n");
        movTestCsv.write("Текущий счёт,40817813206170024534,RUR,19.05.17,CRD_92H9X4,548673++++++1028    809216  /RU/премия       19.05.17 19.05.17 3900.00       RUR MCC6536,3900,0\n");
        movTestCsv.write("Текущий счёт,40817813206170024534,RUR,19.05.17,CRD_4EK8X4,548673++++++1028    809216  /RU/заработная плата         19.05.17 19.05.17 30000.00      RUR MCC6536,30000,0\n");
        movTestCsv.write("Текущий счёт,40817813206170024534,RUR,19.05.17,CRD_91B3CK,548673++++++1028      210106\\643\\расходы в ТРЦ                      18.05.17 17.05.17      9900.5  RUR MCC6011,0,\"9900,50\"\n");
        movTestCsv.write("Текущий счёт,40817813206170024534,RUR,19.05.17,CRD_7VM0CK,548673++++++1028      210106\\643\\покупка на WB                      18.05.17 17.05.17      8900.00  RUR MCC6011,0,8900\n");
        movTestCsv.write("Текущий счёт,40817813206170024534,RUR,19.05.17,CRD_67F2BK,548673++++++1028      210102\\643\\покупка на WB                       18.05.17 17.05.17      4000.00  RUR MCC6011,0,4000\n");
        movTestCsv.write("Текущий счёт,40817813206170024534,RUR,19.05.17,CRD_5R75AK,548673++++++1028      210104\\643\\расходы в ТРЦ                    18.05.17 17.05.17     1000.56  RUR MCC6011,0,\"1000,56\"\n");

        movTestCsv.flush();
        movTestCsv.close();

        test2 = new Movements(testPathMovementsCsv);
    }

    public void testGetIncomeSum() {
        double actual = test2.getIncomeSum();
        double expected = 73900;
        assertEquals(expected, actual);
    }

    public void testGetExpenseSum() {
        double actual = test2.getExpenseSum();
        double expected = 58001.96;
        assertEquals(expected, actual);
    }


}
