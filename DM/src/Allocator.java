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
		if(addr % (1 << k) == 1){
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


	//	Integer addr = this.freeblocks.elementAt(k).iterator().next();

	//	this.freeblocks.get(k);

	//	if(addr != null){
	//		this.freeblocks.elementAt(k).remove(addr);
	//		return addr;
	//	} else {
	//		throw new RuntimeException();
	//	}

		// TODO
		//return 0;
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
		//return 0;
	}

	public void free(int addr, int size) {
		// TODO



		// TODO
	}

	public static void main(String[] args) {
		Allocator a = new Allocator(4);
		
		//a.reserve(2);
		//System.out.println(a.freeblocks.get(2));
		//System.out.println(a.freeblocks.get(3));
		//System.out.println(a.freeblocks.get(2).isEmpty());
		//reserve et buddy seems okay

		//a.alloc(2);
		//System.out.println(a.freeblocks.get(1));
		//System.out.println(a.freeblocks.get(2));
		//System.out.println(a.freeblocks.get(3));
		//System.out.println(a.freeblocks.get(2).isEmpty());
		//alloc seems okay


	}
}
