package salaryCalculatorTemplateDesign;

public enum SalaryCalculatorType {
    STANDARD, FREELANCE;

    public static SalaryCalculatorType fromString(String input) {
        String normalize = input.trim().toUpperCase().replace(" ", "_");
        try {
            return SalaryCalculatorType.valueOf(normalize);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unknown type: " + input);
        }
    }
}
