package ahmetcetinkaya.Solvo.Service;

import ahmetcetinkaya.Solvo.Entity.Limit;
import ahmetcetinkaya.Solvo.Repository.LimitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;

@Service
@Transactional
public class LimitService {

    private final LimitRepository limitRepository;

    @Autowired
    public LimitService(LimitRepository limitRepository) {
        this.limitRepository = limitRepository;
    }

    public Limit setLimit(Limit limit) {
        // Проверка, что лимит не установлен в прошлом или будущем
        if (limit.getLimitDatetime().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Лимит не может быть установлен в прошлом.");
        }
        if (limit.getLimitDatetime().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Лимит не может быть установлен в будущем.");
        }

        // Проверка, что лимит не существует для данной категории и даты
        Optional<Limit> existingLimit = limitRepository.findByExpenseCategoryAndLimitDatetimeAndLimitCurrency(limit.getExpenseCategory(), limit.getLimitDatetime(), limit.getLimitCurrency());
        if (existingLimit.isPresent()) {
            throw new IllegalArgumentException("Лимит для этой категории и даты уже существует.");
        }

        // Сохранение лимита
        return limitRepository.save(limit);
    }
}
