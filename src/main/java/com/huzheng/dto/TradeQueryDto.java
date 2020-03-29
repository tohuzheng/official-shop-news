package com.huzheng.dto;

/**
 * @Author 胡正
 * @Date 2020/3/26 18:22
 * @Description 查询交易dto
 */
public class TradeQueryDto {
    // 商户订单号
    private String out_trade_no;
    // 支付宝订单流水号
    private String trade_no;
    // 查询选项
    private String[] query_options={"TRADE_SETTLE_INFO"};

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

    public String[] getQuery_options() {
        return query_options;
    }

    public void setQuery_options(String[] query_options) {
        this.query_options = query_options;
    }
}
