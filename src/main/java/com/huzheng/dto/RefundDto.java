package com.huzheng.dto;

/**
 * @Author 胡正
 * @Date 2020/3/26 19:58
 * @Description 退款dto
 */
public class RefundDto {
    // 商户订单号
    private String out_trade_no;
    // 支付宝交易流水号
    private String trade_no;
    // 退款金额
    private String refund_amount;
    // 退款原因
    private String refund_reason;
    // 退款请求号，非必选，当多次退款时必填
    private String out_request_no;

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getTrade_no() {
        return trade_no;
    }

    public void setTrade_no(String trade_no) {
        this.trade_no = trade_no;
    }

    public String getRefund_amount() {
        return refund_amount;
    }

    public void setRefund_amount(String refund_amount) {
        this.refund_amount = refund_amount;
    }

    public String getRefund_reason() {
        return refund_reason;
    }

    public void setRefund_reason(String refund_reason) {
        this.refund_reason = refund_reason;
    }

    public String getOut_request_no() {
        return out_request_no;
    }

    public void setOut_request_no(String out_request_no) {
        this.out_request_no = out_request_no;
    }
}
