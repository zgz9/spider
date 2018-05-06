package com.zg.cn.spider.domain;

public class SpiderConfig {
    //爬虫网站总次数
    private int times = 1000;

    //爬虫网站层数
    private int level = 1;

    //网站根目录
    private String rootRrl;

    //网站前缀
    private String prefixUrl;

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getRootRrl() {
        return rootRrl;
    }

    public void setRootRrl(String rootRrl) {
        this.rootRrl = rootRrl;
    }

    public String getPrefixUrl() {
        return prefixUrl;
    }

    public void setPrefixUrl(String prefixUrl) {
        this.prefixUrl = prefixUrl;
    }
}
