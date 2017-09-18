import java.awt.Color;

import javax.swing.JButton;





public class ship_type {
	public int ship_len;
	public JButton[]array;
	public Boolean sunk=false;
	
	public ship_type(int i){
		 array=new JButton[i];
		 ship_len=i;
		}
	
	
	public Boolean full()
	{
		for(int i=0;i<ship_len;i++)
		{
			if(array[i].getBackground()!=Color.RED){return false;}
			
		}
		return true;
	}
}
