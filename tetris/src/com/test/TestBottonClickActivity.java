package com.test;

import com.test.View.ClickListiner;

class View {
	/*
	 * 依題目看起來應該是內部靜態介面
	 */
	public static interface ClickListiner {
		public void onClick(View v);
	}

	/*
	 * 應該是要加到陣列或集合裡,這裡暫時用靜態成員回傳元件
	 */
	public static Button btn_1 = new Button("Button 1");
	public static Button btn_2 = new Button("Button 2");
}

/*
 * 我猜Button 應該是 View 的子類別
 */
class Button extends View {
	private String name;
	private int buttonClickType;//按下的按鍵
	private ClickListiner clickListener;

	public Button() {
	};

	public Button(String name) {
		this.name = name;
	}

	public void clickLeft() {
		buttonClickType = 1;
		click();
	}

	public void clickRight() {
		buttonClickType = 2;
		click();
	}

	/*
	 * 這個click 由 使用者按下Button 後,由程序自動呼叫;
	 * 這個click 應該要命名 onCick,和ClickListener 的 onClick 才有一致性
	 */
	public void click() {
		if (clickListener == null) {
			/*
			 * 沒註冊監聽器,應該也能按下,只是沒反應?
			 */
			System.out.println("Notthing happened");
		} else {
			/*
			 * 執行已實作的 onClick 方法
			 */
			clickListener.onClick(this);
		}
	}

	/*
	 * getter and setter
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setClickListener(ClickListiner listener) {
		this.clickListener = listener;
	}

	public int getButtonClickType() {
		return buttonClickType;
	}

}

/*
 * 主類別
 */
public class TestBottonClickActivity implements ClickListiner {

	public static void main(String[] s) {
		/*
		 * 看題目似乎是由別處呼叫此類別,這裡直接呼叫它,
		 * 
		 */
		TestBottonClickActivity test=new TestBottonClickActivity();
		test.onCreate();
		
		/*
		 * 模擬在Button上按下按鍵
		 */
		View.btn_1.clickLeft();
		View.btn_1.clickRight();
		
		System.out.println("\nbutton2 沒有實作 onClick...");
		View.btn_2.clickLeft();
	}

	
	public void onCreate() {
		Button button = findViewById(1);

		button.setClickListener(this);

		// 取消以下註解,以匿名類別註冊Listener
		/* (前面加一條反斜線即可取消註解)
		button.setClickListener(new ClickListiner() {
			@Override
			public void onClick(View v) {
				Button button = (Button) v;
				System.out.println(button.getName() + ":(匿名類別)按下哪個鍵 =" + button.getButtonClickType());
			}
		});
		//*/
	}

	/*
	 * 模擬 find View
	 */
	public Button findViewById(int id) {
		switch (id) {
		case 1:
			return View.btn_1;
		case 2:
			return View.btn_2;
		default:
			throw new RuntimeException("not match");
		}
	}

	@Override
	public void onClick(View v) {
		Button button = (Button) v;
		System.out.println(button.getName() + ":按下哪個鍵= " + button.getButtonClickType());
	}

}
