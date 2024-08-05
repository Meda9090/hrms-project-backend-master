package ahmetcetinkaya.Solvo.Repository;

import ahmetcetinkaya.Solvo.Entity.Limit;
import ahmetcetinkaya.Solvo.Enum.Currency;
import ahmetcetinkaya.Solvo.Enum.ExpenseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface LimitRepository extends JpaRepository<Limit, Long> {
    Optional<Limit> findByExpenseCategoryAndLimitDatetime (ExpenseCategory expenseCategory);
    Optional<Limit> findByExpenseCategoryAndLimitDatetimeAndLimitCurrency (ExpenseCategory expenseCategory,
                                                                           LocalDate localDate,
                                                                           Currency limitCurrency);
}
