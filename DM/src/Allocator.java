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

		// TODO

		// Écrire le constructeur n'est pas une question de l'énoncé,
		// mais il faut le faire pour tester votre code
	}

	static int buddy(int addr, int k) {
		// TODO

		if((addr / (1 << k)) % 2 == 1){
			return addr - (1 << k);
		} else {
			return addr + (1 << k);
		}
		// TODO
	}

	int reserve(int k) {

		if(k > this.maxk){
			throw new RuntimeException();
		}

		int i = k;

		while(i <= this.maxk){

			if(!this.freeblocks.get(i).isEmpty()){

				//enregistre l'index du bloc maximal et l'enleve de freeblocks
				int return_index = this.freeblocks.get(i).iterator().next();
				this.freeblocks.get(i).remove(return_index);

				// ajoute les nouveaux blocs maximaux
				// pas j=i car l'autre bloc est occupe
				for (int j = k; j < i;j++){ 
					this.freeblocks.get(j).add(buddy(return_index, j));
				}

				//on remarque que prend deja en compte cas i == k avec 
				//j<i (ne pas liberer un bloc à faux)

				return return_index;
			}

			i++;
		}

		throw new RuntimeException();

		// TODO

	}

	public int alloc(int size) {
		// TODO
		int k = 0;

		while(true){
			if(size <= (1 << k)) break;
			k++;
		}

		return reserve(k);
		// TODO
	}

	public void free(int addr, int size) {
		// TODO
		for(int i = addr; i < addr + size; i++){
			this.free_up(i,0);
		}
		// TODO
	}


	// adds a 
	void free_up(int addr, int k){
		
		if(k == this.maxk){
			this.freeblocks.get(k).add(0);
			return;
		}
		
		int k0 = this.maxk;

		// verify if not contained in above already free
		while(k0>=k){
			for(int i : this.freeblocks.get(k0)){
				if((addr >= i) && (addr + (1 << k) <= i +(1 << k0))){
					return;
				}
			}
			k0--;
		}

		// check if other is max, if yes removes and starts to free up, 
		//otherwise add new max
		if(!this.freeblocks.get(k).contains(buddy(addr, k))){
			this.freeblocks.get(k).add(addr);
		} else {
			this.freeblocks.get(k).remove(buddy(addr, k));
			this.free_up(Math.min(addr,buddy(addr, k)),k+1);
		}
	}

	public static void main(String[] args) {
		//Allocator a = new Allocator(4);
		//System.out.println(buddy(1,0));
		
		//System.out.println(a.freeblocks.get(3));
		//a.reserve(2);
		//System.out.println(a.freeblocks.get(2));
		//a.free(0,4);
		//System.out.println(a.freeblocks.get(4));

		// for(int i : a.freeblocks.get(2)){
		// 	System.out.println(i);
		// }

		//System.out.println(a.freeblocks.get(3));
		//System.out.println(a.freeblocks.get(3));
		//System.out.println(a.freeblocks.get(2).isEmpty());
		//reserve et buddy seems okay

		//a.alloc(2);
		//System.out.println(a.freeblocks.get(1));
		//System.out.println(a.freeblocks.get(2));
		//System.out.println(a.freeblocks.get(3));
		//System.out.println(a.freeblocks.get(2).isEmpty());
		//alloc seems okay



		Allocator a = new Allocator(4);
		a.reserve(1);
		a.reserve(1);
		a.reserve(3);

		System.out.println(a.freeblocks.get(1));
		
		a.free(0,10);
		for(int i = 0; i <= 4; i++){
			System.out.println("For k = "+i+" :");
			System.out.println(a.freeblocks.get(i));
		}
		// free seems to work

	}
}
