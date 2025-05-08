package salaryCalculatorTemplateDesign;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static salaryCalculator.SalaryConstants.PSD_PERCENT;
import static salaryCalculator.SalaryConstants.VSD_PERCENT;

public abstract class SalaryCalculatorTemplate {

    public final double calculateNetSalary(double brutoSalary, int workCapability, boolean hasPensionDeduction, boolean hasNpd) {
        List<SalaryBreakdownItem> items = getDeductionsList(brutoSalary, workCapability, hasPensionDeduction, hasNpd);
        double result = brutoSalary;
        for (SalaryBreakdownItem item : items) {
            result -= item.amount();
        }
        return result;
    }

    public final List<SalaryBreakdownItem> getBreakdown(double brutoSalary, int workCapability, boolean hasPensionDeduction, boolean hasNpd) {
        List<SalaryBreakdownItem> items = new ArrayList<>(getDeductionsList(brutoSalary, workCapability, hasPensionDeduction, hasNpd));
        items.sort(Comparator.comparingDouble(SalaryBreakdownItem::amount).reversed());
        items.add(new SalaryBreakdownItem("Total net salary", calculateNetSalary(brutoSalary, workCapability, hasPensionDeduction, hasNpd))
        );
        return items;
    }

    protected double calculatePsd(double brutoSalary) {
        return (brutoSalary * PSD_PERCENT) / 100;
    }

    protected double calculateVsd(double brutoSalary) {
        return (brutoSalary * VSD_PERCENT) / 100;
    }

    protected abstract List<SalaryBreakdownItem> getDeductionsList(double brutoSalary, int workCapability, boolean hasPensionDeduction, boolean hasNpd);
}
