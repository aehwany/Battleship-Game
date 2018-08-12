
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

import java.util.concurrent.TimeUnit;


public class main2 extends GraphicsProgram implements MouseListener {
	
int x=0;
int y=0;
int x_mov=0;
int y_mov=0;
int ref_x=0;
int ref_y=0;
int phase=0;	//phase 0: pregame  phase 1:game
int ship=0;
int algr=0;
int side=0;
int[]sunk=new int[5];
boolean endgame=false;
myGRect[][] myboard=new myGRect[10][10];
myGRect[][] opponent=new myGRect[10][10];
//ships
ship Carrier=new ship(5);
ship Carrier2=new ship(5);
ship Battleship=new ship(4);
ship Battleship2=new ship(4);
ship Frigate=new ship(3);
ship Frigate2=new ship(3);
ship Submarine=new ship(3);
ship Submarine2=new ship(3);
ship Minesweeper=new ship(2);
ship Minesweeper2=new ship(2);
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
GImage faded_ver;
GImage faded_hor;

//JButton beep = new JButton("Horizontal");
GLabel label=new GLabel("Horizontal", 580, 550);
GLabel level=new GLabel("HARD", 580, 650);
GRect beep=new GRect(560,525,120,40);
GRect level_frame=new GRect(560, 620,120,40);
//GRect ship_frame=new GRect(520, 280, 220, 40);
GLabel info=new GLabel("put Carrier", 520, 300);

	public void init() {
		
		label.setFont(new Font("Arial", Font.PLAIN, 20));
		level.setFont(new Font("Arial", Font.ITALIC, 30));
		level.setColor(Color.RED);
		GLabel title = new GLabel("Battleship", 550, 40);
		title.setFont(new Font("Arial", Font.PLAIN, 40));
		add(title);
		info.setFont(new Font("Constantia", Font.ITALIC, 22));
		info.setColor(Color.RED);
		add(info);
		add(label);
		add(level);
		//add(ship_frame);
		add(level_frame);
		BufferedImage img = null;
		BufferedImage img_hor = null;
		BufferedImage faded_img_ver = null;
		BufferedImage faded_img_hor = null;

		try {
		    img = ImageIO.read(new File("/Users/ahmedelehwany/Desktop/Battleship/ship_ver_2.png"));
		    img_hor = ImageIO.read(new File("/Users/ahmedelehwany/Desktop/Battleship/ship_hor_2.png"));
		    faded_img_ver = ImageIO.read(new File("/Users/ahmedelehwany/Desktop/Battleship/ship_ver_faded.png"));
		    faded_img_hor = ImageIO.read(new File("/Users/ahmedelehwany/Desktop/Battleship/ship_hor_faded.png"));

		} catch (IOException e) {
			System.out.println("failed");
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
			faded_ver=new GImage(faded_img_ver);
			faded_hor=new GImage(faded_img_hor);

		//pic.setBounds(0,0,50,250);
		//add(pic);
		//GRect ex=new GRect(100,100);
		//ex.setBounds(200,200,100,100);
		//add(ex);
		
		
		 //myboard[0]=new GRect(100,100);
		 
		//myboard[0].setBounds(100,100,100,100);
		//add(myboard[0]);
		//myboard[0][1].setColor(Color.DARK_GRAY);
		//myboard[0][1].setFilled(true);
		for(int j=0; j<10;j++)
		{
			//System.out.println(j);
			for(int i=0;i<10;i++)
			{
				if((i+j)%2==0){
				myboard[j][i]=new myGRect(50*i,50*j,50,50);
				myboard[j][i].square.setColor(Color.DARK_GRAY);
				}
				else
				{
					myboard[j][i]=new myGRect(50*i, 50*j, 50, 50);
					myboard[j][i].square.setColor(Color.GRAY);
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
				opponent[j][i].square.setColor(Color.DARK_GRAY);
				}
				else
				{
					opponent[j][i]=new myGRect(750+50*i, 50*j, 50, 50);
					opponent[j][i].square.setColor(Color.GRAY);
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
		if(x>560 && x<680 &&y>630 && y<670 && phase==0)
		{
			System.out.println("outta");
			if(level.getLabel()=="HARD"){level.setLabel("EASY");}
			else{level.setLabel("HARD");}
		}
		if(x>560 && x<680 &&y>525 && y<565 && phase==0)
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
							if(isValid2(x, y, 0, 5)==true)
							{
								for(int i=0;i<5;i++)
								{
									//myboard[y][x+i].square.setColor(Color.RED);
									myboard[y][x+i].marked=true;
									//myboard[y][x+i].square.setColor(Color.RED);
									myboard[y][x+i].ship=Carrier;
									Carrier.array[i]=myboard[y][x+i].square;
									if((x+i+y)%2==0)	{myboard[y][x+i].square.setColor(Color.DARK_GRAY);}	//reset even
									else{myboard[y][x+i].square.setColor(Color.GRAY);}	//reset odd
								}
								
								Carrier_hor.setBounds(x*50,y*50-75, 50,50 );
								Carrier_hor.setSize(250, 200);
								add(Carrier_hor);
								info.setLabel("put Battleship");
								ship=1;
							}
						}
						else
						{
							if(isValid2(x, y, 1, 5)==true)
							{
								for(int i=0;i<5;i++)
								{
									
								//myboard[y+i][x].square.setColor(Color.RED);
								myboard[y+i][x].marked=true;
								myboard[y+i][x].ship=Carrier;
								Carrier.array[i]=myboard[y+i][x].square;
								if((x+i+y)%2==0)	{myboard[y+i][x].square.setColor(Color.DARK_GRAY);}	//reset even
								else{myboard[y+i][x].square.setColor(Color.GRAY);}	//reset odd
								
								}
								Carrier_ver.setBounds(x*50-75,y*50,200,5*50);
								add(Carrier_ver);
								info.setLabel("put Battleship");
								ship=1;
							}
						
						}
						break;
					
					case 1:
						if(dir=="Horizontal")
						{
							if(isValid2(x, y, 0, 4)==true)
							{
								for(int i=0;i<4;i++)
								{
									//myboard[y][x+i].square.setColor(Color.YELLOW);
									myboard[y][x+i].marked=true;
									myboard[y][x+i].ship=Battleship;
									Battleship.array[i]=myboard[y][x+i].square;
									if((x+i+y)%2==0)	{myboard[y][x+i].square.setColor(Color.DARK_GRAY);}	//reset even
									else{myboard[y][x+i].square.setColor(Color.GRAY);}	//reset odd

								}
								Battleship_hor.setBounds(x*50,y*50-50,4*50,150);
								add(Battleship_hor);
								info.setLabel("put Frigate");
								ship=2;
							}
						}
						else
						{
							if(isValid2(x, y, 1, 4)==true)
							{
									for(int i=0;i<4;i++)
									{
									//myboard[y+i][x].square.setColor(Color.YELLOW);
									myboard[y+i][x].marked=true;
									myboard[y+i][x].ship=Battleship;
									Battleship.array[i]=myboard[y+i][x].square;
									if((x+i+y)%2==0)	{myboard[y+i][x].square.setColor(Color.DARK_GRAY);}	//reset even
									else{myboard[y+i][x].square.setColor(Color.GRAY);}	//reset odd

									}
									Battleship_ver.setBounds(x*50-50,y*50,150,4*50);
									add(Battleship_ver);
									info.setLabel("put Frigate");
									ship=2;
							}
						}
						
						break;
					case 2:
						if(dir=="Horizontal")
						{
							if(isValid2(x, y, 0, 3)==true)
							{
								for(int i=0;i<3;i++)
								{
									//myboard[y][x+i].square.setColor(Color.YELLOW);
									myboard[y][x+i].marked=true;
									myboard[y][x+i].ship=Frigate;
									Frigate.array[i]=myboard[y][x+i].square;
									if((x+i+y)%2==0)	{myboard[y][x+i].square.setColor(Color.DARK_GRAY);}	//reset even
									else{myboard[y][x+i].square.setColor(Color.GRAY);}	//reset odd

								}
								Frigate_hor.setBounds(x*50,y*50-35,3*50,120);
								add(Frigate_hor);
								info.setLabel("put Submarine");
								ship=3;
							}
						}
						else
						{
							if(isValid2(x, y, 1, 3)==true)
							{
									for(int i=0;i<3;i++)
									{
										//myboard[y+i][x].square.setColor(Color.YELLOW);
										myboard[y+i][x].marked=true;
										myboard[y+i][x].ship=Frigate;
										Frigate.array[i]=myboard[y+i][x].square;
										if((x+i+y)%2==0)	{myboard[y+i][x].square.setColor(Color.DARK_GRAY);}	//reset even
										else{myboard[y+i][x].square.setColor(Color.GRAY);}	//reset odd

									}
									Frigate_ver.setBounds(x*50-35,y*50,120,3*50);
									add(Frigate_ver);
									info.setLabel("put Submarine");
									ship=3;
							}
						}
						
						break;
					case 3:
						if(dir=="Horizontal")
						{
							if(isValid2(x, y, 0, 3)==true)
							{
									for(int i=0;i<3;i++)
									{
										//myboard[y][x+i].square.setColor(Color.YELLOW);
										myboard[y][x+i].marked=true;
										myboard[y][x+i].ship=Submarine;
										Submarine.array[i]=myboard[y][x+i].square;
										if((x+i+y)%2==0)	{myboard[y][x+i].square.setColor(Color.DARK_GRAY);}	//reset even
										else{myboard[y][x+i].square.setColor(Color.GRAY);}	//reset odd

									}
									Submarine_hor.setBounds(x*50,y*50-35,3*50,120);
									add(Submarine_hor);
									info.setLabel("put Minesweeper");
									ship=4;
							}
						}
						else
						{
							if(isValid2(x, y, 1, 3)==true)
							{
								for(int i=0;i<3;i++)
								{
								//myboard[y+i][x].square.setColor(Color.YELLOW);
								myboard[y+i][x].marked=true;
								myboard[y+i][x].ship=Submarine;
								Submarine.array[i]=myboard[y+i][x].square;
								if((x+i+y)%2==0)	{myboard[y+i][x].square.setColor(Color.DARK_GRAY);}	//reset even
								else{myboard[y+i][x].square.setColor(Color.GRAY);}	//reset odd

								}
								Submarine_ver.setBounds(x*50-35,y*50,120,3*50);
								add(Submarine_ver);
								info.setLabel("put Minesweeper");
								ship=4;
							}
						
						}
						break;
					case 4:
						if(dir=="Horizontal")
						{
							if(isValid2(x, y, 0, 2)==true)
							{
								System.out.println("isvalid worked");
								for(int i=0;i<2;i++)
								{
									//myboard[y][x+i].square.setColor(Color.YELLOW);
									myboard[y][x+i].marked=true;
									myboard[y][x+i].ship=Minesweeper;
									Minesweeper.array[i]=myboard[y][x+i].square;
									if((x+i+y)%2==0)	{myboard[y][x+i].square.setColor(Color.DARK_GRAY);}	//reset even
									else{myboard[y][x+i].square.setColor(Color.GRAY);}	//reset odd

								}
								Minesweeper_hor.setBounds(x*50,y*50-25,2*50,100);
								add(Minesweeper_hor);
								System.out.println("end isvalid");
							}
						}
						else
						{
							if(isValid2(x, y, 1, 2)==true)
							{
								for(int i=0;i<2;i++)
								{
								//myboard[y+i][x].square.setColor(Color.YELLOW);
								myboard[y+i][x].marked=true;
								myboard[y+i][x].ship=Minesweeper;
								Minesweeper.array[i]=myboard[y+i][x].square;
								if((x+i+y)%2==0)	{myboard[y+i][x].square.setColor(Color.DARK_GRAY);}	//reset even
								else{myboard[y+i][x].square.setColor(Color.GRAY);}	//reset odd

								}
								Minesweeper_ver.setBounds(x*50-25,y*50,100,2*50);
								add(Minesweeper_ver);
							}
							//ship=5;
						}
						
						putship();
						System.out.println("check");
						info.setLabel("Your turn");
						remove(beep);
						remove(label);
						remove(level);
						remove(level_frame);
						phase=1;
						break;
	
						
							
				}
		    }
	       
		      
		}
		
		
		else if(phase==1)
		{
			if(endgame==false)
			{
			
			x=e.getX();
		    y=e.getY();
		    if(x>=500 && x<=1250)
		    {
			    //System.out.println(opponent[y][x].square.getColor());
			    if(opponent[y/50][(x-750)/50].square.getColor()!=Color.BLUE)
			    {
				  
					    
					    	x=(x-750)/50;
							y=y/50;
							//System.out.println("new "+x+" "+y);
							 if( opponent[y][x].marked==true)
							 {
								 opponent[y][x].square.setColor(Color.RED);
								 
								 //turn=1;
								 
							 }
							 else if(opponent[y][x].square.getColor()==Color.BLUE){;}
							 else 
							 {
								 opponent[y][x].square.setColor(Color.BLUE);
								 info.setLabel("PC's turn");
								 //turn=1;
								 
							 }
							 //if(full("Carrier2")==true)
							 //{
								 
								// if(sunk[0]==0)
								 //{
									 //System.out.println("sunk 0");
									// info.setLabel("Carrier sunk");
									 //sunk[0]=1;
								 //}
							 //}
							 if(Carrier2.full()==true && Carrier2.sunk==false) {info.setLabel("Carrier sunk");Carrier2.sunk=true;}
							 if(Battleship2.full()==true && Battleship2.sunk==false) {info.setLabel("Battleship sunk");Battleship2.sunk=true;}
							 if(Frigate2.full()==true && Frigate2.sunk==false) {info.setLabel("Frigate sunk");Frigate2.sunk=true;}
							 if(Submarine2.full()==true && Submarine2.sunk==false) {info.setLabel("Submarine sunk");Submarine2.sunk=true;}
							 if(Minesweeper2.full()==true && Minesweeper2.sunk==false) {info.setLabel("Minesweeper sunk");Minesweeper2.sunk=true;}
							 
							 if((Carrier2.sunk==true)&&(Battleship2.sunk==true)&&(Frigate2.sunk==true)&&(Submarine2.sunk==true)&&(Minesweeper2.sunk==true)){info.setLabel("YOU WIN");endgame=true;}
			    }	 
							 
							 
			    
			    Timer timer = new Timer(1000, new ActionListener() {
			        @Override
			        public void actionPerformed(ActionEvent e) {
			        	
			        	
			        	
			        ////////////////////////////	
			        	
			        	if(level.getLabel()=="EASY")
			        	{
			        		if(opponent[y][x].square.getColor()!=Color.RED)
							{
			        			info.setLabel("Your Turn");
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
			        	else{
					if(opponent[y][x].square.getColor()!=Color.RED)
					{
						info.setLabel("Your Turn");
						System.out.println("ref"+ref_x+" , "+ref_y+"side is"+side);
						int endloop=0;
						do{
						if(algr==1){x=ref_x;y=ref_y;}
						else 
						{
							/*int smart=0;
							for(int i=0;i<60;i++)
							{
								do
								{
								Random randx = new Random();
								Random randy = new Random();
								x = randx.nextInt(10);
								y = randy.nextInt(10);
								}while( (myboard[y][x].square.getColor()==Color.RED)|| (myboard[y][x].square.getColor()==Color.BLUE)) ;
								if( (x+2<10 && x-2 >-1 && myboard[y][x+1].square.getColor()!=Color.BLUE && myboard[y][x+1].square.getColor()!=Color.RED && myboard[y][x+2].square.getColor()!=Color.BLUE && myboard[y][x+2].square.getColor()!=Color.RED
										&&  myboard[y][x-1].square.getColor()!=Color.BLUE && myboard[y][x-1].square.getColor()!=Color.RED && myboard[y][x-2].square.getColor()!=Color.BLUE && myboard[y][x-2].square.getColor()!=Color.RED ) 
									||
									(x-1<0 && myboard[y][x+1].square.getColor()!=Color.BLUE && myboard[y][x+1].square.getColor()!=Color.RED && myboard[y][x+2].square.getColor()!=Color.BLUE && myboard[y][x+2].square.getColor()!=Color.RED)
									||
									(x-2<0 && myboard[y][x-1].square.getColor()!=Color.BLUE && myboard[y][x-1].square.getColor()!=Color.RED && myboard[y][x+1].square.getColor()!=Color.BLUE && myboard[y][x+1].square.getColor()!=Color.RED && myboard[y][x+2].square.getColor()!=Color.BLUE && myboard[y][x+2].square.getColor()!=Color.RED)
									||
									(x+1>9 && myboard[y][x-1].square.getColor()!=Color.BLUE && myboard[y][x-1].square.getColor()!=Color.RED && myboard[y][x-2].square.getColor()!=Color.BLUE && myboard[y][x-2].square.getColor()!=Color.RED)
									||
									(x+2>9 && myboard[y][x-1].square.getColor()!=Color.BLUE && myboard[y][x-1].square.getColor()!=Color.RED && myboard[y][x-2].square.getColor()!=Color.BLUE && myboard[y][x-2].square.getColor()!=Color.RED && myboard[y][x+1].square.getColor()!=Color.BLUE && myboard[y][x+1].square.getColor()!=Color.RED)
										)
								{
									System.out.println(x+"  "+y);
									System.out.println("hit");
									smart=1;
									break;	
								}
								if(i==29){System.out.println("30 times done");}
							}
							System.out.println("algr is 0");
							if(smart==0)
							{*/
							
							do
							{
								System.out.println("big loop");
							Random randx = new Random();
							Random randy = new Random();
							x = randx.nextInt(10);
							y = randy.nextInt(10);
							//System.out.println(x+" "+y);
							}while( (myboard[y][x].square.getColor()==Color.RED)|| (myboard[y][x].square.getColor()==Color.BLUE)) ;
							//}
						//if(myboard[y][x].ship.full()==false){System.out.println("null ship");}
						}
						
						Timer timer2 = new Timer(200,new ActionListener() {
							@Override
						        public void actionPerformed(ActionEvent e) {
							}});
						timer2.start();
				    	 
						do{
							
							endloop=0;
				    		System.out.println("loop starts"+x+" ,"+y);
				    		//side=side%4;
							if( myboard[y][x].marked==true )
					    	  {
								System.out.println(x+","+y+" "+"marked square");
					    		  myboard[y][x].square.setColor(Color.RED);
					    		  				//algr=1;
					    		  //myboard[y][x].marked=false;
									
								
								
								
								
								
					    	  	//}
					    		   
					    		  if(algr==1 && myboard[ref_y][ref_x].ship!=myboard[y][x].ship)
					    		  {
					    			  System.out.println("dif ship");
					    			  
					    			  x=ref_x;
					    			  y=ref_y;
					    			  side=(side+1)%4;
					    		  }
					    		  
					    		  if(algr==0)
					    		  {
					    		  algr=1;
					    		  side=0;
					    		  ref_x=x;
					    		  ref_y=y;
					    		  System.out.println("ref"+ref_x+" , "+ref_y);
					    		  }
					    		  System.out.println("ship is "+myboard[y][x].ship.ship_len);
					    		  
					    		  //hereeeeeeeeeeee
					    		  if( myboard[y][x].ship.full()==true)
				    		  		{
					    			System.out.println("ship sunk");
				    		  		algr=0;
				    		  		side=0;
				    		  		myboard[y][x].ship.sunk=true;
				    		  		}
					    		  myboard[y][x].square.setFilled(true);
						    	  //add(myboard[y][x].square);
					    		  if(algr==1){
					    			 do{
					    			  
					    			  System.out.println("loop");
					    			  if(y>9 || x>9 || y<0 || x<0 || myboard[y][x].square.getColor()==Color.BLUE){x=ref_x;y=ref_y;side++;}
							    		  switch (side)
							    		  {
								    		  case 0:{y--;break;}	//up
								    		  case 1:{y++; break;}	//down
								    		  case 2:{x++;break;}	//right
								    		  case 3:{x--;break;}	//left
							    		  }
					    			 } while(y>9 || x>9 || y<0 || x<0 ||myboard[y][x].square.getColor()==Color.BLUE);
					    			 
					    			  
					    		  }
							      if(loss()==true){info.setLabel("YOU LOSE");}
							      
					    	  }
						
					    	  else
					    	  {
					    		  
					    		 // System.out.println("else side is "+side);
					    		  myboard[y][x].square.setColor(Color.BLUE);
					    		  
					    		  //if(algr==1){side++;side=side%4;System.out.println("side inc");}
					    		  System.out.println("else side is "+side);
					    		  endloop=1;
					    		  //if(myboard[y][x].ship==null){algr=0;}
					    		  myboard[y][x].square.setFilled(true);
						    	  //add(myboard[y][x]);
					    	  }
				    	  /*if(x<=9 || x>=0 || y<=9 || y>=0)
				    	  {
				    		  endloop=1;
				    		  side=(side+1)%4;
				    	  }*/
							
							
							System.out.println(endloop+" "+algr);
						       
				    	}while( endloop==0 && algr==1 && x<=9 && x>=0 && y<=9 && y>=0 && myboard[y][x].square.getColor()!=Color.BLUE );
						System.out.println("end loop");
						side=(side+1)%4;
						}while(endloop==0 );	 
					System.out.println("FINISH");
					}
					
					
					 }		//if easy || hard
			        ///////////	
			        	
			        	
			        }
			        });
			    timer.setRepeats(false);
			    timer.start();
			    
			    
			       	
			
		    }		//if x<> boundaries
		}		//if endgame
		}		//if phase=0
		
		
     }		//mouse clicked
	public void  mouseMoved(MouseEvent e)
	{
		int new_x=e.getX();
		int new_y=e.getY();
		new_x=new_x/50;
		new_y=new_y/50;
		//if(new_x!=x_mov || new_y!=y_mov)
		//{
		//System.out.println("mouse  moved");
		//System.out.println("phase is "+phase);
		if(phase==0)
		{
		
		//System.out.println(new_x+" , "+new_y);
		//if(new_x <500 && new_y<500)
		//{
			//System.out.println("outer cond");
			
			switch (ship)
			{
				case 0:	
					if (label.getLabel()=="Horizontal")
					{
						
						if(isValid2(new_x, new_y, 0, 5)==true && isValid2(x_mov, y_mov, 0, 5)==true)
						{
							//System.out.println(new_y);
							for(int i=0;i<5;i++)
							{
								//if(x_mov!=0 || y_mov!=0)
								//{
								if((x_mov+i+y_mov)%2==0)	{myboard[y_mov][x_mov+i].square.setColor(Color.DARK_GRAY);}//reset even
								else{myboard[y_mov][x_mov+i].square.setColor(Color.GRAY);}//reset odd
								//myboard[new_y][new_x+i].square.setColor(Color.YELLOW);
								//}
							}
							faded_hor.setBounds(new_x*50,new_y*50-75, 50,50 );
							faded_hor.setSize(250, 200);
							add(faded_hor);

							x_mov=new_x;
							y_mov=new_y;
						}
						else
						{
							//System.out.println("outaaaa");
							for(int i=0;i<5;i++)
							{
								if(x_mov!=0 || y_mov!=0)
								{
								if((x_mov+i+y_mov)%2==0)	{myboard[y_mov][x_mov+i].square.setColor(Color.DARK_GRAY);}	//reset even
								else{myboard[y_mov][x_mov+i].square.setColor(Color.GRAY);}	//reset odd
								
								}
							}
							remove(faded_hor);
							y_mov=0;
							x_mov=0;
						}
					}
					else	//VERTICAL
					{
						//System.out.println("vertical code");
						if(isValid2(new_x, new_y, 1, 5)==true && isValid2(x_mov, y_mov, 1, 5)==true)
						{
							//System.out.println(new_y);
							for(int i=0;i<5;i++)
							{
								//if(x_mov!=0 || y_mov!=0)
								//{
								if((x_mov+i+y_mov)%2==0)	{myboard[y_mov+i][x_mov].square.setColor(Color.DARK_GRAY);}//reset even
								else{myboard[y_mov+i][x_mov].square.setColor(Color.GRAY);}//reset odd
								//myboard[new_y+i][new_x].square.setColor(Color.YELLOW);
								//}
							}
							faded_ver.setBounds(new_x*50-75,new_y*50,200,5*50);
							add(faded_ver);

							x_mov=new_x;
							y_mov=new_y;
						}
						else
						{
							//System.out.println("outaaaa");
							for(int i=0;i<5;i++)
							{
								if(x_mov!=0 || y_mov!=0)
								{
								if((x_mov+i+y_mov)%2==0)	{myboard[y_mov+i][x_mov].square.setColor(Color.DARK_GRAY);}	//reset even
								else{myboard[y_mov+i][x_mov].square.setColor(Color.GRAY);}	//reset odd
								}
							}
							remove(faded_ver);
							y_mov=0;
							x_mov=0;
						}
					}
					break;
				case 1:
					if (label.getLabel()=="Horizontal")
					{
						//System.out.println("hor case 1");
						//System.out.println(new_x+" "+new_y+" "+x_mov+" "+y_mov);
						if(isValid2(new_x, new_y, 0, 4)==true )
						{
							//System.out.println("mark");
							for(int i=0;i<4;i++)
							{
								//if(x_mov!=0 || y_mov!=0)
								//{
								if((x_mov+i+y_mov)%2==0)	{myboard[y_mov][x_mov+i].square.setColor(Color.DARK_GRAY);}//reset even
								else{myboard[y_mov][x_mov+i].square.setColor(Color.GRAY);}//reset odd
								//myboard[new_y][new_x+i].square.setColor(Color.YELLOW);
								//}
							}
							faded_hor.setBounds(new_x*50,new_y*50-50,4*50,150);
							add(faded_hor);
							x_mov=new_x;
							y_mov=new_y;
						}
						else
						{
							//System.out.println("outaaaa");
							for(int i=0;i<4;i++)
							{
								if(x_mov!=0 || y_mov!=0)
								{
								if((x_mov+i+y_mov)%2==0)	{myboard[y_mov][x_mov+i].square.setColor(Color.DARK_GRAY);}	//reset even
								else{myboard[y_mov][x_mov+i].square.setColor(Color.GRAY);}	//reset odd
								
								}
							}
							remove(faded_hor);
							y_mov=0;
							x_mov=0;
						}
					}
					else	//VERTICAL
					{
						//System.out.println("vertical code");
						if(isValid2(new_x, new_y, 1, 4)==true )
						{
							System.out.println(new_y);
							for(int i=0;i<4;i++)
							{
								//if(x_mov!=0 || y_mov!=0)
								//{
								if((x_mov+i+y_mov)%2==0)	{myboard[y_mov+i][x_mov].square.setColor(Color.DARK_GRAY);}//reset even
								else{myboard[y_mov+i][x_mov].square.setColor(Color.GRAY);}//reset odd
								//myboard[new_y+i][new_x].square.setColor(Color.YELLOW);
								//}
							}
							faded_ver.setBounds(new_x*50-50,new_y*50,150,4*50);
							add(faded_ver);
							x_mov=new_x;
							y_mov=new_y;
						}
						else
						{
							//System.out.println("outaaaa");
							for(int i=0;i<4;i++)
							{
								if(x_mov!=0 || y_mov!=0)
								{
								if((x_mov+i+y_mov)%2==0)	{myboard[y_mov+i][x_mov].square.setColor(Color.DARK_GRAY);}	//reset even
								else{myboard[y_mov+i][x_mov].square.setColor(Color.GRAY);}	//reset odd
								}
							}
							remove(faded_ver);
							y_mov=0;
							x_mov=0;
						}
					}
					break;
				case 2 : case 3:
					if (label.getLabel()=="Horizontal")
					{
						if(isValid2(new_x, new_y, 0, 3)==true )
						{
							//System.out.println(new_y);
							for(int i=0;i<3;i++)
							{
								//if(x_mov!=0 || y_mov!=0)
								//{
								if((x_mov+i+y_mov)%2==0)	{myboard[y_mov][x_mov+i].square.setColor(Color.DARK_GRAY);}//reset even
								else{myboard[y_mov][x_mov+i].square.setColor(Color.GRAY);}//reset odd
								//myboard[new_y][new_x+i].square.setColor(Color.YELLOW);
								//}
							}
							faded_hor.setBounds(new_x*50,new_y*50-35,3*50,120);
							add(faded_hor);
							x_mov=new_x;
							y_mov=new_y;
						}
						else
						{
							//System.out.println("outaaaa");
							for(int i=0;i<3;i++)
							{
								if(x_mov!=0 || y_mov!=0)
								{
								if((x_mov+i+y_mov)%2==0)	{myboard[y_mov][x_mov+i].square.setColor(Color.DARK_GRAY);}	//reset even
								else{myboard[y_mov][x_mov+i].square.setColor(Color.GRAY);}	//reset odd
								
								}
							}
							remove(faded_hor);
							y_mov=0;
							x_mov=0;
						}
					}
					else	//VERTICAL
					{
						//System.out.println("vertical code");
						if(isValid2(new_x, new_y, 1, 3)==true )
						{
							System.out.println(new_y);
							for(int i=0;i<3;i++)
							{
								//if(x_mov!=0 || y_mov!=0)
								//{
								if((x_mov+i+y_mov)%2==0)	{myboard[y_mov+i][x_mov].square.setColor(Color.DARK_GRAY);}//reset even
								else{myboard[y_mov+i][x_mov].square.setColor(Color.GRAY);}//reset odd
								//myboard[new_y+i][new_x].square.setColor(Color.YELLOW);
								//}
							}
							faded_ver.setBounds(new_x*50-35,new_y*50,120,3*50);
							add(faded_ver);
							x_mov=new_x;
							y_mov=new_y;
						}
						else
						{
							//System.out.println("outaaaa");
							for(int i=0;i<3;i++)
							{
								if(x_mov!=0 || y_mov!=0)
								{
								if((x_mov+i+y_mov)%2==0)	{myboard[y_mov+i][x_mov].square.setColor(Color.DARK_GRAY);}	//reset even
								else{myboard[y_mov+i][x_mov].square.setColor(Color.GRAY);}	//reset odd
								}
							}
							remove(faded_ver);
							y_mov=0;
							x_mov=0;
						}
					}
					break;
				
				case 4:
					if (label.getLabel()=="Horizontal")
					{
						if(isValid2(new_x, new_y, 0, 2)==true)
						{
							//System.out.println(new_y);
							for(int i=0;i<2;i++)
							{
								//if(x_mov!=0 || y_mov!=0)
								//{
								if((x_mov+i+y_mov)%2==0)	{myboard[y_mov][x_mov+i].square.setColor(Color.DARK_GRAY);}//reset even
								else{myboard[y_mov][x_mov+i].square.setColor(Color.GRAY);}//reset odd
								//myboard[new_y][new_x+i].square.setColor(Color.YELLOW);
								//}
							}
							faded_hor.setBounds(new_x*50,new_y*50-25,2*50,100);
							add(faded_hor);
							x_mov=new_x;
							y_mov=new_y;
						}
						else
						{
							//System.out.println("outaaaa");
							for(int i=0;i<2;i++)
							{
								if(x_mov!=0 || y_mov!=0)
								{
								if((x_mov+i+y_mov)%2==0)	{myboard[y_mov][x_mov+i].square.setColor(Color.DARK_GRAY);}	//reset even
								else{myboard[y_mov][x_mov+i].square.setColor(Color.GRAY);}	//reset odd
								
								}
							}
							remove(faded_hor);
							y_mov=0;
							x_mov=0;
						}
					}
					else	//VERTICAL
					{
						//System.out.println("vertical code");
						if(isValid2(new_x, new_y, 1, 2)==true )
						{
							System.out.println(new_y);
							for(int i=0;i<2;i++)
							{
								//if(x_mov!=0 || y_mov!=0)
								//{
								if((x_mov+i+y_mov)%2==0)	{myboard[y_mov+i][x_mov].square.setColor(Color.DARK_GRAY);}//reset even
								else{myboard[y_mov+i][x_mov].square.setColor(Color.GRAY);}//reset odd
								//myboard[new_y+i][new_x].square.setColor(Color.YELLOW);
								//}
							}
							faded_ver.setBounds(new_x*50-25,new_y*50,100,2*50);
							add(faded_ver);
							x_mov=new_x;
							y_mov=new_y;
						}
						else
						{
							//System.out.println("outaaaa");
							for(int i=0;i<2;i++)
							{
								if(x_mov!=0 || y_mov!=0)
								{
								if((x_mov+i+y_mov)%2==0)	{myboard[y_mov+i][x_mov].square.setColor(Color.DARK_GRAY);}	//reset even
								else{myboard[y_mov+i][x_mov].square.setColor(Color.GRAY);}	//reset odd
								}
							}
							remove(faded_ver);
							y_mov=0;
							x_mov=0;
						}
					}
					break;
			
				}
			}
			
		//}
		//else
		//{
			
		//}
	}
	public void putship()
	{
		System.out.println("putship");
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
			Carrier2.array[i]=opponent[y[0]][x[0]+i].square;
			}
		}
		else		//vertical
		{
			for(int i=0;i<5;i++)
			{
			opponent[y[0]-i][x[0]].marked=true;
			//opponent[y[0]-i][x[0]].square.setColor(Color.YELLOW);
			Carrier2.array[i]=opponent[y[0]-i][x[0]].square;
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
			Battleship2.array[i]=opponent[y[1]][x[1]+i].square;
			}
		}
		else		//vertical
		{
			for(int i=0;i<4;i++)
			{
			opponent[y[1]-i][x[1]].marked=true;
			//opponent[y[1]-i][x[1]].square.setColor(Color.YELLOW);
			Battleship2.array[i]=opponent[y[1]-i][x[1]].square;
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
			Frigate2.array[i]=opponent[y[2]][x[2]+i].square;
			}
		}
		else		//vertical
		{
			for(int i=0;i<3;i++)
			{
			opponent[y[2]-i][x[2]].marked=true;
			//opponent[y[2]-i][x[2]].square.setColor(Color.YELLOW);
			Frigate2.array[i]=opponent[y[2]-i][x[2]].square;
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
			Submarine2.array[i]=opponent[y[3]][x[3]+i].square;
			}
		}
		else		//vertical
		{
			for(int i=0;i<3;i++)
			{
			opponent[y[3]-i][x[3]].marked=true;
			//opponent[y[3]-i][x[3]].square.setColor(Color.YELLOW);
			Submarine2.array[i]=opponent[y[3]-i][x[3]].square;
			
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
			Minesweeper2.array[i]=opponent[y[4]][x[4]+i].square;
			}
		}
		else		//vertical
		{
			for(int i=0;i<2;i++)
			{
			opponent[y[4]-i][x[4]].marked=true;
			//opponent[y[4]-i][x[4]].square.setColor(Color.YELLOW);
			Minesweeper2.array[i]=opponent[y[4]-i][x[4]].square;
			}
		}
		
	}
	public boolean isValid(int x, int y, int dir, int shiplen)
	{
		if(dir==0)//horizontal
		{
			for(int i=0;i<shiplen;i++)
			{
				if( opponent[y][x+i].marked==true ){return false;}
			}
		}
		else
		{
			for(int i=0;i<shiplen;i++)
			{
				if(opponent[y-i][x].marked==true  ){return false;}
			}
		}
		return true;
	}
	
	public boolean isValid2(int x, int y, int dir, int shiplen)
	{
		if(dir==0)//horizontal
		{
			for(int i=0;i<shiplen;i++)
			{
				if(y>9 || y<0 || x+i>9 || x+i<0 ||myboard[y][x+i].marked==true){return false;}
			}
		}
		else
		{
			for(int i=0;i<shiplen;i++)
			{
				if(y+i>9 || y+i<0 || x>9 || x<0 || myboard[y+i][x].marked==true){return false;}
			}
		}
		return true;
	}
	public boolean loss()
	{
		if(Carrier.full()!=true){return false;}
		if(Battleship.full()!=true){return false;}
		if(Frigate.full()!=true){return false;}
		if(Submarine.full()!=true){return false;}
		if(Minesweeper.full()!=true){return false;}
		endgame=true;
		return true;
		
	}
}


