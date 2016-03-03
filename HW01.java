package ee.icefire.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Random;


/**
 * Template for HW01: Treasurehunt.
 * More information:
 * https://courses.cs.ttu.ee/pages/ITI0011:Aardejaht
 */
public class HW01 {
    /**
     * Value to return in makeMove in case
     * the cell was empty.
     */
    public static final int CELL_EMPTY = 0;
 
    /**
     * Value to return in makeMove in case
     * the cell contained treasure.
     */
    public static final int CELL_TREASURE = 1;
 
    /**
     * Value to return in makeMove in case
     * the cell does not exist.
     */
 
    public static final int CELL_ERROR = -1;
    
    private int row = 0;
    private int col = 0;
    private int digging = 0;
    private int treasures = 0;
    
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getDigging() {
		return digging;
	}

	public void setDigging(int digging) {
		this.digging = digging;
	}

	public int getTreasures() {
		return treasures;
	}

	public void setTreasures(int treasures) {
		this.treasures = treasures;
	}

	public PrintStream printScore() {
		return System.out.format("kaevamisi: %s, aardeid jäänud: %s", digging, treasures);
	}

	public HW01(){
    	startGame();	
    }
    
    private void startGame() {
    	
    	String string = "";
    	do {
    		string = enterArguments("Sisesta M (ridade arv), N (veergude arv), X (aarete arv): ");
		} while (!checkInput(string, 3));
    	
    	setRow(Integer.parseInt(string.split(",")[0]));
    	setCol(Integer.parseInt(string.split(",")[1]));
    	setTreasures(Integer.parseInt(string.split(",")[2]));
    	
    	createMap(getRow(), getCol(), getTreasures());
    	
    	System.out.println("\nEdukat kaevamist!\n");
    	
    	printMap(guessingMap);
    	while (getTreasures()>0) {
    		printScore();
        	String whatDigging = "";
        	do {
        		whatDigging = enterArguments("\nMida kaevame (rida, veerg): ");
    		} while (!checkInput(whatDigging, 2));
        	
        	if(checkDigging(whatDigging)){
        		printMap(guessingMap);
        		System.out.println("AARE!\n");
        		setTreasures(getTreasures()-1);
        	} else {
        		printMap(guessingMap);
        	}
        	setDigging(getDigging()+1);
		}
    	printScore();
    	
    	System.out.format("\n\nMäng läbi! Kokku tehti %s kaevamist.", getDigging());
    	
    	String playMore = enterArguments("Kas soovid veel mängida? [jah/ei] ");
    	
    	if (playMore.equals("jah")) {
			new HW01();
		} else {
			System.out.println("\nBye!");
		}
    	
	}

	private boolean checkDigging(String whatDigging) {
		String[] a = whatDigging.split(",");
		if (map[Integer.parseInt(a[0])-1][Integer.parseInt(a[1])-1].equals(CELL_TREASURE+"")) {
			guessingMap[Integer.parseInt(a[0])-1][Integer.parseInt(a[1])-1]="+";
			return true;
		} else {
			guessingMap[Integer.parseInt(a[0])-1][Integer.parseInt(a[1])-1]=" ";
		}
		return false;
	}

	private String enterArguments(String inputQuestion) {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print(inputQuestion);
        String string = null;
        try {
			string = bufferedReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return string;
	}
	
	private boolean checkInput(String string, int argumentCount) {
		string = string.replaceAll("\\s",""); // remove spaces
		if (string.split(",").length!=argumentCount) {
			return false;
		}
		String[] numbers = string.split(",");
		for (String number : numbers) { 
			if(!number.matches("[0-9]+")){ // only numbers 
				return false;
			}
			if(Integer.parseInt(number)<1){ // number must be integer and bigger than 1 
				return false;
			}
		}
		// there must be less treasures than ROW * COL
		if (argumentCount==3) {
			if (Integer.parseInt(numbers[0])*Integer.parseInt(numbers[1])<Integer.parseInt(numbers[2])){
				return false;
			}
		}
		return true;
		
	}
	/**
     * Main entry.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
    	new HW01();
 
        // game logic here
    }
 
    /**
     * Makes move to cell in certain row and column
     * and returns the contents of the cell.
     * Use CELL_* constants in return.
     * @param row Row to make move to.
     * @param col Column to make move to.
     * @return Contents of the cell.
     */
    public static int makeMove(int row, int col) {
        if (row == -1) return CELL_ERROR;
        return CELL_EMPTY;
    }
 
    /**
     * Creates a map with certain measures and treasures.
     * As this is a static method which doesn't return anything (void),
     * you should store the created map internally.
     * This means you can choose your own implementation of how to store
     * the map.
     * The treasures should be put on the map randomly using setCell method.
     * @param height Height of the map.
     * @param width Width of the map.
     * @param treasures The number of treasures on the map.
     * @return Whether map was created.
     */
    static String[][] map;
    static String[][] guessingMap;
    
    public static boolean createMap(int height, int width, int treasures) {
        // initialize map (for example 2D-array)
        //   - set all cells empty (is this needed?)
        // do some random for every treasure and add them to map:
    	
    	drawMap(height, width, treasures);
 
        if (height == -3) return false;
        return false;
    }
    
    private static void printMap(String[][] map) {
    	for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}

	private static void drawMap(int height, int width, int treasures) {
    	map = new String[height][width];
    	guessingMap = new String[height][width];
    	for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				map[i][j]=".";
				guessingMap[i][j]=".";
			}
		}
    	putTreasures(height, width, treasures);
	}

	private static void putTreasures(int height, int width, int treasures) {
		Random random = new Random();
    	while (countTresures()<treasures) {
    		setCell(random.nextInt(height), random.nextInt(width), CELL_TREASURE);
		}
	}
	
	private static int countTresures() {
		int number = 0;
		for (String[] strings : map) {
			for (String string : strings) {
				if (string.equals(CELL_TREASURE+"")) {
					number ++;
				}
			}
		}
		return number;
	}

	/**
     * Sets the cell value for the active map (created earlier using
     * createMap method).
     * This method is required to test certain maps
     * @param row Row index.
     * @param col Column index.
     * @param cellContents The value of the cell.
     * @return Whether the cell value was set.
     */
    public static boolean setCell(int row, int col, int cellContents) {
    	map[row][col]=CELL_TREASURE+"";
        if (row == -123) return false;
        return false;
    }
}
