package pl.pik.rss.data.dataservice.subscription.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pik.rss.data.dataservice.subscription.model.Subscription;
import pl.pik.rss.data.dataservice.subscription.service.SubscriptionService;
import java.util.Optional;

@RestController
public class SubscriptionRestController {
    private final SubscriptionService subscriptionService;

    @Autowired
    public SubscriptionRestController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping("/subscribe")
    public ResponseEntity<?> subscribe(@RequestParam("rssUrl") String rssUrl) {
        try {
            subscriptionService.subscribeToRssUrl(rssUrl);
            return new ResponseEntity(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping("/subscriptions")
    public ResponseEntity<?> getAllSubscriptions() {
        return new ResponseEntity<>(subscriptionService.getAllSubscriptions(), HttpStatus.OK);
    }

    @GetMapping("/subscription")
    public ResponseEntity<?> getSubscription(@RequestParam("rssUrl") String rssUrl) {
        try {
            Optional<Subscription> subscription = subscriptionService.findSubscriptionByRssUrl(rssUrl);
            return new ResponseEntity<>(subscription, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
