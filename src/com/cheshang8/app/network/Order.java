package com.cheshang8.app.network;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.cheshang8.app.adapter.OrderAdapter.Model.Status;

public class Order {
	
	public static class Payment{
		private int price;
		private int gift;
		private int pay;
		private String type;
		private String receipt;
		public int getPrice() {
			return price;
		}
		public int getGift() {
			return gift;
		}
		public int getPay() {
			return pay;
		}
		public String getType() {
			return type;
		}
		public String getReceipt() {
			return receipt;
		}
		
		
	}
	
	private long date;
	private String no;
	private String consume_no;
	private int status;
	private int commented;
	private Service service;
	private Payment payment;
	
	public Status getStatusModel(){
		return new Status[]{Status.待支付,Status.待支付,Status.已完成,Status.待体验,Status.已完成,Status.退款中,Status.退款完成,Status.退款失败}[status];
	}
	
	public long getDate() {
		return date;
	}

	public String getNo() {
		return no;
	}

	public String getConsume_no() {
		return consume_no;
	}

	public int getStatus() {
		return status;
	}

	public int getCommented() {
		return commented;
	}

	public Service getService() {
		return service;
	}

	public Payment getPayment() {
		return payment;
	}

	public String getDateString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(new Date(date));
	}
	
}
