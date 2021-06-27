

public class Movie {
	private int id;
	private String color;
	private String movie_title;
	private int duration;
	private String director_name;
	private String act1;
	private String act2;
	private String act3;
	private String movie_imdb_link;
	private String language;
	private String country;
	private String rating;
	private int title_year;
	private double imdb_score;
	
	public Movie(int id, String color, String movie_title,
			int duration, String director_name, String act1, String act2,
			String act3, String movie_imdb_link, String language,
			String country, String rating, int title_year, double imdb_score){
		this.id = id;
		this.color = color;
		this.movie_title = movie_title;
		this.duration = duration;
		this.director_name = director_name;
		this.act1 = act1;
		this.act2 = act2;
		this.act3 = act3;
		this.movie_imdb_link = movie_imdb_link;
		this.language = language;
		this.country = country;
		this.rating = rating;
		this.title_year = title_year;
		this.imdb_score = imdb_score;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getMovie_title() {
		return movie_title;
	}

	public void setMovie_title(String movie_title) {
		this.movie_title = movie_title;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getDirector_name() {
		return director_name;
	}

	public void setDirector_name(String director_name) {
		this.director_name = director_name;
	}

	public String getAct1() {
		return act1;
	}

	public void setAct1(String act1) {
		this.act1 = act1;
	}

	public String getAct2() {
		return act2;
	}

	public void setAct2(String act2) {
		this.act2 = act2;
	}

	public String getAct3() {
		return act3;
	}

	public void setAct3(String act3) {
		this.act3 = act3;
	}

	public String getMovie_imdb_link() {
		return movie_imdb_link;
	}

	public void setMovie_imdb_link(String movie_imdb_link) {
		this.movie_imdb_link = movie_imdb_link;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public int getTitle_year() {
		return title_year;
	}

	public void setTitle_year(int title_year) {
		this.title_year = title_year;
	}

	public double getImdb_score() {
		return imdb_score;
	}

	public void setImdb_score(double imdb_score) {
		this.imdb_score = imdb_score;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}
	
	public String toString() {
		StringBuilder StrBuilder = new StringBuilder();
		StrBuilder.append("id: " + getId() + "\n");
		StrBuilder.append("color: " + getColor() + "\n");
		StrBuilder.append("title: " + getMovie_title() + "\n");
		StrBuilder.append("duration: " + getDuration() + "\n");
		StrBuilder.append("director_name: " + getDirector_name() + "\n");
		StrBuilder.append("act1: " + getAct1() + "\n");
		StrBuilder.append("act2: " + getAct2() + "\n");
		StrBuilder.append("act3: " + getAct3() + "\n");
		StrBuilder.append("movie_imdb_link: " + getMovie_imdb_link() + "\n");
		StrBuilder.append("language: " + getLanguage() + "\n");
		StrBuilder.append("country: " + getCountry() + "\n");
		StrBuilder.append("content_rating: " + getRating() + "\n");
		StrBuilder.append("title_year: " + getTitle_year() + "\n");
		StrBuilder.append("imdb_score: " + getImdb_score() + "\n");
		
		
		return StrBuilder.toString();
	}
	
}
