package demo.webmagic.es.pipeline;

import cn.hutool.core.util.IdUtil;
import demo.webmagic.es.model.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import javax.annotation.Resource;
import java.util.Map;

@Component
public class WeixinArticlePipeline implements Pipeline {
    @Resource
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public void process(ResultItems resultItems, Task task) {
        //插入数据
        for (Map.Entry<String, Object> entry : resultItems.getAll().entrySet()) {
            System.out.println("key:" + entry.getKey().substring(1, 20) + ";value:" + entry.getValue().toString().substring(1, 20));
            Page page = new Page();
            page.setId(IdUtil.randomUUID());
            page.setTitle(entry.getKey());
            page.setData(entry.getValue().toString());

//            elasticsearchRestTemplate.save(page);
        }
    }
}
