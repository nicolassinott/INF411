import java.util.HashSet;
import java.util.Vector;

public class Allocator {

	Vector<HashSet<Integer>> freeblocks = new Vector<HashSet<Integer>>();
	int maxk;

	Allocator(int size) {
		// TODO

		this.maxk = size;

		for(int i = 0; i<size; i++){
			HashSet<Integer> set_of_addr = new HashSet<Integer>();
			this.freeblocks.add(set_of_addr);
		}

		HashSet<Integer> set_of_addr = new HashSet<Integer>();
		set_of_addr.add(0);

		this.freeblocks.add(set_of_addr);

		//while(size != 1){
		//	HashSet<Integer> set_of_addr = new HashSet<Integer>();
		//	int number_of_addr = this.size / size;
		//	
		//	for(int i = 0; i < number_of_addr ; i++){
		//		set_of_addr.add((Integer)(i*size));
		//	}

		//	this.freeblocks.add(set_of_addr);

		//	size-- ;
		//}

		// TODO

		// Écrire le constructeur n'est pas une question de l'énoncé,
		// mais il faut le faire pour tester votre code
	}

	static int buddy(int addr, int k) {
		// TODO
		if(addr % (1 << k) == 0){
			return addr - (1 << k);
		} else {
			return addr + (1 << k);
		}
		// TODO
	}

	int reserve(int k) {

		Integer addr = this.freeblocks.elementAt(k).iterator().next();

		this.freeblocks.get(k);

		if(addr != null){
			this.freeblocks.elementAt(k).remove(addr);
			return addr;
		} else {
			throw new RuntimeException();
		}

		// TODO
		//return 0;
	}

	public int alloc(int size) {
		// TODO
		return 0;
	}

	public void free(int addr, int size) {
		// TODO
	}

	public static void main(String[] args) {
		Allocator a = new Allocator(4);
		System.out.println(a.freeblocks.get(4));
	}
}
