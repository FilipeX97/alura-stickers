
public class Filme {
	
	private String id;
	private String rank;
    private String title;
    private String image;
    private String imDbRating;
    private String imDbRatingUser;
    private String imDbRatingCount;
    
	public String getImDbRating() {
		return imDbRating;
	}
	
	public void setImDbRating(String imDbRating) {
		this.imDbRating = imDbRating;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getImage() {
		return image;
	}
	
	public String getId() {
		return id;
	}
	
	public String getImDbRatingCount() {
		return imDbRatingCount;
	}

	public String getImDbRatingUser() {
		return imDbRatingUser;
	}

	public void setImDbRatingUser(String imDbRatingUser) {
		this.imDbRatingUser = imDbRatingUser;
	}

	public String getRank() {
		return rank;
	}

	@Override
	public String toString() {
		return "Filme [id=" + id + ", title=" + title + ", image=" + image + ", imDbRating=" + imDbRating
				+ ", imDbRatingUser=" + imDbRatingUser + ", imDbRatingCount=" + imDbRatingCount + "]";
	}
    
    

}
