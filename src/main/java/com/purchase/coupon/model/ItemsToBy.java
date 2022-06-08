package com.purchase.coupon.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemsToBy {
	@JsonProperty("items_ids")
	private List<String> itemsIds = null;

	@JsonProperty("amount")
	private Double amount = null;

	@JsonProperty("total")
	private Double total = null;

	@JsonProperty("message")
	private String message = null;

	public ItemsToBy(List<String> couponItems, double total2) {
		this.itemsIds = couponItems;
		this.total = total2;
	}

	public ItemsToBy() {

	}

	public ItemsToBy itemsIds(List<String> itemsIds) {
		this.itemsIds = itemsIds;
		return this;
	}

	public ItemsToBy addItemsIdsItem(String itemsIdsItem) {
		if (this.itemsIds == null) {
			this.itemsIds = new ArrayList<String>();
		}
		this.itemsIds.add(itemsIdsItem);
		return this;
	}

	public List<String> getItemsIds() {
		return itemsIds;
	}

	public void setItemsIds(List<String> itemsIds) {
		this.itemsIds = itemsIds;
	}

	public ItemsToBy amount(Double amount) {
		this.amount = amount;
		return this;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public ItemsToBy total(Double total) {
		this.total = total;
		return this;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public ItemsToBy message(String message) {
		this.message = message;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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
		return Objects.equals(this.itemsIds, itemsToBy.itemsIds) && Objects.equals(this.amount, itemsToBy.amount)
				&& Objects.equals(this.total, itemsToBy.total) && Objects.equals(this.message, itemsToBy.message);
	}

	@Override
	public int hashCode() {
		return Objects.hash(itemsIds, amount, total, message);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ItemsToBy {\n");

		sb.append("    itemsIds: ").append(toIndentedString(itemsIds)).append("\n");
		sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
		sb.append("    total: ").append(toIndentedString(total)).append("\n");
		sb.append("    message: ").append(toIndentedString(message)).append("\n");
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
