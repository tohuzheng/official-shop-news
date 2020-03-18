package com.huzheng.controller.shop;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huzheng.entity.Product;
import com.huzheng.service.IProductService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

/**
 * 产品(Product)表控制层
 *
 * @author zheng.hu
 * @since 2020-03-11 21:24:11
 */
@RestController
@RequestMapping("product")
public class ProductController {
    /**
     * 服务对象
     */
    @Autowired
    private IProductService productService;

    @PostMapping("/deleteProduct")
    public void deleteProduct(Integer id){
        productService._deleteById(id);
    }

    /**
     * @author zheng.hu
     * @date 2020/3/17 23:10
     * @description 根据实体类条件分页查询
     */
    @PostMapping("/queryPage")
    public IPage queryPage(Page page, Product product) {

        return productService.queryPageByEntity(page,product);
    }

    @PostMapping("/addProduct")
    public void addProduct(Product product){
        productService._insert(product);
    }

    @PostMapping("/addProductImg")
    public String addProductImg(MultipartFile file, HttpServletRequest request){
        String basePath = "E:/img/";
        String fileName = UUID.randomUUID().toString()+".png";
        String savePath = basePath+fileName;
        //文件上传
        File saveFile = new File(basePath);
        //判断路径是否存在，不存在则创建
        if(!saveFile.exists()){
            saveFile.mkdirs();
        }
        //判断上传文件是否为空
        if(!file.isEmpty()){
            try {
                InputStream in = file.getInputStream();
                OutputStream out = new FileOutputStream(savePath);
                byte[] b = new byte[1024*3];
                int count = -1;
                while((count=in.read(b)) != -1) {
                    out.write(b,0,count);
                }
                if(in!=null){
                    in.close();
                }
                if(out != null) {
                    out.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        System.out.println(savePath);

        return "/img/"+fileName;
    }
}