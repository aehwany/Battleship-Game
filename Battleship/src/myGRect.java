
import acm.graphics.*;

import java.applet.Applet;
import java.awt.*;
import acm.gui.*;
import acm.program.*;
import javax.swing.*;
import java.awt.BorderLayout;

public class myGRect extends GraphicsProgram {
public GRect square;
public boolean marked;
public ship ship;

public myGRect(double x, double y, double w, double h)
{
	square=new GRect(x, y, w, h);
	marked=false;
	ship=null;
}
public void init()
{
	myGRect a=new myGRect(0,0,100,100);
	add(a);
}

}

