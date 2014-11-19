package Controllers;

import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

import Database.SongQuerys;
import Models.Genre;
import Models.Songs;

public class SongController {
	GenreController gc = new GenreController();
	Scanner in = new Scanner(new InputStreamReader(System.in));
	Scanner in1 = new Scanner(new InputStreamReader(System.in));
	ArrayList<Songs> songs = new ArrayList<Songs>();
	ArrayList<Songs> searchArray = new ArrayList<Songs>();
	Genre genre;
	int option =0;

	public void insertSongs() {
		ResultSet rs;
		rs = SongQuerys.SelectSongs();
		try {
			while (rs.next()) {
				int songID = rs.getInt(1);
				String name = rs.getString(2);
				String genreID = rs.getString(3);
				String artist = rs.getString(4);

				Genre genre = gc.getGenre(genreID);
				Songs x = new Songs(songID, name, genre, artist);

				songs.add(x);
			}
		} catch (Exception error) {error.printStackTrace();}
	}
	
	public ArrayList<Songs> getSongArray(){
		insertSongs();
		return songs;
	}

	
	public void start() {
		boolean back = false;
		System.out.println("Welcome to the Song menu\n"
				+ "--------------------------");
		try {
			while (!back) {
				
				System.out.println("\nChoose a option: \n" + "1) View Song List \n"
						+ "2) Search \n9) Back");
				
				option = in.nextInt();
				
				if (option < 1 || option > 9 || (option > 2 && option < 9)) {
					System.out.println("Please enter a Number between 1 and 2");
					
				}

				switch(option){		
				case 1:
					printSongs();
					break;
				case 2:
					search();
					break;
				case 9: 
					back = true;
				}
			}
		} catch (Exception e) {e.printStackTrace();}
		
		//in.close();
		
	}

	
	public void printSongs() {
		boolean back = false;

		for (int i = 0; i <= songs.size() - 1; i++) {
			Songs song = songs.get(i);
			System.out.println("\nSong title: " + song.getName() + "\nGenre: "
					+ song.getGenre() + "\nArtists: " + song.getArtist());
		}
		System.out.println("\n9)Back");
		while (!back) {
			option = in.nextInt();
			if (option == 9) {
				back = true;
			} else {
				System.out.println("unrecognisable input");
			}
		}
	}

	
	public void search() {
		ResultSet rs = SongQuerys.SelectSongs();
		boolean back = false;
		String searchOp;
		try{
			while (!back) {
				System.out.println("\n \nSearch by: \n" + "1) Title \n"
						+ "2) Genre \n" + "3) Artist \n" + "9) Back \n");

				option = in.nextInt();
				if (option < 1 || option > 9 || (option > 4 && option < 9)) {
					System.out.println("Please enter a number between 1 and 4 or Enter 9 to go back");
					Thread.sleep(1000);
				}
				
				switch(option){
				case 1:
					System.out.println("Enter Title: ");
					searchOp = in1.nextLine();
					rs = SongQuerys.SelectSongsByTitle(searchOp);
					putSongsInArray(rs);
					Thread.sleep(2000);
					break;
				case 2:
					System.out.println("Enter Genre: ");
					searchOp = in1.nextLine();
					rs = SongQuerys.SelectSongsByGenre(searchOp);
					putSongsInArray(rs);
					Thread.sleep(2000);
					break;
				case 3:
					System.out.println("Enter Artist: ");
					searchOp = in1.nextLine();
					rs = SongQuerys.SelectSongsByArtist(searchOp);
					putSongsInArray(rs);
					Thread.sleep(2000);
					break;
				case 9: 
					back = true;
				}
				
			}
		}catch(Exception e){System.out.println("Song search Error.");}
			
	}
	
	
	public void putSongsInArray(ResultSet rs) {
		try {
			while (rs.next()) {
				int songID = rs.getInt(1);
				String name = rs.getString(2);
				String genreID = rs.getString(3);
				String artist = rs.getString(4);

				Genre genre = gc.getGenre(genreID);
				Songs x = new Songs(songID, name, genre, artist);
				searchArray.add(x);
			}

			if (searchArray.size() == 0) {
				System.out.println("\nNo entries found.");
			} else {
				for (int i = 0; i < searchArray.size(); i++) {
					Songs song = searchArray.get(i);
					System.out.println("\nSong title: " + song.getName()
							+ "\nGenre: " + song.getGenre() + "\nArtists: "
							+ song.getArtist());
				}
			}
		} catch (Exception e) {
			System.out.println("Search Error, please try again.");
		}
	}
	
	
}
