package com.atguigu.stduy.imagessearchmcp.tools;

import jakarta.annotation.Resource;
import jakarta.validation.constraints.Size;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ImageSearchToolTest {

    @Resource
    private ImageSearchTool imageSearchTool;

    @Test
    void searchImage() {
    }

    @Test
    void searchMediumImages() {
        String s = imageSearchTool.searchImage("cat");
        System.out.println(s);
        //log.info("图片信息 = {}", s);
    }
}