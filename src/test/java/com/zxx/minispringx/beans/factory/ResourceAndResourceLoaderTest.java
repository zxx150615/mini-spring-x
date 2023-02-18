package com.zxx.minispringx.beans.factory;

import cn.hutool.core.io.IoUtil;
import com.zxx.minispringx.core.io.DefaultResourceLoader;
import com.zxx.minispringx.core.io.Resource;
import org.junit.Test;
import org.junit.jupiter.api.DynamicTest;

import java.io.InputStream;

public class ResourceAndResourceLoaderTest {

    @Test
    public void testResourceLoader() throws Exception {
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();

        //加载classPath下的资源
        Resource classResource = resourceLoader.getResource("classpath:hello.txt");
        InputStream inputStream = classResource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);

        // 加载文件系统下的资源
        Resource fileResource = resourceLoader.getResource("D:\\file\\hello.txt");
        InputStream inputStream1 = fileResource.getInputStream();
        String fileContent = IoUtil.readUtf8(inputStream1);
        System.out.println(fileContent);


        // 加载url的资源
        Resource urlResource = resourceLoader.getResource("https://www.baidu.com");
        InputStream inputStream2 = urlResource.getInputStream();
        String urlContent = IoUtil.readUtf8(inputStream2);
        System.out.println(urlContent);
    }
}
