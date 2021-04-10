package com.test.mainview.behavior;

import java.awt.Panel;

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
		JPanel canvas_main_panel = (JPanel) session.getAttribute("canvas_main_panel");
		BorderFixer<JPanel> center_canvas_fixer = (BorderFixer<JPanel>) session.getAttribute("center_canvas_fixer");

		
		center_canvas_fixer.reset();
		
		Direction d = BorderFixers.calcCenterSize(center_canvas_fixer, canvas_box_panel.getWidth(),
				canvas_box_panel.getHeight());
		Direction fixSize = BorderFixers.getFixedAdvice(12, 18, d.getWidth(), d.getHeight());
		BorderFixers.fixAsCenter(center_canvas_fixer, fixSize);
		canvas_box_panel.revalidate();
		



	}

}
