package com.atguigu.stduy.aiagent.rag;


import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.rag.advisor.RetrievalAugmentationAdvisor;
import org.springframework.ai.rag.retrieval.search.DocumentRetriever;
import org.springframework.ai.rag.retrieval.search.VectorStoreDocumentRetriever;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.filter.Filter;
import org.springframework.ai.vectorstore.filter.FilterExpressionBuilder;

public class LoveAppRagCustomAdvisorFactory {
    /**
     * 创建自定义的 RAG 检索增强顾问
     *
     * @param vectorStore 向量存储
     * @param status      状态
     * @return 自定义的 RAG 检索增强顾问
     */

    public static Advisor createLoveAppRagCustomAdvisor(VectorStore vectorStore, String status){

        //过滤特定文档
        Filter.Expression expression = new FilterExpressionBuilder().eq("status",status).build();

        //创建文档检索器
        VectorStoreDocumentRetriever retriever = VectorStoreDocumentRetriever.builder()
                .vectorStore(vectorStore)
                .similarityThreshold(0.5)
                .filterExpression(expression)
                .topK(3)
                .build();

        return RetrievalAugmentationAdvisor.builder()
                .documentRetriever(retriever)
                .queryAugmenter(LoveAppContextualQueryAugmenterFactory.createInstance())
                .build();

    }

}
