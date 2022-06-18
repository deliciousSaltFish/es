package demo.webmagic.es.controller;


import demo.webmagic.es.Common.Response;
import demo.webmagic.es.service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping(produces = "application/json;charset=UTF-8")
public class SearchController {
    @Resource
    public SearchService searchService;

    //抓取指定微信公众号文章
    @GetMapping(value = "/search/weixin/officalAccount")
    public Response<Boolean> officalAccountSnatch(@RequestParam(value = "offical_account_name") String officalAccountName) {
        return searchService.searchWeixinArticle(officalAccountName);
    }
    //根据关键字查询文章
//    @GetMapping(value = "/search/weixin/queryById")
//    public String queryById(@RequestParam(value = "title") String title) {
//        return searchService.queryByTitle(title);
//    }
}
