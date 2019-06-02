package pl.pik.rss.data.dataservice.news.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@ToString
@Getter
@AllArgsConstructor
@Document(collection = "news")
public class News {
    private String title;
    private Long publishedDate;
    private String description;
    private String link;
    private String imgUrl;
}
