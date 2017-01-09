package com.baijie.GoldGame.common;

import java.math.BigDecimal;

public class StockData {
	
	private String date;//日期
	
	private BigDecimal openingPrice;//开盘价
	
	private BigDecimal closingPrice;//收盘价

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public BigDecimal getOpeningPrice() {
		return openingPrice;
	}

	public void setOpeningPrice(BigDecimal openingPrice) {
		this.openingPrice = openingPrice;
	}

	public BigDecimal getClosingPrice() {
		return closingPrice;
	}

	public void setClosingPrice(BigDecimal closingPrice) {
		this.closingPrice = closingPrice;
	}
	
	
	
}
