
// 3.2 COLLISION ENTRE DEUX BILLES 

public class BallCollisionEvent extends Event {
	Ball a, b;
	// ...
	int nb_collisions_a;
	int nb_collisions_b;

	BallCollisionEvent(Billiard billiard, double time, Ball a, Ball b) {
		super(billiard, time);

		this.a = a;
		this.b = b;
		// TODO (2 lignes)
		this.nb_collisions_a = this.a.nbCollisions();
		this.nb_collisions_b = this.b.nbCollisions();
	}

	@Override
	boolean isValid() {
		if (a.nbCollisions() != this.nb_collisions_a || b.nbCollisions() != this.nb_collisions_b) {
			return false;
		}
		// TODO (1 ligne)
		return true;
	}

	@Override
	void process() {
		// TODO (3 lignes)
		a.collideBall(b);
		this.billiard.predictCollisions(a);
		this.billiard.predictCollisions(b);
	}

}
