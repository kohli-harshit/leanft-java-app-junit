package SwingSetAutomation;

import com.hp.lft.sdk.*;
import com.hp.lft.sdk.java.*;

public class SwingSet {
	String appPath = "C:\\Users\\leanft\\Desktop\\SwingSet2.jar";
	public SwingAppModel OR;
	
	public SwingSet() throws GeneralLeanFtException
	{
		OR = new SwingAppModel();
	}
	
	public Boolean launchApp()
	{
		try
		{
			//Launch the App through Java			
			//new ProcessBuilder("java","-jar",appPath).start();
			ProcessBuilder PB = new ProcessBuilder("java","-jar",appPath);
			PB.start();
			return appExistsorNot();
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	
	public void closeAllInstances() throws GeneralLeanFtException	
	{
		try
		{
			Window mainWindow = Desktop.describe(Window.class, new WindowDescription.Builder()
			.title("SwingSet2").index(0).build());
			
			while(mainWindow.exists(1))
			{
				mainWindow.close();
			}
		}
		catch(Exception e)
		{
			//Do nothing
		}		
	}
	
	public Boolean appExistsorNot() throws GeneralLeanFtException
	{
		Boolean appExists = false;

		//Check if Main Window Exists or not
		if(OR.MainWindow().exists(5))
		{
			OR.MainWindow().maximize();
			appExists=true;
		}
		else{
			appExists=false;
		}
		return appExists;
	}
	
	
	
}
