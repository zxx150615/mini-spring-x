package com.zxx.minispringx.core.io;

import java.net.URL;

public class DefaultResourceLoader implements ResourceLoader {

    public static final String CLASSPATH_URL_PREFIX = "classpath:";

    @Override
    public Resource getResource(String location) {

        // 先加载类目录下的文件
        if (location.startsWith(CLASSPATH_URL_PREFIX)) {
            // classpath下的资源
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        } else {
            // 当做url来处理
            try {
                URL url = new URL(location);
                return new UrlResource(new URL(location));
            } catch (Exception e) {
                // 最后当做文件路径来处理
                return new FileSystemResource(location);
            }
        }
    }
}
