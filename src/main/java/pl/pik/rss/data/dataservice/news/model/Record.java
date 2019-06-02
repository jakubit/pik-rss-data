package pl.pik.rss.data.dataservice.news.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Document(collection = "news")
public class Record {
    @Id
    private String id;
    private Channel rssChannelInfo;
    private News rssItem;
}
