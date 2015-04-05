package com.example.xmltest;

public class StockInfo {
	private String days_low="";
	private String days_high="";
	private String name="";
	private String days_range="";
	private String change="";
	private String lastTradePrice="";
	/**
	 * @return the days_low
	 */
	public String getDays_low() {
		return days_low;
	}

	public StockInfo(String days_low, String days_high, String name,
			String days_range, String change, String lastTradePrice) {
		super();
		this.days_low = days_low;
		this.days_high = days_high;
		this.name = name;
		this.days_range = days_range;
		this.change = change;
		this.lastTradePrice = lastTradePrice;
	}
	
	/**
	 * @param days_low the days_low to set
	 */
	public void setDays_low(String days_low) {
		this.days_low = days_low;
	}
	/**
	 * @return the days_high
	 */
	public String getDays_high() {
		return days_high;
	}
	/**
	 * @param days_high the days_high to set
	 */
	public void setDays_high(String days_high) {
		this.days_high = days_high;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the days_range
	 */
	public String getDays_range() {
		return days_range;
	}
	/**
	 * @param days_range the days_range to set
	 */
	public void setDays_range(String days_range) {
		this.days_range = days_range;
	}
	/**
	 * @return the change
	 */
	public String getChange() {
		return change;
	}
	/**
	 * @param change the change to set
	 */
	public void setChange(String change) {
		this.change = change;
	}
	/**
	 * @return the lastTradePrice
	 */
	public String getLastTradePrice() {
		return lastTradePrice;
	}
	/**
	 * @param lastTradePrice the lastTradePrice to set
	 */
	public void setLastTradePrice(String lastTradePrice) {
		this.lastTradePrice = lastTradePrice;
	}
}
