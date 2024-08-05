package ahmetcetinkaya.Solvo.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
public class ExchangeRate { //Обмениваться

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Currency basaCurrency;
    private Currency targetCurrency;
    private BigDecimal rate;
    private LocalDate date;
}
