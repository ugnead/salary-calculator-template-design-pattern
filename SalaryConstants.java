package salaryCalculatorTemplateDesign;

public final class SalaryConstants {
    private SalaryConstants() {
    }

    // tax rates
    public static final double PSD_PERCENT = 6.98;
    public static final double VSD_PERCENT = 12.52;
    public static final double PENSION_DEDUCTION_PERCENT = 3;

    // salary thresholds
    public static final double MMA = 1038;
    public static final double VDU = 2108.88;
    public static final double VDU_60 = 126532.80; // VDU*60

    // NPD
    public static final double NPD_UNDER_25_WORK_CAP = 1127;
    public static final double NPD_UNDER_55_WORK_CAP = 1057;
    public static final double NPD_AT_MMA = 747;

    // GPM
    public static final double GPM_RATE_LOWER = 0.20;
    public static final double GPM_RATE_UPPER = 0.32;
}
