package com.phil.wechat.msg.model.req;

/**
 * 地理位置消息
 * 
 * @author phil
 * @date 2017年6月30日
 *
 */
public class LocationMsg extends AbstractMsg {
	
	private static final long serialVersionUID = -8794342243923366046L;
	private double Location_X; // 地理位置维度
	private double Location_Y; // 地理位置经度
	private int Scale; // 地图缩放大小
	private String Label; // 地理位置信息

	@Override
	public String SetMsgType() {
		return "location";
	}

	public double getLocation_X() {
		return Location_X;
	}

	public void setLocation_X(double locationX) {
		Location_X = locationX;
	}

	public double getLocation_Y() {
		return Location_Y;
	}

	public void setLocation_Y(double locationY) {
		Location_Y = locationY;
	}

	public int getScale() {
		return Scale;
	}

	public void setScale(int scale) {
		Scale = scale;
	}

	public String getLabel() {
		return Label;
	}

	public void setLabel(String label) {
		Label = label;
	}
}
