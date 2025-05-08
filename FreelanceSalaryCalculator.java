package salaryCalculatorTemplateDesign;

import java.util.List;

public class FreelanceSalaryCalculator extends SalaryCalculatorTemplate {

    protected List<SalaryBreakdownItem> getDeductionsList(double brutoSalary, int workCapability, boolean hasPensionDeduction, boolean hasNpd) {
        return List.of(
                new SalaryBreakdownItem("PSD", calculatePsd(brutoSalary)),
                new SalaryBreakdownItem("VSD", calculateVsd(brutoSalary)));
    }
}
