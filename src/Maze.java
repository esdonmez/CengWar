import java.awt.Color;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import enigma.console.TextAttributes;

public class Maze {
	static public String[][] Maze;

	public Maze() throws IOException {
		Maze = CreateMaze();
	}

	public String[][] getMaze() {
		return Maze;
	}

	public void setMaze(String[][] maze) {
		Maze = maze;
	}

	public String[][] CreateMaze() throws IOException {
		String str = "";
		String[] lines = new String[19];
		String[][] array = new String[19][49];

		FileInputStream fStream = new FileInputStream(Game.map);
		DataInputStream dStream = new DataInputStream(fStream);
		BufferedReader bReader = new BufferedReader(new InputStreamReader(
				dStream));

		for (int i = 0; i < 19; i++) {
			str = bReader.readLine();
			lines[i] = str;
		}

		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 49; j++) {
				if (lines[i].charAt(j) == '#') {
					array[i][j] = "#";
				} else if (lines[i].charAt(j) == ' ') {
					array[i][j] = " ";
				}
			}
			System.out.println();
		}

		dStream.close();
		return array;
	}

	public void DisplayMaze() {
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 49; j++) {
				if (CengMan.playermaze[i][j] == 1) {
					Main.cn.getTextWindow().setCursorPosition(j, i + 5);
					if (Maze[i][j] == "#") {
						Main.cn.getTextWindow().output("#",
								new TextAttributes(Color.orange, Color.orange));
					} else if (Maze[i][j] == " ") {
						Main.cn.getTextWindow().output(" ",
								new TextAttributes(Color.black, Color.black));
					} else if (Maze[i][j] == "pb") {
						Main.cn.getTextWindow().output("|",
								new TextAttributes(Color.black, Color.red));
					} else if (Maze[i][j] == "pc") {
						Main.cn.getTextWindow().output(
								("" + Game.player_cengmans.get(
										Game.count_pc - 1).getLevel()),
								new TextAttributes(Color.black, Color.red));
					} else if (Maze[i][j] == "cb") {
						Main.cn.getTextWindow().output("|",
								new TextAttributes(Color.black, Color.blue));
					} else if (Maze[i][j] == "cc") {
						Main.cn.getTextWindow().output(
								("" + Game.computer_cengmans.get(
										Game.count_cc - 1).getLevel()),
								new TextAttributes(Color.black, Color.blue));
					} else if (Maze[i][j] == "pt") {
						Main.cn.getTextWindow().output("T",
								new TextAttributes(Color.black, Color.red));
					} else if (Maze[i][j] == "ct") {
						Main.cn.getTextWindow().output("T",
								new TextAttributes(Color.black, Color.blue));
					} else if (Maze[i][j] == "pce" || Maze[i][j] == "O") {
						Main.cn.getTextWindow().output("O",
								new TextAttributes(Color.black, Color.red));
					} else if (Maze[i][j] == "F") {
						Main.cn.getTextWindow().output("F",
								new TextAttributes(Color.black, Color.red));
					} else if (Maze[i][j] == "pw") {
						Main.cn.getTextWindow().output("X",
								new TextAttributes(Color.black, Color.red));
					}
				} else if (CengMan.playermaze[i][j] == 0) {
					Main.cn.getTextWindow().setCursorPosition(j, i + 5);
					if (Maze[i][j] == "#") {
						Main.cn.getTextWindow().output("#",
								new TextAttributes(Color.orange, Color.orange));
					} else if (Maze[i][j] == " ") {
						Main.cn.getTextWindow().output(" ",
								new TextAttributes(Color.black, Color.black));
					} else if (Maze[i][j] == "pb") {
						Main.cn.getTextWindow().output("|",
								new TextAttributes(Color.black, Color.black));
					} else if (Maze[i][j] == "pc") {
						Main.cn.getTextWindow().output(
								("" + Game.player_cengmans.get(
										Game.count_pc - 1).getLevel()),
								new TextAttributes(Color.black, Color.red));
					} else if (Maze[i][j] == "cb") {
						Main.cn.getTextWindow().output("|",
								new TextAttributes(Color.black, Color.black));
					} else if (Maze[i][j] == "cc") {
						Main.cn.getTextWindow().output(
								("" + Game.computer_cengmans.get(
										Game.count_cc - 1).getLevel()),
								new TextAttributes(Color.black, Color.black));
					} else if (Maze[i][j] == "pt") {
						Main.cn.getTextWindow().output("T",
								new TextAttributes(Color.black, Color.black));
					} else if (Maze[i][j] == "pce") {
						Main.cn.getTextWindow().output("O",
								new TextAttributes(Color.black, Color.black));
					}
				}
			}
		}
		Main.cn.getTextWindow().setCursorPosition(0, 0);
	}

	public void initializeCengmanMaze() {
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 49; j++) {
				CengMan.playermaze[i][j] = 0;
				CengMan.computermaze[i][j] = 0;
			}
		}
	}
}
