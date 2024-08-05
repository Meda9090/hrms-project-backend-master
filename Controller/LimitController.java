package ahmetcetinkaya.Solvo.Controller;

import ahmetcetinkaya.Solvo.Entity.Limit;
import ahmetcetinkaya.Solvo.Service.LimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/limits")
public class LimitController {

    private final LimitService limitService;

    @Autowired
    public LimitController(LimitService limitService) {
        this.limitService = limitService;
    }

    @PostMapping
    public ResponseEntity<Limit> setLimit(@RequestBody Limit limit) {
        Limit savedLimit = limitService.setLimit(limit);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedLimit);
    }
}

