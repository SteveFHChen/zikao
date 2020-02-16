package com.dto;

public class HttpResponse {

	private HttpReponseHeader header = new HttpReponseHeader();
	private Object data;
	
	public HttpReponseHeader getHeader() {
		return header;
	}
	public void setHeader(HttpReponseHeader header) {
		this.header = header;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
