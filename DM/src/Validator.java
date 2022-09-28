import java.util.TreeSet;

public class Validator {
	TreeSet<Integer> startpoints, endpoints;
	int N;

	Validator(int N) {
		startpoints = new TreeSet<Integer>();
		endpoints = new TreeSet<Integer>();
		this.N = N;
	}

	void alloc(int addr, int size) {
		startpoints.add(addr);
		endpoints.add(addr + size);
	}

	void free(int addr, int size) {
		startpoints.remove(addr);
		endpoints.remove(addr + size);
	}

	boolean isfree(int addr, int size) {
		// TODO
		return false;
	}
}
