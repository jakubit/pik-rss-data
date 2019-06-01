package pl.pik.rss.data.dataservice.news.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Document(collection = "testcollection")
public class Channel {
    private String link;
    private String description;
    private String language;
    private String title;
}
