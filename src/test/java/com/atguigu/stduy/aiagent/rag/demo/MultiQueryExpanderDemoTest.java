package com.atguigu.stduy.aiagent.rag.demo;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.ai.rag.Query;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MultiQueryExpanderDemoTest {

    @Resource
    private MultiQueryExpanderDemo multiQueryExpanderDemo;
    @Test
    void expand() {
        List<Query> expand = multiQueryExpanderDemo.expand("我想去海南旅游，有什么攻略吗？");
        for(Query query: expand)
        {
            System.out.println(query.text());
        }
    }
}