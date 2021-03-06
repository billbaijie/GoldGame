package com.baijie.GoldGame.buyer;

import java.math.BigDecimal;

import com.baijie.GoldGame.common.StockData;
import com.baijie.GoldGame.gold.Item;
/**
 * 按照价格定投方式，进行定投的买家
 * @author baijie
 *进行成本定投的买家类
 *每个月投入相等的成本
 */
public class BuyerImpl2 extends Buyer{
	
	public void Buy(Item item, BigDecimal perMonth){
		BigDecimal targetShare = new BigDecimal("0");
		BigDecimal price = new BigDecimal("0");
		
		//获得第一个月价格
		StockData currentData = item.getStockData();
		for( ; currentData != null; currentData = item.getStockData()){
			price = currentData.getClosingPrice();
			System.out.print("定投轮数 " + num + "  ");
			num ++ ;
			System.out.print("本次买入时间为 " + currentData.getDate() + "  \n");
			System.out.print("本月花费 " + perMonth+ "  ");
			cost = cost.add(perMonth);
			targetShare = share.add(perMonth.divide(price, 4, BigDecimal.ROUND_HALF_EVEN));
			System.out.print("当前手数为" + targetShare + "  ");
			value = targetShare.multiply(price);
			//打印当前盈利率
			rate = value.subtract(cost).divide(value, 4, BigDecimal.ROUND_HALF_EVEN);
			System.out.print("当前净利率为" + rate + "  ");
			//打印结果
			System.out.print("成本为： " + cost + "  ");
			System.out.print("总价值为： " + value + "\n\n");
		}
		
	}
	
}