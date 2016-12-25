package br.com.erudio.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ResponseWrapper<T> {
	
	private HttpStatus status;
	private String description;
	private T result;
	
	public ResponseWrapper(){}
	
	public ResponseWrapper(HttpStatus status, String description, T result) {
		this.status = status;
		this.description = description;
		this.result = result;
	}
	
	public ResponseEntity<ResponseWrapper<T>> response(){
		return new ResponseEntity<ResponseWrapper<T>>(this,HttpStatus.OK);	
	}
	
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(T result) {
		this.result = result;
	}
	
}
