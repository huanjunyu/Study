package com.order.model;

/**
 * @ClassName: Common
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: H_jy
 * @date: 2019-04-02 15:27
 */
public class Common {
    private int pagesize;
    private int pageid;
    private int pagebegin;
    private int count;

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public int getPageid() {
        return pageid;
    }

    public void setPageid(int pageid) {
        this.pageid = pageid;
    }

    public int getPagebegin() {
        return pagebegin;
    }

    public void setPagebegin(int pagebegin) {
        this.pagebegin = pagebegin;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
