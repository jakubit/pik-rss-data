package pl.pik.rss.data.dataservice.news.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.pik.rss.data.dataservice.news.exception.InputException;
import pl.pik.rss.data.dataservice.news.service.NewsService;


@RestController
public class NewsRestController {

    private final NewsService newsService;

    @Autowired
    public NewsRestController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/news/range")
    public ResponseEntity<?> getNewsFromChannelBetweenDates(@RequestParam(name = "rssUrl") String rssUrl,
                                                            @RequestParam(name = "startDate") String startDateString,
                                                            @RequestParam(name = "endDate", required = false) String endDateString) {

        try {
            return new ResponseEntity<>(newsService.getNewsFromChannelBetweenDates(rssUrl, startDateString, endDateString), HttpStatus.OK);
        } catch (InputException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping("/news/newest")
    public ResponseEntity<?> getNewsNewestNews(@RequestParam(name = "rssUrl", required = false) String rssUrl,
                                                           @RequestParam(name = "quantity") int quantity) {
        try {
            return new ResponseEntity<>(newsService.getNewestNews(rssUrl, quantity), HttpStatus.OK);
        } catch (InputException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
