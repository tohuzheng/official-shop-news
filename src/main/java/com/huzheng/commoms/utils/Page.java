package com.huzheng.commoms.utils;

import com.alibaba.fastjson.annotation.JSONField;
import org.apache.commons.lang.StringUtils;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


/**
 * @Author 胡正
 * @Date 2020/3/10 14:13
 * @Description 分页数据模型
 */
public class Page<T> implements Serializable {
    private static final long serialVersionUID = -1023518292361681996L;

    private int pageNo = 1; // 当前页码

    private int pageSize = 30; // 页面大小，设置为“-1”表示不进行分页（分页无效）

    private long count; // 总记录数，设置为“-1”表示不查询总数

    private int first; // 首页索引

    private int last; // 尾页索引

    private boolean firstPage;// 是否是第一页

    private boolean lastPage;// 是否是最后一页

    @SuppressWarnings("rawtypes")
    private List list = new ArrayList();

    private String orderBy = ""; // 标准查询有效， 实例： updatedate desc, name asc

    public Page() {
        this.pageNo = 1;
    }

    /**
     * 构造方法
     *
     * @param request  传递 repage 参数，来记住页码
     */
    public Page(HttpServletRequest request) {
        // 设置页码参数（传递repage参数，来记住页码）
        String no = getLastRequestParam(request, "page.pageNo");

        if (no == null) {
            no = getLastRequestParam(request, "pageNo");
        }

        if (StringUtils.isNumeric(no)) {
            this.setPageNo(Integer.parseInt(no));
        } else {
            this.setPageNo(1);
        }
        // 设置页面大小参数（传递repage参数，来记住页码大小）
        String size = getLastRequestParam(request, "page.pageSize");

        if (size == null) {
            size = getLastRequestParam(request, "pageSize");
        }

        if (StringUtils.isNumeric(size)) {
            this.setPageSize(Integer.parseInt(size));
        }
        // 设置排序参数
        String orderBy = request.getParameter("page.orderBy");

        if (orderBy == null) {
            orderBy = request.getParameter("orderBy");
        }

        if (StringUtils.isNotBlank(orderBy)) {
            this.setOrderBy(orderBy);
        }
    }

    private String getLastRequestParam(HttpServletRequest request, String paramName) {
        String[] strs = request.getParameterValues(paramName);

        if (strs != null) {
            return strs[strs.length - 1];
        } else {
            return null;
        }
    }

    /**
     * 构造方法
     *
     * @param pageNo   当前页码
     * @param pageSize 分页大小
     */
    public Page(int pageNo, int pageSize) {
        this(pageNo, pageSize, 0);
    }

    /**
     * 构造方法
     *
     * @param pageNo   当前页码
     * @param pageSize 分页大小
     * @param count    数据条数
     */
    public Page(int pageNo, int pageSize, long count) {
        this(pageNo, pageSize, count, new ArrayList<T>());
    }

    /**
     * 构造方法
     *
     * @param pageNo   当前页码
     * @param pageSize 分页大小
     * @param count    数据条数
     * @param list     本页数据对象列表
     */
    public Page(int pageNo, int pageSize, long count, List<T> list) {
        this.setCount(count);
        this.setPageNo(pageNo);
        this.pageSize = pageSize;
        this.list = list;
    }

    /**
     * 初始化参数
     */
    public void initialize() {

        // 1
        this.first = 1;

        this.last = (int) (count / (this.pageSize < 1 ? 20 : this.pageSize) + first - 1);

        if (this.count % this.pageSize != 0 || this.last == 0) {
            this.last++;
        }

        if (this.last < this.first) {
            this.last = this.first;
        }

        if (this.pageNo <= 1) {
            this.pageNo = this.first;
            this.firstPage = true;
        }

        if (this.pageNo >= this.last) {
            this.pageNo = this.last;
            this.lastPage = true;
        }

        // 2
        if (this.pageNo < this.first) {// 如果当前页小于首页
            this.pageNo = this.first;
        }

        if (this.pageNo > this.last) {// 如果当前页大于尾页
            this.pageNo = this.last;
        }

    }

    /**
     * 获取设置总数
     *
     * @return
     */
    public long getCount() {
        return count;
    }

    /**
     * 设置数据总数
     *
     * @param count
     */
    public void setCount(long count) {
        this.count = count;
        if (pageSize >= count) {
            pageNo = 1;
        }

        initialize();
    }

    /**
     * 获取当前页码
     *
     * @return
     */
    public int getPageNo() {
        return pageNo;
    }

    /**
     * 设置当前页码
     *
     * @param pageNo
     */
    public Page<T> setPageNo(int pageNo) {
        this.pageNo = pageNo;

        return this;
    }

    /**
     * 获取页面大小
     *
     * @return
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 设置页面大小（最大500）
     *
     * @param pageSize
     */
    public Page<T> setPageSize(int pageSize) {
        this.pageSize = pageSize <= 0 ? 10 : pageSize;// > 500 ? 500 : pageSize;

        return this;
    }

    /**
     * 首页索引
     *
     * @return
     */
    // @JsonIgnore
    @JSONField(serialize = false)
    public int getFirst() {
        return first;
    }

    /**
     * 尾页索引
     *
     * @return
     */
    // @JsonIgnore
    @JSONField(serialize = false)
    public int getLast() {
        return last;
    }

    /**
     * 获取页面总数
     *
     * @return getLast();
     */
    // @JsonIgnore
    @JSONField(serialize = false)
    public int getTotalPage() {
        return getLast();
    }

    /**
     * 是否为第一页
     *
     * @return
     */
    // @JsonIgnore
    @JSONField(serialize = false)
    public boolean isFirstPage() {
        return firstPage;
    }

    /**
     * 是否为最后一页
     *
     * @return
     */
    // @JsonIgnore
    @JSONField(serialize = false)
    public boolean isLastPage() {
        return lastPage;
    }

    /**
     * 上一页索引值
     *
     * @return
     */
    // @JsonIgnore
    @JSONField(serialize = false)
    public int getPrev() {
        if (isFirstPage()) {
            return pageNo;
        } else {
            return pageNo - 1;
        }
    }

    /**
     * 下一页索引值
     *
     * @return
     */
    // @JsonIgnore
    @JSONField(serialize = false)
    public int getNext() {
        if (isLastPage()) {
            return pageNo;
        } else {
            return pageNo + 1;
        }
    }

    /**
     * 获取本页数据对象列表
     *
     * @return List<T>
     */
    @SuppressWarnings("rawtypes")
    public List getList() {
        return list;
    }

    /**
     * 设置本页数据对象列表
     *
     * @param list
     */
    @SuppressWarnings("rawtypes")
    public Page<T> setList(List list) {
        this.list = list;
        // initialize();
        return this;
    }

    /**
     * 获取查询排序字符串
     *
     * @return
     */
    // @JsonIgnore
    @JSONField(serialize = false)
    public String getOrderBy() {
        if (orderBy == null) {
            return null;
        }

        // SQL过滤，防止注入
        String reg = "(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|"
                + "(\\b(select|update|and|or|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute)\\b)";
        Pattern sqlPattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
        if (sqlPattern.matcher(orderBy).find()) {
            return "";
        }
        return orderBy;
    }

    /**
     * 设置查询排序，标准查询有效， 实例： updatedate desc, name asc
     */
    public Page<T> setOrderBy(String orderBy) {
        this.orderBy = orderBy;

        return this;
    }

    /**
     * 分页是否有效
     *
     * @return this.pageSize==-1
     */
    // @JsonIgnore
    @JSONField(serialize = false)
    public boolean isDisabled() {
        return this.pageSize == -1;
    }

    /**
     * 是否进行总数统计
     *
     * @return this.count==-1
     */
    // @JsonIgnore
    @JSONField(serialize = false)
    public boolean isNotCount() {
        return this.count == -1;
    }

    /**
     * 获取 Hibernate FirstResult
     */
    public int getFirstResult() {
        int firstResult = (getPageNo() - 1) * getPageSize();
        if (firstResult >= getCount()) {
            firstResult = 0;
        }
        return firstResult;
    }

    /**
     * 获取 Hibernate MaxResults
     */
    public int getMaxResults() {
        return getPageSize();
    }
}
