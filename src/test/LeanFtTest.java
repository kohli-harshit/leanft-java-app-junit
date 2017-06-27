package test;

import static org.junit.Assert.*;

import java.awt.image.*;
import java.awt.Dimension;
import java.awt.PageAttributes.OriginType;
import java.awt.Robot;
import java.io.File;

import javax.imageio.*;
import javax.swing.tree.TreeNode;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.hp.lft.sdk.*;
import com.hp.lft.sdk.insight.*;
import com.hp.lft.sdk.java.*;
import com.hp.lft.sdk.NativeObject;

import unittesting.*;
import SwingSetAutomation.*;
import Util.LeanFTJavaTreeHelper;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LeanFtTest extends UnitTestClassBase {

	private static SwingSet mySet;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		instance = new LeanFtTest();
		globalSetup(LeanFtTest.class);
		
		//Close all Instances and Launch a new instance
		mySet = new SwingSet();
		mySet.closeAllInstances();
		mySet.launchApp();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		mySet.closeAllInstances();
		globalTearDown();		
	}

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	//This example demonstrates how to find child objects of a Window
	//using Descriptive Programming
	@Test
	public void test1_addFrames() throws GeneralLeanFtException{
		try
		{				
			
			System.out.println("Test 1 - Check Add Frames Started");
			
			//App should exist 
			assertTrue(mySet.appExistsorNot());		
			Window mainWindow = Desktop.describe(Window.class, new WindowDescription.Builder()
			.title("SwingSet2").index(0).build());

			//Find all Internal Frames having native class as JInternalFrame 
			InternalFrame[] children =  mainWindow.findChildren(InternalFrame.class, new InternalFrameDescription.Builder()
			.nativeClass("javax.swing.JInternalFrame").build());
			
			int originalLength = children.length;
			
			//Assert that array should have some values
			assertTrue("No. of Frames Found = " + originalLength,originalLength>0);
			
			//Highlight each Frame
			for(InternalFrame child : children)
			{
				child.highlight();
			}
			
			//Add a new Frame with Frame Generator
			mainWindow.describe(InternalFrame.class, new InternalFrameDescription.Builder()
			.title("Internal Frame Generator").nativeClass("javax.swing.JInternalFrame").index(0).build())
			.describe(Button.class, new ButtonDescription.Builder()
			.label("moon_small").build()).click();
			
			//Get Child count again
			children =  mainWindow.findChildren(InternalFrame.class, new InternalFrameDescription.Builder()
			.nativeClass("javax.swing.JInternalFrame").build());
			
			int newLength = children.length;
			
			//Verify that count increased by 1
			assertEquals(newLength,originalLength+1);
			
			InternalFrame newFrame = mainWindow.describe(InternalFrame.class, new InternalFrameDescription.Builder()
			.nativeClass("javax.swing.JInternalFrame").title("Frame " + (newLength-2) + "  ").build());
			
			//Verify that new Frame is Maximizable
			assertTrue(newFrame.isMaximizable());
			
			//Verify that new Frame is Resizable
			assertTrue(newFrame.isResizable());
			
			//Uncheck the Maximizable checkbox
			mainWindow.describe(InternalFrame.class, new InternalFrameDescription.Builder()
			.title("Internal Frame Generator").index(0).build()).describe(CheckBox.class, new CheckBoxDescription.Builder()
			.attachedText("Maximizable").build()).click();
			

			//Uncheck the Resizable checkbox
			mainWindow.describe(InternalFrame.class, new InternalFrameDescription.Builder()
			.title("Internal Frame Generator").index(0).build()).describe(CheckBox.class, new CheckBoxDescription.Builder()
			.attachedText("Resizable").build()).click();
			
			//Add a new Frame with Frame Generator
			mainWindow.describe(InternalFrame.class, new InternalFrameDescription.Builder()
			.title("Internal Frame Generator").nativeClass("javax.swing.JInternalFrame").index(0).build())
			.describe(Button.class, new ButtonDescription.Builder()
			.label("moon_small").build()).click();
			
			//Get Child count again
			children =  mainWindow.findChildren(InternalFrame.class, new InternalFrameDescription.Builder()
			.nativeClass("javax.swing.JInternalFrame").build());
			
			int newerLength = children.length;
						
			//Verify that count increased by 1
			assertEquals(newerLength,newLength+1);
			
			InternalFrame newerFrame = mainWindow.describe(InternalFrame.class, new InternalFrameDescription.Builder()
			.nativeClass("javax.swing.JInternalFrame").title("Frame " + (newerLength-2) + "  ").build());
			
			
			//Verify that new Frame is not Maximizable
			assertFalse(newerFrame.isMaximizable());
			
			//Verify that new Frame is not Resizable
			assertFalse(newerFrame.isResizable());
			
		}
		catch(Exception e)
		{
			System.out.println("Unexpected Error Occurred. Message = " + e.getMessage());
			assertTrue(false);
		}
		finally
		{
			System.out.println("Test 1 - Check Add Frames Finished");
		}
		
	}
			
	//This example demonstrates the automation of Menu options 
	//using Descriptive Programming 
	@Test
	public void test2_changeTheme() throws GeneralLeanFtException {
		
		
		try
		{
			System.out.println("Test 2 - Check Change Theme Started");
			
			//App should exist 
			assertTrue(mySet.appExistsorNot());		
			Window mainWindow = Desktop.describe(Window.class, new WindowDescription.Builder()
			.title("SwingSet2").index(0).build());

			// Set the nativeClass value for the Description object.
			UiObject desktop = mainWindow.describe(UiObject.class, new UiObjectDescription.Builder().nativeClass("javax.swing.JDesktopPane").build());

			// Verify that the item is not selected. (When selected, the background color is Aqua, 0x80c0c0 in hexadecimal.)
			assertNotEquals("80c0c0", desktop.getBackgroundColor());

			// Create a description for the top-level menu item: Themes
			Menu themesMenu = mainWindow.describe(Menu.class, new MenuDescription.Builder().label("Themes").build());

			// Verify that the themesMenu has the expected 9 sub-menus.
			assertEquals(9, themesMenu.getSubMenus().size());

			// *** Various ways to select a sub-menu ***
			// 1) Use the SelectSubMenu method to accept the path of the sub-menu and select the item.
			//    Separate multi-level path elements with a semicolon (;).
			themesMenu.selectSubMenu("Aqua");

			// 2) Use the GetSubMenu method to return a menu test object representing the sub-menu item, which is then selected.
			themesMenu.getSubMenu("Aqua").select();

			// 3) Use the SubMenus property to return a menu test object representing the sub-menu item, which is then selected.
			//    (Applicable only when selecting a direct child of the parent menu.)
			Menu aquaSubMenu = themesMenu.getSubMenus().get(4);
			assertEquals("Aqua", aquaSubMenu.getLabel()); //to verify we have taken the correct menu from the SubMenus collection.
			aquaSubMenu.select();

			// Check that the item is selected by verifying that the background color is Aqua.
			assertEquals("80c0c0", desktop.getBackgroundColor());

		}
		catch(Exception e)
		{
			System.out.println("Unexpected Error Occurred. Message = " + e.getMessage());
			assertTrue(false);
		}
		finally
		{
			System.out.println("Test 2 - Check Change Theme Finished");
		}
		
		
	}

	//This example demonstrates how we can work upon buttons,radio buttons and checkboxes
	// Also uses Robot class to move cursor
	//using Application Models	
	@Test
	public void test3_checkButtons() throws GeneralLeanFtException{
		
		try{
			
			System.out.println("Test 3 - Check Buttons Started");
			
			//Check that the Main window should exist
			assertTrue(mySet.appExistsorNot());			
			
			//Toolbar Object
			ToolBar toolbar = mySet.OR.MainWindow().ToolBar(); 

			// Clicking the JButton displays the required frame
			toolbar.getButton("JButton").press();
			
			//Getting the Image Buttons in the Panel
			Button[] imgButtons = mySet.OR.MainWindow().ImageButtonsPanelUiObject()
			  .findChildren(Button.class,new ButtonDescription.Builder().nativeClass("javax.swing.JButton").build());
			  
			//There should be 3 Image Buttons
			assertEquals(3, imgButtons.length);
			
			//Trigger the Mouse Entered Event for the each button in the Panel				
			Robot robo = new Robot();			
			for(Button btn : imgButtons)
			{				
				robo.mouseMove(btn.getAbsoluteLocation().x,btn.getAbsoluteLocation().y);
				Thread.sleep(500);
			}
			
			//Store the Original Dimension
			Dimension orginalDimension = mySet.OR.MainWindow().ImageButtonsPanelUiObject().getSize();
			
			//Increase the Size by Selecting the Pad Radio Button
			mySet.OR.MainWindow().Pad10RadioButton().click();
			
			//Verify that the radio button is checked
			assertTrue(mySet.OR.MainWindow().Pad10RadioButton().isChecked()==true);
			
			//Store the New Dimension
			Dimension newDimension = mySet.OR.MainWindow().ImageButtonsPanelUiObject().getSize();
			
			//Assert that Height Increases and Width Decreases
			assertTrue((newDimension.width < orginalDimension.width) && (newDimension.height > orginalDimension.height));
			
			//Click the Enabled Check box
			mySet.OR.MainWindow().EnabledCheckBox().click();
			
			//Check that the Check Box has been unchecked 
			assertTrue(mySet.OR.MainWindow().EnabledCheckBox().getState()==CheckedState.UNCHECKED);
			
			//Check that all Image Buttons are disabled
			for(Button btn : imgButtons)
			{
				assertTrue(btn.isEnabled()==false);				
			}
			
		}		
		catch(Exception e)
		{
			System.out.println("Unexpected Error Occurred. Message = " + e.getMessage() + "Stack Trace = ");
			e.printStackTrace();
			assertTrue(false);
		}
		finally
		{
			System.out.println("Test 3 - Check Buttons Finished");
		}
		
	}
	
	//This example demonstrates how the we can use
	// Insight Object Identification and VRI
	@Test
	public void test4_verifyImage_VRI() throws GeneralLeanFtException {
				
		try
		{
			System.out.println("Test 4 - Verify Insight & VRI Started");
			
			//App should exist 
			assertTrue(mySet.appExistsorNot());		
			Window mainWindow = Desktop.describe(Window.class, new WindowDescription.Builder()
			.title("SwingSet2").index(0).build());

			// Create a description for the top-level menu item: File
			Menu fileMenu = mainWindow.describe(Menu.class, new MenuDescription.Builder().label("File").build());

			//Select About Menu option
			fileMenu.selectSubMenu("About");
			
			Dialog about = mainWindow.describe(Dialog.class, new DialogDescription.Builder()
			.title("About Swing!").build());
			
			//Access the Swing Image in the Resources folder
			//ClassLoader classLoader = getClass().getClassLoader();
			//File imgFile = new File(classLoader.getResource("SwingImage.PNG").getFile());
			File imgFile = new File("resources\\SwingImage.PNG");
	        RenderedImage image = ImageIO.read(imgFile);
	        
	        //Create Insight Object with 100% similarity
	        InsightObject swingImage = about.describe(InsightObject.class, new InsightDescription(image,90));
	        
	        //Highlight the Object if present
	        assertTrue(swingImage.exists(5));
	        swingImage.highlight();
	        
	        //Close the Dialog
	        about.close();
	        
	        ToolBar toolbar = mainWindow.describe(ToolBar.class, new ToolBarDescription.Builder()
			.nativeClass("SwingSet2$ToggleButtonToolBar").build());
	        
	        toolbar.getButton("JDesktop").press();
	        
			
	        //Maximizable checkbox in the Internal Frame
	        CheckBox maximizable = mainWindow.describe(InternalFrame.class, new InternalFrameDescription.Builder()
			.title("Internal Frame Generator").index(0).build()).describe(CheckBox.class, new CheckBoxDescription.Builder()
			.attachedText("Maximizable").build());
	        
	        //Create Visual Relation Object and Set the Relation with the Test Object
	        VisualRelation vr = new VisualRelation();
	        vr.setTestObject(maximizable);
	        vr.setVerticalRelation(VerticalVisualRelation.BELOW_AND_INLINE);
	        
	        //Create Description for Checkbox which is above the Maximizable checkbox
	        CheckBox above = mainWindow.describe(InternalFrame.class, new InternalFrameDescription.Builder()
			.label("Internal Frame Generator").build()).describe(CheckBox.class, new CheckBoxDescription.Builder()
			.nativeClass("javax.swing.JCheckBox").vri(vr).build());
	        
	        //Check for Existence
	        assertTrue("Checkbox above Maximizable should exist",above.exists(5));	        
	        above.highlight();
	        
	        //Get name of the Checkbox
	        System.out.println("Name of the Checkbox above Maximizable CheckBox = " + above.getAttachedText());

		}
		catch(Exception e)
		{
			System.out.println("Unexpected Error Occurred. Message = " + e.getMessage());
			assertTrue(false);
		}
		finally
		{
			System.out.println("Test 4 - Verify Insight & VRI finished");
		}
		
	}
	
	//This example demonstrates how the Toolbar can be used 
	// as well as getting the text of the current window
	// using Application Models
	@Test
	public void test5_verifyEditor() throws GeneralLeanFtException {
		
		try
		{
			System.out.println("Test 5 - Verify Editor Started");
			
			//Check that the Main window should exist
			assertTrue(mySet.appExistsorNot());			

			//Toolbar Object
			ToolBar toolbar = mySet.OR.MainWindow().ToolBar(); 

			// Clicking the JEditorPane button displays the Editor test object.
			toolbar.getButton("JEditorPane").press();

			// Create a description for the Editor test object.
			Editor edit = mySet.OR.MainWindow().JEditor();

			// Verify that the editor in this AUT is read-only HTML with links.
			assertTrue(edit.isReadOnly());

			// Click the link to king.html.
			edit.clickLink("king.html");

			// Verify that the correct page loaded by checking the text.        
			String expectedTextPrefix = "Do here most humbly lay this small Present";
			String text = edit.getText().trim().replaceAll("[\n\r]", "").replaceAll(Character.toString((char)160), "");

			System.out.println("Text fetched - " + text);        

			assertTrue(text.startsWith(expectedTextPrefix));
		}
		catch(Exception e)
		{
			System.out.println("Unexpected Error Occurred. Message = " + e.getMessage());
			assertTrue(false);
		}
		finally
		{
			System.out.println("Test 5 - Verify Editor finished");	
		}
	}
	
	//This example demonstrates how to work with Java Trees	
	@Test
	public void test6_OpenTree() throws GeneralLeanFtException{
		try
		{				
			System.out.println("Test 6 - Open Trees Started");
			
			//Main Window
			Window mainWindow = Desktop.describe(Window.class, new WindowDescription.Builder()
			.title("SwingSet2").index(0).build());
			Thread.sleep(2000);
			
			//Toolbar Object
			ToolBar toolbar = mySet.OR.MainWindow().ToolBar(); 

			// Clicking the JTree displays the required frame
			toolbar.getButton("JTree").press();
			
			//Tree View Object
			TreeView javaTree = mainWindow.describe(TreeView.class, new TreeViewDescription.Builder()
			.nativeClass("TreeDemo$1").build());
			
			//Expand a Node
			String nodePath = "Music;Classical;Beethoven;concertos";
			TreeViewNode requiredNode = javaTree.getNode(nodePath);
			requiredNode.expand();

			//Select a Node
			nodePath = "Music;Classical;Beethoven;concertos;No. 4 - G Major";			
			javaTree.activateNode(nodePath);				
			
			//Get Number of Sub Nodes of a Node
			nodePath = "Music;Classical;Beethoven;Quartets";			
			System.out.println("Child Count - " + LeanFTJavaTreeHelper.getSubNodeCount(javaTree, nodePath));

			//Get All Sub Nodes
			java.util.List<String> subNodes = LeanFTJavaTreeHelper.getAllSubNodes(javaTree,nodePath);
			
			//Search for a Node in the Whole Tree
			NativeObject root = LeanFTJavaTreeHelper.getRoot(javaTree);
			assertTrue(LeanFTJavaTreeHelper.searchNodeinTree(javaTree,root, "No. 5 - E-Flat"));
						
		}		
		catch(Exception e)
		{
			System.out.println("Unexpected Error Occurred. Message = " + e.getMessage());
			assertTrue(false);
		}
		finally
		{
			System.out.println("Test 6 - Open Trees finished");	
		}
	}
	
}


 