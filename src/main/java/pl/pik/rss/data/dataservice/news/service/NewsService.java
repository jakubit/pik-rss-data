package pl.pik.rss.data.dataservice.news.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.DateOperators;
import org.springframework.stereotype.Service;
import pl.pik.rss.data.dataservice.news.model.Channel;
import pl.pik.rss.data.dataservice.news.model.News;
import pl.pik.rss.data.dataservice.news.model.Record;
import pl.pik.rss.data.dataservice.news.repository.NewsRepository;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

@Service
public class NewsService {

    private final NewsRepository newsRepository;

    @Autowired
    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public List<Record> getNews(String channelUrl, String startDateString, String endDateString) {
        LocalDateTime startDate = LocalDateTime.parse(startDateString, DateTimeFormatter.ofPattern("yyyy/MM/dd/HH:mm:ss"));
        LocalDateTime endDate = LocalDateTime.parse(endDateString, DateTimeFormatter.ofPattern("yyyy/MM/dd/HH:mm:ss"));

        Long startDateLong = startDate.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        Long endDateLong = endDate.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        List<Record> recordList = newsRepository.findRecordFromChannelBetweenDates(startDateLong, endDateLong, channelUrl);

        return  recordList;
    }

    public List<Record> getNewsTest(String channelUrl) {
        List<Record> recordList = newsRepository.findTest(channelUrl);
        return  recordList;
        //return recordList.stream().map(Record::getNews).collect(Collectors.toList());
    }
}
