package salaryCalculatorTemplateDesign;

import java.util.List;

import static salaryCalculator.SalaryConstants.*;

public class StandardSalaryCalculator extends SalaryCalculatorTemplate {

    public boolean isWorkCapabilityValid(int workCapability) {
        return (workCapability >= 60 && workCapability <= 100) ||
                (workCapability >= 30 && workCapability <= 55) ||
                (workCapability >= 0 && workCapability <= 25);
    }

    private double calculateNpd(double brutoSalary, int workCapability) {
        if (workCapability <= 25) return NPD_UNDER_25_WORK_CAP;
        if (workCapability <= 55) return NPD_UNDER_55_WORK_CAP;
        if (brutoSalary <= MMA) return NPD_AT_MMA;
        if (brutoSalary <= 2387.29) return 747 - 0.49 * (brutoSalary - MMA);
        return 400 - 0.18 * (brutoSalary - 642);
    }

    private double applyNpd(double brutoSalary, int workCapability) {
        return brutoSalary - calculateNpd(brutoSalary, workCapability);
    }

    private double calculateYearlySalary(double brutoSalary, int workCapability, boolean hasNpd) {
        if ((workCapability <= 55) || hasNpd) {
            return applyNpd(brutoSalary, workCapability) * 12;
        }
        return brutoSalary * 12;
    }

    public double calculateGpm(double brutoSalary, int workCapability, boolean hasNpd) {
        double yearlySalary = calculateYearlySalary(brutoSalary, workCapability, hasNpd);
        double totalGpm;
        if (yearlySalary <= VDU_60) {
            totalGpm = yearlySalary * GPM_RATE_LOWER;
        } else {
            totalGpm = (VDU_60 * GPM_RATE_LOWER) + ((yearlySalary - VDU_60) * GPM_RATE_UPPER);
        }
        totalGpm /= 12;
        return totalGpm >= 0 ? totalGpm : 0;
    }

    public double calculatePensionDeduction(double brutoSalary, boolean hasPensionDeduction) {
        if (!hasPensionDeduction) return 0;
        return (brutoSalary * PENSION_DEDUCTION_PERCENT) / 100;
    }

    protected List<SalaryBreakdownItem> getDeductionsList(double brutoSalary, int workCapability, boolean hasPensionDeduction, boolean hasNpd) {
        return List.of(
                new SalaryBreakdownItem("GPM", calculateGpm(brutoSalary, workCapability, hasNpd)),
                new SalaryBreakdownItem("PSD", calculatePsd(brutoSalary)),
                new SalaryBreakdownItem("VSD", calculateVsd(brutoSalary)),
                new SalaryBreakdownItem("Deduction for pension fund", calculatePensionDeduction(brutoSalary, hasPensionDeduction)));
    }
}
