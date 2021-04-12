package com.main.control.manager;

public class AppManager {
	private static AppManager appManager=new AppManager();
	
	
	private AppManager() {
		this.initialize();
	}
	
	public static AppManager getDefaultManager() {
		return appManager;
	}
	private  void initialize(){
		SignManager managerMainSign=SignManager.getManager(SignManager.SignType.MAINSIGN);
		managerMainSign.createNewSign(MainSignGetter.GetterMainSginType.SIGNS);
		managerMainSign.createNewSign(MainSignGetter.GetterMainSginType.SIGNZ);
		managerMainSign.createNewSign(MainSignGetter.GetterMainSginType.SIGNT);
		managerMainSign.createNewSign(MainSignGetter.GetterMainSginType.SIGNL);
		managerMainSign.createNewSign(MainSignGetter.GetterMainSginType.SIGNJ);
		managerMainSign.createNewSign(MainSignGetter.GetterMainSginType.SIGNO);
		managerMainSign.createNewSign(MainSignGetter.GetterMainSginType.SIGNI);
		
		SignManager managerObstacleSign = SignManager.getManager(SignManager.SignType.OBSTACLE);
		managerObstacleSign.createNewSign(ObstacleSignGetter.GetterObstacleSignType.WALL);
	}
}
