package com.ypdaic.mymall.mymallsearch;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.AvgAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MymallSearchApplicationTests {

    @Autowired
    RestHighLevelClient client;

    @Autowired
    RequestOptions requestOptions;

    @Test
    public void contextLoads() {
        IndexRequest indexRequest = new IndexRequest("users");
        indexRequest.id("1");
        User user = new User();
        user.setUsername("dai");
        user.setAge(28);
        indexRequest.source(JSON.toJSONString(user), XContentType.JSON);

        try {
            client.index(indexRequest, requestOptions);
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @Test
    public void search() throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        // 创建检索索引
        searchRequest.indices("bank");

        /**
         * 指定DSL语句
         */
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        searchSourceBuilder.query(QueryBuilders.matchQuery("address", "mill"));

        // 按照年龄的值分布进行聚合
        TermsAggregationBuilder aggregationBuilder = AggregationBuilders.terms("ageAge").field("age").size(10);
        searchSourceBuilder.aggregation(aggregationBuilder);

        // 计算平均值
        AvgAggregationBuilder avgAggregationBuilder = AggregationBuilders.avg("balanceAvg").field("balance");

        searchSourceBuilder.aggregation(avgAggregationBuilder);

        searchRequest.source(searchSourceBuilder);

        SearchResponse response = client.search(searchRequest, requestOptions);
        System.out.println(response);
        SearchHits hits = response.getHits();
        SearchHit[] hits1 = hits.getHits();
        // 获取查询的数据
        for (SearchHit documentFields : hits1) {
            String sourceAsString = documentFields.getSourceAsString();
            Account account = JSONObject.toJavaObject(JSONObject.parseObject(sourceAsString), Account.class);
            System.out.println(account);
        }
        Aggregations aggregations = response.getAggregations();
        // 获取聚合信息
        Map<String, Aggregation> stringAggregationMap = aggregations.asMap();
        System.out.println(stringAggregationMap);
    }

    @Data
    class User {
        private String username;

        private Integer age;
    }

}
