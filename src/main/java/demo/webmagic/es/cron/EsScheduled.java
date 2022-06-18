package demo.webmagic.es.cron;

import demo.webmagic.es.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Description:
 * <p>
 * es
 *
 * @Date: 2022/6/18 13:54
 * @Author: James Lin
 * @Version: 1.0
 */
@Component
public class EsScheduled {
    @Autowired
    SearchService searchService;

//    @Scheduled(fixedDelay = 50000)
//    public void webMagicScheduled(){
//        searchService.searchWeixinArticle("Andlao");
//    }
}
