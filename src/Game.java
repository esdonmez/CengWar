import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import enigma.console.TextAttributes;
import enigma.event.TextMouseEvent;
import enigma.event.TextMouseListener;

public class Game {

	Queue<Object> ProductionQueue = new Queue<Object>();
	static List<CengMan> player_cengmans = new ArrayList<>();
	static List<CengMan> computer_cengmans = new ArrayList<>();
	static List<Cement> player_cement = new ArrayList<>();
	static List<Tree> player_tree = new ArrayList<>();
	static List<Cement> computer_cement = new ArrayList<>();
	static List<Tree> computer_tree = new ArrayList<>();
	static List<Wall> computer_wall = new ArrayList<>();
	static List<Wall> player_wall = new ArrayList<>();
	static List<Food> player_food = new ArrayList<>();
	
	
	Queue<Node> tempQueue = new Queue<Node>();

	static String map = "map.txt";
	Maze maze = new Maze();
	static long time = System.currentTimeMillis();
	static boolean flag = true;
	static Random rnd = new Random();

	static boolean Clickable = false;

	static boolean Pressed_G = false; // Go
	static boolean Pressed_E = false; // Extract
	static boolean Pressed_F = false; // Feed
	static boolean Pressed_W = false; // Walk

	int create_time = 0;
	int takeTime = 500;
	int counter = 0;

	Node[][] node = new Node[19][49];

	Base player_base, computer_base;

	static int count_pc = 0;
	static int count_pt = 0;
	static int count_cc = 0;
	static int count_pce = 0;
	static int count_pf = 0;
	static int count_pw = 0;
	static int count_ct = 0;
	static int count_cw = 0;
	

	String str = "";

	public TextMouseListener tmlis;
	public int mousepr; // mouse pressed?
	public int mousex, mousey; // mouse text coords.

	public Game() throws IOException, InterruptedException {
		// Starting Initializations
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 49; j++) {
				node[i][j] = new Node();
			}
		}
		maze.initializeCengmanMaze();

		startScreen();

		maze = new Maze();
		maze.CreateMaze();
		
		createPlayerBase();
		createPlayerTree();
		createComputerBase();
		createComputerTree();
		
		for (int i = 0; i < 3; i++) {
			createPlayerCengman();
		}

		for (int i = 0; i < 3; i++) {
			createComputerCengman();
		}

		initializeNodes();
		viewRangePlayer();
		viewRangeComputer();

		tmlis = new TextMouseListener() {
			public void mouseClicked(TextMouseEvent arg0) {
			}

			public void mousePressed(TextMouseEvent arg0) {
				if (mousepr == 0) {
					mousepr = 1;
					mousex = arg0.getX();
					mousey = arg0.getY();
				}
			}

			public void mouseReleased(TextMouseEvent arg0) {
			}
		};
		Main.cn.getTextWindow().addTextMouseListener(tmlis);
		
		// Game
		while (true) {
			if (time + takeTime < System.currentTimeMillis()) {
				time = System.currentTimeMillis();
				counter++; // clock counter
				Main.cn.getTextWindow().setCursorPosition(50, 1);
				Main.cn.getTextWindow().output("Time = " + counter, new TextAttributes(Color.WHITE, Color.BLACK));
				
				clearBag();
				clearQueue();
				followPath();
				viewRangePlayer();
				viewRangeComputer();
				attackCengman();
				attackBaseCengman();
				attackTreeCengman();
				creatingFood();
				IsUseful();
				initializeNodes1();
				maze.DisplayMaze();
				screen();

				controlProductionQueue();
				ProductionQueuePrint();
			}



			if (mousepr == 1 && mousex >= 59 && mousex <= 61 && mousey >= 4 && mousey <= 6) // Cengman
			{
				ProductionQueue.enqueue("C");
				Main.cn.getTextWindow().setCursorPosition(60, 5);
				Main.cn.getTextWindow().output("C", new TextAttributes(Color.BLACK, Color.WHITE));
				Thread.sleep(250);
				mousepr = 0;
				ProductionQueuePrint();
			}
			if (mousepr == 1 && mousex >= 54 && mousex <= 56 && mousey >= 4 && mousey <= 6) // Tree
			{
				ProductionQueue.enqueue("T");
				Main.cn.getTextWindow().setCursorPosition(55, 5);
				Main.cn.getTextWindow().output("T", new TextAttributes(Color.BLACK, Color.WHITE));
				Thread.sleep(250);
				mousepr = 0;
				ProductionQueuePrint();
			}
			if (mousepr == 1 && mousex >= 64 && mousex <= 66 && mousey >= 4 && mousey <= 6) // Cement
			{
				ProductionQueue.enqueue("W");
				Main.cn.getTextWindow().setCursorPosition(65, 5);
				Main.cn.getTextWindow().output("W", new TextAttributes(Color.BLACK, Color.WHITE));
				Thread.sleep(250);
				mousepr = 0;
				ProductionQueuePrint();
			}
			if (mousepr == 1 && mousex >= 69 && mousex <= 71 && mousey >= 4 && mousey <= 6) // Repair
			{
				ProductionQueue.enqueue("R");
				Main.cn.getTextWindow().setCursorPosition(70, 5);
				Main.cn.getTextWindow().output("R", new TextAttributes(Color.BLACK, Color.WHITE));
				Thread.sleep(250);
				mousepr = 0;
				ProductionQueuePrint();
			}
			

			if (mousepr == 1 && mousex >= 55 && mousex <= 57 && mousey >= 22 && mousey <= 24) // Go button
			{
				for (int i = 0; i < count_pc; i++) {
					if (player_cengmans != null && player_cengmans.get(i) != null && player_cengmans.get(i).isClicked() == true && Clickable == true) {
						Main.cn.getTextWindow().setCursorPosition(56, 23);
						Main.cn.getTextWindow().output("G", new TextAttributes(Color.black, Color.white));
						Clickable = false;
						Pressed_G = true;
						Thread.sleep(250);
						mousepr = 0;
						break;
					}
				}
				mousepr = 0;
			}
			
			if (mousepr == 1 && mousex >= 60 && mousex <= 63 && mousey >= 22 && mousey <= 24) // Extract button
			{
				for (int i = 0; i < count_pc; i++) {
					if (player_cengmans != null && player_cengmans.get(i) != null && player_cengmans.get(i).isClicked() == true && Clickable == true) {
						Main.cn.getTextWindow().setCursorPosition(62, 23);
						Main.cn.getTextWindow().output("E", new TextAttributes(Color.black, Color.white));
						Clickable = false;
						Pressed_E = true;
						extractFromBag(player_cengmans.get(i));
						Thread.sleep(250);
						mousepr = 0;
						break;
					}
				}
				mousepr = 0;
			}
			
			if (mousepr == 1 && mousex >= 66 && mousex <= 69 && mousey >= 22 && mousey <= 24) // Feed button
			{
				for (int i = 0; i < count_pc; i++) {
					if (player_cengmans != null && player_cengmans.get(i) != null && player_cengmans.get(i).isClicked() == true && Clickable == true) {
						Main.cn.getTextWindow().setCursorPosition(68, 23);
						Main.cn.getTextWindow().output("F", new TextAttributes(Color.black, Color.white));
						Clickable = false;
						Pressed_F = true;
						Thread.sleep(250);
						mousepr = 0;
						break;
					}
				}
				mousepr = 0;
			}
			
			if (mousepr == 1 && mousex >= 0 && mousex <= 48 && mousey >= 5 && mousey < 24) // Wall
			{
				if (Maze.Maze[mousey - 5][mousex] == "#")
				{
					for(int i = 0 ; i < count_pc; i++)
					{
						if(player_cengmans.get(i).isClicked() == true)
						{
							player_cengmans.get(i).setClicked(false);
							mousepr = 0;
							break;
						}
					}
					Clickable = false;
					Pressed_G = false; // Go
					Pressed_E = false; // Extract
					Pressed_F = false; // Feed
					Pressed_W = false; // Walk
				}
				mousepr = 0;
			}
			
			if (mousepr == 1 && mousex >= 72 && mousex <= 75 && mousey >= 22 && mousey <= 24) // Wall button
			{
				for (int i = 0; i < count_pc; i++) {
					if (player_cengmans != null && player_cengmans.get(i) != null && player_cengmans.get(i).isClicked() == true && Clickable == true) {
						Main.cn.getTextWindow().setCursorPosition(74, 23);
						Main.cn.getTextWindow().output("W", new TextAttributes(Color.black, Color.white));
						Clickable = false;
						Pressed_W = true;
						Thread.sleep(250);
						mousepr = 0;
						break;
					}
				}
				mousepr = 0;
			}
			
			if (mousepr == 1 && mousex >= 0 && mousex <= 48 && mousey >= 5 && mousey < 24 && Maze.Maze[mousey - 5][mousex] == "pc") { // cengman
				for (int i = 0; i < count_pc; i++) {
					if (player_cengmans != null && player_cengmans.get(i) != null
							&& player_cengmans.get(i).getLocx() == mousey - 5
							&& player_cengmans.get(i).getLocy() == mousex) {
						Main.cn.getTextWindow().setCursorPosition(66, 12);
						Main.cn.getTextWindow().output("" + player_cengmans.get(i).getID(),
								new TextAttributes(Color.white, Color.black));
						Main.cn.getTextWindow().setCursorPosition(65, 13);
						Main.cn.getTextWindow().output("" + player_cengmans.get(i).getLife(),
								new TextAttributes(Color.white, Color.black));
						Main.cn.getTextWindow().setCursorPosition(65, 14);
						Main.cn.getTextWindow().output("" + player_cengmans.get(i).getLevel(),
								new TextAttributes(Color.white, Color.black));
						player_cengmans.get(i).setClicked(true);
						Clickable = true;
						Main.cn.getTextWindow().output(Integer.toString(player_cengmans.get(i).getLevel()),
								new TextAttributes(Color.black, Color.white));
						mousepr = 0;
						break;
					}
				}
				mousepr = 0;
			}

			if (mousepr == 1 && mousex >= 0 && mousex <= 48 && mousey >= 5 && mousey < 24 && Maze.Maze[mousey - 5][mousex] == " ") { // Go to an empty node
				for (int i = 0; i < count_pc; i++) {
					if (player_cengmans != null && player_cengmans.get(i) != null && player_cengmans.get(i).isClicked() == true && Clickable == false && Pressed_G == true) {
						Clickable = false;
						Pressed_G = false;
						player_cengmans.get(i).setFinalx(mousey - 5);
						player_cengmans.get(i).setFinaly(mousex);
						DrawPath(player_cengmans.get(i));
						player_cengmans.get(i).setClicked(false);

						mousepr = 0;
						break;
					}
				}
				mousepr = 0;
			}

			if (mousepr == 1 && mousex >= 0 && mousex <= 48 && mousey >= 5 && mousey < 24 && Maze.Maze[mousey - 5][mousex] == "pt") { // Go to player tree
				for (int i = 0; i < player_tree.size(); i++) {
					if (player_tree.get(i) != null && player_tree.get(i).getX() == mousey -5 && player_tree.get(i).getY() == mousex) {
						for (int j = 0; j < count_pc; j++) {
							if (player_cengmans != null && player_cengmans.get(j) != null && player_cengmans.get(j).isClicked() == true && Clickable == false && Pressed_G == true) {
								Clickable = false;
								Pressed_G = false;
								player_cengmans.get(j).setFinalx(mousey - 5);
								player_cengmans.get(j).setFinaly(mousex);
								DrawPath(player_cengmans.get(j));
								collectingFood(player_cengmans.get(j), player_tree.get(i));
								player_cengmans.get(j).setClicked(false);
								break;
							}
						}
						player_tree.get(i).setClicked(true);

						mousepr = 0;
						break;
					}
				}
				mousepr = 0;
			}
			
			if (mousepr == 1 && mousex >= 0 && mousex <= 48 && mousey >= 5 && mousey < 24 && Maze.Maze[mousey - 5][mousex] == "ct") { // Attack the computer tree
				for (int i = 0; i < computer_tree.size(); i++) {
					if (computer_tree.get(i) != null && computer_tree.get(i).getX() == mousey -5 && computer_tree.get(i).getY() == mousex) {
						for (int j = 0; j < count_pc; j++) {
							if (player_cengmans != null && player_cengmans.get(j) != null && player_cengmans.get(j).isClicked() == true && Clickable == false && Pressed_G == true) {
								Clickable = false;
								Pressed_G = false;
								player_cengmans.get(j).setFinalx(mousey - 5);
								player_cengmans.get(j).setFinaly(mousex);
								DrawPath(player_cengmans.get(j));
								player_cengmans.get(j).setFighting(true);
								computer_tree.get(i).setAttacking(true);
								player_cengmans.get(j).setClicked(false);
								break;
							}
						}

						mousepr = 0;
						computer_tree.get(i).setClicked(true);
						break;
					}
				}
				mousepr = 0;
			}

			if (mousepr == 1 && mousex >= 0 && mousex <= 48 && mousey >= 5 && mousey < 24 && Maze.Maze[mousey - 5][mousex] == "pce") { // Go to cement
				for (int i = 0; i < player_cement.size(); i++) {
					if (player_cement.get(i) != null && player_cement.get(i).getX() == mousey - 5 && player_cement.get(i).getY() == mousex) {
						for (int j = 0; j < count_pc; j++) {
							if (player_cengmans != null && player_cengmans.get(j) != null && player_cengmans.get(j).isClicked() == true && Clickable == false && Pressed_G == true) {
								Clickable = false;
								Pressed_G = false;
								player_cengmans.get(j).setFinalx(mousey - 5);
								player_cengmans.get(j).setFinaly(mousex);
								DrawPath(player_cengmans.get(j));
								addCementToBag(player_cengmans.get(j), player_cement.get(i));;
								player_cengmans.get(j).setClicked(false);
								break;
							}
						}
						player_cement.get(i).setClicked(true);
						mousepr = 0;
						break;
					}
				}
				mousepr = 0;
			}
			
			if (mousepr == 1 && mousex >= 0 && mousex <= 48 && mousey >= 5 && mousey < 24 && Maze.Maze[mousey - 5][mousex] == "cb") { // Attack the computer base
				for (int j = 0; j < count_pc; j++) {
					if (player_cengmans != null && player_cengmans.get(j) != null && player_cengmans.get(j).isClicked() == true && Clickable == false && Pressed_G == true) {
						Clickable = false;
						Pressed_G = false;
						player_cengmans.get(j).setFinalx(mousey - 5);
						player_cengmans.get(j).setFinaly(mousex);
						DrawPath(player_cengmans.get(j));
						player_cengmans.get(j).setClicked(false);
						player_cengmans.get(j).setFighting(true);
						computer_base.setAttacking(true);
						mousepr = 0;
						break;
					}
				}
				mousepr = 0;
			}
			
			if (mousepr == 1 && mousex >= 0 && mousex <= 48 && mousey >= 5 && mousey < 24 && Maze.Maze[mousey - 5][mousex] == "pb") { // Go to player base
				for (int j = 0; j < count_pc; j++) {
					if (player_cengmans != null && player_cengmans.get(j) != null && player_cengmans.get(j).isClicked() == true && Clickable == false && Pressed_G == true) {
						Clickable = false;
						Pressed_G = false;
						player_cengmans.get(j).setFinalx(mousey - 5);
						player_cengmans.get(j).setFinaly(mousex);
						DrawPath(player_cengmans.get(j));
						player_cengmans.get(j).setClicked(false);
						mousepr = 0;
						break;
					}
				}
				mousepr = 0;
			}
			
			if (mousepr == 1 && mousex >= 0 && mousex <= 48 && mousey >= 5 && mousey < 24 && Maze.Maze[mousey - 5][mousex] == "cc") { // Go to computer cengman
				for (int j = 0; j < count_pc; j++) {
					if (player_cengmans != null && player_cengmans.get(j) != null && player_cengmans.get(j).isClicked() == true && Clickable == false && Pressed_G == true) {
						Clickable = false;
						Pressed_G = false;
						player_cengmans.get(j).setFinalx(mousey - 5);
						player_cengmans.get(j).setFinaly(mousex);
						DrawPath(player_cengmans.get(j));
						for(int i = 0; i < count_cc; i++)
						{
							if(computer_cengmans.get(i).getLocx() == mousey - 5 && computer_cengmans.get(i).getLocy() == mousex)
							{
								computer_cengmans.get(i).setFighting(true);
								player_cengmans.get(j).setFighting(true);
								break;
							}
						}
						player_cengmans.get(j).setClicked(false);
						mousepr = 0;
						break;
					}
				}
				mousepr = 0;
			}
			
			if (mousepr == 1 && mousex >= 0 && mousex <= 48 && mousey >= 5 && mousey < 24 && Maze.Maze[mousey - 5][mousex] == "cw") { // Attack the computer wall
				for (int j = 0; j < count_pc; j++) {
					if (player_cengmans != null && player_cengmans.get(j) != null && player_cengmans.get(j).isClicked() == true && Clickable == false && Pressed_G == true) {
						Clickable = false;
						Pressed_G = false;
						player_cengmans.get(j).setFinalx(mousey - 5);
						player_cengmans.get(j).setFinaly(mousex);
						DrawPath(player_cengmans.get(j));
						for(int i = 0; i < count_cw; i++)
						{
							if(computer_wall.get(i).getX() == mousey - 5 && computer_wall.get(i).getY() == mousex)
							{
								player_cengmans.get(j).setFighting(true);
								computer_wall.get(i).setAttacking(true);
							}
						}
						player_cengmans.get(j).setClicked(false);
						
						mousepr = 0;
						break;
					}
				}
				mousepr = 0;
			}
			
			if (mousepr == 1 && mousex >= 0 && mousex <= 48 && mousey >= 5 && mousey < 24 && Maze.Maze[mousey - 5][mousex] == "pw") { // Player wall
				for (int j = 0; j < count_pc; j++) {
					if (player_cengmans != null && player_cengmans.get(j) != null && player_cengmans.get(j).isClicked() == true && Clickable == false && Pressed_G == true) {
						Clickable = false;
						Pressed_G = false;
						player_cengmans.get(j).setFinalx(mousey - 5);
						player_cengmans.get(j).setFinaly(mousex);
						DrawPath(player_cengmans.get(j));
						player_cengmans.get(j).setClicked(false);
						mousepr = 0;
						break;
					}
				}
				mousepr = 0;
			}
		}
	}


	public void clearBag() {
		for (int i = 12; i < 21; i++) {

			Main.cn.getTextWindow().setCursorPosition(53, i);
			Main.cn.getTextWindow().output(" ");

		}
		// Main.cn.getTextWindow().setCursorPosition(0, 0);
	}

	public void clearQueue() {
		for (int i = 20; i < 30; i++) {
			Main.cn.getTextWindow().setCursorPosition(i, 2);
			Main.cn.getTextWindow().output(" ");
		}
		// Main.cn.getTextWindow().setCursorPosition(0, 0);
	}

	public void clearMenu() {
		for (int i = 33; i < 79; i++) {
			for (int j = 2; j < 11; j++) {
				Main.cn.getTextWindow().setCursorPosition(i, j);
				Main.cn.getTextWindow().output(" ");
			}
		}

		Main.cn.getTextWindow().setCursorPosition(0, 0);
	}
	
	public void viewRangePlayer()
	{
		for(int i = 0; i < count_pc; i++)
		{
			int pc_x = player_cengmans.get(i).getLocx();
			int pc_y = player_cengmans.get(i).getLocy();
			
			
			for (int k = -5; k <= 5; k++) {
				for (int j = -5; j <= 5; j++) {
					if (((pc_x + k) < 19 && (pc_x + k) >= 0) && ((pc_y + j) < 49 && (pc_y + j) >= 0)) {
						CengMan.playermaze[pc_x + k][pc_y + j] = 1;
					}
				}
			}
		}
		CengMan.playermaze[player_base.getX()][player_base.getY1()] = 1;
		CengMan.playermaze[player_base.getX()][player_base.getY2()] = 1;
	}
	
	public void viewRangeComputer()
	{
		for(int i = 0; i < count_cc; i++)
		{
			int cc_x = computer_cengmans.get(i).getLocx();
			int cc_y = computer_cengmans.get(i).getLocy();
			
			for (int k = -5; k <= 5; k++) {
				for (int j = -5; j <= 5; j++) {
					if ((cc_x + k < 19 && cc_x + k >= 0) && (cc_y + j < 49 && cc_y + j >= 0) && CengMan.computermaze[cc_x + k][cc_y + j] == 0) {
						CengMan.computermaze[cc_x + k][cc_y + j] = 1;						
					}
				}
			}
		}
		CengMan.computermaze[computer_base.getX()][computer_base.getY1()] = 1;
		CengMan.computermaze[computer_base.getX()][computer_base.getY2()] = 1;
	}

	
	public void controlProductionQueue() {
		if (!(ProductionQueue.isEmpty()) && ProductionQueue.peek().toString().equals("C")) {
			if (create_time == 8) {
				createPlayerCengman();
				ProductionQueue.dequeue();
				create_time = 0;
			} else
				create_time++;
		}

		else if (!(ProductionQueue.isEmpty()) && ProductionQueue.peek().toString().equals("T")) {
			if (create_time == 1) {
				createPlayerTree();
				ProductionQueue.dequeue();
				create_time = 0;
			} else
				create_time++;
		}

		else if (!(ProductionQueue.isEmpty()) && ProductionQueue.peek().toString().equals("W")) {
			if (create_time == 1) {
				createPlayerCement();
				ProductionQueue.dequeue();
				create_time = 0;
			} else
				create_time++;
		} else if (!(ProductionQueue.isEmpty()) && ProductionQueue.peek().toString().equals("R")) {
			if (create_time == 1) {
				if (player_base.getY1() >= 2 && Maze.Maze[player_base.getX()][player_base.getY1() - 2] == "f") {
					repairBase(player_base.getX(), player_base.getY1() - 2);
				} else if (player_base.getY2() <= 46 && Maze.Maze[player_base.getX()][player_base.getY2() + 2] == "f") {
					repairBase(player_base.getX(), player_base.getY2() + 2);
				} else if (player_base.getX() >= 1 && Maze.Maze[player_base.getX() - 1][player_base.getY1()] == "f") {
					repairBase(player_base.getX() - 1, player_base.getY1());
				} else if (player_base.getX() <= 17 && Maze.Maze[player_base.getX() + 1][player_base.getY1()] == "f") {
					repairBase(player_base.getX() + 1, player_base.getY1());
				} else if (player_base.getX() >= 1 && Maze.Maze[player_base.getX() - 1][player_base.getY2()] == "f") {
					repairBase(player_base.getX() - 1, player_base.getY2());
				} else if (player_base.getX() <= 17 && Maze.Maze[player_base.getX() + 1][player_base.getY2()] == "f") {
					repairBase(player_base.getX() + 1, player_base.getY2());
				} else
					ProductionQueue.dequeue();
				create_time = 0;
			} else
				create_time++;
		}
	}

	public void ProductionQueuePrint() {
		int size = ProductionQueue.size();
		if (size != 0) {
			for (int i = 0; i < size; i++) {
				Main.cn.getTextWindow().setCursorPosition(28 - i, 2);
				if (i < 9)
					Main.cn.getTextWindow().output(ProductionQueue.peek().toString());
				ProductionQueue.enqueue(ProductionQueue.dequeue());
			}
		}
	}

	
	public void createPlayerCement() {
		count_pce++;
		player_cement.add(new Cement(100, 1));
		if (Maze.Maze[player_base.getX()][player_base.getY1() - 1] == " ") {
			Maze.Maze[player_base.getX()][player_base.getY1() - 1] = "pce";
		} else if (Maze.Maze[player_base.getX()][player_base.getY2() + 1] == " ") {
			Maze.Maze[player_base.getX()][player_base.getY2() + 1] = "pce";
		} else if (Maze.Maze[player_base.getX() - 1][player_base.getY1()] == " ") {
			Maze.Maze[player_base.getX() - 1][player_base.getY1()] = "pce";
		} else if (Maze.Maze[player_base.getX() + 1][player_base.getY1()] == " ") {
			Maze.Maze[player_base.getX() + 1][player_base.getY1()] = "pce";
		} else if (Maze.Maze[player_base.getX() - 1][player_base.getY2()] == " ") {
			Maze.Maze[player_base.getX() - 1][player_base.getY2()] = "pce";
		} else if (Maze.Maze[player_base.getX() + 1][player_base.getY2()] == " ") {
			Maze.Maze[player_base.getX() + 1][player_base.getY2()] = "pce";
		}
	}

	public void repairBase(int x, int y) // total food - 1
	{
		Maze.Maze[x][y] = " ";
		player_base.setLife(player_base.getLife() + 10);
		ProductionQueue.dequeue();
		create_time = 0;
	}

	public void createPlayerCengman() {
		player_cengmans.add(new CengMan("pc", count_pc, 100, 1, 10, new Stack(), 0, 0));
		count_pc++;
		while (true) {
			int pc_x = rnd.nextInt(10) + 8;
			int pc_y = rnd.nextInt(23) + 25;
			if (player_cengmans != null && player_cengmans.get(count_pc-1) != null && Maze.Maze[pc_x][pc_y] == " ") {
				Maze.Maze[pc_x][pc_y] = "pc";
				player_cengmans.get(count_pc-1).setLocx(pc_x); // place in the array
				player_cengmans.get(count_pc-1).setLocy(pc_y);
				
				CengMan.playermaze[pc_x][pc_y] = 1;
				//viewRange();
				break;
			}
		}
	}

	public void createComputerCengman() {
		count_cc++;
		computer_cengmans.add(new CengMan("cc", count_cc, 100, 1, 10, new Stack(), 0, 0));

		while (true) {
			int cc_x = rnd.nextInt(9);
			int cc_y = rnd.nextInt(18);

			if (Maze.Maze[cc_x][cc_y] == " ") {
				Maze.Maze[cc_x][cc_y] = "cc";
				computer_cengmans.get(count_cc-1).setLocx(cc_x); // place in the array
				computer_cengmans.get(count_cc-1).setLocy(cc_y);
				CengMan.computermaze[cc_x][cc_y] = 1;
				break;
			}
		}
	}

	public void createPlayerTree() {
		count_pt++;
		player_tree.add(new Tree("pt", 50, 50, 1, 0));

		while (true) {
			int pt_x = rnd.nextInt(10) + 8;
			int pt_y = rnd.nextInt(23) + 25;

			if (Maze.Maze[pt_x][pt_y] == " ") {
				Maze.Maze[pt_x][pt_y] = "pt";
				player_tree.get(count_pt-1).setX(pt_x);
				player_tree.get(count_pt-1).setY(pt_y);

				break;
			}
		}
	}
	
	public void createComputerTree() {
		count_ct++;
		computer_tree.add(new Tree("ct", 50, 50, 1, 0));

		while (true) {
			int ct_x = rnd.nextInt(9) + 8;
			int ct_y = rnd.nextInt(18) + 25;

			if (Maze.Maze[ct_x][ct_y] == " ") {
				Maze.Maze[ct_x][ct_y] = "pt";
				player_tree.get(count_pt-1).setX(ct_x);
				player_tree.get(count_pt-1).setY(ct_y);

				break;
			}
		}
	}

	public void createPlayerBase() {
		player_base = new Base("pb", 1000, 1, 10, new OldQueue(10));
		while (true) {
			int base_x = rnd.nextInt(9) + 9;
			int base_y = rnd.nextInt(23) + 25;

			if (Maze.Maze[base_x][base_y] == " " && Maze.Maze[base_x][base_y + 1] == " "
					&& Maze.Maze[base_x][base_y - 1] == " ") {
				Maze.Maze[base_x][base_y] = "pb";
				Maze.Maze[base_x][base_y + 1] = "pb";
				player_base.setX(base_x);
				player_base.setY1(base_y);
				player_base.setY2(base_y + 1);
				break;
			}
		}
	}

	public void createComputerBase() {
		computer_base = new Base("cb", 1000, 1, 10, new OldQueue(10));
		while (true) {
			int base_x = rnd.nextInt(9);
			int base_y = rnd.nextInt(23);
			if (Maze.Maze[base_x][base_y] == " " && Maze.Maze[base_x][base_y + 1] == " "
					&& Maze.Maze[base_x][base_y - 1] == " ") {
				Maze.Maze[base_x][base_y] = "cb";
				Maze.Maze[base_x][base_y + 1] = "cb";
				computer_base.setX(base_x);
				computer_base.setY1(base_y);
				computer_base.setY2(base_y + 1);
				break;
			}
		}
	}

	
	public void DrawPath(CengMan cengman) {
		int count = 0;
		int startx = cengman.getLocx();
		int starty = cengman.getLocy();
		int finalx = cengman.getFinalx();
		int finaly = cengman.getFinaly();
		cengman.setTimer(1);
		cengman.setPath1(PathFinding(starty, startx, finalx, finaly));
		cengman.setMoving(true);
		Stack<Node> temp = new Stack<Node>();

		while (!(cengman.getPath1().isEmpty())) {
			temp.push(cengman.getPath1().peek());
			int x = cengman.getPath1().peek().getLocx() + 5;
			int y = cengman.getPath1().pop().getLocy();
			Main.cn.getTextWindow().setCursorPosition(y, x);
			Main.cn.getTextWindow().output(".");
			Maze.Maze[x - 5][y] = ".";
			count++;
		}
		while (!temp.isEmpty()) {
			cengman.getPath1().push(temp.pop());
		}
		cengman.setTimer(count);
	}

	public void followPath() {
		int x;
		int y;
		for (int j = 0; j < count_pc; j++) {
			if (player_cengmans != null && player_cengmans.get(j) != null && player_cengmans.get(j).getTimer() != 0 && player_cengmans.get(j).getPath1() != null && player_cengmans.get(j).isMoving() == true) {
				x = player_cengmans.get(j).getPath1().peek().getLocx() + 5;
				y = player_cengmans.get(j).getPath1().pop().getLocy();
				Maze.Maze[player_cengmans.get(j).getLocx()][player_cengmans.get(j).getLocy()] = " ";
				Maze.Maze[x - 5][y] = "pc";
				player_cengmans.get(j).setLocx(x - 5);
				player_cengmans.get(j).setLocy(y);
				Main.cn.getTextWindow().setCursorPosition(y, x);
				Main.cn.getTextWindow().output(("" + Game.player_cengmans.get(j).getLevel()),
						new TextAttributes(Color.black, Color.red));
				player_cengmans.get(j).setTimer(player_cengmans.get(j).getTimer() - 1);
				node[x - 5][y].setUsed(false);
				
			}
			if (player_cengmans != null && player_cengmans.get(j) != null && player_cengmans.get(j).getTimer() == 0 && player_cengmans.get(j).isMoving() == true) {
				player_cengmans.get(j).setPath1(null);
				player_cengmans.get(j).setMoving(false);
			}
		}
	}
	
	public void initializeNodes()
	{
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 49; j++) {
				node[i][j].setKey(Integer.MAX_VALUE);
				node[i][j].setParent(null);
				node[i][j].setChecked(false);
				node[i][j].setAvailable(false);
				node[i][j].setLocx(0);
				node[i][j].setLocy(0);
				node[i][j].setUsed(false);
				if (Maze.Maze[i][j] == " " || Maze.Maze[i][j] == ".")
					node[i][j].setNull(false);
				else if(Maze.Maze[i][j] == "pt" || Maze.Maze[i][j] == "cc" || Maze.Maze[i][j] == "pb" || Maze.Maze[i][j] == "cb" || Maze.Maze[i][j] == "cw" || Maze.Maze[i][j] == "pce" || Maze.Maze[i][j] == "pw" || Maze.Maze[i][j] == "ct")
					node[i][j].setThing(true);
				else if(Maze.Maze[i][j] == "#")
					node[i][j].setNull(true);
			}
		}
	}

	public void initializeNodes1()
	{
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 49; j++) {
				if(node[i][j].isUsed() == false)
				{
					node[i][j].setKey(Integer.MAX_VALUE);
					node[i][j].setParent(null);
					node[i][j].setChecked(false);
					node[i][j].setAvailable(false);
					node[i][j].setLocx(0);
					node[i][j].setLocy(0);
					if (Maze.Maze[i][j] == " ")
						node[i][j].setNull(false);
					else if(Maze.Maze[i][j] == "pt" || Maze.Maze[i][j] == "cc" || Maze.Maze[i][j] == "pb" || Maze.Maze[i][j] == "cb" || Maze.Maze[i][j] == "cw" || Maze.Maze[i][j] == "pce" || Maze.Maze[i][j] == "pw" || Maze.Maze[i][j] == "ct")
					{
						node[i][j].setThing(true);
					}
					else
						node[i][j].setNull(true);
				}
			}
		}
	}
	
	public Stack<Node> PathFinding(int starty, int startx, int finalx, int finaly) {
		Queue<Node> nodeQueue = new Queue<Node>();
		Node n = null;		

		Node root = node[startx][starty]; // find root nodekk
		root.setKey(0);
		root.setParent(null);
		node[startx][starty].setKey(root.getKey());
		node[startx][starty].setParent(root.getParent());
		root.setLocx(startx);
		root.setLocy(starty);
		nodeQueue.enqueue(root);
		//tempQueue.enqueue(root);
		
		while (!nodeQueue.isEmpty()) {
			Node current = (Node) nodeQueue.dequeue();
			current.setAvailableAdjacent(availableAdjacent(current.getLocx(), current.getLocy(), finalx, finaly));
			node[current.getLocx()][current.getLocy()].setChecked(true);
			for (int i = 0; i < current.getAvailableAdjacent().size(); i++) {
				if (current.getAvailableAdjacent().get(i).isAvailable == true) {
					n = current.getAvailableAdjacent().get(i);
					n.setLocx(current.getAvailableAdjacent().get(i).getLocx());
					n.setLocy(current.getAvailableAdjacent().get(i).getLocy());

					if (n.getKey() == Integer.MAX_VALUE) {
						n.setKey(current.getKey() + 1);
						n.setParent(current);
						node[n.getLocx()][n.getLocy()].setKey(n.getKey());
						node[n.getLocx()][n.getLocy()].setParent(n.getParent());

						nodeQueue.enqueue(n);

						if (n.getLocx() == finalx && n.getLocy() == finaly) {
							Stack<Node> path2 = new Stack<Node>();
							while (n.getKey() != 0) {
								if(node[n.getLocx()][n.getLocy()].isThing == false)
								{
									path2.push(n);
									node[n.getLocx()][n.getLocy()].setUsed(true);
								}
								if (n != null && n.getParent() != null)
									n = n.getParent();
								else
									break;
								
							}
							return path2;
						}
					}
				}
			}
		}
		return null;
	}

	public LinkedList<Node> availableAdjacent(int x, int y, int finalx, int finaly) {
		// null olunca dolu
		LinkedList<Node> queue = new LinkedList<Node>();
		// Stack prev = new Stack();
		Node current = node[x][y];

		if (x < 18 && !(node[x + 1][y].isNull) && node[x + 1][y].isChecked == false && ((node[x + 1][y].isThing == true && (x+1) == finalx && y == finaly) || node[x + 1][y].isThing == false)) {
			queue.add(node[x + 1][y]);
			node[x + 1][y].setAvailable(true);
			node[x + 1][y].setLocx(x + 1);
			node[x + 1][y].setLocy(y);
		}
		if (y < 48 && !(node[x][y + 1].isNull) && node[x][y + 1].isChecked == false && ((node[x][y+1].isThing == true && x == finalx && (y+1) == finaly) || node[x][y+1].isThing == false)) {
			queue.add(node[x][y + 1]);
			node[x][y + 1].setAvailable(true);
			node[x][y + 1].setLocx(x);
			node[x][y + 1].setLocy(y + 1);
		}
		if (x > 0 && !(node[x - 1][y].isNull) && node[x - 1][y].isChecked == false && ((node[x - 1][y].isThing == true && (x-1) == finalx && y == finaly) || node[x - 1][y].isThing == false)) {
			queue.add(node[x - 1][y]);
			node[x - 1][y].setAvailable(true);
			node[x - 1][y].setLocx(x - 1);
			node[x - 1][y].setLocy(y);
		}
		if (y > 0 && !(node[x][y - 1].isNull) && node[x][y - 1].isChecked == false && ((node[x][y-1].isThing == true && x == finalx && (y-1) == finaly) || node[x][y-1].isThing == false)) {
			queue.add(node[x][y - 1]);
			node[x][y - 1].setAvailable(true);
			node[x][y - 1].setLocx(x);
			node[x][y - 1].setLocy(y - 1);
		}
		return queue;
	}

	
	public boolean IsUseful() {
		for (int j = 0; j < count_pc; j++) {
			Stack<Box> temp = new Stack<Box>();
			boolean isSquashed = false;
			while (!player_cengmans.get(j).getBag().isEmpty()) {
				if (player_cengmans.get(j).getBag().peek().getSymbol()
						.equalsIgnoreCase("C")) // cementin
												// sembol�
												// ??
				{
					isSquashed = true;
				}

				if (isSquashed == true
						&& player_cengmans.get(j).getBag().peek().getSymbol()
								.equals("F")) {
					int amount = player_cengmans.get(j).getBag().peek()
							.getAmount();
					player_cengmans.get(j).getBag().pop();
					player_cengmans.get(j).getBag().push(new Box("~", amount));
					temp.push(player_cengmans.get(j).getBag().pop());
				} else
					temp.push(player_cengmans.get(j).getBag().pop());
			}
			while (!temp.isEmpty()) {
				player_cengmans.get(j).getBag().push(temp.pop());
			}

		}
		return true;
	}

	
	public void creatingFood() {
		for (int i = 0; i < player_tree.size(); i++) {
			player_tree.get(i).setFood(player_tree.get(i).getFood() + 5);
		}
		for (int i = 0; i < computer_tree.size(); i++) {
			computer_tree.get(i).setFood(computer_tree.get(i).getFood() + 5);
		}
	}

	public void collectingFood(CengMan y, Tree x) {
		if (y.isClicked() == true && x.isClicked == true) {
			if (Maze.Maze[x.getUp_co().getX()][x.getUp_co().getY()] == "pc"
					|| Maze.Maze[x.getDown_co().getX()][x.getDown_co().getY()] == "pc"
					|| Maze.Maze[x.getRight_co().getX()][x.getRight_co().getY()] == "pc"
					|| Maze.Maze[x.getLeft_co().getX()][x.getLeft_co().getY()] == "pc") {
				{
					if (x.getFood() <= 50) {
						y.getBag().push(new Box("F", x.getFood())); // set
																	// amount
						x.setFood(0);

					} else {
						x.setFood(x.getFood() - 50);
						y.getBag().push(new Box("F", 50)); // set amount
					}
					y.setClicked(false);
					x.setClicked(false);
				}
			}
		}
	}

	
	public void addCementToBag(CengMan cengman, Cement c) {

		if (cengman.isClicked() == true && c.isClicked() == true) {

			if (Maze.Maze[c.getUp_co().getX()][c.getUp_co().getY()] == "pc"
					|| Maze.Maze[c.getDown_co().getX()][c.getDown_co().getY()] == "pc"
					|| Maze.Maze[c.getRight_co().getX()][c.getRight_co().getY()] == "pc"
					|| Maze.Maze[c.getLeft_co().getX()][c.getLeft_co().getY()] == "pc") {

				cengman.getBag().push(new Box("pce", 1));
				Maze.Maze[c.getX()][c.getY()] = " ";
				c.setClicked(false);
				cengman.setClicked(false);
			}
		}
	}

	public void addFoodToBag(CengMan c, Food f) {

		if (c.isClicked() == true && f.isClicked() == true) {
			c.getBag().push(new Box("F", 50));
			Maze.Maze[f.getX()][f.getY()] = " ";
			f.setClicked(false);
			c.setClicked(false);
		}

	}

	public void extractFromBag(CengMan cengman) {
		if (cengman.getBag().peek().getSymbol().equalsIgnoreCase("F")) {
			if (Maze.Maze[cengman.getLocx()][cengman.getLocy() - 1] == " ") {
				player_food.add(new Food(cengman.getBag().peek().getAmount(),
						cengman.getLocx(), cengman.getLocy() - 1));

				Maze.Maze[cengman.getLocx()][cengman.getLocy() - 1] = cengman

				.getBag().pop().getSymbol();

			} else if (Maze.Maze[cengman.getLocx()][cengman.getLocy() + 1] == " ") {
				player_food.add(new Food(cengman.getBag().peek().getAmount(),
						cengman.getLocx(), cengman.getLocy() + 1));

				Maze.Maze[cengman.getLocx()][cengman.getLocy() + 1] = cengman
						.getBag().pop().getSymbol();
			} else if (Maze.Maze[cengman.getLocx() - 1][cengman.getLocy()] == " ") {
				player_food.add(new Food(cengman.getBag().peek().getAmount(),
						cengman.getLocx() - 1, cengman.getLocy()));

				Maze.Maze[cengman.getLocx() - 1][cengman.getLocy()] = cengman
						.getBag().pop().getSymbol();
			} else if (Maze.Maze[cengman.getLocx() + 1][cengman.getLocy()] == " ") {
				player_food.add(new Food(cengman.getBag().peek().getAmount(),
						cengman.getLocx() + 1, cengman.getLocy()));

				Maze.Maze[cengman.getLocx() + 1][cengman.getLocy()] = cengman
						.getBag().pop().getSymbol();
			}

			count_pf++;
			player_food.get(count_pf - 1).setUp_co(
					new Point(player_food.get(count_pf - 1).getX(), player_food
							.get(count_pf - 1).getY() - 1));
			player_food.get(count_pf - 1).setDown_co(
					new Point(player_food.get(count_pf - 1).getX(), player_food
							.get(count_pf - 1).getY() + 1));
			player_food.get(count_pf - 1).setRight_co(
					new Point(player_food.get(count_pf - 1).getX() + 1,
							player_food.get(count_pf - 1).getY()));
			player_food.get(count_pf - 1).setLeft_co(
					new Point(player_food.get(count_pf - 1).getX() - 1,
							player_food.get(count_pf - 1).getY()));

		} else if (cengman.getBag().peek().getSymbol().equalsIgnoreCase("pce")) {
			if (Maze.Maze[cengman.getLocx()][cengman.getLocy() - 1] == " ") {
				player_cement.add(new Cement(cengman.getLocx(), cengman
						.getLocy() - 1));

				Maze.Maze[cengman.getLocx()][cengman.getLocy() - 1] = cengman

				.getBag().pop().getSymbol();

			} else if (Maze.Maze[cengman.getLocx()][cengman.getLocy() + 1] == " ") {
				player_cement.add(new Cement(cengman.getLocx(), cengman
						.getLocy() + 1));

				Maze.Maze[cengman.getLocx()][cengman.getLocy() + 1] = cengman
						.getBag().pop().getSymbol();
			} else if (Maze.Maze[cengman.getLocx() - 1][cengman.getLocy()] == " ") {
				player_cement.add(new Cement(cengman.getLocx() - 1, cengman
						.getLocy()));

				Maze.Maze[cengman.getLocx() - 1][cengman.getLocy()] = cengman
						.getBag().pop().getSymbol();
			} else if (Maze.Maze[cengman.getLocx() + 1][cengman.getLocy()] == " ") {
				player_cement.add(new Cement(cengman.getLocx() + 1, cengman
						.getLocy()));

				Maze.Maze[cengman.getLocx() + 1][cengman.getLocy()] = cengman
						.getBag().pop().getSymbol();
			}

			count_pce++;
			player_cement.get(count_pce - 1).setUp_co(
					new Point(player_cement.get(count_pce - 1).getX(),
							player_cement.get(count_pce - 1).getY() - 1));
			player_cement.get(count_pce - 1).setDown_co(
					new Point(player_cement.get(count_pce - 1).getX(),
							player_cement.get(count_pce - 1).getY() + 1));
			player_cement.get(count_pce - 1).setRight_co(
					new Point(player_cement.get(count_pce - 1).getX() + 1,
							player_cement.get(count_pce - 1).getY()));
			player_cement.get(count_pce - 1).setLeft_co(
					new Point(player_cement.get(count_pce - 1).getX() - 1,
							player_cement.get(count_pce - 1).getY()));
		}
	}
	public void moveComputer()
	{
		for(int i = 1; i <= count_cc; i++)
		{
			if(i%3 == 0)
			{
				findBaseComputer(computer_cengmans.get(i));
			}
			if(i%3 == 1)
			{
				attackComputer(computer_cengmans.get(i));
			}
			if(i%3 == 2)
			{
				collectFoodComputer(computer_cengmans.get(i));
			}
		}
	}
	
	public void attackComputer(CengMan cengman)
	{
		while(!cengman.viewRange.isEmpty())
		{
			int x = cengman.viewRange.peek().getLocx();
			int y = cengman.viewRange.pop().getLocy();
			
			if(Maze.Maze[x][y] == "pc"){
				cengman.setFinalx(x);
				cengman.setFinaly(y);
				DrawPath(cengman);
				cengman.setFighting(true);
				for(int j = 0; j < count_pc; j++)
				{
					if(player_cengmans.get(j).getLocx() == x && player_cengmans.get(j).getLocy() == y)
					{
						cengman.setLife(cengman.getLife() - player_cengmans.get(j).getLevel()*10);
						player_cengmans.get(j).setLife(player_cengmans.get(j).getLife() - cengman.getLevel()*10);
					}
				}
			}
		}
	}
	
	public void collectFoodComputer(CengMan cengman)
	{
		while(!cengman.viewRange.isEmpty())
		{
			int x = cengman.viewRange.peek().getLocx();
			int y = cengman.viewRange.pop().getLocy();
			
			if(Maze.Maze[x][y] == "F"){
				cengman.setFinalx(x);
				cengman.setFinaly(y);
				DrawPath(cengman);
				cengman.getBag().push(new Box("F", 50));
			}
		}
	}
	
	public void findBaseComputer(CengMan cengman)
	{
		while(!cengman.viewRange.isEmpty())
		{
			int x = cengman.viewRange.peek().getLocx();
			int y = cengman.viewRange.pop().getLocy();
			
			if(Maze.Maze[x][y] == "pb"){
				cengman.setFinalx(x);
				cengman.setFinaly(y);
				DrawPath(cengman);
				cengman.setFighting(true);
				for(int j = 0; j < count_pc; j++)
				{
					if(player_cengmans.get(j).getLocx() == x && player_cengmans.get(j).getLocy() == y)
					{
						cengman.setLife(cengman.getLife() - player_cengmans.get(j).getLevel()*10);
						player_cengmans.get(j).setLife(player_cengmans.get(j).getLife() - cengman.getLevel()*10);
					}
				}
			}
		}
	}

	public void attackCengman()
	{
		int x;
		int y;
		for(int i = 0; i < count_pc; i++)
		{
			if(player_cengmans.get(i).isFighting() == true)
			{
				x = player_cengmans.get(i).getLocx();
				y = player_cengmans.get(i).getLocy();
				for(int j = -1; j <1; j++)
				{
					for(int k = -1; k < 1; k++)
					{

						for(int l = 0; l < count_cc; l++)
						{
							if(Maze.Maze[x + j][y + k] == "cc" && computer_cengmans.get(l).isFighting() == true)
							{
								if(computer_cengmans.get(l).getLocx() == (x + j) && computer_cengmans.get(l).getLocy() == (y + k))
								{
									player_cengmans.get(i).setLife(player_cengmans.get(i).getLife() - computer_cengmans.get(l).getLevel()*10);
									computer_cengmans.get(l).setLife(computer_cengmans.get(l).getLife() - player_cengmans.get(i).getLevel()*10);
									if(player_cengmans.get(i).getLife() == 0 || computer_cengmans.get(l).getLife() == 0)
									{
										computer_cengmans.get(l).setFighting(false);
										player_cengmans.get(l).setFighting(false);
										if(player_cengmans.get(i).getLife() == 0)
											player_cengmans.remove(i);
										if(computer_cengmans.get(l).getLife() == 0)
											computer_cengmans.remove(l);
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	public void attackTreeCengman()
	{
		int x;
		int y;
		for(int i = 0; i < count_pc; i++)
		{
			if(player_cengmans.get(i).isFighting() == true)
			{
				x = player_cengmans.get(i).getLocx();
				y = player_cengmans.get(i).getLocy();
				for(int j = -1; j <1; j++)
				{
					for(int k = -1; k < 1; k++)
					{
						if(Maze.Maze[x + j][y + k] == "ct")
						{
							for(int l = 0; l < count_ct; l++)
							{
								if(computer_tree.get(l).getX() == (x + j) && computer_tree.get(l).getY() == (y + k) && computer_tree.get(l).isAttacking() == true)
								{
									computer_tree.get(l).setLife(computer_tree.get(l).getLife() - player_cengmans.get(i).getLevel()*10);
									computer_tree.get(l).setAttacking(false);
									player_cengmans.get(i).setFighting(false);
									if(computer_tree.get(l).getLife() == 0)
									{
										computer_tree.remove(l);
									}
								}
							}
						}
					}
				}
			}
			
		}
	}
	
	public void attackBaseCengman()
	{
		int x;
		int y;
		for(int i = 0; i < count_pc-1; i++)
		{
			if(player_cengmans.get(i).isFighting() == true)
			{
				x = player_cengmans.get(i).getLocx();
				y = player_cengmans.get(i).getLocy();
				for(int j = -1; j <1; j++)
				{
					for(int k = -1; k < 1; k++)
					{
						if(Maze.Maze[x + j][y + k] == "cb" && computer_base.isAttacking() == true)
						{
							computer_base.setLife(computer_base.getLife() - player_cengmans.get(i).getLevel()*10);
							if(computer_base.getLife() == 0)
							{
								Maze.Maze[computer_base.getX()][computer_base.getY1()] = " ";
								Maze.Maze[computer_base.getX()][computer_base.getY2()] = " ";
								computer_base = null;
							}
						
						}
					}
				}
			}
		}
	}
	
	public void attackWallCengman()
	{
		
	}
	
	public void screen() throws IOException {
		maze.DisplayMaze();

		Main.cn.getTextWindow().setCursorPosition(0, 2);
		Main.cn.getTextWindow().output("Production Queue =>");

		Main.cn.getTextWindow().setCursorPosition(30, 2);
		Main.cn.getTextWindow().output("=>");

		Main.cn.getTextWindow().setCursorPosition(0, 4);
		Main.cn.getTextWindow().output("Base Life:");

		Main.cn.getTextWindow().setCursorPosition(10, 4);
		Main.cn.getTextWindow().output("" + player_base.getLife());

		Main.cn.getTextWindow().setCursorPosition(20, 1);
		Main.cn.getTextWindow().output("----------");

		Main.cn.getTextWindow().setCursorPosition(20, 3);
		Main.cn.getTextWindow().output("----------");

		Main.cn.getTextWindow().setCursorPosition(59, 3);
		Main.cn.getTextWindow().output("Base Mods");

		Main.cn.getTextWindow().setCursorPosition(53, 4);
		Main.cn.getTextWindow().output(" ---  ---  ---  ---");
		Main.cn.getTextWindow().setCursorPosition(53, 5);
		Main.cn.getTextWindow().output("| T || C || W || R |");
		Main.cn.getTextWindow().setCursorPosition(53, 6);
		Main.cn.getTextWindow().output(" ---  ---  ---  ---");

		Main.cn.getTextWindow().setCursorPosition(58, 11);
		Main.cn.getTextWindow().output("Active Unit");
		Main.cn.getTextWindow().setCursorPosition(59, 12);
		Main.cn.getTextWindow().output("CengMAN");
		Main.cn.getTextWindow().setCursorPosition(59, 13);
		Main.cn.getTextWindow().output("Life:");
		Main.cn.getTextWindow().setCursorPosition(59, 14);
		Main.cn.getTextWindow().output("Level:");
		for (int i = 0; i < 9; i++) {
			Main.cn.getTextWindow().setCursorPosition(52, 12 + i);
			Main.cn.getTextWindow().output("|");
			Main.cn.getTextWindow().setCursorPosition(56, 12 + i);
			Main.cn.getTextWindow().output("|");
		}
		Main.cn.getTextWindow().setCursorPosition(56, 21);
		Main.cn.getTextWindow().output("Active Unit Mods");
		Main.cn.getTextWindow().setCursorPosition(54, 22);
		Main.cn.getTextWindow().output(" ---   ---   ---   ---");
		Main.cn.getTextWindow().setCursorPosition(54, 23);
		Main.cn.getTextWindow().output("| G | | E | | F | | W |");
		Main.cn.getTextWindow().setCursorPosition(54, 24);
		Main.cn.getTextWindow().output(" ---   ---   ---   ---");

		Main.cn.getTextWindow().setCursorPosition(0, 0);
	}
	
	public void startScreen() throws IOException {
		Main.cn.getTextWindow().setCursorPosition(45, 3);
		Main.cn.getTextWindow().output("Welcome to Ceng a War!", new TextAttributes(Color.white, Color.black));

		Main.cn.getTextWindow().setCursorPosition(35, 5);
		Main.cn.getTextWindow().output("Please choose the map you want to play with.",
				new TextAttributes(Color.white, Color.black));

		Main.cn.getTextWindow().setCursorPosition(45, 10);
		Main.cn.getTextWindow().output("Maze 1", new TextAttributes(Color.white, Color.black));

		Main.cn.getTextWindow().setCursorPosition(60, 10);
		Main.cn.getTextWindow().output("Maze 2", new TextAttributes(Color.white, Color.black));

		while (true) {
			tmlis = new TextMouseListener() {
				public void mouseClicked(TextMouseEvent arg0) {
				}

				public void mousePressed(TextMouseEvent arg0) {
					if (mousepr == 0) {
						mousepr = 1;
						mousex = arg0.getX();
						mousey = arg0.getY();
					}
				}

				public void mouseReleased(TextMouseEvent arg0) {
				}
			};
			Main.cn.getTextWindow().addTextMouseListener(tmlis);

			if (mousepr == 1 && mousex >= 45 && mousex <= 50 && mousey == 10) {
				mousepr = 0;
				map = "map.txt";
				clearMenu();
				break;
			}

			else if (mousepr == 1 && mousex >= 60 && mousex <= 65 && mousey == 10) {
				mousepr = 0;
				map = "map2.txt";
				clearMenu();
				break;
			} else
				mousepr = 0;
		}
	}
}
