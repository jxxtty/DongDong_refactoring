package com.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class StatisticService {
	
	public Map<String, List> getComplaintChartData(String date, String statisticType, int dataNumber) {
		Map<String, List> returnData = new HashMap<String, List>();
		List<String> complaintChartLabel = new ArrayList<String>();
		List<Integer> complaintChartData = new ArrayList<Integer>();
		SimpleDateFormat dateFormat = null;
		SimpleDateFormat labelFormat = null;
		File readFile = new File("C:/Dong-Dong_log/statistics.stat");
		Calendar cal = Calendar.getInstance();
		Date startDate = null;
		String currentTime=null;
		String nextTime=null;
		try {
			if(statisticType.equals("H")) {
				dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH");
				labelFormat = new SimpleDateFormat("HH시");
				startDate = dateFormat.parse(date);
				cal.setTime(startDate);
				currentTime=dateFormat.format(cal.getTime());
				cal.add(Calendar.HOUR, 1);
				nextTime=dateFormat.format(cal.getTime());
				cal.add(Calendar.HOUR, -(dataNumber));
			} else if(statisticType.equals("D")) {
				dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				labelFormat = new SimpleDateFormat("dd일");
				startDate = dateFormat.parse(date);
				cal.setTime(startDate);
				currentTime=dateFormat.format(cal.getTime());
				cal.add(Calendar.DATE, 1);
				nextTime=dateFormat.format(cal.getTime());
				cal.add(Calendar.DATE, -(dataNumber));
			} else if(statisticType.equals("M")) {	
				dateFormat = new SimpleDateFormat("yyyy-MM");
				labelFormat = new SimpleDateFormat("MM월");
				startDate = dateFormat.parse(date);
				cal.setTime(startDate);
				currentTime=dateFormat.format(cal.getTime());
				cal.add(Calendar.MONTH, 1);
				nextTime=dateFormat.format(cal.getTime());
				cal.add(Calendar.MONTH, -(dataNumber));
			}
			BufferedReader reader = new BufferedReader(new FileReader(readFile));
			String targetDate=null;
			String nextLine=null;
			int thisDateSum=0;
			while((nextLine = reader.readLine())!=null){
				targetDate=dateFormat.format(cal.getTime());
				if(targetDate.equals(nextTime)) {
					break;
				}
				if(nextLine.contains("["+statisticType+"]"+targetDate)) {
					String [] str = nextLine.split(":");
					complaintChartLabel.add("\""+labelFormat.format(cal.getTime())+"\"");
					complaintChartData.add(Integer.parseInt(str[1]));
					if(statisticType.equals("H")) {
						cal.add(Calendar.HOUR, 1);
					} else if(statisticType.equals("D")) {
						cal.add(Calendar.DATE, 1);
					} else if(statisticType.equals("M")) {
						cal.add(Calendar.MONTH, 1);
					}
				} else if(statisticType.equals("M")&&nextLine.contains("[H]"+currentTime)) {
					String [] str = nextLine.split(":");
					thisDateSum+=Integer.parseInt(str[1]);
				} else if(statisticType.equals("D")&&nextLine.contains("[H]"+currentTime)) {
					String [] str = nextLine.split(":");
					thisDateSum+=Integer.parseInt(str[1]);
				}
			}
			reader.close();
			
			readFile = new File("C:/Dong-Dong_log/statistics.log");
			reader = new BufferedReader(new FileReader(readFile));
			while((nextLine = reader.readLine())!=null){
				if(nextLine.contains("ComplaintAccept")) {
					thisDateSum++;
				}
			}
			reader.close();

			complaintChartLabel.add("\""+labelFormat.format(startDate)+"\"");
			complaintChartData.add(thisDateSum);
		} catch (Exception e) {
			// TODO: handle exception
		}
		returnData.put("complaintChartLabel", complaintChartLabel);
		returnData.put("complaintChartData", complaintChartData);
		return returnData;
	}
	
	public Map<String, List> getPurchaseChartData(String date, String statisticType, int dataNumber) {
		Map<String, List> returnData = new HashMap<String, List>();
		List<String> purchaseChartLabel = new ArrayList<String>();
		List<Integer> purchaseChartData = new ArrayList<Integer>();
		SimpleDateFormat dateFormat = null;
		SimpleDateFormat labelFormat = null;
		File readFile = new File("C:/Dong-Dong_log/statistics.stat");
		Calendar cal = Calendar.getInstance();
		Date startDate = null;
		String currentTime=null;
		String nextTime=null;
		try {
			if(statisticType.equals("H")) {
				dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH");
				labelFormat = new SimpleDateFormat("HH시");
				startDate = dateFormat.parse(date);
				cal.setTime(startDate);
				currentTime=dateFormat.format(cal.getTime());
				cal.add(Calendar.HOUR, 1);
				nextTime=dateFormat.format(cal.getTime());
				cal.add(Calendar.HOUR, -(dataNumber));
			} else if(statisticType.equals("D")) {
				dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				labelFormat = new SimpleDateFormat("dd일");
				startDate = dateFormat.parse(date);
				cal.setTime(startDate);
				currentTime=dateFormat.format(cal.getTime());
				cal.add(Calendar.DATE, 1);
				nextTime=dateFormat.format(cal.getTime());
				cal.add(Calendar.DATE, -(dataNumber));
			} else if(statisticType.equals("M")) {	
				dateFormat = new SimpleDateFormat("yyyy-MM");
				labelFormat = new SimpleDateFormat("MM월");
				startDate = dateFormat.parse(date);
				cal.setTime(startDate);
				currentTime=dateFormat.format(cal.getTime());
				cal.add(Calendar.MONTH, 1);
				nextTime=dateFormat.format(cal.getTime());
				cal.add(Calendar.MONTH, -(dataNumber));
			}
			BufferedReader reader = new BufferedReader(new FileReader(readFile));
			String targetDate=null;
			String nextLine=null;
			int thisDateSum=0;
			while((nextLine = reader.readLine())!=null){
				targetDate=dateFormat.format(cal.getTime());
				if(targetDate.equals(nextTime)) {
					break;
				}
				if(nextLine.contains("["+statisticType+"]"+targetDate)) {
					String [] str = nextLine.split(":");
					purchaseChartLabel.add("\""+labelFormat.format(cal.getTime())+"\"");
					purchaseChartData.add(Integer.parseInt(str[2]));
					if(statisticType.equals("H")) {
						cal.add(Calendar.HOUR, 1);
					} else if(statisticType.equals("D")) {
						cal.add(Calendar.DATE, 1);
					} else if(statisticType.equals("M")) {
						cal.add(Calendar.MONTH, 1);
					}
				} else if(statisticType.equals("M")&&nextLine.contains("[H]"+currentTime)) {
					String [] str = nextLine.split(":");
					thisDateSum+=Integer.parseInt(str[2]);
				} else if(statisticType.equals("D")&&nextLine.contains("[H]"+currentTime)) {
					String [] str = nextLine.split(":");
					thisDateSum+=Integer.parseInt(str[2]);
				}
			}
			reader.close();
			
			readFile = new File("C:/Dong-Dong_log/statistics.log");
			reader = new BufferedReader(new FileReader(readFile));
			while((nextLine = reader.readLine())!=null){
				if(nextLine.contains("PurchaseComplete")) {
					thisDateSum++;
				}
			}
			reader.close();

			purchaseChartLabel.add("\""+labelFormat.format(startDate)+"\"");
			purchaseChartData.add(thisDateSum);
		} catch (Exception e) {
			// TODO: handle exception
		}
		returnData.put("purchaseChartLabel", purchaseChartLabel);
		returnData.put("purchaseChartData", purchaseChartData);
		return returnData;
	}
	
	public Map<String, List> getPostWriteChartData(String date, String statisticType, int dataNumber) {
		Map<String, List> returnData = new HashMap<String, List>();
		List<String> postWriteChartLabel = new ArrayList<String>();
		List<Integer> postWriteChartData = new ArrayList<Integer>();
		SimpleDateFormat dateFormat = null;
		SimpleDateFormat labelFormat = null;
		File readFile = new File("C:/Dong-Dong_log/statistics.stat");
		Calendar cal = Calendar.getInstance();
		Date startDate = null;
		String currentTime=null;
		String nextTime=null;
		try {
			if(statisticType.equals("H")) {
				dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH");
				labelFormat = new SimpleDateFormat("HH시");
				startDate = dateFormat.parse(date);
				cal.setTime(startDate);
				currentTime=dateFormat.format(cal.getTime());
				cal.add(Calendar.HOUR, 1);
				nextTime=dateFormat.format(cal.getTime());
				cal.add(Calendar.HOUR, -(dataNumber));
			} else if(statisticType.equals("D")) {
				dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				labelFormat = new SimpleDateFormat("dd일");
				startDate = dateFormat.parse(date);
				cal.setTime(startDate);
				currentTime=dateFormat.format(cal.getTime());
				cal.add(Calendar.DATE, 1);
				nextTime=dateFormat.format(cal.getTime());
				cal.add(Calendar.DATE, -(dataNumber));
			} else if(statisticType.equals("M")) {	
				dateFormat = new SimpleDateFormat("yyyy-MM");
				labelFormat = new SimpleDateFormat("MM월");
				startDate = dateFormat.parse(date);
				cal.setTime(startDate);
				currentTime=dateFormat.format(cal.getTime());
				cal.add(Calendar.MONTH, 1);
				nextTime=dateFormat.format(cal.getTime());
				cal.add(Calendar.MONTH, -(dataNumber));
			}
			BufferedReader reader = new BufferedReader(new FileReader(readFile));
			String targetDate=null;
			String nextLine=null;
			int thisDateSum=0;
			while((nextLine = reader.readLine())!=null){
				targetDate=dateFormat.format(cal.getTime());
				if(targetDate.equals(nextTime)) {
					break;
				}
				if(nextLine.contains("["+statisticType+"]"+targetDate)) {
					String [] str = nextLine.split(":");
					postWriteChartLabel.add("\""+labelFormat.format(cal.getTime())+"\"");
					postWriteChartData.add(Integer.parseInt(str[3]));
					if(statisticType.equals("H")) {
						cal.add(Calendar.HOUR, 1);
					} else if(statisticType.equals("D")) {
						cal.add(Calendar.DATE, 1);
					} else if(statisticType.equals("M")) {
						cal.add(Calendar.MONTH, 1);
					}
				} else if(statisticType.equals("M")&&nextLine.contains("[H]"+currentTime)) {
					String [] str = nextLine.split(":");
					thisDateSum+=Integer.parseInt(str[3]);
				} else if(statisticType.equals("D")&&nextLine.contains("[H]"+currentTime)) {
					String [] str = nextLine.split(":");
					thisDateSum+=Integer.parseInt(str[3]);
				}
			}
			reader.close();
			
			readFile = new File("C:/Dong-Dong_log/statistics.log");
			reader = new BufferedReader(new FileReader(readFile));
			while((nextLine = reader.readLine())!=null){
				if(nextLine.contains("postWriteSuccess")) {
					thisDateSum++;
				}
			}
			reader.close();

			postWriteChartLabel.add("\""+labelFormat.format(startDate)+"\"");
			postWriteChartData.add(thisDateSum);
		} catch (Exception e) {
			// TODO: handle exception
		}
		returnData.put("postWriteChartLabel", postWriteChartLabel);
		returnData.put("postWriteChartData", postWriteChartData);
		return returnData;
	}
	
	public Map<String, List> getAccountCreateChartData(String date, String statisticType, int dataNumber) {
		Map<String, List> returnData = new HashMap<String, List>();
		List<String> accountCreateChartLabel = new ArrayList<String>();
		List<Integer> accountCreateChartData = new ArrayList<Integer>();
		SimpleDateFormat dateFormat = null;
		SimpleDateFormat labelFormat = null;
		File readFile = new File("C:/Dong-Dong_log/statistics.stat");
		Calendar cal = Calendar.getInstance();
		Date startDate = null;
		String currentTime=null;
		String nextTime=null;
		try {
			if(statisticType.equals("H")) {
				dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH");
				labelFormat = new SimpleDateFormat("HH시");
				startDate = dateFormat.parse(date);
				cal.setTime(startDate);
				currentTime=dateFormat.format(cal.getTime());
				cal.add(Calendar.HOUR, 1);
				nextTime=dateFormat.format(cal.getTime());
				cal.add(Calendar.HOUR, -(dataNumber));
			} else if(statisticType.equals("D")) {
				dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				labelFormat = new SimpleDateFormat("dd일");
				startDate = dateFormat.parse(date);
				cal.setTime(startDate);
				currentTime=dateFormat.format(cal.getTime());
				cal.add(Calendar.DATE, 1);
				nextTime=dateFormat.format(cal.getTime());
				cal.add(Calendar.DATE, -(dataNumber));
			} else if(statisticType.equals("M")) {	
				dateFormat = new SimpleDateFormat("yyyy-MM");
				labelFormat = new SimpleDateFormat("MM월");
				startDate = dateFormat.parse(date);
				cal.setTime(startDate);
				currentTime=dateFormat.format(cal.getTime());
				cal.add(Calendar.MONTH, 1);
				nextTime=dateFormat.format(cal.getTime());
				cal.add(Calendar.MONTH, -(dataNumber));
			}
			BufferedReader reader = new BufferedReader(new FileReader(readFile));
			String targetDate=null;
			String nextLine=null;
			int thisDateSum=0;
			while((nextLine = reader.readLine())!=null){
				targetDate=dateFormat.format(cal.getTime());
				if(targetDate.equals(nextTime)) {
					break;
				}
				if(nextLine.contains("["+statisticType+"]"+targetDate)) {
					String [] str = nextLine.split(":");
					accountCreateChartLabel.add("\""+labelFormat.format(cal.getTime())+"\"");
					accountCreateChartData.add(Integer.parseInt(str[4]));
					if(statisticType.equals("H")) {
						cal.add(Calendar.HOUR, 1);
					} else if(statisticType.equals("D")) {
						cal.add(Calendar.DATE, 1);
					} else if(statisticType.equals("M")) {
						cal.add(Calendar.MONTH, 1);
					}
				} else if(statisticType.equals("M")&&nextLine.contains("[H]"+currentTime)) {
					String [] str = nextLine.split(":");
					thisDateSum+=Integer.parseInt(str[4]);
				} else if(statisticType.equals("D")&&nextLine.contains("[H]"+currentTime)) {
					String [] str = nextLine.split(":");
					thisDateSum+=Integer.parseInt(str[4]);
				}
			}
			reader.close();
			
			readFile = new File("C:/Dong-Dong_log/statistics.log");
			reader = new BufferedReader(new FileReader(readFile));
			while((nextLine = reader.readLine())!=null){
				if(nextLine.contains("AccountCreated")) {
					thisDateSum++;
				}
			}
			reader.close();

			accountCreateChartLabel.add("\""+labelFormat.format(startDate)+"\"");
			accountCreateChartData.add(thisDateSum);
		} catch (Exception e) {
			// TODO: handle exception
		}
		returnData.put("accountCreateChartLabel", accountCreateChartLabel);
		returnData.put("accountCreateChartData", accountCreateChartData);
		return returnData;
	}
	
	public Map<String, List> getTXChartData(String date, String statisticType, int dataNumber) {
		Map<String, List> returnData = new HashMap<String, List>();
		List<String> txChartLabel = new ArrayList<String>();
		List<Integer> txChartData = new ArrayList<Integer>();
		SimpleDateFormat dateFormat = null;
		SimpleDateFormat labelFormat = null;
		File readFile = new File("C:/Dong-Dong_log/statistics.stat");
		Calendar cal = Calendar.getInstance();
		Date startDate = null;
		String startDateStr=null;
		String nextDateStr=null;
		boolean isToday = false;
		try {
			dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String todayStr=dateFormat.format(cal.getTime());
			if(statisticType.equals("H")) {
				dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH");
				labelFormat = new SimpleDateFormat("dd일 HH시");
				startDate = dateFormat.parse(date);
				cal.setTime(startDate);
				startDateStr=dateFormat.format(cal.getTime());
				cal.add(Calendar.HOUR, 1);
				nextDateStr=dateFormat.format(cal.getTime());
				cal.add(Calendar.HOUR, -(dataNumber));
			} else if(statisticType.equals("D")) {
				dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				labelFormat = new SimpleDateFormat("MM월 dd일");
				startDate = dateFormat.parse(date);
				cal.setTime(startDate);
				startDateStr=dateFormat.format(cal.getTime());
				cal.add(Calendar.DATE, 1);
				nextDateStr=dateFormat.format(cal.getTime());
				cal.add(Calendar.DATE, -(dataNumber));
			} else if(statisticType.equals("M")) {	
				dateFormat = new SimpleDateFormat("yyyy-MM");
				labelFormat = new SimpleDateFormat("yyyy년 MM월");
				startDate = dateFormat.parse(date);
				cal.setTime(startDate);
				startDateStr=dateFormat.format(cal.getTime());
				cal.add(Calendar.MONTH, 1);
				nextDateStr=dateFormat.format(cal.getTime());
				cal.add(Calendar.MONTH, -(dataNumber));
			}
			BufferedReader reader = new BufferedReader(new FileReader(readFile));
			String targetDate=null;
			String nextLine=null;
			int thisDateSum=0;
			while((nextLine = reader.readLine())!=null){
				targetDate=dateFormat.format(cal.getTime());
				if(targetDate.equals(nextDateStr)) {
					break;
				}
				if(nextLine.contains("["+statisticType+"]"+targetDate)) {
					String [] str = nextLine.split(":");
					txChartLabel.add(labelFormat.format(cal.getTime()));
					txChartData.add(Integer.parseInt(str[1])+Integer.parseInt(str[2])+Integer.parseInt(str[3])+Integer.parseInt(str[4]));
					if(statisticType.equals("H")) {
						cal.add(Calendar.HOUR, 1);
					} else if(statisticType.equals("D")) {
						cal.add(Calendar.DATE, 1);
					} else if(statisticType.equals("M")) {
						cal.add(Calendar.MONTH, 1);
					}
				} else if(statisticType.equals("M")&&nextLine.contains("[H]"+startDateStr)) {
					String [] str = nextLine.split(":");
					thisDateSum+=Integer.parseInt(str[1])+Integer.parseInt(str[2])+Integer.parseInt(str[3])+Integer.parseInt(str[4]);
				} else if(statisticType.equals("D")&&nextLine.contains("[H]"+startDateStr)) {
					String [] str = nextLine.split(":");
					thisDateSum+=Integer.parseInt(str[1])+Integer.parseInt(str[2])+Integer.parseInt(str[3])+Integer.parseInt(str[4]);
				}
			}
			reader.close();
			
			readFile = new File("C:/Dong-Dong_log/statistics.log");
			reader = new BufferedReader(new FileReader(readFile));
			while((nextLine = reader.readLine())!=null){
				if(nextLine.contains("ComplaintAccept")||nextLine.contains("PurchaseComplete")||nextLine.contains("postWriteSuccess")||nextLine.contains("AccountCreated")) {
					thisDateSum++;
				}
			}
			reader.close();
			if(!statisticType.equals("M")&&date.substring(0,10).equals(todayStr)) {
				txChartLabel.add(labelFormat.format(startDate));
				txChartData.add(thisDateSum);
			} else if(statisticType.equals("M")&&date.substring(0,7).equals(todayStr.substring(0,7))) {
				txChartLabel.add(labelFormat.format(startDate));
				txChartData.add(thisDateSum);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		returnData.put("txChartLabel", txChartLabel);
		returnData.put("txChartData", txChartData);
		return returnData;
	}
}
