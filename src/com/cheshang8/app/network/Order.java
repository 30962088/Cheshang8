package com.cheshang8.app.network;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.ArrayUtils;

import com.cheshang8.app.OrderActivity.Model.Pay;
import com.cheshang8.app.adapter.OrderAdapter.Model.Status;

public class Order implements Serializable {

	public static class Payment implements Serializable {
		private int price;
		private int wallet;
		private int gift;
		private int pay;
		private String type;
		private String receipt;

		
		public Payment(int price, int wallet, int gift, int pay, String type,
				String receipt) {
			super();
			this.price = price;
			this.wallet = wallet;
			this.gift = gift;
			this.pay = pay;
			this.type = type;
			this.receipt = receipt;
		}

		public Payment() {
			// TODO Auto-generated constructor stub
		}

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

		public Pay toModel() {
			return new Pay(price, wallet, gift,pay, type, receipt);
		}

	}

	private long date;
	private String no;
	private String consume_no;
	private int status;
	private int commented;
	private Service service;
	private Payment payment;

	public void setDate(long date) {
		this.date = date;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public void setCommented(int commented) {
		this.commented = commented;
	}

	public void setConsume_no(String consume_no) {
		this.consume_no = consume_no;
	}

	private static Status[] STATUS = new Status[] { Status.待支付, Status.待支付,
			Status.已完成, Status.待体验, Status.已完成, Status.退款中, Status.退款完成,
			Status.退款失败 };

	public Status getStatusModel() {
		Status st = STATUS[status];

		return st;
	}

	public void setStatus(Status status) {
		this.status = ArrayUtils.indexOf(STATUS, status);
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
