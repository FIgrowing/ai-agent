package com.atguigu.stduy.aiagent.app;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoveAppTest {

    @Resource
    private LoveApp loveApp;

    @Test
    void doChatWithReport() {
        LoveApp.LoveReport loveReport = loveApp.doChatWithReport("你好我叫周天赐，我想让另一半更喜欢我，该怎么做","001");
    }

    @Test
    void doChat() {
        String chat = loveApp.doChat("你知道我是谁吗？只需要回答我的名字即可，除此之外不要输出其他任何内容","003");
    }

    @Test
    void doChatWithRag() {

        String chatId = "100";
        String message = "我已经结婚了，但是婚后关系不太亲密，怎么办？";
        String answer =  loveApp.doChatWithRag(message, chatId);
    }
}