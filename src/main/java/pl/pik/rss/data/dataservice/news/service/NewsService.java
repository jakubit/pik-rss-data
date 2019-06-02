package pl.pik.rss.data.dataservice.news.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.pik.rss.data.dataservice.news.exception.InputException;
import pl.pik.rss.data.dataservice.news.model.Record;
import pl.pik.rss.data.dataservice.news.repository.NewsRepository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
public class NewsService {

    private final NewsRepository newsRepository;

    @Autowired
    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public List<Record> getNewsFromChannelBetweenDates(String channelUrl, String startDateString, String endDateString) throws InputException {
        Long startDateLong;
        Long endDateLong;

        if (endDateString == null || endDateString.isEmpty())
            endDateLong = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        else
            endDateLong = getLongValueOfDate(endDateString);

        startDateLong = getLongValueOfDate(startDateString);
        return newsRepository.findRecordsFromChannelBetweenDates(startDateLong, endDateLong, channelUrl);
    }

    public List<Record> getNewestNews(String rssUrl, int quantity) throws InputException {
        try {
            PageRequest request = PageRequest.of(0, quantity, new Sort(Sort.Direction.DESC, "rssItem.publishedDate"));

            if (rssUrl == null || rssUrl.isEmpty())
                return newsRepository.findNewestRecordsFromAllChannels(request).getContent();
            else
                return newsRepository.findNewestRecordsFromOneChannel(rssUrl, request).getContent();
        }
        catch (IllegalArgumentException e){
            throw new InputException(e.getMessage());
        }

    }

    private Long getLongValueOfDate(String date) throws InputException {
        Long startDateLong;
        try {
            LocalDateTime startDate = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy/MM/dd/HH:mm:ss"));
            startDateLong = startDate.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        } catch (DateTimeParseException e) {
            throw new InputException(e.getMessage());
        }
        return startDateLong;
    }
}
