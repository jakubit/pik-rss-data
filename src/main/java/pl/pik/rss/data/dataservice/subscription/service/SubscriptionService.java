package pl.pik.rss.data.dataservice.subscription.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pik.rss.data.dataservice.subscription.model.Subscription;
import pl.pik.rss.data.dataservice.subscription.repository.SubscriptionRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;

    @Autowired
    public SubscriptionService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public void subscribeToRssUrl(String rssUrl){
        Subscription subscription = new Subscription(rssUrl, LocalDateTime.now().minusYears(1));
        subscriptionRepository.save(subscription);
    }

    public Optional<Subscription> findSubscriptionByRssUrl(String rssUrl) {
        return subscriptionRepository.findById(rssUrl);
    }
}
