package com.huzheng.commoms.utils;

import cn.hutool.core.io.IoUtil;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

/**
 * @Author 胡正
 * @Date 2020/4/6 16:39
 * @Description
 */
public class ExcelSaveUtils {

    /**
     * @author zheng.hu
     * @date 2020/4/6 16:41
     * @description excel导入保存到硬盘，E://img/temp/目录中
     * @param file
     * @return 保存地址
     */
    public static String saveExcel(MultipartFile file) throws Exception {
        if (file == null) {
            return null;
        }
        String baseUrl = "E://img/temp/";
        String fileName = System.currentTimeMillis()+".xlsx";
        // 查看Base目录是否存在，不存在就创建
        if (!new File(baseUrl).exists()) {
            new File(baseUrl).mkdirs();
        }
        // 文件流不为空则保存
        if (!file.isEmpty()) {
            OutputStream out = new FileOutputStream(baseUrl+fileName);
            InputStream in = file.getInputStream();
            IoUtil.copy(in, out);
        }

        return baseUrl+fileName;
    }
}
