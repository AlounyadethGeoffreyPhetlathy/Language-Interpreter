public class Token {
	private String value;
	private String type;
	
	public Token(String value, String type) {
		this.value = value;
		this.type = type;
	}
	
	public String GetValue() {
		return value;
	}
	
	public String GetType() {
		return type;
	}
}
