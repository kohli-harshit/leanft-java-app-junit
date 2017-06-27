package Util;

import com.hp.lft.sdk.*;
import com.hp.lft.sdk.insight.*;
import com.hp.lft.sdk.java.*;
import com.hp.lft.sdk.NativeObject;

public class LeanFTJavaTreeHelper {
	
	public static void printAllMembers(TreeView javaTree) throws GeneralLeanFtException
	{
		//To Get all the Properties and Methods of the Native Object
		NativeObject obj = javaTree.getNativeObject();
		java.util.List<String> members = obj.getMembers();			
		for (String  s : members  ) {
            System.out.println(s);
        }
	}	
	
	public static int getSubNodeCount(TreeView javaTree,String nodePath) throws GeneralLeanFtException
	{
		// From - https://docs.oracle.com/javase/7/docs/api/javax/swing/JTree.html
		
		//Get the Native Java Object
		NativeObject obj = javaTree.getNativeObject();

		//Get the Tree Model Instance
		NativeObject model = obj.getProperty("treeModel", NativeObject.class);

		//Select the given Node
		javaTree.activateNode(nodePath);

		//Get the Selected Node		
		NativeObject parent = obj.invokeMethod("getLastSelectedPathComponent", NativeObject.class);
		
		//Fetch the Child Count for the Selected Node		
		return Integer.parseInt(model.invokeMethod("getChildCount", String.class, parent ));
	}	
	
	
	public static java.util.List<String> getAllSubNodes(TreeView javaTree,String nodePath) throws GeneralLeanFtException
	{
		//Get the Native Java Object
		NativeObject obj = javaTree.getNativeObject();
		
		//Get the Tree Model Instance
		NativeObject model = obj.getProperty("treeModel", NativeObject.class);
		
		//Select the given Node
		javaTree.activateNode(nodePath);
		
		//Get the Selected Node		
		NativeObject parent = obj.invokeMethod("getLastSelectedPathComponent", NativeObject.class);
		
		//Fetch the Child Count for the Selected Node		
		int childCount =  Integer.parseInt(model.invokeMethod("getChildCount", String.class, parent ));
		
		java.util.List<String> subNodes = new java.util.ArrayList<String>();
		
		for(int i=0;i<=childCount-1;i++)
		{
			NativeObject node = model.invokeMethod("getChild", NativeObject.class, parent,i );			
			NativeObject userObj = node.invokeMethod("getUserObject",NativeObject.class);
			System.out.println("Node Name - " + userObj.invokeMethod("toString", String.class ));
			subNodes.add(userObj.invokeMethod("toString", String.class ));
		}
		
		return subNodes;
	}
	
	public static NativeObject getRoot(TreeView javaTree) throws GeneralLeanFtException
	{
		//Get the Native Java Object
		NativeObject obj = javaTree.getNativeObject();
		
		//Get the Tree Model Instance
		NativeObject model = obj.getProperty("treeModel", NativeObject.class);
		
		return model.invokeMethod("getRoot", NativeObject.class);
	}
	
	/*
	public static Boolean searchNodeinTree(TreeView javaTree,NativeObject root,String nodeName) throws GeneralLeanFtException
	{
		
		//Get the Native Java Object
		NativeObject obj = javaTree.getNativeObject();
		
		//Get the Tree Model Instance
		NativeObject model = obj.getProperty("treeModel", NativeObject.class);

		//Iterate Through the Root Elements Recursively
		int rootCount = Integer.parseInt(model.invokeMethod("getChildCount", String.class, root ));		
		for(int rootCounter=0;rootCounter<rootCount;rootCounter++)
		{
			NativeObject node = model.invokeMethod("getChild", NativeObject.class, root,rootCounter );
			String name = node.invokeMethod("getUserObject",NativeObject.class).invokeMethod("toString", String.class );
			if(name.contains(nodeName))
			{				
				return true;
			}
			else
			{
				String isLeaf = model.invokeMethod("isLeaf", String.class, node);
				if(isLeaf.toLowerCase().equals("false"))
				{
					return searchNodeinTree(javaTree,node,nodeName);
				}				
			}			
		}		

		return false;
	}
	*/
	
	public static Boolean searchNodeinTree(TreeView javaTree,NativeObject root,String nodeName) throws GeneralLeanFtException
	{		
		//Get the Native Java Object
		NativeObject obj = javaTree.getNativeObject();
		
		//Get the Tree Model Instance
		NativeObject model = obj.getProperty("treeModel", NativeObject.class);

		//Iterate Through the Root Elements Recursively and Check for the Required Node Name
		String name = root.invokeMethod("getUserObject",NativeObject.class).invokeMethod("toString", String.class );
		if(name.contains(nodeName))
		{	
			System.out.println("Node Search Successfull");
			return true;
		}
		else
		{
			System.out.println("Node Name fetched - " + name);
			String isLeaf = model.invokeMethod("isLeaf", String.class, root);
			if(isLeaf.toLowerCase().equals("false"))
			{
				int rootCount = Integer.parseInt(model.invokeMethod("getChildCount", String.class, root ));
				for(int rootCounter=0;rootCounter<rootCount;rootCounter++)
				{
					NativeObject node = model.invokeMethod("getChild", NativeObject.class, root,rootCounter );				
					if(searchNodeinTree(javaTree,node,nodeName))
					{
						return true;
					}
				}
			}				
		}			
		
		return false;
	}
}
