package salaryCalculatorTemplateDesign;

public final class SalaryCalculatorFactory {
    private SalaryCalculatorFactory() {
    }

    public static SalaryCalculatorTemplate create(SalaryCalculatorType type) {
        return switch (type) {
            case STANDARD -> new StandardSalaryCalculator();
            case FREELANCE -> new FreelanceSalaryCalculator();
        };
    }
}
