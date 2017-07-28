package com.common.util.global;

/**
 * json返回数据
 * @author adminx
 *
 */
public class ResponseBean {

	private String code;
	
	private String msg;
	
	private Object data;
	
	public ResponseBean(String code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	public ResponseBean(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	public ResponseBean() {
	}
	public ResponseBean(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
}
