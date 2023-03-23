package com.wizeline.maven.learningjava.model;

public class ErrorDTO {
    /**
     ** Attribute errorCode: Save error code id
	 */
    String errorCode;
    /**
     * Attribute message: Save error message
     */
    String message;
    

    public ErrorDTO() {
		super();
	}

	public ErrorDTO(String errorCode, String message) {
		super();
		this.errorCode = errorCode;
		this.message = message;
	}

	public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
