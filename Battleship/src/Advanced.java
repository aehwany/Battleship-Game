
//import java.applet.Applet;
// Using AWT's Graphics and Color
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
// Using AWT event classes and listener interfaces
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Advanced {

	JFrame frame = new JFrame();
	JLayeredPane myboard_main;
	JPanel myboard_back;
	JPanel opponent_panel;
	JPanel top;
	myJButton myboard[][] = new myJButton[10][10];
	myJButton opponent[][] = new myJButton[10][10];
	JButton screen;
	// screen.setBorder(null);
	// screen.setBorder();
	JButton dir;
	JButton title;
	JButton you;
	JButton pc;
	JLabel ship_hor = new JLabel("");
	JLabel Carrier_img = new JLabel("");
	JLabel Battleship_img = new JLabel("");
	JLabel Frigate_img = new JLabel("");
	JLabel Minesweeper_img = new JLabel("");
	JLabel Submarine_img = new JLabel("");
	JLabel faded_hor = new JLabel("");
	JLabel faded_ver = new JLabel("");
	ship_type Carrier = new ship_type(5);
	ship_type Battleship = new ship_type(4);
	ship_type Frigate = new ship_type(3);
	ship_type Submarine = new ship_type(3);
	ship_type Minesweeper = new ship_type(2);
	ship_type Carrier2 = new ship_type(5);
	ship_type Battleship2 = new ship_type(4);
	ship_type Frigate2 = new ship_type(3);
	ship_type Submarine2 = new ship_type(3);
	ship_type Minesweeper2 = new ship_type(2);
	Boolean end_game = false;
	int x = 0;
	int y = 0;
	int x_mov = 0;
	int y_mov = 0;
	int ref_x = 0;
	int ref_y = 0;
	int phase = 0; // phase 0: pregame phase 1:game
	int ship = 0;
	int algr = 0;
	int side = 0;
	int ship_phase = 0;
	BufferedImage img_ver;
	BufferedImage img_hor;
	BufferedImage faded_img_ver;
	BufferedImage faded_img_hor;

	public static void main(String[] args) {
		Advanced a = new Advanced();
		a.init();
	}

	public void init() {
		JPanel main = new JPanel(new BorderLayout());
		main.setVisible(true);

		myboard_back = new JPanel(new GridLayout(10, 10)); // creating a layer in myboard panel
		myboard_back.setBounds(0, 0, 500, 500);
		myboard_back.setPreferredSize(new Dimension(500, 500));
		myboard_back.setBounds(0, 0, 500, 500);

		top = new JPanel(new BorderLayout());
		you = new JButton("YOU"); // creating screen and direction buttons and a panel to put them in
		you.setFont(new Font("Arial", Font.PLAIN, 30));
		// you.setForeground(Color.magenta);
		you.setPreferredSize(new Dimension(480, 40));
		you.setBorder(null);
		top.add(you, BorderLayout.LINE_START);

		pc = new JButton("PC"); // creating screen and direction buttons and a panel to put them in
		pc.setFont(new Font("Arial", Font.PLAIN, 30));
		// pc.setForeground(Color.magenta);
		pc.setPreferredSize(new Dimension(480, 40));
		pc.setBorder(null);
		top.add(pc, BorderLayout.LINE_END);

		title = new JButton("Battleship"); // creating screen and direction buttons and a panel to put them in
		title.setFont(new Font("Arial", Font.PLAIN, 40));
		title.setPreferredSize(new Dimension(480, 40));
		title.setBorder(null);
		top.add(title, BorderLayout.CENTER);

		dir = new JButton("HORIZONTAL"); // creating screen and direction buttons and a panel to put them in
		dir.setFont(new Font("Arial", Font.PLAIN, 20));
		dir.setPreferredSize(new Dimension(260, 40));
		dir.addMouseListener(listener);
		screen = new JButton("Put Carrier");
		screen.setFont(new Font("Arial", Font.ITALIC, 30));
		// screen.setForeground(Color.magenta);
		screen.setPreferredSize(new Dimension(260, 200));
		screen.setBorder(null);
		JPanel middle = new JPanel(new BorderLayout());
		middle.add(screen, BorderLayout.PAGE_START);
		middle.add(dir, BorderLayout.PAGE_END);

		opponent_panel = new JPanel(new GridLayout(10, 10)); // creating opponent panel
		opponent_panel.setBounds(0, 0, 500, 500);
		opponent_panel.setPreferredSize(new Dimension(500, 500));
		// opponent_panel.setBounds(opponent_panel.getX(),
		// opponent_panel.getY(),500,500);

		myboard_main = new JLayeredPane(); // creating myboard panel
		myboard_main.setBounds(0, 0, 500, 500);
		myboard_main.setPreferredSize(new Dimension(500, 500));
		myboard_main.add(myboard_back, new Integer("2"));
		// myboard_main.add(myboard_front,new Integer("4"));

		// adding the layered panels to the main border layout
		main.add(top, BorderLayout.PAGE_START);
		main.add(myboard_main, BorderLayout.LINE_START);
		main.add(middle, BorderLayout.CENTER);
		main.add(opponent_panel, BorderLayout.LINE_END);
		// if(myboard!=null && opponent!=null)
		// {
		// System.out.println("first pass");
		print(myboard, myboard_back);
		print(opponent, opponent_panel);
		// opponent_panel.setBounds(opponent[0][0].button.getLocation().x,
		// opponent[0][0].button.getLocation().y,500,500);
		// JButton opponent=new JButton("PC");
		// opponent.add
		// }
		img_ver = null;
		img_hor = null;
		faded_img_ver = null;
		faded_img_hor = null;
		try {
			// img_ver=ImageIO.read( ClassLoader.getSystemResource( "res/ship_ver_2.png" )
			// );
			// img_ver = ImageIO.read(getClass().getResourceAsStream("res/ship_ver_2.png"));
			// img_ver = ImageIO.read(this.getClass().getResource("res/ship_ver_2.png"));
			// img_ver=new
			// BufferedImage(this.getClass().getResource("/images/NotSet_16x16.png"));
			img_ver = ImageIO.read(resource_loader.load("ship_ver_2.png"));
			img_hor = ImageIO.read(resource_loader.load("ship_hor.png"));
			// img_hor=this.class.getResourceAsStream("/"+path);
			faded_img_ver = ImageIO.read(resource_loader.load("ship_ver_faded.png"));
			faded_img_hor = ImageIO.read(resource_loader.load("ship_hor_faded.png"));
			/*
			 * this.img_ver = ImageIO.read(new FileInputStream("res/ship_ver_2.png"));
			 * //this.img_ver=
			 * ImageIO.read(getClass().getResourceAsStream("res/ship_ver_2.png"));
			 * this.img_hor = ImageIO.read(new FileInputStream("res/ship_hor.png"));
			 * this.faded_img_ver = ImageIO.read(new
			 * FileInputStream("res/ship_ver_faded.png")); this.faded_img_hor =
			 * ImageIO.read(new FileInputStream("res/ship_hor_faded.png"));
			 */
			// img_ver = ImageIO.read(new File("res/ship_ver_2.png"));

			// faded_img_ver = ImageIO.read(new File("res/ship_ver_faded.png"));
			// faded_img_hor = ImageIO.read(new File("res/ship_hor_faded.png"));
			/*
			 * img_hor = ImageIO.read(new File(
			 * "/Users/ahmedelehwany/Desktop/Main/Workspace/Battleship/bin/ship_hor.png"));
			 * img_ver = ImageIO.read(new File(
			 * "/Users/ahmedelehwany/Desktop/Main/Workspace/Battleship/bin/ship_ver_2.png"))
			 * ; faded_img_ver = ImageIO.read(new File(
			 * "/Users/ahmedelehwany/Desktop/Main/Workspace/Battleship/bin/ship_ver_faded.png"
			 * )); faded_img_hor = ImageIO.read(new File(
			 * "/Users/ahmedelehwany/Desktop/Main/Workspace/Battleship/bin/ship_hor_faded.png"
			 * ));
			 */
		} catch (IOException e) {
			System.out.println("failed");
		}

		if (img_hor != null) {
			System.out.println("print");
			ship_hor.setIcon(
					new ImageIcon(new ImageIcon(img_hor).getImage().getScaledInstance(250, 80, Image.SCALE_DEFAULT)));
			// ship_hor.setIcon(new ImageIcon(new
			// ImageIcon(img_hor).getImage().getScaledInstance(250, 50,
			// Image.SCALE_DEFAULT)));
			// ship_hor.setIcon(new ImageIcon(new
			// ImageIcon(img_ver).getImage().getScaledInstance(53, 250,
			// Image.SCALE_DEFAULT)));
			// Carrier = new JLabel(new ImageIcon(img_ver));
			if (img_hor != null) {
				System.out.println("print ship");
			}

			System.out.println("ship parent is" + faded_hor.getParent());
			phase = 0;

		}

		frame.add(main);
		frame.setVisible(true);
		frame.setSize(1280, 570);
		screen.setBorder(null);

	}

	MouseListener listener = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			Object obj = e.getSource();
			// int x=e.getLocationOnScreen().x-frame.getX();
			y = e.getLocationOnScreen().y - myboard[0][0].button.getLocationOnScreen().y;
			System.out.println(myboard[0][0].button.getLocationOnScreen().y + " , " + frame.getY());
			System.out.println("original coordinates are :" + x + " " + y);
			// x=x/50;
			y = (y / 50);
			System.out.println("coordinates are :" + x + " " + y);
			JButton button = (JButton) obj;
			System.out.println(button.getText());
			// button.setContentAreaFilled(true);
			if (button.getText().equals("HORIZONTAL")) {
				myboard_main.remove(faded_hor);
				myboard_main.revalidate();
				myboard_main.repaint();
				button.setText("VERTICAL");
			}

			else if (button.getText().equals("VERTICAL")) {
				myboard_main.remove(faded_ver);
				myboard_main.revalidate();
				myboard_main.repaint();
				button.setText("HORIZONTAL");
			} else {
				if (phase == 0) {
					x = e.getLocationOnScreen().x - frame.getX();
					// System.out.println(button.getParent().getName());
					System.out.println(myboard_back);
					if (button.getParent() == myboard_back) {
						x = (x / 50);
						System.out.println("panel");
						switch (ship_phase) {
						case 0:

							if (dir.getText().equals("HORIZONTAL")) {
								if (isValid2(x, y, 0, 5) == true) {
									save(x, y, 5, 0, Carrier_img, Carrier, img_hor);
									myboard_main.remove(faded_hor);
									ship = 1;
									ship_phase = 1;
									screen.setText("Put Battleship");
								}
							} else {
								if (isValid2(x, y, 1, 5) == true) {
									save(x, y, 5, 1, Carrier_img, Carrier, img_ver);
									myboard_main.remove(faded_ver);
									ship = 1;
									ship_phase = 1;
									screen.setText("Put Battleship");
								}
							}

							break;
						case 1:
							if (dir.getText().equals("HORIZONTAL")) {
								if (isValid2(x, y, 0, 4) == true) {
									save(x, y, 4, 0, Battleship_img, Battleship, img_hor);
									myboard_main.remove(faded_hor);
									ship = 2;
									ship_phase = 2;
									screen.setText("Put Frigate");
								}
							} else {
								if (isValid2(x, y, 1, 4) == true) {
									save(x, y, 4, 1, Battleship_img, Battleship, img_ver);
									myboard_main.remove(faded_ver);
									ship = 2;
									ship_phase = 2;
									screen.setText("Put Frigate");
								}
							}

							break;
						case 2:
							if (dir.getText().equals("HORIZONTAL")) {
								if (isValid2(x, y, 0, 3) == true) {
									save(x, y, 3, 0, Frigate_img, Frigate, img_hor);
									myboard_main.remove(faded_hor);
									ship = 3;
									ship_phase = 3;
									screen.setText("Put Submarine");
								}
							} else {
								if (isValid2(x, y, 1, 3) == true) {
									save(x, y, 3, 1, Frigate_img, Frigate, img_ver);
									myboard_main.remove(faded_ver);
									ship = 3;
									ship_phase = 3;
									screen.setText("Put Submarine");
								}
							}

							break;
						case 3:
							if (dir.getText().equals("HORIZONTAL")) {
								if (isValid2(x, y, 0, 3) == true) {
									save(x, y, 3, 0, Submarine_img, Submarine, img_hor);
									myboard_main.remove(faded_hor);
									ship = 4;
									ship_phase = 4;
									screen.setText("Put Minesweeper");
								}
							} else {
								if (isValid2(x, y, 1, 3) == true) {
									save(x, y, 3, 1, Submarine_img, Submarine, img_ver);
									myboard_main.remove(faded_ver);
									ship = 4;
									ship_phase = 4;
									screen.setText("Put Minesweeper");
								}
							}

							break;
						case 4:
							if (dir.getText().equals("HORIZONTAL")) {
								if (isValid2(x, y, 0, 2) == true) {
									save(x, y, 2, 0, Minesweeper_img, Minesweeper, img_hor);
									myboard_main.remove(faded_hor);
									screen.setText("Your turn");
									screen.setBorder(null);
									putship();
									phase = 1;
								}
							} else {
								if (isValid2(x, y, 1, 2) == true) {
									save(x, y, 2, 1, Minesweeper_img, Minesweeper, img_ver);
									myboard_main.remove(faded_ver);
									screen.setText("Your turn");
									screen.setBorder(null);
									putship();
									phase = 1;
								}
							}

							break;
						}

					}
					screen.setBorder(null);
				} else if (phase == 1) // GAME STARTS
				{
					x = (e.getLocationOnScreen().x - opponent[0][0].button.getLocationOnScreen().x) / 50;
					// System.out.println("phase 1");
					if (button.getParent() == opponent_panel) {
						System.out.println(x + "," + y);
						// x=x*15;
						System.out.println(x + "," + y);
						if (end_game == false) {

							if (opponent[y][x].button.getBackground() != Color.BLUE) {
								if (opponent[y][x].marked == true) {
									opponent[y][x].button.setBackground(Color.RED);

								} else if (opponent[y][x].button.getBackground() == Color.BLUE) {
									;
								} else {
									opponent[y][x].button.setBackground(Color.BLUE);
									screen.setText("PC's turn");
									// turn=1;

								}

								if (Carrier2.full() == true && Carrier2.sunk == false) {
									screen.setText("Carrier sunk");
									Carrier2.sunk = true;
								}
								if (Battleship2.full() == true && Battleship2.sunk == false) {
									screen.setText("Battleship sunk");
									Battleship2.sunk = true;
								}
								if (Frigate2.full() == true && Frigate2.sunk == false) {
									screen.setText("Frigate sunk");
									Frigate2.sunk = true;
								}
								if (Submarine2.full() == true && Submarine2.sunk == false) {
									screen.setText("Submarine sunk");
									Submarine2.sunk = true;
								}
								if (Minesweeper2.full() == true && Minesweeper2.sunk == false) {
									screen.setText("Minesweeper sunk");
									Minesweeper2.sunk = true;
								}

								if ((Carrier2.sunk == true) && (Battleship2.sunk == true) && (Frigate2.sunk == true)
										&& (Submarine2.sunk == true) && (Minesweeper2.sunk == true)) {
									screen.setText("YOU WIN");
									end_game = true;
								}
							}
							/////////////
							/////////////////
							///////////////////////

//							Timer timer = new Timer(1000, new ActionListener() {
//								@Override
//								public void actionPerformed(ActionEvent e) {
									if (end_game == false) {

										// public void actionPerformed(ActionEvent e)
										// {
										if (opponent[y][x].button.getBackground() != Color.RED) {
											screen.setText("Your Turn");
											screen.setBorder(null);
											// System.out.println("ref"+ref_x+" , "+ref_y+"side is"+side);
											int endloop = 0;
											do {
												if (algr == 1) {
													x = ref_x;
													y = ref_y;
													System.out.println(x + " " + y);
												} else {

													do {
														System.out.println("big loop");
														Random randx = new Random();
														Random randy = new Random();
														x = randx.nextInt(10);
														y = randy.nextInt(10);
														// System.out.println(x+" "+y);
													} while ((myboard[y][x].button.getBackground() == Color.RED)
															|| (myboard[y][x].button.getBackground() == Color.BLUE));
													// }
													// if(myboard[y][x].ship.full()==false){System.out.println("null
													// ship");}
												}

//												Timer timer2 = new Timer(200, new ActionListener() {
//													@Override
//													public void actionPerformed(ActionEvent e) {
//													}
//												});
//												timer2.start();

												do {
													
													endloop = 0;
													System.out.println("loop starts" + x + " ," + y);
													// side=side%4;
													if (myboard[y][x].marked == true) {
														System.out.println(x + "," + y + " " + "marked button");
														myboard[y][x].button.setBackground(Color.RED);
														// algr=1;
														// myboard[y][x].marked=false;

														// }

														if (algr == 1
																&& myboard[ref_y][ref_x].ship != myboard[y][x].ship) {
															// System.out.println("dif ship");

															x = ref_x;
															y = ref_y;
															side = (side + 1) % 4;
														}

														if (algr == 0) {
															algr = 1;
															System.out.println("ALGR ON");
															side = 0;
															ref_x = x;
															ref_y = y;
															// System.out.println("ref"+ref_x+" , "+ref_y);
														}
														// System.out.println("ship is "+myboard[y][x].ship.ship_len);

														// hereeeeeeeeeeee
														if (myboard[y][x].ship.full() == true) {
															System.out.println("ship sunk");
															algr = 0;
															side = 0;
															myboard[y][x].ship.sunk = true;
														}
														// myboard[y][x].button.setFilled(true);
														// add(myboard[y][x].button);
														if (algr == 1) {
															do {

																System.out.println("loop");
																if (y > 9 || x > 9 || y < 0 || x < 0
																		|| myboard[y][x].button
																				.getBackground() == Color.BLUE) {
																	x = ref_x;
																	y = ref_y;
																	side++;
																}
																switch (side) {
																case 0: {
																	y--;
																	break;
																} // up
																case 1: {
																	y++;
																	break;
																} // down
																case 2: {
																	x++;
																	break;
																} // right
																case 3: {
																	x--;
																	break;
																} // left
																}
															} while (y > 9 || x > 9 || y < 0 || x < 0
																	|| myboard[y][x].button
																			.getBackground() == Color.BLUE);

														}
														if (loss() == true) {
															screen.setText("YOU LOSE");
															screen.setBorder(null);
														}

													}

													else {

														// System.out.println("else side is "+side);
														myboard[y][x].button.setBackground(Color.BLUE);

														// if(algr==1){side++;side=side%4;System.out.println("side
														// inc");}
														// System.out.println("else side is "+side);
														endloop = 1;
														// if(myboard[y][x].ship==null){algr=0;}
														// myboard[y][x].button.setFilled(true);
														// add(myboard[y][x]);
													}
													/*
													 * if(x<=9 || x>=0 || y<=9 || y>=0) { endloop=1; side=(side+1)%4; }
													 */

													System.out.println(endloop + " " + algr);

												} while (endloop == 0 && algr == 1 && x <= 9 && x >= 0 && y <= 9
														&& y >= 0
														&& myboard[y][x].button.getBackground() != Color.BLUE);
												System.out.println("end loop");
												side = (side + 1) % 4;
											} while (endloop == 0);
											System.out.println("FINISH");
										}

									}
//								}
//							});
//							timer.setRepeats(false);
//							timer.start();
									try {
										wait(1000);
									} catch (InterruptedException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}

						}

					}

				}
			}

			screen.setBorder(null);
		}

	};

	MouseMotionListener listener2 = new MouseAdapter() {
		@Override
		public void mouseMoved(MouseEvent e) {
			// System.out.println("mouse moved");
			int new_x = e.getLocationOnScreen().x - frame.getX();
			int new_y = e.getLocationOnScreen().y - myboard[0][0].button.getLocationOnScreen().y;
			new_x = new_x / 50;
			new_y = new_y / 50;
			Object obj = e.getSource();
			JButton button = (JButton) obj;
			// if(new_x!=x_mov || new_y!=y_mov)
			// {

			// System.out.println("phase is "+phase);
			if (button.getParent() == myboard_back) {
				System.out.println("myboard");
				if (phase == 0) {

					System.out.println(new_x + " , " + new_y);
					System.out.println(x_mov + " , " + y_mov);
					// if(new_x <500 && new_y<500)
					// {
					// System.out.println("outer cond");

					switch (ship) {
					case 0:
						if (dir.getText() == "HORIZONTAL") {
							System.out.println("horizontal");
							faded_ver.setVisible(false);
							if (isValid2(new_x, new_y, 0, 5) == true && isValid2(x_mov, y_mov, 0, 5) == true) {

								// myboard_main.remove(ship_hor);
								// System.out.println("pass");
								/*
								 * if(x_mov!=new_x || y_mov!=new_y) { myboard_main.remove(faded_hor); }
								 */
								faded_hor.setIcon(new ImageIcon(new ImageIcon(faded_img_hor).getImage()
										.getScaledInstance(5 * 50, 50, Image.SCALE_DEFAULT)));
								faded_hor.setPreferredSize(new Dimension(5 * 50, 50));
								faded_hor.setBounds(new_x * 50, new_y * 50, 5 * 50, 50);
								faded_hor.setVisible(true);
								// faded_hor.setSize(250, 200);
								if (faded_hor.getParent() == null) {
									myboard_main.add(faded_hor, new Integer("3"));
								}
								x_mov = new_x;
								y_mov = new_y;
							} else {
								System.out.println("outaaaa");

								// myboard_main.remove(faded_hor);
								faded_hor.setVisible(false);
								y_mov = 0;
								x_mov = 0;
								// System.out.println("end else");

							}
						} else // VERTICAL
						{

							// faded_hor.setVisible(false);
							if (isValid2(new_x, new_y, 1, 5) == true && isValid2(x_mov, y_mov, 1, 5) == true) {
								// System.out.println(new_y);
								/*
								 * for(int i=0;i<5;i++) { //if(x_mov!=0 || y_mov!=0) //{
								 * if((x_mov+i+y_mov)%2==0)
								 * {myboard[y_mov+i][x_mov].button.setBackground(Color.DARK_GRAY);}//reset even
								 * else{myboard[y_mov+i][x_mov].button.setBackground(Color.GRAY);}//reset odd
								 * //myboard[new_y+i][new_x].button.setBackground(Color.YELLOW); //} }
								 */
								faded_ver.setIcon(new ImageIcon(new ImageIcon(faded_img_ver).getImage()
										.getScaledInstance(50, 5 * 50, Image.SCALE_DEFAULT)));
								faded_ver.setPreferredSize(new Dimension(50, 5 * 50));
								faded_ver.setBounds(new_x * 50, new_y * 50, 50, 5 * 50);
								faded_ver.setVisible(true);
								if (faded_ver.getParent() == null) {
									myboard_main.add(faded_ver, new Integer("3"));
								}
								// ship_img.setIcon(new ImageIcon(new
								// ImageIcon(img).getImage().getScaledInstance(50, length*50,
								// Image.SCALE_DEFAULT)));

								x_mov = new_x;
								y_mov = new_y;
							} else {
								System.out.println("outaaaa");

								faded_ver.setVisible(false);
								// System.out.println("end else");
								y_mov = 0;
								x_mov = 0;
							}
						}
						break;
					case 1:
						if (dir.getText() == "HORIZONTAL") {
							// System.out.println("hor case 1");
							// System.out.println(new_x+" "+new_y+" "+x_mov+" "+y_mov);
							if (isValid2(new_x, new_y, 0, 4) == true) {
								// System.out.println("mark");
								/*
								 * for(int i=0;i<4;i++) { //if(x_mov!=0 || y_mov!=0) //{
								 * if((x_mov+i+y_mov)%2==0)
								 * {myboard[y_mov][x_mov+i].button.setBackground(Color.DARK_GRAY);}//reset even
								 * else{myboard[y_mov][x_mov+i].button.setBackground(Color.GRAY);}//reset odd
								 * //myboard[new_y][new_x+i].button.setBackground(Color.YELLOW); //} }
								 */
								faded_hor.setIcon(new ImageIcon(new ImageIcon(faded_img_hor).getImage()
										.getScaledInstance(4 * 50, 50, Image.SCALE_DEFAULT)));
								faded_hor.setPreferredSize(new Dimension(4 * 50, 50));
								faded_hor.setBounds(new_x * 50, new_y * 50, 4 * 50, 50);
								faded_hor.setVisible(true);
								// faded_hor.setSize(250, 200);
								if (faded_hor.getParent() == null) {
									myboard_main.add(faded_hor, new Integer("3"));
								}
								x_mov = new_x;
								y_mov = new_y;
							} else {
								// System.out.println("outaaaa");

								faded_hor.setVisible(false);
								y_mov = 0;
								x_mov = 0;
							}
						} else // VERTICAL
						{
							// System.out.println("vertical code");
							if (isValid2(new_x, new_y, 1, 4) == true) {
								System.out.println(new_y);
								/*
								 * for(int i=0;i<4;i++) { //if(x_mov!=0 || y_mov!=0) //{
								 * if((x_mov+i+y_mov)%2==0)
								 * {myboard[y_mov+i][x_mov].button.setBackground(Color.DARK_GRAY);}//reset even
								 * else{myboard[y_mov+i][x_mov].button.setBackground(Color.GRAY);}//reset odd
								 * //myboard[new_y+i][new_x].button.setBackground(Color.YELLOW); //} }
								 */
								faded_ver.setIcon(new ImageIcon(new ImageIcon(faded_img_ver).getImage()
										.getScaledInstance(50, 4 * 50, Image.SCALE_DEFAULT)));
								faded_ver.setPreferredSize(new Dimension(50, 4 * 50));
								faded_ver.setBounds(new_x * 50, new_y * 50, 50, 4 * 50);
								faded_ver.setVisible(true);
								if (faded_ver.getParent() == null) {
									myboard_main.add(faded_ver, new Integer("3"));
								}
								x_mov = new_x;
								y_mov = new_y;
							} else {
								// System.out.println("outaaaa");

								faded_ver.setVisible(false);
								y_mov = 0;
								x_mov = 0;
							}
						}
						break;
					case 2:
					case 3:
						if (dir.getText() == "HORIZONTAL") {
							if (isValid2(new_x, new_y, 0, 3) == true) {
								// System.out.println(new_y);
								/*
								 * for(int i=0;i<3;i++) { //if(x_mov!=0 || y_mov!=0) //{
								 * if((x_mov+i+y_mov)%2==0)
								 * {myboard[y_mov][x_mov+i].button.setBackground(Color.DARK_GRAY);}//reset even
								 * else{myboard[y_mov][x_mov+i].button.setBackground(Color.GRAY);}//reset odd
								 * //myboard[new_y][new_x+i].button.setBackground(Color.YELLOW); //} }
								 */
								faded_hor.setIcon(new ImageIcon(new ImageIcon(faded_img_hor).getImage()
										.getScaledInstance(3 * 50, 50, Image.SCALE_DEFAULT)));
								faded_hor.setPreferredSize(new Dimension(3 * 50, 50));
								faded_hor.setBounds(new_x * 50, new_y * 50, 3 * 50, 50);
								faded_hor.setVisible(true);
								// faded_hor.setSize(250, 200);
								if (faded_hor.getParent() == null) {
									myboard_main.add(faded_hor, new Integer("3"));
								}
								x_mov = new_x;
								y_mov = new_y;
							} else {
								// System.out.println("outaaaa");

								faded_hor.setVisible(false);
								y_mov = 0;
								x_mov = 0;
							}
						} else // VERTICAL
						{
							// System.out.println("vertical code");
							if (isValid2(new_x, new_y, 1, 3) == true) {
								System.out.println(new_y);
								/*
								 * for(int i=0;i<3;i++) { //if(x_mov!=0 || y_mov!=0) //{
								 * if((x_mov+i+y_mov)%2==0)
								 * {myboard[y_mov+i][x_mov].button.setBackground(Color.DARK_GRAY);}//reset even
								 * else{myboard[y_mov+i][x_mov].button.setBackground(Color.GRAY);}//reset odd
								 * //myboard[new_y+i][new_x].button.setBackground(Color.YELLOW); //} }
								 */
								faded_ver.setIcon(new ImageIcon(new ImageIcon(faded_img_ver).getImage()
										.getScaledInstance(50, 3 * 50, Image.SCALE_DEFAULT)));
								faded_ver.setPreferredSize(new Dimension(50, 3 * 50));
								faded_ver.setBounds(new_x * 50, new_y * 50, 50, 3 * 50);
								faded_ver.setVisible(true);
								if (faded_ver.getParent() == null) {
									myboard_main.add(faded_ver, new Integer("3"));
								}
								x_mov = new_x;
								y_mov = new_y;
							} else {
								// System.out.println("outaaaa");

								faded_ver.setVisible(false);
								y_mov = 0;
								x_mov = 0;
							}
						}
						break;

					case 4:
						if (dir.getText() == "HORIZONTAL") {
							if (isValid2(new_x, new_y, 0, 2) == true) {
								// System.out.println(new_y);
								/*
								 * for(int i=0;i<2;i++) { //if(x_mov!=0 || y_mov!=0) //{
								 * if((x_mov+i+y_mov)%2==0)
								 * {myboard[y_mov][x_mov+i].button.setBackground(Color.DARK_GRAY);}//reset even
								 * else{myboard[y_mov][x_mov+i].button.setBackground(Color.GRAY);}//reset odd
								 * //myboard[new_y][new_x+i].button.setBackground(Color.YELLOW); //} }
								 */
								faded_hor.setIcon(new ImageIcon(new ImageIcon(faded_img_hor).getImage()
										.getScaledInstance(2 * 50, 50, Image.SCALE_DEFAULT)));
								faded_hor.setPreferredSize(new Dimension(2 * 50, 50));
								faded_hor.setBounds(new_x * 50, new_y * 50, 2 * 50, 50);
								faded_hor.setVisible(true);
								// faded_hor.setSize(250, 200);
								if (faded_hor.getParent() == null) {
									myboard_main.add(faded_hor, new Integer("3"));
								}
								x_mov = new_x;
								y_mov = new_y;
							} else {
								// System.out.println("outaaaa");

								faded_hor.setVisible(false);
								y_mov = 0;
								x_mov = 0;
							}
						} else // VERTICAL
						{
							// System.out.println("vertical code");
							if (isValid2(new_x, new_y, 1, 2) == true) {
								System.out.println(new_y);
								/*
								 * for(int i=0;i<2;i++) { //if(x_mov!=0 || y_mov!=0) //{
								 * if((x_mov+i+y_mov)%2==0)
								 * {myboard[y_mov+i][x_mov].button.setBackground(Color.DARK_GRAY);}//reset even
								 * else{myboard[y_mov+i][x_mov].button.setBackground(Color.GRAY);}//reset odd
								 * //myboard[new_y+i][new_x].button.setBackground(Color.YELLOW); //} }
								 */
								faded_ver.setIcon(new ImageIcon(new ImageIcon(faded_img_ver).getImage()
										.getScaledInstance(50, 2 * 50, Image.SCALE_DEFAULT)));
								faded_ver.setPreferredSize(new Dimension(50, 2 * 50));
								faded_ver.setBounds(new_x * 50, new_y * 50, 50, 2 * 50);
								faded_ver.setVisible(true);
								if (faded_ver.getParent() == null) {
									myboard_main.add(faded_ver, new Integer("3"));
								}
								x_mov = new_x;
								y_mov = new_y;
							} else {
								// System.out.println("outaaaa");

								faded_ver.setVisible(false);
								y_mov = 0;
								x_mov = 0;
							}
						}
						break;

					}
				}
			}

			// else
			// {

			// }
		}
	};

	public void print(myJButton[][] array, JPanel panel) {
		// System.out.println("printaaaaaaaaaaa");
		for (int j = 0; j < 10; j++) {
			for (int i = 0; i < 10; i++) {
				array[j][i] = new myJButton();

				if ((i + j) % 2 == 0) {
					array[j][i].button.setBackground(Color.DARK_GRAY);
				} else {
					array[j][i].button.setBackground(Color.GRAY);

				}
				array[j][i].button.addMouseListener(listener);
				array[j][i].button.addMouseMotionListener(listener2);
				array[j][i].button.setOpaque(true);
				array[j][i].button.setPreferredSize(new Dimension(50, 50));
				array[j][i].button.setBorder(null);

				panel.add(array[j][i].button);
			}

		}

	}

	public void save(int x, int y, int length, int dir, JLabel ship_img, ship_type ship, BufferedImage img) {
		if (dir == 0) {
			for (int i = 0; i < length; i++) {
				myboard[y][x + i].marked = true;
				myboard[y][x + i].ship = ship;
				ship.array[i] = myboard[y][x + i].button;
			}
			ship_img.setIcon(new ImageIcon(
					new ImageIcon(img).getImage().getScaledInstance(length * 50, 50, Image.SCALE_DEFAULT)));
			ship_img.setPreferredSize(new Dimension(length * 50, 50));
			ship_img.setBounds(x * 50, y * 50, length * 50, 50);
			myboard_main.add(ship_img, new Integer("3"));
		} else {
			for (int i = 0; i < length; i++) {

				myboard[y + i][x].marked = true;
				myboard[y + i][x].ship = ship;
				ship.array[i] = myboard[y + i][x].button;

			}
			ship_img.setIcon(new ImageIcon(
					new ImageIcon(img).getImage().getScaledInstance(50, length * 50, Image.SCALE_DEFAULT)));
			ship_img.setPreferredSize(new Dimension(50, length * 50));
			ship_img.setBounds(x * 50, y * 50, 50, length * 50);
			myboard_main.add(ship_img, new Integer("3"));
		}
	}

	public boolean isValid(int x, int y, int dir, int shiplen) {
		if (dir == 0)// horizontal
		{
			for (int i = 0; i < shiplen; i++) {
				if (opponent[y][x + i].marked == true) {
					return false;
				}
			}
		} else {
			for (int i = 0; i < shiplen; i++) {
				if (opponent[y - i][x].marked == true) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean isValid2(int x, int y, int dir, int shiplen) {
		if (dir == 0)// horizontal
		{
			for (int i = 0; i < shiplen; i++) {
				if (y > 9 || y < 0 || x + i > 9 || x + i < 0 || myboard[y][x + i].marked == true) {
					System.out.println("not valid");
					return false;
				}
			}
		} else {
			for (int i = 0; i < shiplen; i++) {
				if (y + i > 9 || y + i < 0 || x > 9 || x < 0 || myboard[y + i][x].marked == true) {
					return false;
				}
			}
		}
		System.out.println("success");
		return true;
	}

	public void putship() {
		// System.out.println("putship");
		Random randx[] = new Random[5];
		Random randy[] = new Random[5];
		Random randir[] = new Random[5];
		int x[] = new int[5];
		int y[] = new int[5];
		int dir[] = new int[5];
		for (int i = 0; i < 5; i++) {
			randx[i] = new Random();
			randy[i] = new Random();
			randir[i] = new Random();
			dir[i] = randir[i].nextInt(2);
			// System.out.println(dir[i]);
		}
		// put carrier2 #5
		x[0] = randx[0].nextInt(6);
		y[0] = 4 + randy[0].nextInt(6);

		// System.out.println("putship2");

		if (dir[0] == 0) // horizontal
		{
			for (int i = 0; i < 5; i++) {
				opponent[y[0]][x[0] + i].marked = true;
				// opponent[y[0]][x[0]+i].button.setBackground(Color.YELLOW);
				Carrier2.array[i] = opponent[y[0]][x[0] + i].button;
			}
		} else // vertical
		{
			for (int i = 0; i < 5; i++) {
				opponent[y[0] - i][x[0]].marked = true;
				// opponent[y[0]-i][x[0]].button.setBackground(Color.YELLOW);
				Carrier2.array[i] = opponent[y[0] - i][x[0]].button;
			}
		}
		// put Battleship2 #4
		do {
			x[1] = randx[1].nextInt(7);
			y[1] = 3 + randy[1].nextInt(7);
		} while (isValid(x[1], y[1], dir[1], 4) == false);
		if (dir[1] == 0) // horizontal
		{
			for (int i = 0; i < 4; i++) {
				opponent[y[1]][x[1] + i].marked = true;
				// opponent[y[1]][x[1]+i].button.setBackground(Color.YELLOW);
				Battleship2.array[i] = opponent[y[1]][x[1] + i].button;
			}
		} else // vertical
		{
			for (int i = 0; i < 4; i++) {
				opponent[y[1] - i][x[1]].marked = true;
				// opponent[y[1]-i][x[1]].button.setBackground(Color.YELLOW);
				Battleship2.array[i] = opponent[y[1] - i][x[1]].button;
			}
		}
		// put Frigate2 #3
		do {
			x[2] = randx[2].nextInt(8);
			y[2] = 2 + randy[2].nextInt(8);
		} while (isValid(x[2], y[2], dir[2], 3) == false);
		if (dir[2] == 0) // horizontal
		{
			for (int i = 0; i < 3; i++) {
				opponent[y[2]][x[2] + i].marked = true;
				// opponent[y[2]][x[2]+i].button.setBackground(Color.YELLOW);
				Frigate2.array[i] = opponent[y[2]][x[2] + i].button;
			}
		} else // vertical
		{
			for (int i = 0; i < 3; i++) {
				opponent[y[2] - i][x[2]].marked = true;
				// opponent[y[2]-i][x[2]].button.setBackground(Color.YELLOW);
				Frigate2.array[i] = opponent[y[2] - i][x[2]].button;
			}
		}
		// put Submarine2 #3
		do {
			x[3] = randx[3].nextInt(8);
			y[3] = 2 + randy[3].nextInt(8);
		} while (isValid(x[3], y[3], dir[3], 3) == false);
		if (dir[3] == 0) // horizontal
		{
			for (int i = 0; i < 3; i++) {
				opponent[y[3]][x[3] + i].marked = true;
				// opponent[y[3]][x[3]+i].button.setBackground(Color.YELLOW);
				Submarine2.array[i] = opponent[y[3]][x[3] + i].button;
			}
		} else // vertical
		{
			for (int i = 0; i < 3; i++) {
				opponent[y[3] - i][x[3]].marked = true;
				// opponent[y[3]-i][x[3]].button.setBackground(Color.YELLOW);
				Submarine2.array[i] = opponent[y[3] - i][x[3]].button;

			}
		}
		// put Minesweeper2 #2
		do {
			x[4] = randx[4].nextInt(9);
			y[4] = 1 + randy[4].nextInt(9);
		} while (isValid(x[4], y[4], dir[4], 2) == false);

		if (dir[4] == 0) // horizontal
		{
			for (int i = 0; i < 2; i++) {
				opponent[y[4]][x[4] + i].marked = true;
				// opponent[y[4]][x[4]+i].button.setBackground(Color.YELLOW);
				Minesweeper2.array[i] = opponent[y[4]][x[4] + i].button;
			}
		} else // vertical
		{
			for (int i = 0; i < 2; i++) {
				opponent[y[4] - i][x[4]].marked = true;
				// opponent[y[4]-i][x[4]].button.setBackground(Color.YELLOW);
				Minesweeper2.array[i] = opponent[y[4] - i][x[4]].button;
			}
		}
	}

	public boolean loss() {
		if (Carrier.full() != true) {
			return false;
		}
		if (Battleship.full() != true) {
			return false;
		}
		if (Frigate.full() != true) {
			return false;
		}
		if (Submarine.full() != true) {
			return false;
		}
		if (Minesweeper.full() != true) {
			return false;
		}
		end_game = true;
		return true;

	}

}
