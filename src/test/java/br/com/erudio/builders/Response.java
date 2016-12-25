package br.com.erudio.builders;

public interface Response {
	public static final String OK = "\"status\":\"OK\"";
	public static final String CREATED = "\"status\":\"CREATED\"";
	public static final String CONFLICT = "\"status\":\"CONFLICT\"";
	public static final String NOT_FOUND = "\"status\":\"NOT_FOUND\"";
	public static final String INTERNAL_SERVER_ERROR = "\"status\":\"INTERNAL_SERVER_ERROR\"";
}
