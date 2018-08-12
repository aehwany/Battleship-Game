
import acm.graphics.*;
import java.util.Random;

import java.applet.Applet;
import java.awt.*;
import acm.gui.*;
import acm.program.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;


public class main extends GraphicsProgram implements MouseListener {
	
int x=0;
int y=0;
int phase=0;	//phase 0: pregame  phase 1:game
int ship=0;
int algr=0;
int side=0;
int[]sunk=new int[5];
boolean win=false;
myGRect[][] myboard=new myGRect[10][10];
myGRect[][] opponent=new myGRect[10][10];
//ships
GRect[] Carrier=new GRect[5];
GRect[] Carrier2=new GRect[5];
GRect[] Battleship=new GRect[4];
GRect[] Battleship2=new GRect[4];
GRect[] Frigate=new GRect[3];
GRect[] Frigate2=new GRect[3];
GRect[] Submarine=new GRect[3];
GRect[] Submarine2=new GRect[3];
GRect[] Minesweeper=new GRect[2];
GRect[] Minesweeper2=new GRect[2];
GImage Carrier_ver;
GImage Battleship_ver;
GImage Frigate_ver;
GImage Submarine_ver;
GImage Minesweeper_ver;
GImage Carrier_hor;
GImage Battleship_hor;
GImage Frigate_hor;
GImage Submarine_hor;
GImage Minesweeper_hor;
//JButton beep = new JButton("Horizontal");
GLabel label=new GLabel("Horizontal", 580, 550);

GLabel info=new GLabel("put Carrier", 550, 300);

	public void init() {
		GRect beep=new GRect(560,525,120,40);
		label.setFont(new Font("Arial", Font.PLAIN, 20));
		GLabel title = new GLabel("Battleship", 550, 40);
		title.setFont(new Font("Arial", Font.PLAIN, 40));
		add(title);
		info.setFont(new Font("Arial", Font.ITALIC, 20));
		info.setColor(Color.RED);
		add(info);
		add(label);
		BufferedImage img = null;
		BufferedImage img_hor = null;
		try {
		    img = ImageIO.read(new File("/Users/ahmedelehwany/Desktop/Battleship/ship_ver.png"));
		    img_hor = ImageIO.read(new File("/Users/ahmedelehwany/Desktop/Battleship/ship_hor.png"));
		} catch (IOException e) {
		}
		if(img!=null){
		//JLabel pic = new JLabel(new ImageIcon(img));
		//pic.setBounds(0,0, 50,50);
			Carrier_ver=new GImage(img);
			Frigate_ver=new GImage(img);
			Submarine_ver=new GImage(img);
			Battleship_ver=new GImage(img);
			Minesweeper_ver=new GImage(img);
			Carrier_hor=new GImage(img_hor);
			Frigate_hor=new GImage(img_hor);
			Submarine_hor=new GImage(img_hor);
			Battleship_hor=new GImage(img_hor);
			Minesweeper_hor=new GImage(img_hor);
		//pic.setBounds(0,0,50,250);
		//add(pic);
		//GRect ex=new GRect(100,100);
		//ex.setBounds(200,200,100,100);
		//add(ex);
		
		
		 //myboard[0]=new GRect(100,100);
		 
		//myboard[0].setBounds(100,100,100,100);
		//add(myboard[0]);
		//myboard[0][1].setColor(Color.GRAY);
		//myboard[0][1].setFilled(true);
		for(int j=0; j<10;j++)
		{
			//System.out.println(j);
			for(int i=0;i<10;i++)
			{
				if((i+j)%2==0){
				myboard[j][i]=new myGRect(50*i,50*j,50,50);
				myboard[j][i].square.setColor(Color.GRAY);
				}
				else
				{
					myboard[j][i]=new myGRect(50*i, 50*j, 50, 50);
					myboard[j][i].square.setColor(Color.LIGHT_GRAY);
				}
				myboard[j][i].square.setFilled(true);
				add(myboard[j][i].square);
			}
		}
		//myboard[5][5].setColor(Color.RED);
		for(int j=0; j<10;j++)
		{
			//System.out.println(j);
			for(int i=0;i<10;i++)
			{
				if((i+j)%2==0){
				opponent[j][i]=new myGRect(750+50*i, 50*j,50,50);
				opponent[j][i].square.setColor(Color.GRAY);
				}
				else
				{
					opponent[j][i]=new myGRect(750+50*i, 50*j, 50, 50);
					opponent[j][i].square.setColor(Color.LIGHT_GRAY);
				}
				opponent[j][i].square.setFilled(true);
				add(opponent[j][i].square);
			}
			
		}
		 addMouseListeners(); 
		 setLayout(null);
		 //setLayout(new FlowLayout());
		 
		 //beep.setBounds(550,550,150,50);
/////		 beep.setFont(new Font("Arial", Font.PLAIN, 20));
		 add(beep);
		 //add(pic);
		}	 
	}
	
	
	public void  mouseClicked(MouseEvent e)
	{
		x=e.getX();
		y=e.getY();
		if(x>560 && x<680 &&y>525 && y<565)
		{
			System.out.println("out");
			if(label.getLabel()=="Horizontal"){label.setLabel("Vertical");}
			else{label.setLabel("Horizontal");}
		}
		//System.out.println(x+" "+y);
		if(phase==0)
		{
			if(x<=500)
		    {
			x=e.getX();
		    y=e.getY();
		    x=x/50;
		    y=y/50;
		    String dir=label.getLabel();
		    
				switch (ship)
				{
					case 0:
						if(dir=="Horizontal")
						{
							for(int i=0;i<5;i++)
							{
								//myboard[y][x+i].square.setColor(Color.YELLOW);
								myboard[y][x+i].marked=true;	
								Carrier[i]=myboard[y][x+i].square;
							}
							
							Carrier_hor.setBounds(x*50,y*50-75, 50,50 );
							Carrier_hor.setSize(250, 200);
							add(Carrier_hor);
						}
						else
						{
							//System.out.println(x+","+y+" "+(x*50-75)+","+y*50);
							for(int i=0;i<5;i++)
							{
								
							//myboard[y+i][x].square.setColor(Color.YELLOW);
							myboard[y+i][x].marked=true;
							Carrier[i]=myboard[y+i][x].square;
							}
							Carrier_ver.setBounds(x*50-75,y*50,200,5*50);
							add(Carrier_ver);
						}
						info.setLabel("put Battleship");
						ship=1;
						break;
					
					case 1:
						if(dir=="Horizontal")
						{
							for(int i=0;i<4;i++)
							{
								//myboard[y][x+i].square.setColor(Color.YELLOW);
								myboard[y][x+i].marked=true;
								Battleship[i]=myboard[y][x+i].square;
							}
							Battleship_hor.setBounds(x*50,y*50-50,4*50,150);
							add(Battleship_hor);
						}
						else
						{
							for(int i=0;i<4;i++)
							{
							//myboard[y+i][x].square.setColor(Color.YELLOW);
							myboard[y+i][x].marked=true;
							Battleship[i]=myboard[y+i][x].square;
							}
							Battleship_ver.setBounds(x*50-50,y*50,150,4*50);
							add(Battleship_ver);
						}
						info.setLabel("put Frigate");
						ship=2;
						break;
					case 2:
						if(dir=="Horizontal")
						{
							for(int i=0;i<3;i++)
							{
								//myboard[y][x+i].square.setColor(Color.YELLOW);
								myboard[y][x+i].marked=true;
								Frigate[i]=myboard[y][x+i].square;
							}
							Frigate_hor.setBounds(x*50,y*50-35,3*50,120);
							add(Frigate_hor);
						}
						else
						{
							for(int i=0;i<3;i++)
							{
							//myboard[y+i][x].square.setColor(Color.YELLOW);
							myboard[y+i][x].marked=true;
							Frigate[i]=myboard[y+i][x].square;
							}
							Frigate_ver.setBounds(x*50-35,y*50,120,3*50);
							add(Frigate_ver);
						}
						info.setLabel("put Submarine");
						ship=3;
						break;
					case 3:
						if(dir=="Horizontal")
						{
							for(int i=0;i<3;i++)
							{
								//myboard[y][x+i].square.setColor(Color.YELLOW);
								myboard[y][x+i].marked=true;
								Submarine[i]=myboard[y][x+i].square;
							}
							Submarine_hor.setBounds(x*50,y*50-35,3*50,120);
							add(Submarine_hor);
						}
						else
						{
							for(int i=0;i<3;i++)
							{
							//myboard[y+i][x].square.setColor(Color.YELLOW);
							myboard[y+i][x].marked=true;
							Submarine[i]=myboard[y+i][x].square;
							}
							Submarine_ver.setBounds(x*50-35,y*50,120,3*50);
							add(Submarine_ver);
						}
						info.setLabel("put Minesweeper");
						ship=4;
						break;
					case 4:
						if(dir=="Horizontal")
						{
							for(int i=0;i<2;i++)
							{
								//myboard[y][x+i].square.setColor(Color.YELLOW);
								myboard[y][x+i].marked=true;
								Minesweeper[i]=myboard[y][x+i].square;
							}
							Minesweeper_hor.setBounds(x*50,y*50-25,2*50,100);
							add(Minesweeper_hor);
						}
						else
						{
							for(int i=0;i<2;i++)
							{
							//myboard[y+i][x].square.setColor(Color.YELLOW);
							myboard[y+i][x].marked=true;
							Minesweeper[i]=myboard[y+i][x].square;
							}
							Minesweeper_ver.setBounds(x*50-25,y*50,100,2*50);
							add(Minesweeper_ver);
						}
						
						putship();
						
						
						
						info.setLabel("Your turn");
						phase=1;
						break;
					default:
						
							
				}
		    }
	       
		      
		}
		
	
		else if(phase==1)
		{
			info.setLabel("Your turn");
			x=e.getX();
		    y=e.getY();
		    if(x>=500 && x<=1250)
		    {
			    //System.out.println(opponent[y][x].square.getColor());
			    if(opponent[y/50][(x-750)/50].square.getColor()!=Color.BLUE)
			    {
				  
					    
					    	x=(x-750)/50;
							y=y/50;
							System.out.println("new "+x+" "+y);
							 if( opponent[y][x].marked==true)
							 {
								 opponent[y][x].square.setColor(Color.RED);
								 
								 //turn=1;
								 
							 }
							 else if(opponent[y][x].square.getColor()==Color.BLUE){;}
							 else 
							 {
								 opponent[y][x].square.setColor(Color.BLUE);
								 //turn=1;
								 
							 }
							 if(full("Carrier2")==true)
							 {
								 
								 if(sunk[0]==0)
								 {
									 //System.out.println("sunk 0");
									 info.setLabel("Carrier sunk");
									 sunk[0]=1;
								 }
							 }
							 if(full("Battleship2")==true)
							 {
								
								 if(sunk[1]==0)
								 {
									 //System.out.println("sunk 1");
									 info.setLabel("Battleship sunk");
									 sunk[1]=1;
								 }
							 }
							 if(full("Frigate2")==true)
							 {
								
								 if(sunk[2]==0)
								 {
									 //System.out.println("sunk 2");
									 info.setLabel("Frigate sunk");
									 sunk[2]=1;
								 }
							 }
							 if(full("Submarine2")==true)
							 {
								
								 if(sunk[3]==0)
								 {
									 //System.out.println("sunk 3");
									 info.setLabel("Submarine sunk");
									 sunk[3]=1;
								 }
							 }
							 if(full("Minesweeper2")==true)
							 {
								 
								 if(sunk[4]==0)
								 {
									 //System.out.println("sunk 4");
									 info.setLabel("Minesweeper sunk");
									 sunk[4]=1;
								 }
							 }
							 if((sunk[0]==1)&&(sunk[1]==1)&&(sunk[2]==1)&&(sunk[3]==1)&&(sunk[4]==1)){info.setLabel("YOU WIN");}
							 
							 
							 
					    //}
							 
					if(opponent[y][x].square.getColor()!=Color.RED)
					{
						do{
						do
						{
						Random randx = new Random();
						Random randy = new Random();
						x = randx.nextInt(9);
						y = randy.nextInt(9);
						//System.out.println(x+" "+y);
						}while( (myboard[y][x].square.getColor()==Color.RED) || (myboard[y][x].square.getColor()==Color.BLUE)) ;
						
				    	  if( myboard[y][x].marked==true)
				    	  {
				    		  myboard[y][x].square.setColor(Color.RED);
				    		  
				    	  }
				    	  else
				    	  {
				    		  myboard[y][x].square.setColor(Color.BLUE);
				    	  }
				    	  myboard[y][x].square.setFilled(true);
				    	  add(myboard[y][x]);
				    	  if(loss()==true){info.setLabel("YOU LOSE");}
						}while(myboard[y][x].square.getColor()==Color.RED);
					}
					 }
			    	
			}
		}
		
     }
	public void putship()
	{
		//System.out.println("putship");
		Random randx[]=new Random[5];
		Random randy[]=new Random[5];
		Random randir[]=new Random[5];
		int x[]=new int[5];
		int y[]=new int[5];
		int dir[]=new int[5];
		for(int i=0;i<5;i++)
		{
			randx[i]=new Random();
			randy[i]=new Random();
			randir[i]=new Random();
			dir[i]=randir[i].nextInt(2);
			//System.out.println(dir[i]);
			
			
		}
		//put carrier2 #5
		x[0]=randx[0].nextInt(6);
		y[0]=4+randy[0].nextInt(6);
		
		//System.out.println("putship2");
		
		if(dir[0]==0)	//horizontal
		{
			for(int i=0;i<5;i++)
			{
			opponent[y[0]][x[0]+i].marked=true;
			//opponent[y[0]][x[0]+i].square.setColor(Color.YELLOW);
			Carrier2[i]=opponent[y[0]][x[0]+i].square;
			}
		}
		else		//vertical
		{
			for(int i=0;i<5;i++)
			{
			opponent[y[0]-i][x[0]].marked=true;
			//opponent[y[0]-i][x[0]].square.setColor(Color.YELLOW);
			Carrier2[i]=opponent[y[0]-i][x[0]].square;
			}
		}
		//put Battleship2 #4
		do{
		x[1]=randx[1].nextInt(7);
		y[1]=3+randy[1].nextInt(7);
		}while(isValid(x[1],y[1],dir[1], 4)==false);
		if(dir[1]==0)	//horizontal
		{
			for(int i=0;i<4;i++)
			{
			opponent[y[1]][x[1]+i].marked=true;
			//opponent[y[1]][x[1]+i].square.setColor(Color.YELLOW);
			Battleship2[i]=opponent[y[1]][x[1]+i].square;
			}
		}
		else		//vertical
		{
			for(int i=0;i<4;i++)
			{
			opponent[y[1]-i][x[1]].marked=true;
			//opponent[y[1]-i][x[1]].square.setColor(Color.YELLOW);
			Battleship2[i]=opponent[y[1]-i][x[1]].square;
			}
		}
		//put Frigate2 #3
		do{
		x[2]=randx[2].nextInt(8);
		y[2]=2+randy[2].nextInt(8);
		}while(isValid(x[2],y[2],dir[2], 3)==false);
		if(dir[2]==0)	//horizontal
		{
			for(int i=0;i<3;i++)
			{
			opponent[y[2]][x[2]+i].marked=true;
			//opponent[y[2]][x[2]+i].square.setColor(Color.YELLOW);
			Frigate2[i]=opponent[y[2]][x[2]+i].square;
			}
		}
		else		//vertical
		{
			for(int i=0;i<3;i++)
			{
			opponent[y[2]-i][x[2]].marked=true;
			//opponent[y[2]-i][x[2]].square.setColor(Color.YELLOW);
			Frigate2[i]=opponent[y[2]-i][x[2]].square;
			}
		}
		//put Submarine2	#3
		do{
		x[3]=randx[3].nextInt(8);
		y[3]=2+randy[3].nextInt(8);
		}while(isValid(x[3],y[3],dir[3], 3)==false);
		if(dir[3]==0)	//horizontal
		{
			for(int i=0;i<3;i++)
			{
			opponent[y[3]][x[3]+i].marked=true;
			//opponent[y[3]][x[3]+i].square.setColor(Color.YELLOW);
			Submarine2[i]=opponent[y[3]][x[3]+i].square;
			}
		}
		else		//vertical
		{
			for(int i=0;i<3;i++)
			{
			opponent[y[3]-i][x[3]].marked=true;
			//opponent[y[3]-i][x[3]].square.setColor(Color.YELLOW);
			Submarine2[i]=opponent[y[3]-i][x[3]].square;
			
			}
		} 
		//put Minesweeper2 #2
		do{
		x[4]=randx[4].nextInt(9);
		y[4]=1+randy[4].nextInt(9);
		}while(isValid(x[4],y[4],dir[4], 2)==false);
		
		if(dir[4]==0)	//horizontal
		{
			for(int i=0;i<2;i++)
			{
			opponent[y[4]][x[4]+i].marked=true;
			//opponent[y[4]][x[4]+i].square.setColor(Color.YELLOW);
			Minesweeper2[i]=opponent[y[4]][x[4]+i].square;
			}
		}
		else		//vertical
		{
			for(int i=0;i<2;i++)
			{
			opponent[y[4]-i][x[4]].marked=true;
			//opponent[y[4]-i][x[4]].square.setColor(Color.YELLOW);
			Minesweeper2[i]=opponent[y[4]-i][x[4]].square;
			}
		}
		
	}
	public boolean isValid(int x, int y, int dir, int shiplen)
	{
		if(dir==0)//horizontal
		{
			for(int i=0;i<shiplen;i++)
			{
				if(opponent[y][x+i].marked==true){return false;}
			}
		}
		else
		{
			for(int i=0;i<shiplen;i++)
			{
				if(opponent[y-i][x].marked==true){return false;}
			}
		}
		return true;
	}
	public boolean full(String a)
	{
		if(a.equals("Carrier2"))
		{
		for(int i=0;i<5;i++)
		{
			if(Carrier2[i].getColor()!=Color.RED){return false;}
		}
		}
		else if(a.equals("Battleship2"))
		{
		for(int i=0;i<4;i++)
		{
			if(Battleship2[i].getColor()!=Color.RED){return false;}
		}
		}
		else if(a.equals("Frigate2"))
		{
		for(int i=0;i<3;i++)
		{
			if(Frigate2[i].getColor()!=Color.RED){return false;}
		}
		}
		else if(a.equals("Submarine2"))
		{
		for(int i=0;i<3;i++)
		{
			if(Submarine2[i].getColor()!=Color.RED){return false;}
		}
		}
		else if(a.equals("Minesweeper2"))
		{
		for(int i=0;i<2;i++)
		{
			if(Minesweeper2[i].getColor()!=Color.RED){;return false;}
		}
		}
		return true;
		
	}
	public boolean loss()
	{
		for(int i=0;i<4;i++)
		{
			if(Carrier[i].getColor()!=Color.RED){return false;}
		}
		for(int i=0;i<3;i++)
		{
			if(Battleship[i].getColor()!=Color.RED){return false;}
		}
		for(int i=0;i<3;i++)
		{
			if(Frigate[i].getColor()!=Color.RED){return false;}
		}
		for(int i=0;i<2;i++)
		{
			if(Submarine[i].getColor()!=Color.RED){return false;}
		}
		for(int i=0;i<1;i++)
		{
			if(Minesweeper[i].getColor()!=Color.RED){return false;}
		}
		return true;
	}
}

