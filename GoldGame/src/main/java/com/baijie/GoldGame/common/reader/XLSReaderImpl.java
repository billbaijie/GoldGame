package com.baijie.GoldGame.common.reader;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.baijie.GoldGame.common.DateUtil;
import com.baijie.GoldGame.common.StockData;
import com.baijie.GoldGame.gold.Item;
/**
 * 大智慧用reader
 * @author baijie
 *
 */
public class XLSReaderImpl implements XLSReader{
	

	public List<StockData> ReadExcel(String name){
		String path = this.getClass().getClassLoader().getResource(name).getPath();
		System.out.println(path);
		File file = new File(path);
		List<StockData> dataList = new ArrayList<StockData>();
		
		HSSFWorkbook workbook = null;
		try {
			//载入文件
			workbook = new HSSFWorkbook(FileUtils.openInputStream(file));
			HSSFSheet sheet = workbook.getSheetAt(0);
			int lastRow = sheet.getLastRowNum();
			//将文件中的时间，开盘价，收盘价信息，放到datalist中
			for(int rowNum = 1; rowNum<lastRow; rowNum++){
				StockData data = new StockData();
				HSSFRow row = sheet.getRow(rowNum) ;
				data.setDate(row.getCell(0).getStringCellValue().split(",")[0]);//时间
				data.setOpeningPrice(new BigDecimal(String.valueOf(row.getCell(1).getNumericCellValue())));//开盘价
				data.setClosingPrice(new BigDecimal(String.valueOf(row.getCell(4).getNumericCellValue())));//收盘价
				dataList.add(data);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				workbook.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return dataList;
	}
	
	public Item analysisDataList(List<StockData> dataList, Item i,int paramDate){
		
		Iterator<StockData> dataIterator = dataList.iterator(); 
		//将开始日期的年和月记录下来，并将定投日期设定到这个日期中，从这个年月日开始统计
		String startDate = dataList.get(0).getDate();
		//得到开始月份的第一天(将字符串末尾两位变为01)
		startDate.replaceFirst("-\\d{2}$", "01");
		Date startDateDate = DateUtil.String2Date(startDate);
		//从每月月首第一天往后roll paramDate-1天，获得第一个定投指定日期
		for(int j=0;j<paramDate-1;j++){
			startDateDate = DateUtil.rollDate(startDateDate, Calendar.DATE, 1);
		}
		//将list中每个日期拿出来与定投指定日期比较
		while(dataIterator.hasNext()){
			StockData currentData = dataIterator.next();
			//得到list中日期
			Date currentDate = DateUtil.String2Date(currentData.getDate());
			//当list中日期比指定日期大或与指定日期相等的时候，将指定日期往后移动一个月
			if(DateUtil.compare(currentDate, startDateDate)>=0){
//				System.out.println("本次买入时间为 " + currentDate + "  \n");
				startDateDate = DateUtil.getNextMonthDay(startDateDate,true);
				i.addData(currentData);
			}
		}
		return i;
	}
	
	
	
	
	
	
	
}
