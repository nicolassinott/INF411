import java.io.FileNotFoundException;

class GraphicalBilliard extends Billiard {

	GraphicalBilliard() {
		super();
	}

	GraphicalBilliard(String path) throws FileNotFoundException {
		super(path);
	}

	@Override
	void initialize() {
		super.initialize();

		StdDraw.setCanvasSize(600, 600);
		StdDraw.enableDoubleBuffering();
		eventQueue.add(new RedrawEvent(this, currentTime));
	}

	public static void main(String[] args) throws FileNotFoundException {
		//Billiard billiard = new GraphicalBilliard("init/billiards2.txt");
		// Billiard billiard = new GraphicalBilliard("init/billiards4.txt");
		// Billiard billiard = new GraphicalBilliard("init/billiards5.txt");
		// Billiard billiard = new GraphicalBilliard("init/brownian.txt");
		// Billiard billiard = new GraphicalBilliard("init/brownian2.txt");
		// Billiard billiard = new GraphicalBilliard("init/diagonal.txt");
		// Billiard billiard = new GraphicalBilliard("init/diagonal1.txt");
		// Billiard billiard = new GraphicalBilliard("init/diagonal2.txt");
		Billiard billiard = new GraphicalBilliard("init/diffusion.txt");
		// Billiard billiard = new GraphicalBilliard("init/diffusion2.txt");
		// Billiard billiard = new GraphicalBilliard("init/diffusion3.txt");
		// Billiard billiard = new GraphicalBilliard("init/p10.txt");
	    //Billiard billiard = new GraphicalBilliard("init/p100-.5K.txt");
		// Billiard billiard = new GraphicalBilliard("init/p100-.125K.txt");
		// Billiard billiard = new GraphicalBilliard("init/p100-2K.txt");
		// Billiard billiard = new GraphicalBilliard("init/p1000-.5K.txt");
		// Billiard billiard = new GraphicalBilliard("init/p1000-2K.txt");
		// Billiard billiard = new GraphicalBilliard("init/p2000.txt");
		// Billiard billiard = new GraphicalBilliard("init/pendulum.txt");
		// Billiard billiard = new GraphicalBilliard("init/squeeze.txt");
		// Billiard billiard = new GraphicalBilliard("init/squeeze2.txt");
		// Billiard billiard = new GraphicalBilliard("init/wallbouncing.txt");
		// Billiard billiard = new GraphicalBilliard("init/wallbouncing2.txt");
		// Billiard billiard = new GraphicalBilliard("init/wallbouncing3.txt");
		//Billiard billiard = new GraphicalBilliard("init/joule-expansion.txt");
		
		

		billiard.run();		
	}

}
