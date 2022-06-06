package com.purchase.coupon.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemsToBy   {
  @JsonProperty("items_ids")
  private List<String> item_ids = null;

  @JsonProperty("amount")
  private Integer amount = null;

  @JsonProperty("total")
  private Integer total = null;

  public ItemsToBy itemsIds(List<String> itemsIds) {
    this.item_ids = itemsIds;
    return this;
  }

  public ItemsToBy addItemsIdsItem(String itemsIdsItem) {
    if (this.item_ids == null) {
      this.item_ids = new ArrayList<String>();
    }
    this.item_ids.add(itemsIdsItem);
    return this;
  }

  public List<String> getItemsIds() {
    return item_ids;
  }

  public void setItemsIds(List<String> itemsIds) {
    this.item_ids = itemsIds;
  }

  public ItemsToBy amount(Integer amount) {
    this.amount = amount;
    return this;
  }

  public Integer getAmount() {
    return amount;
  }

  public void setAmount(Integer amount) {
    this.amount = amount;
  }

  public ItemsToBy total(Integer total) {
    this.total = total;
    return this;
  }

  public Integer getTotal() {
    return total;
  }

  public void setTotal(Integer total) {
    this.total = total;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ItemsToBy itemsToBy = (ItemsToBy) o;
    return Objects.equals(this.item_ids, itemsToBy.item_ids) &&
        Objects.equals(this.amount, itemsToBy.amount) &&
        Objects.equals(this.total, itemsToBy.total);
  }

  @Override
  public int hashCode() {
    return Objects.hash(item_ids, amount, total);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ItemsToBy {\n");
    
    sb.append("    itemsIds: ").append(toIndentedString(item_ids)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    total: ").append(toIndentedString(total)).append("\n");
    sb.append("}");
    return sb.toString();
  }


  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

