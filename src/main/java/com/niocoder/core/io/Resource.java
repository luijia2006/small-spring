package com.niocoder.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 抽取一个资源接口
 */
public interface Resource {
    /**
     * 获取一个流
     */
    InputStream getInputStream() throws IOException;

    /**
     * 获取文件的描述
     */
    String getDescription();
}
