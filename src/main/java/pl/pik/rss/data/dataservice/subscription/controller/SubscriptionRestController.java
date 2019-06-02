package pl.pik.rss.data.dataservice.subscription.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.pik.rss.data.dataservice.subscription.model.Subscription;
import pl.pik.rss.data.dataservice.subscription.service.SubscriptionService;


import java.time.LocalDateTime;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class SubscriptionRestController {
    private final SubscriptionService subscriptionService;

    @Autowired
    public SubscriptionRestController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping("/subscribe")
    public void subscribe(@RequestParam("rssUrl") String rssUrl) {
        subscriptionService.subscribeToRssUrl(rssUrl);
    }

    @GetMapping("/subscription")
    public Optional<Subscription> getSubscription(@RequestParam("rssUrl") String rssUrl) {
        return subscriptionService.findSubscriptionByRssUrl(rssUrl);
    }
}
