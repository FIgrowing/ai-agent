package com.atguigu.stduy.aiagent.config;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ChatClientConfigTest {

    @Resource
    private ChatClient chatClient;

   @Test
    void test() {
       String conversationId = "008";
       String result = chatClient.prompt()
               .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, conversationId))
               .user("你好，我是周天赐，你是谁？").call().content();
       System.out.printf(result);

       String result2 = chatClient.prompt()
               .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, conversationId))
               .user("你知道我是谁吗,只需要回答我的名字即可，其他任何信息都不要输出").call().content();
       System.out.printf(result2);
   }

   //测试流式返回
    @Test
    void testStream() {
        Flux<String> output = chatClient.prompt()
                .user("你是谁")
                .stream()
                .content();
        // 订阅流并实时打印每一段内容
           output
                .doOnNext(chunk -> System.out.print(chunk)) // 每收到一段内容就打印（注意用print而非println，避免换行）
                .doOnComplete(() -> System.out.println("\n--- 流式响应结束 ---")) // 流结束时的回调
                .blockLast(); // 阻塞等待流结束（测试环境可用，生产环境慎用）
    }
}