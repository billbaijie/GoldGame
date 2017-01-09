package com.baijie.GoldGame.common.reader;

import java.util.List;

import com.baijie.GoldGame.common.StockData;
import com.baijie.GoldGame.gold.Item;
/**
 * excel解析借口
 * @author baijie
 *
 */
public interface XLSReader {
	
	/**
	 * 读取excel文件,返回list
	 * @param i 需要注入价格的item
	 * @param name 文件名
	 */
	public List<StockData> ReadExcel(String name);
	
	/**
	 * 解析datalist，获取每月指定日期的数据,装配到item里
	 * @param dataList
	 * @param paramDate 定投日期
	 * @param i
	 */
	public Item analysisDataList(List<StockData> dataList, Item i,int paramDate);
}
