package Models;

public class Songs {
	int ID;
	String name;
	Genre genre;
	String artist;
	
	public Songs(int id,String title, Genre gen, String singer){
		ID = id;
		name = title;
		genre = gen;
		artist = singer;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}
	
	
	
}
