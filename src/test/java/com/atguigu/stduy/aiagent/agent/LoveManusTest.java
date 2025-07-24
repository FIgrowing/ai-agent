package com.atguigu.stduy.aiagent.agent;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoveManusTest {

    @Resource
    private LoveManus loveManus;

    @Test
    public void run() {
        String userPrompt = """
                周末想带女朋友去北京约会，搜索推荐几个适合情侣的小众打卡地，
                并结合一些网络图片，制定一份详细的约会计划，
                并以 PDF 格式输出""";
        String answer = loveManus.run(userPrompt);
        System.out.println(answer);
        //Assertions.assertNotNull(answer);
    }

    @Test
    public void runStream() {
        String userPrompt = """
                周末想带女朋友去北京约会，搜索推荐几个适合情侣的小众打卡地，
                并结合一些网络图片，制定一份详细的约会计划，
                并生成一个PDF文件""";
        SseEmitter sseEmitter = loveManus.runStream(userPrompt);
        System.out.println(sseEmitter);
        //Assertions.assertNotNull(answer);
    }

}