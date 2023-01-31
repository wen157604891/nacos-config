package com.wen.service1.fileUtil;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Properties;

/**
 * @Author wsw
 * @Date 2023/1/31 11:02
 **/
@Component
public class ConfigFileUtils {
    private static String path;

    public ConfigFileUtils() {
    }

    @Value("${file.path}")
    public void setPath(String path) {
        ConfigFileUtils.path = path;
    }

    public static String getFilePath(String filePath) {
        return path + File.separator + filePath;
    }

    public static InputStream getInputStream(String fileName) {
        try {
            InputStream inputStream = (new FileSystemResource(getFilePath(fileName))).getInputStream();
            return inputStream;
        } catch (IOException var3) {
            var3.printStackTrace();
        }
        return null;
    }

    public static InputStreamReader getInputStreamReader(String fileName) {
        try {
            return new InputStreamReader(getInputStream(fileName), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Properties getProperties(String fileName) {
        Properties props = new Properties();
        try {
            props.load(getInputStreamReader(fileName));
            return props;
        } catch (IOException var3) {
            var3.printStackTrace();
            //throw new DefaultException("读取属性文件失败");
        }
        return null;
    }

    public static String getPropertiesValue(String fileName, String key) {
        Properties props = getProperties(fileName);
        return props.getProperty(key);
    }

    public static Document getDocument(String fileName) {
        try {
            Document document = (new SAXReader()).read(getInputStream(fileName));
            return document;
        } catch (DocumentException var3) {
            var3.printStackTrace();
        }
        return null;
    }
}
