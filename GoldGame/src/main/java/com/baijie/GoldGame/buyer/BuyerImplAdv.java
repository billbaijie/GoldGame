package com.baijie.GoldGame.buyer;

import java.math.BigDecimal;

import com.baijie.GoldGame.common.StockData;
import com.baijie.GoldGame.gold.Item;

/**
 * 
 * @author baijie
 *价值定投的买家类
 *每个月让自己的持股价值增加一个定值，
 *并且还要再增加一个比率addRate
 */
public class BuyerImplAdv extends Buyer{
	
	public void Buy(Item item, BigDecimal perMonth){
		BigDecimal targetShare = new BigDecimal("0");
		BigDecimal currentMonth = new BigDecimal("0");
		BigDecimal currentCost = new BigDecimal("0");
		BigDecimal price = new BigDecimal("0");
		BigDecimal addRate = new BigDecimal("0.99");//每期定投，都会在原本基础上新增一个比率
		
		//获得第一个月价格
		StockData currentData = item.getStockData();
		for( ; currentData != null; currentData = item.getStockData()){
			price = currentData.getClosingPrice();
			System.out.print("定投轮数 " + num + "  ");
			num ++ ;
			System.out.print("本次买入时间为 " + currentData.getDate() + "  \n");
			//求出本月应该持有的总价值
			value = value.add(perMonth).multiply(addRate).setScale(4, BigDecimal.ROUND_HALF_EVEN);
			//求出本月应该持有的总克数
			targetShare = value.divide(price, 4, BigDecimal.ROUND_HALF_EVEN);
			//求出本月应该购买的克数
			currentMonth = targetShare.subtract(share);
			//求出本月的花费
			currentCost = currentMonth.multiply(price);
			System.out.print("本月花费 " + currentCost+ "  ");
			//将定投的钱加入成本
			cost = cost.add(currentCost);
			//修改buyer状态
			share = targetShare;
			System.out.print("当前手数为" + targetShare + "  ");
			//打印当前盈利率
			rate = value.subtract(cost).divide(value, 4, BigDecimal.ROUND_HALF_EVEN);
			System.out.print("当前净利率为" + rate + "  ");
			//打印结果
			System.out.print("成本为： " + cost + "  ");
			System.out.print("总价值为： " + value + "\n\n");
		}
		
	}
	
}