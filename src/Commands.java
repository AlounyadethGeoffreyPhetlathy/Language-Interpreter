public enum Commands{
	PRINT("ຂຽນ"),
	CREATE_VARIABLE("ສ້າງ"),
	READ_VARIABLE("ອ່ານ");
	
	private String command;
	
	private Commands(String s) {
		command = s;
	}
	
	public String GetCommand() {
		return command;
	}
	
	public static Commands Execute(String input) {
		for (Commands c : Commands.values()) {
			if (c.GetCommand().equals(input)) {
				return c;
			}
		}
		throw new RuntimeException("Invaild command in file");
	}
}