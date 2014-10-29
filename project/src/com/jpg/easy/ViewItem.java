package com.jpg.easy;

import android.view.View;

public class ViewItem {

	private View  mView;
	public Object mData;
	public int    mOrder;
	
	private int mStartX=-1;
	private int mStartY=-1;
	
	private boolean isKeepScale=false;
	private int mWidthScale;
	private int mHeightScale;
	
	private float mXWidth,mYHeight;
	
	private String mName;
	private String mLeftName,mTopName;

	public View getView() {
		return mView;
	}

	public void setView(View View) {
		this.mView = View;
	}

	public int getStartX() {
		return mStartX;
	}

	/**
	 * 设置起始位置 行 从0开始
	 * @param StartX
	 */
	public void setStartX(int StartX) {
		this.mStartX = StartX;
	}

	
	/**
	 * 当长宽都是整数时用此方法布局
	 *
	 *坐标系是这样设定的 屏幕左上角为原点，往下为x，往右为y，x和y均从1开始计数
	 * @param x 
	 * @param y
	 * @param w
	 * @param h
	 */
	public void initPosAndSize(int x,int y,float w,float h){
		mStartX = x;
		mStartY = y;
		mXWidth = w;
		mYHeight= h;
	}
	
	/**
	 * 当长宽不为整数时用此方法布局
	 * 
	 * @param leftName 
	 * @param topName
	 * @param w
	 * @param h
	 */
	public void initPosAndSize(String leftName,String topName,float w,float h){
		mLeftName = leftName;
		mTopName  = topName;
		mXWidth   = w;
		mYHeight  = h;
	}


	public int getStartY() {
		return mStartY;
	}

	/**
	 * 设置起始位置 列 从0开始
	 * @param StartY
	 */
	public void setStartY(int StartY) {
		this.mStartY = StartY;
	}


	public void setYHeight(int YHeight) {
		this.mYHeight = YHeight;
	}
	
	public int getItemHeight(int per,int margin){
		return (int) (per * mYHeight + ( margin*(mYHeight-1) ));
	}
	
	public int getItemWidth(int per,int margin){
		return (int) (per * mXWidth + ( margin*(mXWidth-1) )  );
	}

	public String getLeftName() {
		return mLeftName;
	}

	public void setLeftName(String leftName) {
		this.mLeftName = leftName;
	}

	public String getTopName() {
		return mTopName;
	}

	public void setTopName(String topName) {
		this.mTopName = topName;
	}

	public void setName(String string) {
		mName = string;
	}
	
	public String getName(){
		return  mName;
	}
	
	public boolean isKeepScale() {
		return isKeepScale;
	}

	public void setKeepScale(boolean isKeepScale) {
		this.isKeepScale = isKeepScale;
	}

	public int getWidthScale() {
		return mWidthScale;
	}

	public void setWidthScale(int widthScale) {
		this.mWidthScale = widthScale;
	}

	public int getHeightScale() {
		return mHeightScale;
	}

	public void setHeightScale(int heightScale) {
		this.mHeightScale = heightScale;
	}
	
	public void setScale(int w,int h) {
		this.mHeightScale = h;
		this.mWidthScale  = w;
	}
}
