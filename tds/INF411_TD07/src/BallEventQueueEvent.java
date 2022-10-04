import java.util.PriorityQueue;

public class BallEventQueueEvent extends Event {
	Ball ball;
	int nbCollisions;
	PriorityQueue<Event> eventQueue;

	public BallEventQueueEvent(Billiard billiard, Ball ball, PriorityQueue<Event> eventQueue) {
		super(billiard, eventQueue.peek().time);
		this.ball = ball;
		this.nbCollisions = ball.nbCollisions();
		this.eventQueue = eventQueue;
	}

	@Override
	boolean isValid() {
		// TODO
		return true;
	}

	@Override
	void process() {
		// TODO
	}

}
