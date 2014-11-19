package Controllers;

import Models.Genre;

public class GenreController {
	
	Genre genre;
	public Genre getGenre(String gen){
		switch(gen){
		case "Rock": genre = Genre.Rock;
			break;
		case "Pop": genre = Genre.Pop;
			break;
		case "Metal": genre = Genre.Metal;
			break;
		case "HipHop": genre = Genre.HipHop;
			break;
		case "Classical": genre = Genre.Classical;
			break;
		case "House": genre = Genre.House;
			break;
		}
		return genre;
	}

}
