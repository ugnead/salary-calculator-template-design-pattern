package salaryCalculatorTemplateDesign;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Select calculator type: ");
        String type = scanner.next();

        System.out.print("Enter bruto salary: ");
        double bruto = scanner.nextDouble();

        System.out.print("Enter work capability (0–100): ");
        int workCap = scanner.nextInt();

        System.out.print("Apply pension deduction? (true/false): ");
        boolean pension = scanner.nextBoolean();

        System.out.print("Apply NPD? (true/false): ");
        boolean npd = scanner.nextBoolean();

        SalaryCalculatorTemplate salary = SalaryCalculatorFactory.create(SalaryCalculatorType.fromString(type));

        List<SalaryBreakdownItem> items = salary.getBreakdown(bruto, workCap, pension, npd);

        for (SalaryBreakdownItem item : items) {
            System.out.printf("%s: %.2f EUR %n", item.label(), item.amount());
        }

        scanner.close();
    }
}
