package demo.webmagic.es.service;

import demo.webmagic.es.Common.Response;
import demo.webmagic.es.pageprocesser.WeixinArticlePageProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Spider;

@Service
@Slf4j
public class SearchService {
    private String SOGOU_WEIXIN_SEARCH_PREFIX = "http://weixin.sogou.com/weixin?type=2&s_from=input&query=";
    private String SOGOU_WEIXIN_SEARCH_SUFFIX = "&ie=utf8&_sug_=n&_sug_type_=";

//    @Resource
//    private ElasticsearchTemplate elasticsearchTemplate;
    /**
     * 根据公众号名称抓取文章
     * @param officialAccountName
     * @return
     */
    public Response<Boolean> searchWeixinArticle(String officialAccountName) {
        try {
            System.out.println(">>"+SOGOU_WEIXIN_SEARCH_PREFIX + officialAccountName + SOGOU_WEIXIN_SEARCH_SUFFIX);
            Spider.create(new WeixinArticlePageProcessor())
                    .addUrl(SOGOU_WEIXIN_SEARCH_PREFIX + officialAccountName + SOGOU_WEIXIN_SEARCH_SUFFIX)
                    .thread(2)
                    .run();
        } catch (Exception e) {
            log.error("[snatchWeixinArticle][抓取公众号:{}  文章失败!]", officialAccountName, e);
            return Response.failed(false);
        }
        return Response.success(true);
    }

//    public String queryByTitle(String title) {
//        Query query = new NativeSearchQueryBuilder().withFilter(regexpQuery("title",".*"+title+".*")).build();
//        SearchHits<Page> results=elasticsearchTemplate.search(query, Page.class, IndexCoordinates.of("page"));
//        return JSONUtil.toJsonStr(results);
//    }
}
