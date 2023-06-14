package org.rsystems.response;

public enum Status {
	
	SUCCESS("Success"), FAIL("Fail"), RETRY("Retry"), INVALID("Invalid"), SUBSCRIPTION_PLAN_ISSUE("Subscription_Plan_Issue"), OTHER_ERROR("Other_error");

	private String status;

	private Status(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}
