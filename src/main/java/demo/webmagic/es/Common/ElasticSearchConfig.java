package demo.webmagic.es.Common;

import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

/**
 * Description:
 * <p>
 * es
 *
 * @Date: 2022/6/17 20:40
 * @Author: James Lin
 * @Version: 1.0
 */

public class ElasticSearchConfig extends AbstractElasticsearchConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(ElasticSearchConfig.class);

    @Value("${spring.elasticsearch.host}")
    private String host;//elasticsearch的地址

    @Value("${spring.elasticsearch.port}")
    private Integer port;//elasticsearch的端口

    @Value("${elasticsearch.cluster.name}")
    private String clusterName;//集群

    @Override
    public RestHighLevelClient elasticsearchClient() {
        ClientConfiguration configuration = ClientConfiguration.builder(
        ).connectedTo(host+"+"+port).build();
        RestHighLevelClient client = RestClients.create(configuration).rest();
        return client;
    }
}