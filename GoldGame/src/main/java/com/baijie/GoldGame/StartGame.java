package com.baijie.GoldGame;

import java.math.BigDecimal;
import java.util.List;

import com.baijie.GoldGame.buyer.Buyer;
import com.baijie.GoldGame.buyer.BuyerImpl;
import com.baijie.GoldGame.common.StockData;
import com.baijie.GoldGame.common.reader.XLSReader;
import com.baijie.GoldGame.common.reader.XLSReaderImpl;
import com.baijie.GoldGame.gold.ETF;

public class StartGame {
	public static void main (String args[]){
		XLSReader reader = new XLSReaderImpl();
		ETF etf = new ETF();
		Buyer b = new BuyerImpl();
		
		List<StockData> dataList = reader.ReadExcel("gwd.xls");
		
		reader.analysisDataList(dataList, etf, 4);
		b.Buy(etf, new BigDecimal("1000"));
	}
}
