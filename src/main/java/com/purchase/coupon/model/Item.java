package com.purchase.coupon.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Item   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("quantity")
  private Long quantity = null;

  public Item id(Long id) {
    this.id = id;
    return this;
  }


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Item quantity(Long quantity) {
    this.quantity = quantity;
    return this;
  }


  public Long getQuantity() {
    return quantity;
  }

  public void setQuantity(Long quantity) {
    this.quantity = quantity;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Item item = (Item) o;
    return Objects.equals(this.id, item.id) &&
        Objects.equals(this.quantity, item.quantity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, quantity);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Item {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
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

