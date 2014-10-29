package com.jpg.easy;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jpg.easy.ViewRender.IViewAdapter;
import com.jpg.easytvview.R;

public class TestActivity extends Activity{

	private ViewRender 		mRender;
	private LayoutInflater 	mInflater;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		setContentView(R.layout.main);
		
		initViews();
		
		Intent intent = new Intent();
		
		super.onCreate(savedInstanceState);
	}

	private void initViews() {
		mRender = (ViewRender) findViewById(R.id.main_view_render);
		
		List<ViewItem> list = new ArrayList<ViewItem>();
		ViewItem item = new ViewItem();
		
		item.setName("A");
		item.initPosAndSize(1, 1, 1, 1);
		item.setKeepScale(true);
		item.setWidthScale(3);
		item.setHeightScale(4);
		list.add(item);
		
		item = new ViewItem();
		item.setName("B");
		item.setKeepScale(true);
		item.setScale(1, 1);
		item.initPosAndSize(2, 1, 1, 1);
		list.add(item);
		
		item = new ViewItem();
		item.setName("C");
		item.initPosAndSize(3, 1, 1, 1);
		list.add(item);
		
		item = new ViewItem();
		item.setName("D");
		item.setKeepScale(true);
		item.setScale(1, 1);
		item.initPosAndSize(1, 2, 2, 3);
		list.add(item);
		
		item = new ViewItem();
		item.setName("E");
		item.setKeepScale(true);
		item.setScale(16, 9);
		item.initPosAndSize(1, 4, 2f, 1.4f);
		list.add(item);
		
		item = new ViewItem();
		item.setName("F");
		item.initPosAndSize("D", "E", 1f, 1.6f);
		list.add(item);
		
		item = new ViewItem();
		item.setName("G");
		item.initPosAndSize("F", "E", 1f, 1.6f);
		list.add(item);
		
		item = new ViewItem();
		item.setName("H");
		item.initPosAndSize(1, 6, 2f, 1.4f);
		list.add(item);
		
		item = new ViewItem();
		item.setName("I");
		item.initPosAndSize("G", "H", 1f, 1.6f);
		list.add(item);
		
		item = new ViewItem();
		item.setName("J");
		item.initPosAndSize("I", "H", 1f, 1.6f);
		list.add(item);
		
		mInflater = LayoutInflater.from(this);
		
		mRender.setListItems(list);
		mRender.setGetViewListener(new IViewAdapter() {
			
			@Override
			public ViewItem getView(ViewItem item) {
				View view = mInflater.inflate(R.layout.item_poster, null);
				
				if( item.getName().equals("A") ){
					view.findViewById(R.id.item_poster_iv)
					.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_bright));
					((TextView)view.findViewById(R.id.item_poster_tv)).setText("����");
					
				}
				if( item.getName().equals("B") ){
					view.findViewById(R.id.item_poster_iv)
					.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
					((TextView)view.findViewById(R.id.item_poster_tv)).setText("�������");
				}
				if( item.getName().equals("C") ){
					view.findViewById(R.id.item_poster_iv)
					.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
					((TextView)view.findViewById(R.id.item_poster_tv)).setText("˹�ʹ��˹");
				}
				if( item.getName().equals("D") ){
					view.findViewById(R.id.item_poster_iv)
					.setBackgroundColor(getResources().getColor(android.R.color.holo_purple));
					((TextView)view.findViewById(R.id.item_poster_tv)).setText("������");
				}
				if( item.getName().equals("E") ){
					view.findViewById(R.id.item_poster_iv)
					.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
					((TextView)view.findViewById(R.id.item_poster_tv)).setText("ϱ�����Ҹ�����");
				}
				if( item.getName().equals("F") ){
					view.findViewById(R.id.item_poster_iv)
					.setBackgroundColor(getResources().getColor(android.R.color.white));
					((TextView)view.findViewById(R.id.item_poster_tv)).setText("�ɷ���Ҹ�����");
				}
				if( item.getName().equals("G") ){
					view.findViewById(R.id.item_poster_iv)
					.setBackgroundColor(getResources().getColor(android.R.color.white));
					((TextView)view.findViewById(R.id.item_poster_tv)).setText("����ʱ��");
				}
				if( item.getName().equals("H") ){
					view.findViewById(R.id.item_poster_iv)
					.setBackgroundColor(getResources().getColor(android.R.color.white));
					((TextView)view.findViewById(R.id.item_poster_tv)).setText("ʹͽ����");
				}
				if( item.getName().equals("I") ){
					view.findViewById(R.id.item_poster_iv)
					.setBackgroundColor(getResources().getColor(android.R.color.white));
					((TextView)view.findViewById(R.id.item_poster_tv)).setText("��͵�̰�");
				}
				if( item.getName().equals("J") ){
					view.findViewById(R.id.item_poster_iv)
					.setBackgroundColor(getResources().getColor(android.R.color.white));
					((TextView)view.findViewById(R.id.item_poster_tv)).setText("��Թ");
				}
				
				
				item.setView(view);
				
				return item;
			}

			@Override
			public void readyToDraw() {
				mRender.startLayoutItems();
			}
		});
	}

	
	
}
