package ahmetcetinkaya.Solvo.Controller;

import ahmetcetinkaya.Solvo.Entity.ExchangeRate;
import ahmetcetinkaya.Solvo.Service.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exchange-rates")
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;

    @Autowired
    public ExchangeRateController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @PostMapping
    public ResponseEntity<ExchangeRate> saveExchangeRate(@RequestBody ExchangeRate exchangeRate) {
        ExchangeRate savedExchangeRate = exchangeRateService.saveExchangeRate(exchangeRate);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedExchangeRate);
    }
}

