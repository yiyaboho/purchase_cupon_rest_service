package com.purchase.coupon.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;


public class TopItems   {
  @JsonProperty("item")
  private List<CouponItem> item = null;

  public TopItems item(List<CouponItem> item) {
    this.item = item;
    return this;
  }

  public TopItems addItemItem(CouponItem itemItem) {
    if (this.item == null) {
      this.item = new ArrayList<>();
    }
    this.item.add(itemItem);
    return this;
  }

  public List<CouponItem> getItem() {
    return item;
  }

  public void setItem(List<CouponItem> item) {
    this.item = item;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TopItems topItems = (TopItems) o;
    return Objects.equals(this.item, topItems.item);
  }

  @Override
  public int hashCode() {
    return Objects.hash(item);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TopItems {\n");
    
    sb.append("    item: ").append(toIndentedString(item)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

