package com.example.taxitestapp.model;

/**
 * Created by mihai on 2/16/2018.
 */

public class Order {

    private int mId;
    private double mPrice;
    private String mOrderDate;
    private String mTargetDate;
    private String mStartAddress;
    private String mEndAddress;
    private String mComment;
    private double mLon;
    private double mLat;

    public int getId() {
        return mId;
    }

    public double getPrice() {
        return mPrice;
    }

    public String getOrderDate() {
        return mOrderDate;
    }

    public String getTargetDate() {
        return mTargetDate;
    }

    public String getStartAddress() {
        return mStartAddress;
    }

    public String getEndAddress() {
        return mEndAddress;
    }

    public String getComment() {
        return mComment;
    }

    public double getLon() {
        return mLon;
    }

    public double getLat() {
        return mLat;
    }

    public void setId(final int id) {
        mId = id;
    }

    public void setPrice(final double price) {
        mPrice = price;
    }

    public void setOrderDate(final String orderDate) {
        mOrderDate = orderDate;
    }

    public void setTargetDate(final String targetDate) {
        mTargetDate = targetDate;
    }

    public void setStartAddress(final String startAddress) {
        mStartAddress = startAddress;
    }

    public void setEndAddress(final String endAddress) {
        mEndAddress = endAddress;
    }

    public void setComment(final String comment) {
        mComment = comment;
    }

    public void setLon(final double lon) {
        mLon = lon;
    }

    public void setLat(final double lat) {
        mLat = lat;
    }

    @Override
    public String toString() {
        return "Order{" +
                "mId=" + mId +
                ", mPrice=" + mPrice +
                ", mOrderDate='" + mOrderDate + '\'' +
                ", mTargetDate='" + mTargetDate + '\'' +
                ", mStartAddress='" + mStartAddress + '\'' +
                ", mEndAddress='" + mEndAddress + '\'' +
                ", mComment='" + mComment + '\'' +
                ", mLon=" + mLon +
                ", mLat=" + mLat +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Order order = (Order) o;

        if (mId != order.mId) return false;
        if (Double.compare(order.mPrice, mPrice) != 0) return false;
        if (Double.compare(order.mLon, mLon) != 0) return false;
        if (Double.compare(order.mLat, mLat) != 0) return false;
        if (mOrderDate != null ? !mOrderDate.equals(order.mOrderDate) : order.mOrderDate != null)
            return false;
        if (mTargetDate != null ? !mTargetDate.equals(order.mTargetDate) : order.mTargetDate != null)
            return false;
        if (mStartAddress != null ? !mStartAddress.equals(order.mStartAddress) : order.mStartAddress != null)
            return false;
        if (mEndAddress != null ? !mEndAddress.equals(order.mEndAddress) : order.mEndAddress != null)
            return false;
        return mComment != null ? mComment.equals(order.mComment) : order.mComment == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = mId;
        temp = Double.doubleToLongBits(mPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (mOrderDate != null ? mOrderDate.hashCode() : 0);
        result = 31 * result + (mTargetDate != null ? mTargetDate.hashCode() : 0);
        result = 31 * result + (mStartAddress != null ? mStartAddress.hashCode() : 0);
        result = 31 * result + (mEndAddress != null ? mEndAddress.hashCode() : 0);
        result = 31 * result + (mComment != null ? mComment.hashCode() : 0);
        temp = Double.doubleToLongBits(mLon);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(mLat);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
