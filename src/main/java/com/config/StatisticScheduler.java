package com.config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class StatisticScheduler {
	
	private Logger statisticLogger = LoggerFactory.getLogger("statistics");

	public void test() {
		System.out.println("test start");
		int days [] = {0,31,28,31,30,31,30,31,31,30,31,30,31};
		// 한시간 동안의 로그파일 읽기 - 통계 내기 - 시간대별 통계 파일에 쓰기
		int y = 2021;
		int m = 1;
		int d = 1;
		int rannum10=0;
		int rannum100=0;
		int rannum500=0;
		int rannum1000=0;
		
		String sm =null;
		String sd =null;
		String sh =null;
		int dComCount = 0;
		int dPurCount = 0;
		int dPosCount = 0;
		int dCreCount = 0;
		int mComCount = 0;
		int mPurCount = 0;
		int mPosCount = 0;
		int mCreCount = 0;
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("C:/Dong-Dong_log/testFile3.log", true));
			while(y!=2022) {
				for(int i=0; i<24; i++) {
					rannum10 = (int)((Math.random()*10000)%10);
					rannum100 = (int)(((Math.random()*10000)%10)*10.123123);
					rannum500 = (int)(((Math.random()*10000)%10)*50.31231);
					rannum1000 = (int)(((Math.random()*10000)%10)*100.12314);
					if(m<10) {
						sm="0"+m;
					} else {
						sm=""+m;
					}
					
					if(d<10) {
						sd="0"+d;
					} else {
						sd=""+d;
					}
					
					if(i<10) {
						sh="0"+i;
					} else {
						sh=""+i;
					}
					writer.write("[H]"+y+"-"+sm+"-"+sd+"_"+sh+":"+rannum100+":"+rannum500+":"+rannum1000+":"+rannum10);
					writer.newLine();
					dComCount += rannum100;
					dPurCount += rannum500;
					dPosCount += rannum1000;
					dCreCount += rannum10;
				}
				writer.write("[D]"+y+"-"+sm+"-"+sd+":"+dComCount+":"+dPurCount+":"+dPosCount+":"+dCreCount);
				writer.newLine();
				mComCount += dComCount;
				mPurCount += dPurCount;
				mPosCount += dPosCount;
				mCreCount += dCreCount;
				dComCount=0;
				dPurCount=0;
				dPosCount=0;
				dCreCount=0;
				d++;
				if(d==days[m]+1) {
					writer.write("[M]"+y+"-"+sm+":"+mComCount+":"+mPurCount+":"+mPosCount+":"+mCreCount);
					writer.newLine();
					System.out.println(m+"월");
					mComCount=0;
					mPurCount=0;
					mPosCount=0;
					mCreCount=0;
					d=1;
					m++;
				}
				if(m==13) {
					m=1;
					y=2022;
				}
			}
			writer.flush();
			writer.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("test end");
	}
	
	
	@Scheduled(cron = "2 0 * * * *")
	public void hourlyStatistic() {
		// 한시간 동안의 로그파일 읽기 - 통계 내기 - 시간대별 통계 파일에 쓰기
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR, -1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH");
		String currentTime=sdf.format(cal.getTime());
		File readFile = new File("C:/Dong-Dong_log/statistics."+currentTime+".log");
		int complaintCount = 0;
		int purchaseCount = 0;
		int postWriteCount = 0;
		int accountCount = 0;
		statisticLogger.info("[H][Statistic] 시간대별 통계 시작");
		if(readFile.exists()) {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(readFile));
				String nextLine=null;
				while((nextLine = reader.readLine())!=null){
					if(nextLine.contains("ComplaintAccept")) {	
						complaintCount++;
					} else if(nextLine.contains("PurchaseComplete")) {
						purchaseCount++;
					} else if(nextLine.contains("postWriteSuccess")) {
						postWriteCount++;
					} else if(nextLine.contains("AccountCreated")) {
						accountCount++;
					}
				}
				reader.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
			String writeFile="C:/Dong-Dong_log/statistics.stat";
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(writeFile, true));
				writer.write("[H]"+currentTime+":"+complaintCount+":"+purchaseCount+":"+postWriteCount+":"+accountCount);
				writer.newLine();
				writer.flush();
				writer.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	@Scheduled(cron = "4 0 0 * * *")
	public void dailyStatistic() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		cal.add(Calendar.DATE, -1);
		String currentDay=sdf.format(cal.getTime());
		File readFile = new File("C:/Dong-Dong_log/statistics.stat");
		int complaintCount = 0;
		int purchaseCount = 0;
		int postWriteCount = 0;
		int accountCount = 0;
		statisticLogger.info("[D][Statistic] 일일 통계 시작");
		if(readFile.exists()) {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(readFile));
				String nextLine=null;
				while((nextLine = reader.readLine())!=null){
					if(nextLine.contains("[H]"+currentDay)) {	
						String [] str = nextLine.split(":");
						complaintCount+=Integer.parseInt(str[1]);
						purchaseCount+=Integer.parseInt(str[2]);
						postWriteCount+=Integer.parseInt(str[3]);
						accountCount+=Integer.parseInt(str[4]);
					}
				}
				reader.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
			String writeFile="C:/Dong-Dong_log/statistics.stat";
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(writeFile, true));
				writer.write("[D]"+currentDay+":"+complaintCount+":"+purchaseCount+":"+postWriteCount+":"+accountCount);
				writer.newLine();
				writer.flush();
				writer.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	@Scheduled(cron = "6 0 0 1 * *")
	public void monthlyStatistic() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String currentMonth=sdf.format(cal.getTime());
		File readFile = new File("C:/Dong-Dong_log/statistics.stat");
		int complaintCount = 0;
		int purchaseCount = 0;
		int postWriteCount = 0;
		int accountCount = 0;
		statisticLogger.info("[M][Statistic] 월간 통계 시작");
		if(readFile.exists()) {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(readFile));
				String nextLine=null;
				while((nextLine = reader.readLine())!=null){
					if(nextLine.contains("[D]"+currentMonth)) {	
						String [] str = nextLine.split(":");
						complaintCount+=Integer.parseInt(str[1]);
						purchaseCount+=Integer.parseInt(str[2]);
						postWriteCount+=Integer.parseInt(str[3]);
						accountCount+=Integer.parseInt(str[4]);
					}
				}
				reader.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
			String writeFile="C:/Dong-Dong_log/statistics.stat";
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(writeFile, true));
				writer.write("[M]"+currentMonth+":"+complaintCount+":"+purchaseCount+":"+postWriteCount+":"+accountCount);
				writer.newLine();
				writer.flush();
				writer.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
