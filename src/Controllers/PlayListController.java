package Controllers;

import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

import Database.PlayListQueries;
import Database.SongQuerys;
import Models.Genre;
import Models.Songs;

public class PlayListController { 
	PlayListQueries db = new PlayListQueries();
	SongQuerys sdb = new SongQuerys();
	GenreController gc = new GenreController();
	
	Scanner in = new Scanner(new InputStreamReader(System.in));
	Scanner inLine = new Scanner(new InputStreamReader(System.in));
	
	ArrayList<Songs> plSongs = new ArrayList<Songs>();
	ArrayList<String> allPlayList= new ArrayList<String>();
	
	SongController sc = new SongController();
	ArrayList<Songs> allSongs = sc.getSongArray(); 
	
	public void insertPL() {
		ResultSet rs;
		rs = PlayListQueries.SelectPL();
		try {
			while (rs.next()) {
				String plName = rs.getString(1);
				allPlayList.add(plName);
			}
		} catch (Exception error) {error.printStackTrace();}
	}

	public void start() {
		System.out.println("Welcome to the PlayList menu\n"
				+ "--------------------------\n");
		System.out.println("Choose a option: \n" + "1) View all PlayLists \n"
				+ "2) Search for playList\n" + "3) Add new PlayList \n"
				+ "4) Add song to PlayList \n");

		try {
			int option = in.nextInt();
			if (option > 4 || option < 1) {
				System.out.println("Please enter a Number between 1 and 4");
				System.exit(1);
			}
			switch (option) {
			case 1:
				printPlayLists();
				break;
			case 2:
				search();
				break;
			case 3:
				createNewPl();
				break;
			case 4:
				addSong();
				break;
			}
		} catch (Exception e) {
			System.out.println("Song start Error, please try again.");
		} finally { in.close(); }
	}
	
	public void createNewPl() {
		try {
			System.out.println("Name of new PlayList (No Spaces): ");

			String plName = inLine.nextLine().toLowerCase();
			if (plName.contains(" ") || plName.length() < 1) {
				System.out.println("Error. No spaces allowed in the playList name");
			} else {
				db.createNewPL(plName);
				db.insertNametoPLList(plName);
				System.out.println("\nPlayList Successfully Created");
			}
		} catch (Exception e) {
			System.out.println("Crate PlayList Error, please try again.");
		} finally { in.close(); }
	}

	
	public void printPlayLists() {
		for (int i = 0; i < allPlayList.size(); i++) {
			String name = allPlayList.get(i);
			int songCount = db.countSongs(name);
			System.out.println("\nPlaylist: " + name + "\n" + songCount + " Songs contained");
		}
		System.out.println();
	}

	
	public void search() {
		boolean found = false;
		String playList = null;

		try {
			System.out.println("Enter playlist name to serach for:");

			String option = in.next();

			for (int i = 0; i < allPlayList.size(); i++) {
				if (option.equals(allPlayList.get(i))) {
					found = true;
					playList = allPlayList.get(i);
						break;
				}
				 else {found = false;}
			}
			if (!found)
				System.out.println("\nPlaylist not found.");
			else{ printPL(playList);}

		} catch (Exception e) {
			System.out.println("Search Error, please try again.");
		} finally {in.close();}
	}
	
	
	public void printPL(String playList) {
		int songCount = db.countSongs(playList);
		System.out.println("\n---Playlist: " + playList + "---\n---"
				+ songCount + " songs contained---");

		ResultSet rs = db.getPLSongs(playList);
		try {
			ArrayList<Integer> songIDs = new ArrayList<Integer>();
			while (rs.next()) {
				int songID = rs.getInt(2);
				songIDs.add(songID);
			}

			for (int j = 0; j < songIDs.size(); j++) {
				int id = songIDs.get(j);
				ResultSet rs1 = sdb.SelectSongsByID(id);
				while (rs1.next()) {
					String name = rs1.getString(2);
					String genreID = rs1.getString(3);
					String artist = rs1.getString(4);

					Genre genre = gc.getGenre(genreID);
					Songs x = new Songs(id, name, genre, artist);
					plSongs.add(x);
				}
			}
			
			for (int k = 0; k <= plSongs.size() - 1; k++) {
				Songs song = plSongs.get(k);
				System.out.println("\nSong title: " + song.getName()
						+ "\nGenre: " + song.getGenre() + "\nArtists: "
						+ song.getArtist());
			}
		} catch (Exception error) { error.printStackTrace(); }
	}
	
	public void addSong(){
		boolean plFound = false;
		boolean sFound = false;
		Songs song = new Songs(0,"null",Genre.House,"STUB");
		String playList = null;
		
		try{
			System.out.println("Enter playlist:");
			
			//finding playList
			String plName = in.next();
			for (int i = 0; i < allPlayList.size(); i++) {
				if (plName.equals(allPlayList.get(i))) {
					plFound = true;
					playList = allPlayList.get(i);
					break;
				}else {plFound = false;}
			}
			if (!plFound){
				System.out.println(plName + " was not found, Please try again");
				System.exit(1);
			}
			
			//finding if song exists
			System.out.println("Enter song title:");
			String searchSong = inLine.nextLine();
			
			for (int i = 0; i < allSongs.size(); i++) {
				if (searchSong.equals(allSongs.get(i).getName())) {
					sFound = true;
					song = allSongs.get(i);
					break;
				}else {sFound = false;}
			}
			if (!sFound){
				System.out.println(searchSong + " was not found, Please try again");
				System.exit(1);
			}
			
			if(plFound && sFound){
				db.insertSongtoPLList(song, playList);
				System.out.println("\n" + song.getName() + " added to playlist " + playList + "\n");
				printPL(playList);
			}

		}catch(Exception e){System.out.println("Add song Error, Please try again");}
		finally{ inLine.close(); }
	}

}
