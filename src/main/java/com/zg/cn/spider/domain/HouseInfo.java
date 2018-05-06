package com.zg.cn.spider.domain;

public class HouseInfo {

    private String houseName;

    private String score;

    private String averagePrice;

    private String priceUnit;

    private String tellphone;

    private String flag;

    /**
     * 主力户型
     */
    private String roomType;

    private String address;

    /**
     * 近期开盘时间
     */
    private String recentDate;

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(String averagePrice) {
        this.averagePrice = averagePrice;
    }

    public String getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
    }

    public String getTellphone() {
        return tellphone;
    }

    public void setTellphone(String tellphone) {
        this.tellphone = tellphone;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRecentDate() {
        return recentDate;
    }

    public void setRecentDate(String recentDate) {
        this.recentDate = recentDate;
    }

    @Override
    public String toString() {
        return "HouseInfo{" +
                "houseName='" + houseName + '\'' +
                ", score='" + score + '\'' +
                ", averagePrice='" + averagePrice + '\'' +
                ", priceUnit='" + priceUnit + '\'' +
                ", tellphone='" + tellphone + '\'' +
                ", flag='" + flag + '\'' +
                ", roomType='" + roomType + '\'' +
                ", address='" + address + '\'' +
                ", recentDate='" + recentDate + '\'' +
                '}';
    }
}
