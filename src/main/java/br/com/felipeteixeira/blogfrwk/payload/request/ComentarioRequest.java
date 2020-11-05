package br.com.felipeteixeira.blogfrwk.payload.request;

public class ComentarioRequest {
	
	private Long idPost;
	private String description;
	
	public Long getIdPost() {
		return idPost;
	}
	public void setIdPost(Long idPost) {
		this.idPost = idPost;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
