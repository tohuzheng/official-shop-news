package com.huzheng.commoms.utils;

import cn.hutool.core.io.IoUtil;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
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
    public static String saveExcel(MultipartFile file){
        if (file == null) {
            return null;
        }
        String baseUrl = "E://img/temp/";
        String fileName = UUID.randomUUID().toString()+".xlsx";
        // 查看Base目录是否存在，不存在就创建
        File file1 = new File(baseUrl);
        if (!file1.exists()) {
            file1.mkdirs();
        }
        // 文件流不为空则保存
        if (!file.isEmpty()) {
            try {
                OutputStream out = new FileOutputStream(baseUrl+fileName);
                InputStream in = file.getInputStream();
                IoUtil.copy(in, out);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return baseUrl+fileName;
    }
}
