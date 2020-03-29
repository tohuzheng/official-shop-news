package com.huzheng.dto;

/**
 * @Author 胡正
 * @Date 2020/3/26 15:52
 * @Description 创建支付宝交易单dto
 */
public class BizContentVO {
    /**
     * 商户订单号,自己随机生成，一般使用时间戳即可
     */
    private String out_trade_no;
    /**
     * 销售产品码，与支付宝签约的产品码名称。
     * 目前仅支持：FAST_INSTANT_TRADE_PAY
     */
    private String product_code = "FAST_INSTANT_TRADE_PAY";
    /**
     * 订单金额
     */
    private String total_amount;
    /**
     * 订单内容
     */
    private String body;
    /**
     * 订单名称
     */
    private String subject;

    /**
     * 验证参数
     */
    private String passback_params;


    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPassback_params() {
        return passback_params;
    }

    public void setPassback_params(String passback_params) {
        this.passback_params = passback_params;
    }
}
