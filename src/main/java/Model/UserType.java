package Model;

public enum UserType {

	CUSTOMER(
			Names.CUSTOMER_NAME
	),
	EMPLOYEE(
			Names.EMPLOYEE_NAME
	),
	ADMIN(
			Names.ADMIN_NAME
	);

	private String userType;

	UserType(String userType) {
		this.userType = userType;
	}

	public String getUserType() {
		return userType;
	}

	private static final class Names {
		private static final String CUSTOMER_NAME = "Customer";
		private static final String EMPLOYEE_NAME = "Employee";
		private static final String ADMIN_NAME = "Admin";
	}
}
