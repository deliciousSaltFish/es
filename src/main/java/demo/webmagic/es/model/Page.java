package demo.webmagic.es.model;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Description:
 * <p>
 * es
 *
 * @Date: 2022/6/18 13:40
 * @Author: James Lin
 * @Version: 1.0
 */

@Document(indexName = "page")
@Data
public class Page {
    private String id;
    private String title;
    private String data;
}