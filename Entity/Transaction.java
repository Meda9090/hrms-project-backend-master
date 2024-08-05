package ahmetcetinkaya.Solvo.Entity;

import ahmetcetinkaya.Solvo.Enum.ExpenseCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity

public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountFrom; //Счет отправителя
    private String accountTo;   //Счет получателя
    private BigDecimal sum;
    private Currency currency;

    private ExpenseCategory expenseCategory;    //Категория расхода
    private Date datetime; //Дата и время
    private boolean limitExceeded;  //Флаг превышения лимита

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "limit_id")
    private Limit limit;

}
