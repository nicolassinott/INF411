import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Vector;

//2 PROGRAMMATION DU BILLARD

public class Billiard {

	Vector<Ball> balls;
	double currentTime;
	PriorityQueue<Event> eventQueue;

	Billiard() {
		eventQueue = new PriorityQueue<Event>();
		balls = new Vector<Ball>();
	}

	// 2.1 LA BOUCLE DES ÉVÈNEMENTS

	void eventLoop() {
		// TODO (7-10 lignes)
		while (!this.eventQueue.isEmpty()) {
			Event e = this.eventQueue.poll();
			if (e.isValid()) {
				for (Ball ball : this.balls) {
					ball.forward(e.time - this.currentTime);
				}
				this.currentTime = e.time;
				e.process();
			}
			//if (this.currentTime % 10 == 0) {
			//	System.out.println("test");
			//} - to update for computing kinetic energy
		}
	}

	// 2.2 PRÉDICTION DES COLLISIONS

	void predictCollisions(Ball b) {
		// TODO (~10 lignes)
		for (Ball ball : this.balls) {
			if (!ball.equals(b)) {
				double collision_time = this.currentTime + b.nextBallCollision(ball);
				if (collision_time > 0) {
					this.eventQueue.add(new BallCollisionEvent(this, collision_time, b, ball));
				}
			}
		}
		double time_vert = this.currentTime + b.nextWallCollision(Direction.Horizontal);
		if (time_vert > 0) {
			this.eventQueue.add(new WallCollisionEvent(this, time_vert, b, Direction.Horizontal));
		}

		double time_hori = this.currentTime + b.nextWallCollision(Direction.Vertical);
		if (time_hori > 0) {
			this.eventQueue.add(new WallCollisionEvent(this, time_hori, b, Direction.Vertical));
		}
	}

	void initialize() {
		// TODO (2 lignes)
		for (Ball b : this.balls) {
			this.predictCollisions(b);
		}
	}

	void run() {
		//if (this.currentTime % 10 == 0) 
		initialize();
		eventLoop();
	}

	// Lit une configuration initiale depuis un fichier
	Billiard(String path) throws FileNotFoundException {
		this();

		Scanner input = new Scanner(new File(path));
		input.useLocale(Locale.ENGLISH); // pour que java reconnaisse "1.2" plutôt que "1,2"

		input.nextInt(); // Le premier entier contient le nombre de lignes.
							// Mais comme on utilise un tableau redimensionnable, on l'ignore.
		while (input.hasNext()) {
			double x = input.nextDouble();
			double y = input.nextDouble();
			double vx = input.nextDouble();
			double vy = input.nextDouble();
			double radius = input.nextDouble();
			double mass = input.nextDouble();
			int r = input.nextInt();
			int g = input.nextInt();
			int b = input.nextInt();

			balls.add(new Ball(x, y, vx, vy, radius, mass, new Color(r, g, b)));
		}
	}
}
