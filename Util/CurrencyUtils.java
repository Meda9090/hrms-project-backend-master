package ahmetcetinkaya.Solvo.Util;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CurrencyUtils {

    public static BigDecimal convertKztToUsd(BigDecimal sum, LocalDate date) {
        // В реальной реализации получить курс из базы данных
        return sum.divide(new BigDecimal("475"), BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal convertRubToUsd(BigDecimal sum, LocalDate date) {
        // В реальной реализации получить курс из базы данных
        return sum.divide(new BigDecimal("90"), BigDecimal.ROUND_HALF_UP);
    }
}
