package pl.pik.rss.data.dataservice.subscription.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pik.rss.data.dataservice.subscription.model.Subscription;
import pl.pik.rss.data.dataservice.subscription.repository.SubscriptionRepository;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;

    @Autowired
    public SubscriptionService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public void subscribeToRssUrl(String rssUrl) throws IllegalArgumentException {
        if (rssUrl.isEmpty())
            throw new IllegalArgumentException(rssUrl);

        try {
            URL channelUrl = new URL(rssUrl);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(rssUrl);
        }

        Subscription subscription = new Subscription(rssUrl, LocalDateTime.now().minusYears(1));
        subscriptionRepository.save(subscription);
    }

    public Optional<Subscription> findSubscriptionByRssUrl(String rssUrl) {
        if (rssUrl.isEmpty())
            throw new IllegalArgumentException(rssUrl);

        return subscriptionRepository.findById(rssUrl);
    }

    public List<Subscription> getAllSubscriptions() {
        Iterable<Subscription> subscriptions = subscriptionRepository.findAll();
        return new LinkedList<>((Collection<? extends Subscription>) subscriptions);
    }
}
