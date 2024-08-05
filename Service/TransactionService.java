//package ahmetcetinkaya.Solvo.Service;
//
//import ahmetcetinkaya.Solvo.Entity.ExchangeRate;
//import ahmetcetinkaya.Solvo.Entity.Limit;
//import ahmetcetinkaya.Solvo.Entity.Transaction;
//import ahmetcetinkaya.Solvo.Enum.ExpenseCategory;
//import ahmetcetinkaya.Solvo.Repository.ExchangeRateRepository;
//import ahmetcetinkaya.Solvo.Repository.LimitRepository;
//import ahmetcetinkaya.Solvo.Repository.TransactionRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//import java.time.ZoneId;
//import java.util.Currency;
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//
//
//@Service
//@Transactional
//
//public class TransactionService {
//
//    private final TransactionRepository transactionRepository;
//    private final LimitRepository limitRepository;
//    private final ExchangeRateRepository exchangeRateRepository;
//
//    @Autowired
//    public TransactionService(TransactionRepository transactionRepository, LimitRepository limitRepository, ExchangeRateRepository exchangeRateRepository) {
//        this.transactionRepository = transactionRepository;
//        this.limitRepository = limitRepository;
//        this.exchangeRateRepository = exchangeRateRepository;
//    }
//
//    @Transactional
//    public Transaction saveTransaction(Transaction transaction) {
//        // Преобразование суммы в USD
//        transaction.setSum(convertCurrencyToUSD(transaction.getSum(),transaction.getCurrency(), transaction.getDatetime()));
//
//        // Проверка лимита
//        checkLimit(transaction);
//
//        return transactionRepository.save(transaction);
//    }
//
//    private void checkLimit(Transaction transaction) {
//        Limit limit = findLimit(transaction.getExpenseCategory(), transaction.getDatetime());
//        if (limit != null) {
//            if (limit.getLimitSum().compareTo(transaction.getSum().add(limit.getLimitSum())) < 0) {
//                transaction.setLimitExceeded(true);
//            }
//        } else {
//            // Установка дефолтного лимита, если лимит не найден
//
//            limit = Limit.builder()
//                    .limitSum(BigDecimal.valueOf(1000))
//                    .limitCurrency(Currency.USD)
//                    .expenseCategory(transaction.getExpenseCategory())
//                    .limitDatetime(LocalDate.now())
//                    .build();
//            limitRepository.save(limit);
//        }
//    }
//    private Limit findLimit(ExpenseCategory expenseCategory, Date datetime) {
//        LocalDate localDateTime = LocalDate.ofInstant(datetime.toInstant(), ZoneId.systemDefault());
//        return limitRepository.findByExpenseCategoryAndLimitDatetime(expenseCategory, localDateTime).orElse(null);
//    }
//
//    private BigDecimal convertCurrencyToUSD(BigDecimal sum, Currency currency, Date date) {
//        // Получить курс валюты
//        Optional<ExchangeRate> rate = exchangeRateRepository.findByBaseCurrencyAndTargetCurrencyAndDate(currency, Currency.USD, LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault()));
//        if (rate.isPresent()) {
//            return sum.divide(rate.get().getRate(), BigDecimal.ROUND_HALF_UP);
//        } else {
//            throw new IllegalArgumentException("Курс валюты не найден.");
//        }
//}
//
//    public List<Transaction> getTransactionsByLimitExceeded() {
//        return transactionRepository.findAllByLimitExceededTrue();
//    }
//
//    public List<Transaction> getTransactionsByDate(LocalDate startDate, LocalDate endDate) {
//        return transactionRepository.findAllByDatetimeBetween(startDate, endDate);
//    }
//    @Transactional
//    public Limit setLimit(Limit limit) {
//        // Проверка, что лимит не установлен в прошлом или будущем
//        if (limit.getLimitDatetime().isBefore(LocalDate.now())) {
//            throw new IllegalArgumentException("Лимит не может быть установлен в прошлом.");
//        }
//        if (limit.getLimitDatetime().isAfter(LocalDate.now())) {
//            throw new IllegalArgumentException("Лимит не может быть установлен в будущем.");
//        }
//
//        // Сохранение лимита
//        return limitRepository.save(limit);
//    }
//}
