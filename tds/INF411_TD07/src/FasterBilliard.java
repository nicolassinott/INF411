import java.io.FileNotFoundException;
import java.util.PriorityQueue;

//2 PROGRAMMATION DU BILLARD

public class FasterBilliard extends Billiard {

	public FasterBilliard() {
		super();
	}

	// Lit une configuration initiale depuis un fichier
	FasterBilliard(String path) throws FileNotFoundException {
		super(path);
	}

	@Override
	void predictCollisions(Ball b) {
		PriorityQueue<Event> evq = new PriorityQueue<>(), billiardEvq = eventQueue;

		// détourne temporairement this.eventQueue pour que predictCollisions
		// enregistre les évènements dans evq plutôt que this.eventQueue.
		eventQueue = evq;
		super.predictCollisions(b);
		eventQueue = billiardEvq;

		if (!evq.isEmpty())
			// enregistre un évènement unique qui contient tous les évènement prédits
			eventQueue.add(new BallEventQueueEvent(this, b, evq));
	}
}
