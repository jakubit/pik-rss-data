package pl.pik.rss.data.dataservice.news.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.pik.rss.data.dataservice.news.model.Record;
import pl.pik.rss.data.dataservice.news.repository.NewsRepository;


import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class NewsService {

    private final NewsRepository newsRepository;

    @Autowired
    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public List<Record> getNewsFromChannelBetweenDates(String channelUrl, String startDateString, String endDateString) {
        Long startDateLong;
        Long endDateLong;

        if (endDateString.isEmpty()) {
            endDateLong = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        } else {
            LocalDateTime endDate = LocalDateTime.parse(endDateString, DateTimeFormatter.ofPattern("yyyy/MM/dd/HH:mm:ss"));
            endDateLong = endDate.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        }

        LocalDateTime startDate = LocalDateTime.parse(startDateString, DateTimeFormatter.ofPattern("yyyy/MM/dd/HH:mm:ss"));
        startDateLong = startDate.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        List<Record> recordList = newsRepository.findRecordsFromChannelBetweenDates(startDateLong, endDateLong, channelUrl);

        return  recordList;
    }

    public List<Record> getNewestNewsFromChannel(String rssUrl, int quantity) {
        PageRequest request =  PageRequest.of(0, quantity, new Sort(Sort.Direction.DESC, "rssItem.publishedDate"));
        return newsRepository.findNewestRecordsFromChannel(rssUrl, request).getContent();

    }
}
