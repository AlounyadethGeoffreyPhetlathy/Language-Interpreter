public enum Commands {
	COMMENT("ຄວາມຄິດເຫັນ"),
	EMPTYLINE(""),
	LEFTBRACKET("{"),
	RIGHTBRACKET("}"),
	PRINT("ຂຽນ"),
	INPUT("ຂໍ້ມູນນໍາເຂົ້າ"),
	CREATE_VARIABLE("ສ້າງ"),
	READ_VARIABLE("ອ່ານ"),
	SET_VARIABLE("ເກັບໄວ້ໃນ"),
	CONCATENATE("ປະກອບ"),
	MATH("ຕົວເລກ"),
//	COMBINE("ລວມກັນ"), DEPRECATED
	IF("ຖ້າວ່າ"),
	WHILE("ໃນຂະນະທີ່"),
	FOR("ສໍາລັບ");
	
	private String command;
	
	private Commands(String com) {
		command = com;
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
