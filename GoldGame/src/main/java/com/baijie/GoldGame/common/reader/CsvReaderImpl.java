package com.baijie.GoldGame.common.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.baijie.GoldGame.common.StockData;
import com.baijie.GoldGame.gold.Item;

public class CsvReaderImpl implements XLSReader {

	public CsvReaderImpl() {
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public List<StockData> ReadExcel(String name) {
		// TODO Auto-generated method stub
		String path = this.getClass().getClassLoader().getResource(name).getPath();
		System.out.println(path);
		//File file = new File(path);
		List<StockData> dataList = new ArrayList<StockData>();
		BufferedReader reader = null;
		try{
			System.out.println("以行为单位读取文件内容，一次读一整行：");
//            reader = new BufferedReader(new FileReader(file));
            FileInputStream fins = new FileInputStream(path);
            InputStreamReader gbReader = new InputStreamReader( fins, "GBK" ); // ＊
            reader = new BufferedReader( gbReader );
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                System.out.println("line " + line + ": " + tempString);
                if(line >= 2){
                	StockData data = new StockData();
                	data.setDate(tempString.substring(0, 10));
                	String parts[] = tempString.split(",");
                	System.out.println(parts[3]);
                	System.out.println(parts[6]);
                	data.setClosingPrice(new BigDecimal(String.valueOf(parts[3])));
                	data.setOpeningPrice(new BigDecimal(String.valueOf(parts[6])));
                	dataList.add(data);
                	
                }
                line++;
            }
            reader.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
		return null;
	}

	@Override
	public Item analysisDataList(List<StockData> dataList, Item i, int paramDate) {
		// TODO Auto-generated method stub
		return null;
	}

}
