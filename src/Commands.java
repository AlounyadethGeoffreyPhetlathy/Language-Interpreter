public enum Commands {
	PRINT("ຂຽນ"),
	CREATE_VARIABLE("ສ້າງ"),
	READ_VARIABLE("ອ່ານ"),
	SET_VARIABLE("ເກັບໄວ້ໃນ"),
	CONCATENATE("ປະກອບ"),
	COMBINE("ລວມກັນ");
	
	private String command;
	
	private Commands(String com) {
		command = com;
	}
	
	public String GetCommand() {
		return command;
	}
	
	public static Commands Execute(String input) {
//		System.out.println(input);
		for (Commands c : Commands.values()) {
			if (c.GetCommand().equals(input)) {
				return c;
			}
		}
		throw new RuntimeException("Invaild command in file");
	}
}
