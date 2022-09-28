import java.util.HashSet;
import java.util.Vector;

public class Allocator {

	Vector<HashSet<Integer>> freeblocks = new Vector<HashSet<Integer>>();
	int maxk;

	Allocator(int size) {
		// TODO
		// Écrire le constructeur n'est pas une question de l'énoncé,
		// mais il faut le faire pour tester votre code
	}

	static int buddy(int addr, int k) {
		// TODO
		return 0;
	}

	int reserve(int k) {
		// TODO
		return 0;
	}

	public int alloc(int size) {
		// TODO
		return 0;
	}

	public void free(int addr, int size) {
		// TODO
	}
}
