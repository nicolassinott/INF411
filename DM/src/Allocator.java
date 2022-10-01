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

		// if(k == 0){
		// 	if(addr % 2 == 1){
		// 		return addr - 1;
		// 	} else {
		// 		return addr + 1;
		// 	}
		// }

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

		//partir du addr et apres monter prenant le plus petit element
		//du paire compagnon

		//attention que addr + taille peut etre n'importe quel format

		// for(int i = addr; i < addr + size; i++){

		// 	if(this.freeblocks.get(this.maxk).contains(0)){
		// 		return;
		// 	}

		// 	int k0 = 0;
			
		// 	if(this.freeblocks.get(k0).contains(buddy(i, k0))){
		// 		this.freeblocks.get(k0).remove(buddy(i, k0));
		// 		int k2 = k0;
		// 		int bloc_index = Math.min(i, buddy(i, k0));
			
		// 		while(true){
		// 			if(k0 == this.maxk){
		// 				this.freeblocks.get(k2).add(0);
		// 				break;
		// 			}

		// 			if(this.freeblocks.get(k2+1).contains(buddy(bloc_index, k2+1))){
		// 				this.freeblocks.get(k2+1).remove(buddy(bloc_index, k2+1));
		// 				bloc_index = Math.min(bloc_index, buddy(bloc_index, k2+1));
		// 				k2++;
		// 			} else {
		// 				if(k2 + 1 == this.maxk){
		// 					this.freeblocks.get(k2+1).add(0);
		// 					break;
		// 				}
		// 				this.freeblocks.get(k2).add(bloc_index);
		// 				break;
		// 			}
		// 		}
		// 		i = Math.max(i, buddy(i, k0));

		// 	} else {
		// 		int k2 = this.maxk - 1;
		// 		boolean b = true;

		// 		while(k2 != 0){
		// 			System.out.println("!!!!!");
		// 			for(int j : this.freeblocks.get(k2)){
						
		// 				if(i >= j && i + 1 <= j + (1 << k2)){
		// 					System.out.println("?");
		// 					b = false;
		// 					break;
		// 				}
		// 			}
		// 			k2--;
		// 		}

		// 		if(b && k2 == 0){
		// 			System.out.println("!!!!!!!!!!!!!!!!!!!!");
		// 			this.freeblocks.get(k0).add(i);
		// 		}

		// 		i = Math.max(i, buddy(i, k0));
		// 	}
			
			

			

		// for(int k =0; k < this.maxk; k++){
		// 	for(int i : this.freeblocks.get(k)){
		// 		if((buddy(i,k) >= addr) || (buddy(i,k) + (1 << k) <= addr + size)){
		// 			int bloc_right = Math.max(buddy(i,k), addr);
		// 			int bloc_left = Math.min(buddy(i,k) + (1 << k), addr + size);

		// 		}
		// 	}
		// }


		// update the freeblocks

		// if(this.freeblocks.get(this.maxk).contains(0)){
		// 	return;
		// }

		// Vector<HashSet<Integer>> blockedblocks = new Vector<HashSet<Integer>>();

		// int k = this.maxk;

		// while(k !=0 ){
		// 	HashSet<Integer> set_of_addr = new HashSet<Integer>();

		// for(int i = 0; i<size; i++){
		// 	HashSet<Integer> set_of_addr = new HashSet<Integer>();
		// 	blockedblocks.add(set_of_addr);
		// }

		//HashSet<Integer> set_of_addr = new HashSet<Integer>();
		//set_of_addr.add(0);

		// this.freeblocks.add(set_of_addr);
		// int k = this.maxk;

		// while(size != 1){
		// 	HashSet<Integer> set_of_addr = new HashSet<Integer>();
		// 	int number_of_addr = this.size / size;
			
		// 	for(int i = 0; i < number_of_addr ; i++){
		// 		set_of_addr.add((Integer)(i*size));
		// 	}

		// 	this.freeblocks.add(set_of_addr);

		// 	size-- ;
		// }

		// this only look at max
		// for(int k = 0; k < this.maxk; k++){
		// 	for(int i : this.freeblocks.get(k)){
		// 		if((buddy(i,k) >= addr) && (buddy(i,k) + (1 << k) <= addr + size)){
		// 			this.freeblocks.get(k).remove(i);

		// 			// okay the neigh work, but now must update the ones above
		// 			int k0 = k;
		// 			int bloc_index = Math.min(i, buddy(i, k0));
		// 			while(true){
		// 				//int bloc_index = Math.min(i, buddy(i, k0));
		// 				if(k0 == this.maxk){
		// 					//System.out.println("??");
		// 					this.freeblocks.get(k0).add(0);
		// 					break;
		// 				}

		// 				if(this.freeblocks.get(k0+1).contains(buddy(bloc_index, k0+1))){
		// 					this.freeblocks.get(k0+1).remove(buddy(bloc_index, k0+1));
							
		// 					bloc_index = Math.min(bloc_index, buddy(bloc_index, k0+1));
		// 					k0++;
		// 				} else {
		// 					if(k0 + 1 == this.maxk){
		// 						this.freeblocks.get(k0+1).add(0);
		// 						break;
		// 					}
		// 					this.freeblocks.get(k0).add(bloc_index);
		// 					break;
		// 				}

		// 			}
		// 		}
		// 	}
		// }


		// if(this.freeblocks.get(this.maxk).contains(0)){
		// 	return;
		// }

		// int k = this.maxk - 1;

		// while(k >= 0){
		// 	for(int i : this.freeblocks.get(k)){
		// 		if(addr >= i && addr + size <= i + (1 << k)){
		// 			return;
		// 		} else {

		// 		}
		// 	}

		// 	k++;
		// }

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
