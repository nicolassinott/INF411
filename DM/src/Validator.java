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
		// TODO (to test)
		
		// On verifie si le [addr,addr+size) est inclus en [0,N)
		
		if(addr < 0 && addr + size >= this.N){
			return false;
		}

		Integer start_floor = this.startpoints.floor(addr + size - 1);
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

	public static void main(String[] args) {
		Validator a = new Validator(128*128);
		a.alloc(0, 1);
		a.alloc(1, 1);
		a.alloc(16, 12);
		a.alloc(2, 1);
		a.alloc(4, 4);
		//a.alloc(8, 2);
		//a.alloc(3, 1);
		System.out.println(a.isfree(3, 1));
		
	
	}
}
