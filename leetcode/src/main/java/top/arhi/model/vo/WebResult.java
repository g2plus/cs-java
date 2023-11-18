package top.arhi.model.vo;

/**
 * @作者: 公众号【Java分享客栈】
 * @日期: 2023/10/12 23:15
 * @描述:
 */
public class WebResult<T> {

	private int code;
	private String msg;
	private T data;

	public WebResult(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public WebResult(int code, String msg, T data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Result{" + "code=" + code + ", msg='" + msg + '\'' + ", data=" + data + '}';
	}
}
