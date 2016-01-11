package com.tsystems.model.excep;

public enum ExceptionMessages {
	DATABASE_FAILED_CONNECT(0, "Unable to connect database. Please wait for a while."),
	DUPLICATE_USER(1,"This user already exists."), 
	WRONG_ID(2, "You are trying to use unapropriate id."),
	RESOURCE_DOES_NOT_EXISTS(3,"Resource does not exists");

	private final int code;
	private final String description;

	private ExceptionMessages(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public int getCode() {
		return code;
	}

	@Override
	public String toString() {
		return code + ": " + description;
	}
}