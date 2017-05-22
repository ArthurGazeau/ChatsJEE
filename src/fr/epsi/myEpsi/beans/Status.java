package fr.epsi.myEpsi.beans;

public enum Status {
	PUBLIC(1), PRIVATE(2), ARCHIVED(3);

	private final int value;

	private Status(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static Status getStatus(int value) {
		switch (value) {
			case 1:
				return Status.PUBLIC;
			case 2:
				return Status.PRIVATE;
			case 3:
				return Status.ARCHIVED;
		}
		return null;
	}
}
