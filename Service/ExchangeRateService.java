package ahmetcetinkaya.Solvo.Service;

import ahmetcetinkaya.Solvo.Entity.ExchangeRate;
import ahmetcetinkaya.Solvo.Repository.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ExchangeRateService {
    private final ExchangeRateRepository exchangeRateRepository;

    @Autowired
    public ExchangeRateService(ExchangeRateRepository exchangeRateRepository) {
        this.exchangeRateRepository = exchangeRateRepository;
    }

    public ExchangeRate saveExchangeRate(ExchangeRate exchangeRate) {
        // Проверка на наличие курса валюты
        ExchangeRate existingRate = findExistingRate(exchangeRate);
        if (existingRate != null) {
            // Обновление существующего курса валюты
            existingRate.setRate(exchangeRate.getRate());
            return exchangeRateRepository.save(existingRate);
        } else {
            return exchangeRateRepository.save(exchangeRate);
        }
    }
    private ExchangeRate findExistingRate(ExchangeRate exchangeRate) {
        return exchangeRateRepository
                .findByBaseCurrencyAndTargetCurrencyAndDate(exchangeRate.getBaseCurrency(),
                        exchangeRate.getTargetCurrency(),
                        exchangeRate.getDate()).orElse(null);
    }
}

