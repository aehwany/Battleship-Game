
import java.applet.Applet;
import java.awt.*;       // Using AWT's Graphics and Color
import java.awt.event.*; // Using AWT event classes and listener interfaces
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*; 


public class applet extends Applet  {
	
	JLayeredPane myboard_main;
	JPanel myboard_back;
	JPanel opponent_panel;
	myJButton myboard[][]=new myJButton[10][10]; 
	myJButton opponent[][]=new myJButton[10][10];
	JButton screen;
	JButton dir;
	JLabel ship_hor=new JLabel("");
	JLabel Carrier_img=new JLabel("");
	JLabel Battleship_img=new JLabel("");
	JLabel Frigate_img=new JLabel("");
	JLabel Minesweeper_img=new JLabel("");
	JLabel Submarine_img=new JLabel("");
	ship_type Carrier=new ship_type(5);
	ship_type Battleship=new ship_type(4);
	ship_type Frigate=new ship_type(3);
	ship_type Submarine=new ship_type(3);
	ship_type Minesweeper=new ship_type(2);
	ship_type Carrier2=new ship_type(5);
	ship_type Battleship2=new ship_type(4);
	ship_type Frigate2=new ship_type(3);
	ship_type Submarine2=new ship_type(3);
	ship_type Minesweeper2=new ship_type(2);
	Boolean end_game=false;
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
	int ship_phase=0;
	BufferedImage img_ver;
	BufferedImage img_hor;
	public static void main(String[] args)
	{
		Advanced a=new Advanced();
		a.init();
	}
	public void init()
	{
		JPanel main=new JPanel(new BorderLayout());
		main.setVisible(true);
		
		myboard_back=new JPanel(new GridLayout(10,10));			//creating a layer in myboard panel
		myboard_back.setBounds(0,0,500,500);
		myboard_back.setPreferredSize(new Dimension(500,500));
		myboard_back.setBounds(0,0,500,500);
		
		
		dir=new JButton("HORIZONTAL");							//creating screen and direction buttons and a panel to put them in
		dir.setFont(new Font("Arial", Font.PLAIN, 20));
		dir.setPreferredSize(new Dimension(260,40));
		dir.addMouseListener(listener);
		screen=new JButton("Put Carrier");
		screen.setFont(new Font("Arial", Font.PLAIN, 30));
		screen.setPreferredSize(new Dimension(260,100));
		screen.setBorder(null);
		JPanel middle= new JPanel(new BorderLayout());
		middle.add(screen, BorderLayout.PAGE_START);
		middle.add(dir, BorderLayout.PAGE_END);
		
		opponent_panel=new JPanel(new BorderLayout());		//creating opponent panel
		opponent_panel.setLayout(new GridLayout(10,10));
		opponent_panel.setBounds(0,0,500,500);
		opponent_panel.setPreferredSize(new Dimension(500,500));
		
		myboard_main=new JLayeredPane();				//creating myboard panel
		myboard_main.setBounds(0,0,500,500);
		myboard_main.setPreferredSize(new Dimension(500,500));
		myboard_main.add(myboard_back,new Integer("2"));
		//myboard_main.add(myboard_front,new Integer("4"));
		
												//adding the layered panels to the main border layout
		main.add(myboard_main,BorderLayout.LINE_START);
		main.add(middle,BorderLayout.CENTER);		
		main.add(opponent_panel,BorderLayout.LINE_END);
		//if(myboard!=null && opponent!=null)
		//{
		//System.out.println("first pass");
		print(myboard,myboard_back);
		print(opponent,opponent_panel);
		//}
		img_ver = null;
		img_hor = null;
		BufferedImage faded_img_ver = null;
		BufferedImage faded_img_hor = null;
		try {
		    img_ver = ImageIO.read(new File("/Users/ahmedelehwany/Desktop/Battleship/ship_ver.png"));
		    img_hor = ImageIO.read(new File("/Users/ahmedelehwany/Desktop/Battleship/ship_hor.png"));
		    faded_img_ver = ImageIO.read(new File("/Users/ahmedelehwany/Desktop/Battleship/ship_ver_faded.png"));
		    faded_img_hor = ImageIO.read(new File("/Users/ahmedelehwany/Desktop/Battleship/ship_hor_faded.png"));

		} catch (IOException e) {
			System.out.println("failed");
		}
		
		if(img_hor!=null)
		{
			System.out.println("print");
			ship_hor.setIcon(new ImageIcon(new ImageIcon(img_hor).getImage().getScaledInstance(250, 80, Image.SCALE_DEFAULT)));
			//ship_hor.setIcon(new ImageIcon(new ImageIcon(img_hor).getImage().getScaledInstance(250, 50, Image.SCALE_DEFAULT)));
			//ship_hor.setIcon(new ImageIcon(new ImageIcon(img_ver).getImage().getScaledInstance(53, 250, Image.SCALE_DEFAULT)));
			//Carrier = new JLabel(new ImageIcon(img_ver));
			if(img_hor!=null)
			{System.out.println("print ship");}
		
		//ship_hor.setPreferredSize(new Dimension(250,50));
		//ship_hor.setSize(250,50);
		//ship_hor.setBounds(0, 0, 250, 50);
		//Carrier.setPreferredSize(new Dimension(50,250));
		//.setSize(50,250);
		//Carrier.setBounds(0, 0, 50, 250);
		
		
			phase=0;
		
		
		}
		
		add(main);
	}
	
	
	
	MouseListener listener = new MouseAdapter() {
		@Override
		 public void mouseClicked(MouseEvent e) {
			Object obj=e.getSource();
			int x=getMousePosition().x;
			int y=getMousePosition().y;
			//System.out.println(" origin is "+opponent[0][0].button.getLocationOnScreen().x);
			System.out.println("original coordinates are :"+x+" "+y);
			
			y=(y/50);
			System.out.println("coordinates are :"+x+" "+y);
			JButton button=(JButton) obj;
			System.out.println(button.getText());
			//button.setContentAreaFilled(true);
			if (button.getText().equals("HORIZONTAL") ) 
			{
				button.setText("VERTICAL");
			}
			
			else if(button.getText().equals("VERTICAL"))
			{
				button.setText("HORIZONTAL");
			}
			else
			{
				if(phase==0)
				{
			
				//System.out.println(button.getParent().getName());
				System.out.println(myboard_back);
				if(button.getParent()==myboard_back)
				{
					x=(x/50);
					System.out.println("panel");
					switch(ship_phase)
					{
					case 0:
						
						if(dir.getText().equals("HORIZONTAL"))
						{
							if(isValid2(x, y, 0, 5)==true)
							{
							save(x, y, 5, 0, Carrier_img,Carrier, img_hor);
							ship_phase=1;
							screen.setText("Put Battleship");
							}
						}
						else
						{
							if(isValid2(x, y, 1, 5)==true)
							{
							save(x, y, 5, 1, Carrier_img,Carrier, img_ver);
							ship_phase=1;
							screen.setText("Put Battleship");
							}
						}
						
						break;
					case 1:
						if(dir.getText().equals("HORIZONTAL"))
						{
							if(isValid2(x, y, 0, 4)==true)
							{
							save(x, y, 4, 0, Battleship_img,Battleship, img_hor);
							ship_phase=2;
							screen.setText("Put Frigate");
							}
						}
						else
						{
							if(isValid2(x, y, 1, 4)==true)
							{
							save(x, y, 4, 1, Battleship_img,Battleship, img_ver);
							ship_phase=2;
							screen.setText("Put Frigate");
							}
						}
						
						break;
					case 2:
						if(dir.getText().equals("HORIZONTAL"))
						{
							if(isValid2(x, y, 0, 3)==true)
							{
							save(x, y, 3, 0, Frigate_img,Frigate, img_hor);
							ship_phase=3;
							screen.setText("Put Submarine");
							}
						}
						else
						{
							if(isValid2(x, y, 1, 3)==true)
							{
							save(x, y, 3, 1, Frigate_img,Frigate, img_ver);
							ship_phase=3;
							screen.setText("Put Submarine");
							}
						}
						
						break;
					case 3:
						if(dir.getText().equals("HORIZONTAL"))
						{
							if(isValid2(x, y, 0, 3)==true)
							{
							save(x, y, 3, 0, Submarine_img,Submarine, img_hor);
							ship_phase=4;
							screen.setText("Put Minesweeper");
							}
						}
						else
						{
							if(isValid2(x, y, 1, 3)==true)
							{
							save(x, y, 3, 1, Submarine_img,Submarine, img_ver);
							ship_phase=4;
							screen.setText("Put Minesweeper");
							}
						}
						
						break;
					case 4:
						if(dir.getText().equals("HORIZONTAL"))
						{
							if(isValid2(x, y, 0, 2)==true)
							{
							save(x, y, 2, 0, Minesweeper_img,Minesweeper, img_hor);
							screen.setText("Your turn");
							putship();
							phase=1;
							}
						}
						else
						{
							if(isValid2(x, y, 1, 2)==true)
							{
							save(x, y, 2, 1, Minesweeper_img,Minesweeper, img_ver);
							screen.setText("Your turn");
							putship();
							phase=1;
							}
						}
						
						break;
					}
					
					screen.setBorder(null);
				}
				}
				else if(phase==1)								//GAME STARTS
				{
					x=(x-opponent[0][0].button.getLocationOnScreen().x)/50;
					//System.out.println("phase 1");
					if(button.getParent()==opponent_panel)
					{
						System.out.println(x+","+y);
						//x=x*15;
						System.out.println(x+","+y);
						if(end_game==false)
						{
							 
							
							
							if(opponent[y][x].button.getBackground()!=Color.BLUE)
							{
								if( opponent[y][x].marked==true)
								 {
									 opponent[y][x].button.setBackground(Color.RED);
									 
								 }
								 else if(opponent[y][x].button.getBackground()==Color.BLUE){;}
								 else 
								 {
									 opponent[y][x].button.setBackground(Color.BLUE);
									 screen.setText("PC's turn");
									 //turn=1;
									 
								 }
								
								if(Carrier2.full()==true && Carrier2.sunk==false) {screen.setText("Carrier sunk");Carrier2.sunk=true;}
								 if(Battleship2.full()==true && Battleship2.sunk==false) {screen.setText("Battleship sunk");Battleship2.sunk=true;}
								 if(Frigate2.full()==true && Frigate2.sunk==false) {screen.setText("Frigate sunk");Frigate2.sunk=true;}
								 if(Submarine2.full()==true && Submarine2.sunk==false) {screen.setText("Submarine sunk");Submarine2.sunk=true;}
								 if(Minesweeper2.full()==true && Minesweeper2.sunk==false) {screen.setText("Minesweeper sunk");Minesweeper2.sunk=true;}
								 
								 if((Carrier2.sunk==true)&&(Battleship2.sunk==true)&&(Frigate2.sunk==true)&&(Submarine2.sunk==true)&&(Minesweeper2.sunk==true)){screen.setText("YOU WIN");end_game=true;}
							}	 
							//if(level.getLabel()=="EASY")
				        	//{
				        		/*if(opponent[y][x].button.getBackground()!=Color.RED)
								{
				        			screen.setText("Your Turn");
									do{
									do
									{
									Random randx = new Random();
									Random randy = new Random();
									x = randx.nextInt(9);
									y = randy.nextInt(9);
									//System.out.println(x+" "+y);
									}while( (myboard[y][x].button.getBackground()==Color.RED) || (myboard[y][x].button.getBackground()==Color.BLUE)) ;
									
							    	  if( myboard[y][x].marked==true)
							    	  {
							    		  myboard[y][x].button.setBackground(Color.RED);
							    		  
							    	  }
							    	  else
							    	  {
							    		  myboard[y][x].button.setBackground(Color.BLUE);
							    	  }
							    	 // myboard[y][x].button.se(true);
							    	  //add(myboard[y][x]);
							    	  if(loss()==true){screen.setText("YOU LOSE");}
									}while(myboard[y][x].button.getBackground()==Color.RED);
								}*/
				        	//}
				        	//else{
						if(opponent[y][x].button.getBackground()!=Color.RED)
						{
							screen.setText("Your Turn");
							//System.out.println("ref"+ref_x+" , "+ref_y+"side is"+side);
							int endloop=0;
							do{
							if(algr==1){x=ref_x;y=ref_y;System.out.println(x+" "+y);}
							else 
							{
								
								
								do
								{
									System.out.println("big loop");
								Random randx = new Random();
								Random randy = new Random();
								x = randx.nextInt(10);
								y = randy.nextInt(10);
								//System.out.println(x+" "+y);
								}while( (myboard[y][x].button.getBackground()==Color.RED)|| (myboard[y][x].button.getBackground()==Color.BLUE)) ;
								//}
							//if(myboard[y][x].ship.full()==false){System.out.println("null ship");}
							}
							
							/*Timer timer2 = new Timer(200,new ActionListener() {
								@Override
							        public void actionPerformed(ActionEvent e) {
								}});
							timer2.start();*/
					    	 
							do{
								
								endloop=0;
					    		System.out.println("loop starts"+x+" ,"+y);
					    		//side=side%4;
								if( myboard[y][x].marked==true )
						    	  {
									System.out.println(x+","+y+" "+"marked square");
						    		  myboard[y][x].button.setBackground(Color.RED);
						    		  				//algr=1;
						    		  //myboard[y][x].marked=false;
										
									
									
									
									
									
						    	  	//}
						    		   
						    		  if(algr==1 && myboard[ref_y][ref_x].ship!=myboard[y][x].ship)
						    		  {
						    			  //System.out.println("dif ship");
						    			  
						    			  x=ref_x;
						    			  y=ref_y;
						    			  side=(side+1)%4;
						    		  }
						    		  
						    		  if(algr==0)
						    		  {
						    		  algr=1;
						    		  System.out.println("ALGR ON");
						    		  side=0;
						    		  ref_x=x;
						    		  ref_y=y;
						    		  //System.out.println("ref"+ref_x+" , "+ref_y);
						    		  }
						    		  //System.out.println("ship is "+myboard[y][x].ship.ship_len);
						    		  
						    		  //hereeeeeeeeeeee
						    		  if( myboard[y][x].ship.full()==true)
					    		  		{
						    			System.out.println("ship sunk");
					    		  		algr=0;
					    		  		side=0;
					    		  		myboard[y][x].ship.sunk=true;
					    		  		}
						    		 // myboard[y][x].button.setFilled(true);
							    	  //add(myboard[y][x].square);
						    		  if(algr==1){
						    			 do{
						    			  
						    			  System.out.println("loop");
						    			  if(y>9 || x>9 || y<0 || x<0 || myboard[y][x].button.getBackground()==Color.BLUE){x=ref_x;y=ref_y;side++;}
								    		  switch (side)
								    		  {
									    		  case 0:{y--;break;}	//up
									    		  case 1:{y++; break;}	//down
									    		  case 2:{x++;break;}	//right
									    		  case 3:{x--;break;}	//left
								    		  }
						    			 } while(y>9 || x>9 || y<0 || x<0 ||myboard[y][x].button.getBackground()==Color.BLUE);
						    			 
						    			  
						    		  }
								      if(loss()==true){screen.setText("YOU LOSE");}
								      
						    	  }
							
						    	  else
						    	  {
						    		  
						    		 // System.out.println("else side is "+side);
						    		  myboard[y][x].button.setBackground(Color.BLUE);
						    		  
						    		  //if(algr==1){side++;side=side%4;System.out.println("side inc");}
						    		  //System.out.println("else side is "+side);
						    		  endloop=1;
						    		  //if(myboard[y][x].ship==null){algr=0;}
						    		  //myboard[y][x].button.setFilled(true);
							    	  //add(myboard[y][x]);
						    	  }
					    	 /* if(x<=9 || x>=0 || y<=9 || y>=0)
					    	  {
					    		  endloop=1;
					    		  side=(side+1)%4;
					    	  }*/
								
								
								System.out.println(endloop+" "+algr);
							       
					    	}while( endloop==0 && algr==1 && x<=9 && x>=0 && y<=9 && y>=0 && myboard[y][x].button.getBackground()!=Color.BLUE );
							System.out.println("end loop");
							side=(side+1)%4;
							}while(endloop==0 );	 
						System.out.println("FINISH");
						//}
						
						
						 }
							
							
							
						    
						}
					}
				}
			}
			screen.setBorder(null);
        }

		
		
    };

	
	public void print(myJButton[][] array, JPanel panel)
	{
		//System.out.println("printaaaaaaaaaaa");
		for(int j=0; j<10;j++)
		{
			for(int i=0;i<10;i++)
			{
			array[j][i]=new myJButton();
			
				if((i+j)%2==0){
				array[j][i].button.setBackground(Color.DARK_GRAY);
				}
				else
				{
					array[j][i].button.setBackground(Color.GRAY);
					
				}
				array[j][i].button.addMouseListener(listener);
				array[j][i].button.setOpaque(true);
				array[j][i].button.setPreferredSize(new Dimension(50,50));
				array[j][i].button.setBorder(null);
				
				panel.add(array[j][i].button);
			}
				
			
		}
	
	}
	
	public void save(int x, int y, int length, int dir, JLabel ship_img, ship_type ship , BufferedImage img)
	{
		if(dir==0)
		{
			for(int i=0;i<length;i++)
			{
				myboard[y][x+i].marked=true;
				myboard[y][x+i].ship=ship;
				ship.array[i]=myboard[y][x+i].button;
			}
			ship_img.setIcon(new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(length*50, 50, Image.SCALE_DEFAULT)));
			ship_img.setPreferredSize(new Dimension(length*50,50));
			ship_img.setBounds(x*50, y*50, length*50, 50);
			myboard_main.add(ship_img,new Integer("3"));
		}
		else
		{
			for(int i=0;i<length;i++)
			{
				
			myboard[y+i][x].marked=true;
			myboard[y+i][x].ship=Carrier;
			ship.array[i]=myboard[y+i][x].button;
			
			}
			ship_img.setIcon(new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(50, length*50, Image.SCALE_DEFAULT)));
			ship_img.setPreferredSize(new Dimension(50,length*50));
			ship_img.setBounds(x*50, y*50, 50, length*50);
			myboard_main.add(ship_img,new Integer("3"));
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
			Carrier2.array[i]=opponent[y[0]][x[0]+i].button;
			}
		}
		else		//vertical
		{
			for(int i=0;i<5;i++)
			{
			opponent[y[0]-i][x[0]].marked=true;
			//opponent[y[0]-i][x[0]].square.setColor(Color.YELLOW);
			Carrier2.array[i]=opponent[y[0]-i][x[0]].button;
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
			Battleship2.array[i]=opponent[y[1]][x[1]+i].button;
			}
		}
		else		//vertical
		{
			for(int i=0;i<4;i++)
			{
			opponent[y[1]-i][x[1]].marked=true;
			//opponent[y[1]-i][x[1]].square.setColor(Color.YELLOW);
			Battleship2.array[i]=opponent[y[1]-i][x[1]].button;
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
			Frigate2.array[i]=opponent[y[2]][x[2]+i].button;
			}
		}
		else		//vertical
		{
			for(int i=0;i<3;i++)
			{
			opponent[y[2]-i][x[2]].marked=true;
			//opponent[y[2]-i][x[2]].square.setColor(Color.YELLOW);
			Frigate2.array[i]=opponent[y[2]-i][x[2]].button;
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
			Submarine2.array[i]=opponent[y[3]][x[3]+i].button;
			}
		}
		else		//vertical
		{
			for(int i=0;i<3;i++)
			{
			opponent[y[3]-i][x[3]].marked=true;
			//opponent[y[3]-i][x[3]].square.setColor(Color.YELLOW);
			Submarine2.array[i]=opponent[y[3]-i][x[3]].button;
			
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
			Minesweeper2.array[i]=opponent[y[4]][x[4]+i].button;
			}
		}
		else		//vertical
		{
			for(int i=0;i<2;i++)
			{
			opponent[y[4]-i][x[4]].marked=true;
			//opponent[y[4]-i][x[4]].square.setColor(Color.YELLOW);
			Minesweeper2.array[i]=opponent[y[4]-i][x[4]].button;
			}
		}
	}
	public boolean loss()
	{
		if(Carrier.full()!=true){return false;}
		if(Battleship.full()!=true){return false;}
		if(Frigate.full()!=true){return false;}
		if(Submarine.full()!=true){return false;}
		if(Minesweeper.full()!=true){return false;}
		end_game=true;
		return true;
		
	}
	
	

}


