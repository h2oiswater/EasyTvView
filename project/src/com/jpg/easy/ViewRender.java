package com.jpg.easy;

import java.util.List;

import com.jpg.easytvview.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ViewRender extends RelativeLayout{

	public ViewRender(Context context) {
		super(context);
	}
	public ViewRender(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ViewRender(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	private final int DEFAULT_VIEW_COLUMN_COUNT = 7;
	private final int DEFAULT_VIEW_ROW_COUNT    = 3;
	
	public static final int POS_TOP    = 0x0001;
	public static final int POS_LEFT   = 1;
	public static final int POS_RIGHT  = 1;
	public static final int POS_BOTTOM = 1;
	
	private boolean isNeedReDraw = true;
	
	private int mExactWidth,mExactHeight;
	
	private int mPerWidth,mPerHeight;
	
	private int mMargin 		= 10;
	
	private List<ViewItem> mListItems;
	
	private IViewAdapter mIViewAdapter;
	
	private boolean isUseMyOrder = false;
	
	private int mPositionWhenScale;

	public boolean isUseMyOrder() {
		return isUseMyOrder;
	}
	public void setUseMyOrder(boolean isUseMyOrder) {
		this.isUseMyOrder = isUseMyOrder;
	}
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		mExactWidth = getWidth();
		mExactHeight= getHeight();
		
		if( isNeedReDraw && mExactWidth != 0 && mExactHeight != 0){
			if( mIViewAdapter != null ){
				mIViewAdapter.readyToDraw();
			}
		}
		super.onLayout(changed, l, t, r, b);
	}


	public int getMargin() {
		return mMargin;
	}

	public void setMargin(int margin) {
		this.mMargin = margin;
	}

	public List<ViewItem> getListItems() {
		return mListItems;
	}

	public void setListItems(List<ViewItem> listItems) {
		this.mListItems = listItems;
	}

	public void startLayoutItems() {
		isNeedReDraw = false;
		if(mExactWidth ==0 || mExactHeight == 0){
			measure(0, 0);
			mExactWidth = getMeasuredWidth();
			mExactHeight= getMeasuredHeight();
		}
		// 获得基本单位大小
		mPerWidth  = (mExactWidth  - ((DEFAULT_VIEW_COLUMN_COUNT+1)*mMargin))/DEFAULT_VIEW_COLUMN_COUNT;
		mPerHeight = (mExactHeight - ((DEFAULT_VIEW_ROW_COUNT+1)*mMargin))/DEFAULT_VIEW_ROW_COUNT;
		
		// 开始布局
		
		// 由用户开始初始化自己的view
		int size = mListItems.size();
		for(int i=0;i<size;i++){
			if( mIViewAdapter!= null ){
				mListItems.set(i, mIViewAdapter.getView(mListItems.get(i)));
			}
		}
		
		if( isUseMyOrder ){
			int pos;
			for(int i=0;i<size;i++){
				pos = i+1;
				for(ViewItem item:mListItems){
					
					if(item.mOrder == pos){
						item.getView().setLayoutParams(caculateParams(item));
						 
						// 添加监听器并加入ViewGroup
						item.getView().setFocusable(true);
						item.getView().setOnFocusChangeListener(mOnFocusChangeListener);
						addView(item.getView());
						continue;
					}
					
				}
			}
		}else{
			// 开始设置每一个item的大小
			for(ViewItem item:mListItems){
				
				item.getView().setLayoutParams(caculateParams(item));
				 
				// 添加监听器并加入ViewGroup
				item.getView().setFocusable(true);
				item.getView().setOnFocusChangeListener(mOnFocusChangeListener);
				addView(item.getView());
			}
		}
		
		
		
	}
	
	private android.widget.RelativeLayout.LayoutParams caculateParams(ViewItem item){
		int itemWidth = item.getItemWidth(mPerWidth, mMargin);
		int itemHeight= item.getItemHeight(mPerHeight, mMargin);
		android.widget.RelativeLayout.LayoutParams lp = new android.widget.RelativeLayout.LayoutParams(
				itemWidth,itemHeight);
		
		if( item.getStartX() == -1 
				|| item.getStartY() == -1 ){
			// 相对布局
			for(ViewItem viewItem:mListItems){
				android.widget.RelativeLayout.LayoutParams tmpLp;
				
				if( item.getTopName() == null ){
					lp.topMargin = mMargin;
				}
				if( item.getLeftName() == null ){
					lp.leftMargin = mMargin;
				}
				
				if( viewItem.getName().equals(item.getLeftName()) ){
					tmpLp = (LayoutParams) viewItem.getView().getLayoutParams();
					lp.leftMargin = tmpLp.leftMargin + tmpLp.rightMargin +tmpLp.width + mMargin;
				}
				
				if( viewItem.getName().equals(item.getTopName()) ){
					tmpLp = (LayoutParams) viewItem.getView().getLayoutParams();
					lp.topMargin = tmpLp.topMargin + tmpLp.bottomMargin + tmpLp.height + mMargin;
				}
			}
		}else{
			// 格子布局
			lp.leftMargin = getMargin(mPerWidth, item.getStartY());
			lp.topMargin  = getMargin(mPerHeight, item.getStartX());
		}
		
		if( item.isKeepScale() ){
			float w = lp.width;
			float h = lp.height;
			float x = item.getWidthScale(); // 宽度比
			float y = item.getHeightScale();// 高度比
			
			float scale = ((float)w/(float)h);
			float needScale = ((float)x/(float)y);
			if( scale != needScale ){
				// 先试着改变长度来适应比例
				float newH = (y/x)*w;
				float newW=0;
				if( newH > h ){
					// 试着改变宽度来适应比例
					newW = (x/y)*h;
					newH = h;
				}else{
					newW = w;
				}
					
				// 居中显示
				// 增加 1/2 的新旧宽度差的margin
				// Ducks Never Drown！
				if( h - newH >0 ){
					float dif = h-newH;
					dif = dif/2;
					lp.topMargin = lp.topMargin + (int)dif;
					lp.bottomMargin = (int) dif;
				}
				
				if( w - newW >0 ){
					float dif = w-newW;
					dif = dif/2;
					lp.leftMargin  = lp.leftMargin +(int)dif;
					lp.rightMargin = (int) dif;
				}
				
				lp.width  = (int) newW;
				lp.height = (int) newH;
			}
		}
		
		return lp;
	}
	
	private OnFocusChangeListener mOnFocusChangeListener = new OnFocusChangeListener() {
		
		@Override
		public void onFocusChange(View v, boolean hasFocus) {
			if( hasFocus ){
				//初始化  
				ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.1f, 1.0f, 1.1f
						,v.getWidth()/2,v.getHeight()/2);
				//设置动画时间
				scaleAnimation.setDuration(500);
				scaleAnimation.setFillAfter(true);
				v.startAnimation(scaleAnimation);  
				v.getParent().bringChildToFront(v);
				
				v.setBackgroundResource(R.drawable.drama_tab_selected);
				v.findViewById(R.id.item_poster_tv).setVisibility(View.VISIBLE);;
			}else{
				v.clearAnimation();
				
				v.setBackgroundColor(getResources().getColor(android.R.color.transparent));
				v.findViewById(R.id.item_poster_tv).setVisibility(View.GONE);;
			}
		}
	};
	
	private int getMargin(int per,int pos){
		return (pos-1)*per + mMargin*pos;
	}
	
	public interface IViewAdapter{
		ViewItem getView(ViewItem item);
		
		void readyToDraw();
	}

	public void setGetViewListener(IViewAdapter iViewAdapter) {
		mIViewAdapter = iViewAdapter;
	}
}
