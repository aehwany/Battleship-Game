import acm.graphics.*;

import java.applet.Applet;
import java.awt.*;
import acm.gui.*;
import acm.program.*;
import javax.swing.*;
import java.awt.BorderLayout;
public class ship {
	public int ship_len;
	public GRect[]array;
	public Boolean sunk=false;
	public ship(int i){
	 array=new GRect[i];
	 ship_len=i;
	}
	public Boolean full()
	{
		for(int i=0;i<ship_len;i++)
		{
			if(array[i].getColor()!=Color.RED){return false;}
			
		}
		return true;
	}
}
