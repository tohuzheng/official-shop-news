package com.huzheng.controller.shop;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huzheng.dto.ProductDetailDto;
import com.huzheng.entity.Customer;
import com.huzheng.entity.Product;
import com.huzheng.service.IProductService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
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

    @PostMapping("/updateProduct")
    public void updateProduct(Product product) {
        if (product.getId() == null) {
            throw new RuntimeException("产品id为空，无法更新");
        }
        productService._updateById(product);
    }

    /**
     * @author zheng.hu
     * @date 2020/4/1 22:07
     * @description 图片上传，返回存贮路径
     * @param file
     */
    @PostMapping("/addProductImg")
    public String addProductImg(MultipartFile file){
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

    /**
     * @author zheng.hu
     * @date 2020/3/24 16:32
     * @description 根据路径删除图片
     */
    @PostMapping("/deleteImg")
    public void deleteImg(String url) {
        String baseImgUrl = "E:";
        String imgUrl = baseImgUrl+url;
        File file = new File(imgUrl);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * @author zheng.hu
     * @date 2020/3/31 23:57
     * @description 前台控制方法开始，获取最新产品，6个
     * @param
     */
    @GetMapping("/queryNewProduct")
    public List<Product> queryNewProduct(){
        return productService.queryNewProduct();
    }

    /**
     * @author zheng.hu
     * @date 2020/3/31 23:59
     * @description 查询推荐产品6个，根据销量最高的6个
     * @param
     */
    @GetMapping("/queryRecommendProduct")
    public List<Product> queryRecommendProduct(){
        List<Product> productList = productService.queryRecommendProduct();
        return productList;
    }

    /**
     * @author zheng.hu
     * @date 2020/4/1 22:10
     * @description 根据id查询产品
     * @param id
     */
    @GetMapping("/queryOne")
    public Product queryOneById(Integer id){
        if (id == null) {
            return null;
        }else {
            return this.productService._selectById(id);
        }
    }

    /**
     * @author zheng.hu
     * @date 2020/4/7 23:30
     * @description 查询产品详细
     * @param
     */
    @PostMapping("/queryProductDetail")
    public ProductDetailDto queryProductDetail(Integer id) {
        ProductDetailDto productDetailDto = productService.queryProductDetail(id);
        return productDetailDto;
    }

}