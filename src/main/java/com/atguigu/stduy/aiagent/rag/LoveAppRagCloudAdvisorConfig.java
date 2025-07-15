package com.atguigu.stduy.aiagent.rag;

import com.alibaba.cloud.ai.dashscope.api.DashScopeApi;
import com.alibaba.cloud.ai.dashscope.rag.DashScopeDocumentRetriever;
import com.alibaba.cloud.ai.dashscope.rag.DashScopeDocumentRetrieverOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.model.ApiKey;
import org.springframework.ai.rag.advisor.RetrievalAugmentationAdvisor;
import org.springframework.ai.rag.retrieval.search.DocumentRetriever;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * 自定义基于阿里云知识库服务的 RAG 增强顾问
 */
//@Configuration
@Slf4j
public class LoveAppRagCloudAdvisorConfig {

    @Value("${spring.ai.dashscope.api-key}")
    private String dashScopeApiKey;

    @Autowired
    private RestClient.Builder restClientBuilder;

    @Autowired
    private WebClient.Builder webClientBuilder;




//    @Bean
    public Advisor loveAppRagCloudAdvisor() {
        ApiKey apiKey = new ApiKey() {
            @Override
            public String getValue() {
                return dashScopeApiKey;
            }
        };
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        DashScopeApi dashScopeApi = new DashScopeApi(null,apiKey,headers,null,restClientBuilder,webClientBuilder,null);
        //DashScopeApi dashScopeApi = new DashScopeApi(apiKey);
        final String KNOWLEDGE_INDEX = "恋爱大师";
        DocumentRetriever dashScopeDocumentRetriever = new DashScopeDocumentRetriever(dashScopeApi,
                DashScopeDocumentRetrieverOptions.builder()
                        .withIndexName(KNOWLEDGE_INDEX)
                        .build());

        return RetrievalAugmentationAdvisor.builder()
                .documentRetriever(dashScopeDocumentRetriever)
                .build();
    }
}