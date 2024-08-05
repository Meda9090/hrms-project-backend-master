package ahmetcetinkaya.Solvo.Entity;

import ahmetcetinkaya.Solvo.Enum.ExpenseCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Limit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal limitSum;
    private Currency limitCurrency;
    private LocalDateTime limitDatetime;
    private ExpenseCategory expenseCategory;

    @OneToMany(mappedBy = "limit", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions;
}
