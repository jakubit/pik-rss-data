package pl.pik.rss.data.dataservice.news.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.pik.rss.data.dataservice.news.service.NewsService;


@RestController
public class NewsRestController {

    private final NewsService newsService;

    @Autowired
    public NewsRestController(NewsService newsService) {
        this.newsService = newsService;
    }

    @PostMapping("/news")
    public ResponseEntity<?> getNewsBetweenDates(@RequestParam(name = "rssUrl") String rssUrl,
                                                 @RequestParam(name = "startDate", required = false) String startDateString,
                                                 @RequestParam(name = "endDate") String endDateString) {

        return new ResponseEntity<>(newsService.getNews(rssUrl, startDateString, endDateString), HttpStatus.OK);
    }
}
