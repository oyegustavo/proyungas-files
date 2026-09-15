package ar.org.proyungas.response;

public class UploadResponse {
    private String message;
    private String bucketId;
    private String fileName;
    
	public UploadResponse(String message, String bucketId, String fileName) {
		super();
		this.message = message;
		this.bucketId = bucketId;
		this.fileName = fileName;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getBucketId() {
		return bucketId;
	}
	public void setBucketId(String bucketId) {
		this.bucketId = bucketId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
    
    
}
