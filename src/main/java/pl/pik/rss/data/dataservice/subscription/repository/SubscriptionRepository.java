package pl.pik.rss.data.dataservice.subscription.repository;

import org.springframework.data.repository.CrudRepository;
import pl.pik.rss.data.dataservice.subscription.model.Subscription;

public interface SubscriptionRepository extends CrudRepository<Subscription, String> {
}
