package com.huzheng.controller.alipay;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.nosql.redis.RedisDS;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.*;
import com.alipay.api.response.AlipayTradeCloseResponse;
import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.huzheng.commoms.exceptions.CorrectException;
import com.huzheng.commoms.utils.RsaUtils;
import com.huzheng.dto.*;
import com.huzheng.entity.Customer;
import com.huzheng.entity.PayList;
import com.huzheng.service.IBuyOrderService;
import com.huzheng.service.IPayListService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author 胡正
 * @Date 2020/3/26 17:01
 * @Description 支付宝支付，退款等操作控制层
 */
@Controller
@RequestMapping("/alipay")
public class AliPayController {

    @Autowired
    IPayListService payListService;
    @Autowired
    IBuyOrderService buyOrderService;

    // 商户私钥，您的PKCS8格式RSA2私钥
    private static final String APP_PRIVATE_KEY="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDZCXgTclEQxe6T0rNzMg/uYCtQ/KnWliflTGOfnWPUFfz+8g3DgTQduH59EQFFHfb8hbDhI0Ci6uhtfDYzlSwQHWEmlvmmeTsKCCHlWRqZM8sIikEz54sH/jKTRDe5rFi+FL1lwEKwjbarlnmUqmH8GzNdGMGohhfTWoSgqKfcF2Q1BEJsoaP9xmii88mHbhP1VJ19FxpWxaCSofK2caiBS0//KHpTe1czgpTrcU832pFUqygm/jLwBa10O/mtewZKlsAUko4tKi5pXnqSVFQ0Oi427RXOHL6cJzQFxCtoPbmXbhRnmGayJgTDE1VSlEW3Bq27tJ335stcSe/6xljjAgMBAAECggEAbhA6P4pih+IIehiI/mst3xIQBXMMYhElzEev4dr8LCq2cUepKTMVjdjo9F2ySi3G+gqGR7YL6lK8agFqrz//53Auu1Ym5oBmK1Eyd093iKfWV89c/4h1XilP1pTquZMoMcTsw3P7nfNP5rZZk8XQCPx2UMjkNKVD4d/ukFbXQWn/7hfdp3xMo3aTXiDTCg6RsjwmHcShS+uofY0Gctd4PrTMSZlKUl+B1NJGBb3OUe3RyYNHOg28qWSzMOml1SGMAwDE7Z8P8vGH7MYGBYY185eUVqiARZzDKPW71jkVaUeE0MDSTLkZHKbgvvK24B2IblXx3WA/sGV0kyiadeq0CQKBgQD48QNZBtXZi5rZKbsald0ca5Xaibs6p+D8z1fTYpzOJEBATvZwA7ujUYwmE/UCPbrt69CO2vmRlSF6QxPIJnLrir98LUBtp1M/53zo/NVTRMzC2scD3aP7k6Rz5DHdQuCw84SY5qMCQCsUv8FcOh9Ywn7O3nPhAZuLNRP7Npo0pwKBgQDfMN8fLtxP66vXyADePzhvCiSeoA5OjUtQVztVSTuvBNTPoR3l9Q7scPSGHodyMOGUZPiiPLx1PZRrGfMvbxydoTtgySorKv1uTv9RdF9jVNRlyf+cZRkwLtceuI2d+1xqs5j7GlUF34VOY1i04av8+F0iYkU0iMCeAZDxkM81ZQKBgQDsIRCf3Y/zLizPWHoVmZmGX7Zg+MwTszICG1qfM6axEkmvtjajwaa2H5/kboG0goFRNKE7IBIiOWbr5bxZ2b6gFZfUXcgDOvgIYbdl+I0Q0u221nZ4AECPXVhcZVO3ILyonBRmRD7S37L9l3X92VPqiXJnHzKjdzbnMI2nd/+FeQKBgCQ6fJ1FMAHREW3PS0TIIlLz5InTFT9u9ACwiDMJ1iRMtnaVgpLnOmrq0uZKYlYI1TUPRsSe80BcpJDV/4F9mbmEiGznem7TZZvYOt1v9GPdSzTSmyk5x+LoV4bnEiXIvXGcgYKQ/agcZjkZKzcBQrenYQbJyM4EPK/pNSvhA6F5AoGAXpBTrC7BFbErOlJs77WKYhJ0JoOnR/aPVcR7vr8nIRd3XN4m0kZDWVd50FSgF7yI8i0JWXvI2o+GXoPyxekd+VETdC13RAWQXERLty8NPRicOJTfZobncYhynUvc7MPcEmYcZT+QKXJbo4RfsgkVMe44+ed4VZPo8+VVN5gOOzI=";
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    private static final String ALIPAY_PUBLIC_KEY="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhYrGZusrU/1Qn2uECm+kAxOIMM1+WqDGg86WUEokDaRjwNJpN8htAmqKLglND0OzQrEZUgtE/AW104UKXFxNC2lshwKssTn0W6GC4XqhC+7MkNiVQzMW7yI7DWZ0okJHwmgXD5tbZ0JopYKx+1854AJL0rtPExGMuFUjLIdatc7a77/MJtOnmCoPqEW3aA2DHr2MuhAl/cvk47LudYxDY+4B657Av22oBUDE4ectyt90LpfPQ0BPieXF/NS4y5FHaO2iS5vYOrQNQZFe6WFx+5DS6TAPI7VjA4zj9jfxzK+xvAvxnfRtDNEXntYbwdB1fUTaJlTWnrLlo6h6J3IipQIDAQAB";
    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    private static final String APP_ID = "2016101900723652";
    // 签名方式
    private static final String SING_TYPE = "RSA2";
    // 字符编码格式
    private static final String CHARSET = "UTF-8";
    // 支付宝网关地址
    private static final String GATEWAY_URL = "https://openapi.alipaydev.com/gateway.do";
    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    private static final String NOTIFY_URL = "http://127.0.0.1/fuwuchuang_demo/return_url.jsp";
    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    private static final String RETURN_URL = "http://8233bede.ngrok.io/alipay/notify";

    @RequestMapping("/createPay")
    public void createPayOrder(HttpServletResponse httpResponse,HttpServletRequest request,
                               @RequestBody SubmitOrderDto submitOrderDto) throws IOException {
        AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY_URL, APP_ID, APP_PRIVATE_KEY,
                "json", CHARSET, ALIPAY_PUBLIC_KEY, SING_TYPE);
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();// 创建API对应的request
        alipayRequest.setReturnUrl(RETURN_URL);
        // 在公共参数中设置回跳和通知地址
        alipayRequest.setNotifyUrl(NOTIFY_URL);

        // 解密订单支付金额
        String payAmount=deRsaCode(submitOrderDto.getAmount(), submitOrderDto.getKeyNo());
        // 获取用户信息
        HttpSession session = request.getSession();
        Customer customer = (Customer)session.getAttribute("userInfo");
        // 商户订单编号
        String orderCode = "S"+System.currentTimeMillis();
        // 持久化订单
        buyOrderService.submitOrder(submitOrderDto, orderCode, payAmount, customer.getId());
        // 请求支付参数dto
        BizContentVO bizContentVO=new BizContentVO();
        submitOrderDto.setAmount("");
        submitOrderDto.setKeyNo("");
        bizContentVO.setBody(JSON.toJSONString(submitOrderDto));
        bizContentVO.setOut_trade_no(orderCode);
        bizContentVO.setSubject("企业产品在线展示销售平台");

        if (StrUtil.isNotEmpty(payAmount) && StringUtils.isNumeric(payAmount)) {
            bizContentVO.setTotal_amount(payAmount);
        }

        alipayRequest.setBizContent(JSON.toJSONString(bizContentVO));
        // 生成支付页面
        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); // 调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        httpResponse.setContentType("text/html;charset=" + "UTF-8");
        // 直接将完整的表单html输出到页面
        httpResponse.getWriter().write(form);
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }

    /**
     * @author zheng.hu
     * @date 2020/3/26 19:05
     * @description 支付成功异步回调
     */
    @RequestMapping("/notify")
    public String alipayNotify(HttpServletRequest request){
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String,String> responseResult = new HashMap<>();
        Set<String> keys = parameterMap.keySet();
        for (String key:keys) {
            responseResult.put(key,parameterMap.get(key)[0]);
        }
        // 修改订单状态
        buyOrderService.changeOrderStatusByCode(responseResult.get("out_trade_no"));
        // 保存支付记录
        PayList payList = new PayList();
        payList.setOrderCode(responseResult.get("out_trade_no"));
        payList.setOutTradeNo(responseResult.get("out_trade_no"));
        payList.setPayDate(DateUtil.parse(responseResult.get("timestamp"), "yyyy-MM-dd HH:mm:ss"));
        payList.setTradeNo(responseResult.get("trade_no"));
        payList.setTotalAmount(new BigDecimal(responseResult.get("total_amount")));
        payListService._insert(payList);
        // 返回主页
        return "redirect:http://localhost:8083/#/index";
    }

    /**
     * @author zheng.hu
     * @date 2020/3/26 19:44
     * @description 交易查询
     */
    @RequestMapping("/tradeQuery")
    @ResponseBody
    public Object tradeQuery (TradeQueryDto queryDto) {
        AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY_URL, APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, SING_TYPE);
        AlipayTradeQueryRequest alipayRequest = new AlipayTradeQueryRequest();

        //TradeQueryDto tradeQueryDto =new TradeQueryDto();
        //tradeQueryDto.setTrade_no("2020032622001487310500645184");

        alipayRequest.setBizContent(JSON.toJSONString(queryDto));
        AlipayTradeQueryResponse response = null;

        try {
            response = alipayClient.execute(alipayRequest);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        if(response.isSuccess()){
            System.out.println("调用成功");
            JSONObject jsStr = JSONObject.parseObject(response.getBody());
            return jsStr;
        } else {
            return null;
        }
    }

    /**
     * @author zheng.hu
     * @date 2020/3/26 19:44
     * @description 交易关闭
     *  商户订单号 ：
     * 支付宝交易号
     */
    @RequestMapping("/tradeClose")
    @ResponseBody
    public AlipayTradeCloseResponse tradeClose(TradeCloseDto closeDto){
        AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY_URL, APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, SING_TYPE);
        AlipayTradeCloseRequest request = new AlipayTradeCloseRequest();

        request.setBizContent(JSON.toJSONString(closeDto));

        AlipayTradeCloseResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if(response.isSuccess()){
            System.out.println("调用成功");
            return response;
        } else {
            System.out.println("调用失败");
            return null;
        }
    }

    /**
     * @author zheng.hu
     * @date 2020/3/26 19:44
     * @description 退款
     * 商户订单号 ：
     * 支付宝交易号:
     * 退款金额 ：
     * 退款原因 ：
     * 退款请求号 ：
     */
    @RequestMapping("/returnAmount")
    @ResponseBody
    public AlipayTradeRefundResponse returnAmount(RefundDto refundDto){
        AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY_URL, APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, SING_TYPE);
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        request.setBizContent(JSON.toJSONString(refundDto));
        AlipayTradeRefundResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if(response.isSuccess()){
            System.out.println("调用成功");
            return response;
        } else {
            System.out.println("调用失败");
            return null;
        }

    }

    /**
     * @author zheng.hu
     * @date 2020/3/26 19:44
     * @description 退款查询
     * 商户订单号 ：
     * 支付宝交易号 ：
     * 退款请求号
     */
    @RequestMapping("/returnAmountQuery")
    @ResponseBody
    public AlipayTradeFastpayRefundQueryResponse returnAmountQuery(RefundQueryDto queryDto){
        AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY_URL, APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, SING_TYPE);
        AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();
        request.setBizContent(JSON.toJSONString(queryDto));
        AlipayTradeFastpayRefundQueryResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if(response.isSuccess()){
            System.out.println("调用成功");

            return response;
        } else {
            System.out.println("调用失败");
        }
        return null;
    }

    /**
     * @author zheng.hu
     * @date 2020/4/16 16:43
     * @description 解密支付金额数据
     * @param
     */
    private String deRsaCode(String orderStr,String keyNo){
        if (StrUtil.isEmpty(orderStr) || StrUtil.isEmpty(keyNo)) {
            throw new CorrectException("参数错误，解密数据不可为空");
        }
        SubmitOrderDto dto=null;
        // 从Redis中获取私钥
        Jedis jedis = RedisDS.create().getJedis();
        String privateKey = jedis.get(keyNo);
        jedis.close();
        try {
            // 解密
            String amount = RsaUtils.decodeRsa(orderStr, privateKey);
            return amount;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
