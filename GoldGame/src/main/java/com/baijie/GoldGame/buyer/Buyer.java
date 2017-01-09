package com.baijie.GoldGame.buyer;

import java.math.BigDecimal;

import com.baijie.GoldGame.gold.Item;

public abstract class Buyer {
	int num = 1;							//定投轮数
	BigDecimal cost = new BigDecimal("0"); 	//成本 
	BigDecimal value = new BigDecimal("0");	//总价值
	BigDecimal share = new BigDecimal("0");	//持有克数
	BigDecimal rate = new BigDecimal("0");	//净利率
	
	
	/**
	 * 进行定投
	 * 按照item中的价格依次求出成本，总价值
	 * @param item
	 * @param perMonth 计划每月新增的定投量
	 */
	public abstract void Buy(Item item, BigDecimal perMonth);
	
	public BigDecimal getCost() {
		return cost;
	}
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	public BigDecimal getShare() {
		return share;
	}
	public void setShare(BigDecimal share) {
		this.share = share;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	
}
