package com.atguigu.stduy.aiagent.app;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

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

    @Test
    void doChatWithTools() {
        // 测试联网搜索问题的答案
        //testMessage("周末想带女朋友去上海约会，推荐几个适合情侣的小众打卡地？");

        // 测试网页抓取：恋爱案例分析
        //testMessage("最近和对象吵架了，看看恋爱网站网站（www.baidu.com）的其他情侣是怎么解决矛盾的？");

        // 测试资源下载：图片下载
        //testMessage("直接下载一张适合做手机壁纸的星空情侣图片为文件");

        // 测试终端操作：执行代码
        //testMessage("执行 Python3 脚本来生成数据分析报告");

        // 测试文件操作：保存用户档案
        //testMessage("保存我的恋爱档案为文件");

        // 测试 PDF 生成
        testMessage("生成一份‘七夕约会计划’PDF，包含餐厅预订、活动流程和礼物清单");
    }

    private void testMessage(String message) {
        String chatId = UUID.randomUUID().toString();
        String answer = loveApp.doChatWithTools(message, chatId);

    }

    @Test
    void doChatWithMcp() {
        String chatId = UUID.randomUUID().toString();
        // 测试图片搜索 MCP
        String message = "帮我搜一些有关猫的图片";
        String answer =  loveApp.doChatWithMcp(message, chatId);
        System.out.println(answer);
    }
}