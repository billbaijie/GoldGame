package com.baijie.GoldGame.buyer;

import java.math.BigDecimal;

import com.baijie.GoldGame.common.StockData;
import com.baijie.GoldGame.gold.Item;

/**
 * 
 * @author baijie
 *价值定投的买家类
 *每个月让自己的持股价值增加一个定值
 *在盈利率达到一定水准时，平仓卖出，并重新开始定投
 *总成本在平仓时不清零
 */
public class BuyerImpl extends Buyer{
	
	public void Buy(Item item, BigDecimal perMonth){
		BigDecimal targetShare = new BigDecimal("0");
		BigDecimal currentMonth = new BigDecimal("0");
		BigDecimal currentMonthCost = new BigDecimal("0");
		BigDecimal currentRoundCost = new BigDecimal("0");//当前轮次成本，每次平仓都会清零
		BigDecimal price = new BigDecimal("0");//当天收盘价
		BigDecimal targetRate = new BigDecimal("0.2");//目标盈利率，如果当前轮次盈利率高于它，则平仓
		BigDecimal currentRate = new BigDecimal("0");//当前轮次盈利率
		BigDecimal currentValue = new BigDecimal("0");//当前轮次持股价值
		
		//获得第一个月价格
		StockData currentData = item.getStockData();
		for( ; currentData != null; currentData = item.getStockData()){
			price = currentData.getClosingPrice();
			System.out.print("定投轮数 " + num + "  ");
			num++;
			System.out.print("本次买入时间为 " + currentData.getDate() + "  \n");
			//求出本月应该持有的总价值
			currentValue = currentValue.add(perMonth);//本轮次持股价值
			value = value.add(perMonth);//总持股价值（不清零）
			//求出本月应该持有的总克数(手)
			targetShare = currentValue.divide(price, 4, BigDecimal.ROUND_HALF_EVEN);
			//求出本月应该购买的克数
			currentMonth = targetShare.subtract(share);
			//求出本月的花费
			currentMonthCost = currentMonth.multiply(price);
			System.out.print("本月花费 " + currentMonthCost+ "  ");
			//将定投的钱加入成本(以及当前轮次成本)
			cost = cost.add(currentMonthCost);
			currentRoundCost = currentRoundCost.add(currentMonthCost);
			//修改buyer状态
			share = targetShare;
			System.out.print("当前手数为" + targetShare + "  ");
			//打印当前盈利率
			currentRate = value.subtract(currentRoundCost).divide(value, 4, BigDecimal.ROUND_HALF_EVEN);
			System.out.print("当前净利率为" + currentRate + "  ");
			//打印总盈利率
			rate = value.subtract(cost).divide(value, 4, BigDecimal.ROUND_HALF_EVEN);
			System.out.print("总净利率为" + rate + "  ");
			//打印结果
			System.out.print("当前成本为： " + currentRoundCost + "  ");
			System.out.print("总成本为： " + cost + "  ");
			System.out.print("总价值为： " + value + "\n\n");
			//检验是否到达平仓标准，如果达到则平仓
			if(currentRate.compareTo(targetRate) >= 0){
				//总成本由于平仓，需要减去平仓拿出的钱，也就是股票总价值
				cost = cost.subtract(value);
				//总价值、当前成本、持有股票手数归零
				value = new BigDecimal("0");
				currentRoundCost = new BigDecimal("0");
				share = new BigDecimal("0");
				System.out.println("平仓！\n\n");
			}
		}
		
	}
	
}