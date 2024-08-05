package ahmetcetinkaya.Solvo.Repository;

import ahmetcetinkaya.Solvo.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllByLimitExceededTrue();
    List<Transaction> findAllByDatetimeBetween (LocalDate startDate,LocalDate endDate);
    Optional<Transaction> findById (Long id);

}
