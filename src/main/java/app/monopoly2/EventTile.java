package app.monopoly2;

public class EventTile extends Tile {
	enum EventType {
		START, JAIL, GO_TO_JAIL, BUS, RANDOM, LOSE
	}

	EventType type;

	public EventTile(EventType type, int id, double x,
			double y, double width, double height) {
		super(id, x, y, width, height);
		this.type = type;
	}

	public void action() {
		switch (type) {
		}
	}

	@Override
	public String toString() {
		return String.format("Type: %s,Pos: %f,%f ,Size: %f,%f",
				type.name(), this.x, this.y, this.width, this.height);
	}

	public EventType getEventType() {
		return type;
	}
}
