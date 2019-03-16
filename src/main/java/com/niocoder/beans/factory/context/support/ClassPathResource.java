package com.niocoder.beans.factory.context.support;

import com.niocoder.beans.factory.context.Resource;
import com.niocoder.util.ClassUtils;

import java.io.*;

public class ClassPathResource implements Resource {
    private String path;

    public ClassPathResource(String path) {
        this.path = path;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        InputStream is = ClassUtils.getDefaultClassLoader().getResourceAsStream(path);
        if (is==null) {
            throw new FileNotFoundException(path+" cannot be opened");
        }
        return is;
    }

    @Override
    public String getDescription() {
        return this.path;
    }
}
