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
		
		// On vérifie si le [addr,addr+size) est inclus en [0,N)
		
		if(addr < 0 && addr + size >= this.N){
			return false;
		}

		Integer start_floor = this.startpoints.floor(addr + size);
		Integer end_floor = this.endpoints.floor(addr + size);

		if(start_floor == null){
			return true;
		}

		if(start_floor >= addr){
			return false;
		} else {
			if(end_floor == null || end_floor > addr){
				return false;
			}
		}

		return true;
		
		// TODO
	}
}
