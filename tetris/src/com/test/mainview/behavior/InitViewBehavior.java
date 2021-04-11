package com.test.mainview.behavior;

import javax.swing.JPanel;

import com.test.mainview.TestView;
import com.tool.Session;
import com.tool.behavior.Behavior;
import com.tool.behavior.BorderFixer;
import com.tool.behavior.BorderFixers;
import com.tool.direction.Direction;

public class InitViewBehavior extends Behavior {

	@Override
	public void run() {
		Session session = TestView.getSession();
		JPanel canvas_box_panel = (JPanel) session.getAttribute("canvas_box_panel");
		JPanel canvas_mainPanel = (JPanel) session.getAttribute("canvas_mainPanel");
		BorderFixer<JPanel> center_canvas_fixer = (BorderFixer<JPanel>) session.getAttribute("center_canvas_fixer");

		//初始化 BorderLayout 內 n、e、w、s四元件長度
		center_canvas_fixer.reset();
		
		//canvas_box_panel.revalidate();此行不起作用...
		
		/*
		 * 因為reset後 center 差不多等於其父元件的長度,
		 * 所以先不做正確的計算,而且可能還不好計算...
		 */
		
		Direction d = BorderFixers.getCenterSize(center_canvas_fixer, canvas_box_panel.getWidth(),
				canvas_box_panel.getHeight());
		
		Direction fixSize = BorderFixers.getFixedAdvice(12, 18, d);
		
		/*
		 * 取消註解下式(並註解上式),使用canvas_mainPanel 的width、height 會因為該元件(center)沒即時反應,取值不到該元件的長度,產生不預期的結果
		 * 按照設計它應該要反應 center_canvas_fixer.reset(),center 要重設長度才對,
		 * 但實際上它沒有反應重設結果,而是取得上一次呼叫this.run 後,center 才有反應的結果 
		 */
		//Direction fixSize = BorderFixers.getFixedAdvice(12, 18, canvas_mainPanel.getWidth(),canvas_mainPanel.getHeight());
		
		BorderFixers.fixAsCenter(center_canvas_fixer, fixSize);
		
		
		/*
		 * 此行為簡化用法
		 */
		//BorderFixers.fixAsCenter(center_canvas_fixer, 12,18,canvas_box_panel.getWidth(),canvas_box_panel.getHeight());
		
		
		
		canvas_box_panel.revalidate();
		



	}

}
