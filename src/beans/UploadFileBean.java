package beans;

import javax.servlet.http.Part;

public class UploadFileBean {

	private String uuid;
	private String fileName;
	private int uploadProgress;
	private Part filePart;

	public UploadFileBean() {
		super();
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getUploadProgress() {
		return uploadProgress;
	}

	public void setUploadProgress(int uploadProgress) {
		this.uploadProgress = uploadProgress;
	}

	public Part getFilePart() {
		return filePart;
	}

	public void setFilePart(Part filePart) {
		this.filePart = filePart;
	}


}