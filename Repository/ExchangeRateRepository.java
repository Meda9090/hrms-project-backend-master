package ahmetcetinkaya.Solvo.Repository;

import ahmetcetinkaya.Solvo.Entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Currency;
import java.util.Optional;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {
    Optional<ExchangeRate>findByBaseCurrencyAndTargetCurrencyAndDate(Currency baseCurrency, Currency targetCurrency, LocalDate date);

}
