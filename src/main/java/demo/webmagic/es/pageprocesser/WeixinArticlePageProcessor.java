package demo.webmagic.es.pageprocesser;

import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

public class WeixinArticlePageProcessor implements PageProcessor {

    @Autowired
    ElasticsearchOperations elasticsearchOperations;
//    private String ARTICLE_URL = "/s?timestamp=\\d+&src=\\d+&ver=\\d+&signature=\\w+";
//    private String ARTICEL_LIST = "https://mp.weixin.qq.com/profile?src=\\d+&timestamp=\\d+&ver=\\d+&signature=\\w+";

    @Override
    public void process(Page page) {
        //处理初始url
        //todo 爬取频繁导致异常请求,验证码
        if (page.getUrl().toString().contains("http://weixin.sogou.com/weixin?type=2&s_from=input&query=")) {
            //处理当前页
            forPageList(page);
        } else {
            forPage(page);
        }
    }
    //处理列表页
    private void forPageList(Page page){
        List<String> urls = page.getHtml().css("div.wrapper>div.main-left>div.news-box>ul.news-list>li>div.txt-box>h3").$("a", "href").all();
        System.out.println("urls>>" + urls);
        for (String url : urls) {
            page.addTargetRequest(url);
        }
        //处理分页
        String nextpage = page.getHtml().css("div.wrapper>div.main-left>div.news-box>div.p-fy").$("a.np", "href").toString();
        Request request=new Request("https://weixin.sogou.com/weixin"+nextpage);
        page.addTargetRequest(request);
        List<String> nextUrls = page.getHtml().css("div.wrapper>div.main-left>div.news-box>ul.news-list>li>div.txt-box>h3").$("a", "href").all();
        for (String url : nextUrls) {
            page.addTargetRequest(url);
        }
    }

    //处理详情页
    private void forPage(Page page){
        //目标页面处理
        System.out.println(page.getHtml().toString());
        //获取到js，解析js后续页面
        String url = page.getHtml().css("script").toString();

        String[] strings = StrUtil.subBetweenAll(url, "url += '", "';");
        url = "";
        for (String tmp : strings) {
            url = url + tmp;
        }
        System.out.println(">>" + url);
        page.addTargetRequest(url);
        page.putField("title", page.getHtml().xpath("//div[@id='img-content']/h1[@class='rich_media_title']/text()").get());
        page.putField("date", page.getHtml().xpath("//div[@id='js_content']").get());
    }

    @Override
    public Site getSite() {
        return Site.me().
                setDomain("http://weixin.sogou.com")
                .setRetryTimes(3)
                .setSleepTime(3000)
                .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
    }
}
