package com.baijie.GoldGame.gold;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.baijie.GoldGame.common.StockData;

public abstract class Item {
	
private static List<StockData> priceList = new ArrayList<StockData>(); 
private static Iterator<StockData> i = null;
	
	/**
	 * 单个新增价格
	 * @param price
	 */
	public void addData (StockData data){
		priceList.add(data);
		//重新声明迭代器，避免报错
		i = priceList.iterator();
	}
	
	/**
	 * 批量新增价格
	 * @param prices
	 */
	public void addPrices(List<StockData> data){
		priceList = data;
		//重新声明迭代器，避免报错
		i = priceList.iterator();
	}
	
	/**
	 * 按顺序得到StockData,
	 * 当迭代器进行到list末尾的时候，返回null
	 * 
	 * @return
	 */
	public StockData getStockData(){
		if(i == null){//当迭代器为空时返回空
			return null;
		}
		if(i.hasNext()){
			StockData s = i.next();
			return s;
		}
		return null;
	}
	
	/**
	 * 清空价格 
	 */
	public void reset(){
		priceList.clear();
		//清空iterator
		i = null;
	}
}
