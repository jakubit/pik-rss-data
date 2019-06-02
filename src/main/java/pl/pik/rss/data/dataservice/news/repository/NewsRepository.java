package pl.pik.rss.data.dataservice.news.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import pl.pik.rss.data.dataservice.news.model.News;
import pl.pik.rss.data.dataservice.news.model.Record;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public interface NewsRepository extends MongoRepository<Record, String> {

    @Query("{'rssItem.publishedDate': {$lte: ?1, $gte: ?0}, 'rssChannelInfo.link': ?2}")
    List<Record> findRecordsFromChannelBetweenDates(Long startDate, Long endDate, String rssUrl);

    /*@Query(value="{name: ?0, approval: {'$ne': null}}",
            sort="{'approval.approvedDate': -1}",
            fields = "{ _id: 1 }")*/

    //$orderby: {dateTime: -1}
    @Query("{'rssChannelInfo.link': ?0, $limit: ?1}")
    List<Record> findNewestRecordsFromChannel(String rssUrl, int quantity);
}
