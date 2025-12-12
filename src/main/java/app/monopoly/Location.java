package app.monopoly;

public abstract class Location {
	protected int id;

	public Location(int id) {
		this.id = id;
	}

	public int getID() {
		return this.id;
	}

}
