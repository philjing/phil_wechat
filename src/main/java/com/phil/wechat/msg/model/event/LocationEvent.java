package com.phil.wechat.msg.model.event;

/**
 * 上报地理位置事件
 * @author phil
 * @data  2017年3月26日
 *
 */
public class LocationEvent extends AbstractEvent {
	
	private static final long serialVersionUID = -2646088896795414864L;
	//地理位置纬度
	private String Latitude;
	//地理位置经度
	private String Longitude;
	//地理位置精度
	private String Precision;
	
	public String getLatitude() {
		return Latitude;
	}
	public void setLatitude(String latitude) {
		Latitude = latitude;
	}
	public String getLongitude() {
		return Longitude;
	}
	public void setLongitude(String longitude) {
		Longitude = longitude;
	}
	public String getPrecision() {
		return Precision;
	}
	public void setPrecision(String precision) {
		Precision = precision;
	}
	@Override
	public String setEvent() {
		return "LOCATION";
	}
}
