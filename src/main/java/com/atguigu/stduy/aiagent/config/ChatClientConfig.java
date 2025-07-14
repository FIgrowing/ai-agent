package com.atguigu.stduy.aiagent.config;

import com.atguigu.stduy.aiagent.chatmemory.FileBasedChatMemory;
import jakarta.annotation.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.ChatMemoryRepository;
import org.springframework.ai.chat.memory.InMemoryChatMemoryRepository;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestParam;

@Configuration
public class ChatClientConfig {

    @Resource
    private ChatModel dashscopeChatModel;

//    @Autowired
//    private ChatMemoryRepository chatMemoryRepository;; // 默认是基于内存的memory，https://springdoc.cn/spring-ai/api/chat-memory.html

    private static final String SYSTEM_PROMPT = "扮演深耕恋爱心理领域的专家。开场向用户表明身份，告知用户可倾诉恋爱难题。" +
            "围绕单身、恋爱、已婚三种状态提问：单身状态询问社交圈拓展及追求心仪对象的困扰；" +
            "恋爱状态询问沟通、习惯差异引发的矛盾；已婚状态询问家庭责任与亲属关系处理的问题。" +
            "引导用户详述事情经过、对方反应及自身想法，以便给出专属解决方案。";

    @Bean
    public ChatClient dashscopeChatClient()
    {
        // 基于内存的memory
        ChatMemoryRepository repository = new InMemoryChatMemoryRepository();
        //持久化保存至文件的Memory
        String fileDir = "src/main/resources/chat-memory";
        FileBasedChatMemory fileBasedChatMemory = new FileBasedChatMemory(fileDir);

        MessageWindowChatMemory chatMemory = MessageWindowChatMemory.builder()
                .chatMemoryRepository(repository)
                .maxMessages(10).build();

        return ChatClient.builder(dashscopeChatModel)
                .defaultSystem(SYSTEM_PROMPT)
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(fileBasedChatMemory).build(),new SimpleLoggerAdvisor())
                .build();
    }

}
