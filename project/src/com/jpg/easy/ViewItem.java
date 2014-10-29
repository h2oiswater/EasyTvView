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
	 * ������ʼλ�� �� ��0��ʼ
	 * @param StartX
	 */
	public void setStartX(int StartX) {
		this.mStartX = StartX;
	}

	
	/**
	 * ������������ʱ�ô˷�������
	 *
	 *����ϵ�������趨�� ��Ļ���Ͻ�Ϊԭ�㣬����Ϊx������Ϊy��x��y����1��ʼ����
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
	 * ������Ϊ����ʱ�ô˷�������
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
	 * ������ʼλ�� �� ��0��ʼ
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
