package JukeBox;

import Controllers.*;

import java.io.InputStreamReader;
import java.util.Scanner;
import Database.DB;

public class JukeBox {

	public void closeScanner(Scanner scanner) {
		scanner.close();
	}

	public static void main(String[] args) {
		SongController sc = new SongController();
		PlayListController plc = new PlayListController();
		PlayQueueController pqc = new PlayQueueController();
		Scanner in = new Scanner(new InputStreamReader(System.in));

		if (DB.openConnection())
			System.out.println("Database Connection successful\n");

		System.out.println("------------------------------\n"
				+ "* Anthony's Kick ass Jukebox *\n"
				+ "------------------------------\n");

		int option1 = 0;
		
		
		try {
			while (true) {
				System.out.println("\nMain Menu\n"
						+ "---------------------------");
				System.out.println("Choose a option: \n" + "1) PlayQueue \n"
						+ "2) PlayList \n" + "3) Songs \n"
								+ "9) Exit\n");
				option1 = in.nextInt();
				if (option1 < 1 || option1 > 9 || (option1 > 3 && option1 < 9)) {  
					System.out.println("Please enter a number between 1 and 3 or Enter 9 to Exit");
					System.exit(1);
				}

				switch (option1) {
				case 1:
					pqc.start();

					break;
				case 2:
					plc.insertPL();
					plc.start();

					break;
				case 3:
					sc.insertSongs();
					sc.start();
					break;
				case 9:
					System.out.println("Good bye ~<3");
					DB.close();
					in.close();
					System.exit(1);
				}
			}
		} catch (Exception e) {e.printStackTrace();}
	}
}
