
/* TD2. Fruits et tables de hachage
 * Ce fichier contient 7 classes:
 * 		- Row représente une ligne de fruits,
 * 		- CountConfigurationsNaive compte naïvement les configurations stables,
 * 		- Quadruple manipule des quadruplets,
 * 		- HashTable construit une table de hachage,
 * 		- CountConfigurationsHashTable compte les configurations stables en utilisant notre table de hachage,
 * 		- Triple manipule des triplets,
 * 		- CountConfigurationsHashMap compte les configurations stables en utilisant la HashMap de java.
 */

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;

class Row { // représente une ligne de fruits
	private final int[] fruits;

	// constructeur d'une ligne vide
	Row() {
		this.fruits = new int[0];
	}

	// constructeur à partir du champ fruits
	Row(int[] fruits) {
		this.fruits = fruits;
	}

	// méthode equals pour comparer la ligne à un objet o
	@Override
	public boolean equals(Object o) {
		// on commence par transformer l'objet o en un objet de la classe Row
		// ici on suppose que o sera toujours de la classe Row
		Row that = (Row) o;
		// on vérifie que les deux lignes ont la meme longueur
		if (this.fruits.length != that.fruits.length)
			return false;
		// on vérifie que les ièmes fruits des deux lignes coincident
		for (int i = 0; i < this.fruits.length; ++i) {
			if (this.fruits[i] != that.fruits[i])
				return false;
		}
		// on a alors bien l'égalité des lignes
		return true;
	}

	// code de hachage de la ligne
	@Override
	public int hashCode() {
		int hash = 0;
		for (int i = 0; i < fruits.length; ++i) {
			hash = 2 * hash + fruits[i];
		}
		return hash;
	}

	// chaîne de caracteres représentant la ligne
	@Override
	public String toString() {
		StringBuffer s = new StringBuffer();
		for (int i = 0; i < fruits.length; ++i)
			s.append(fruits[i]);
		return s.toString();
	}

	// Question 1

	// renvoie une nouvelle ligne en ajoutant fruit à la fin de la ligne
	Row addFruit(int fruit) {
		int size = this.fruits.length;
		
		int[] new_fruits = new int[size+1] ;

		if(size == 0) {
			new_fruits[0] = fruit;
			return new Row(new_fruits);
		} else {
		
			int i = 0;
		
			for(int f : this.fruits) {
				new_fruits[i] = f;
				i++;
			}
		
			new_fruits[size] = fruit;
		
			return new Row(new_fruits);
		}	
		
		//throw new Error("Méthode addFruit(int fruit) à compléter (Question 1)");
	}

	// renvoie la liste de toutes les lignes stables de largeur width
	static LinkedList<Row> allStableRows(int width) {
		
		LinkedList<Row> stable_rows = new LinkedList<Row>();
		LinkedList<Row> final_stable_rows = new LinkedList<Row>();
		
		if(width == 0) {
			Row a = new Row();
			final_stable_rows.add(a);
			return final_stable_rows;
		}
		
		else {
			for (int i = 1 ; i < width+1 ; i++) {
				
				if (i == 1) {
					int[] a = new int[1];
					a[0] = 1;
					stable_rows.add(new Row(a));
					int[] b = new int[1];
					b[0] = 0;
					stable_rows.add(new Row(b));
					
					for(Row row : stable_rows) {
						final_stable_rows.add(row);
					}
					
				}
				
				else {
					
					stable_rows = new LinkedList<Row>();
					
					//LinkedList<Row> new_stable_rows = new LinkedList<Row>();
					
					for (Row row : final_stable_rows) {
						
						Row new_1 = row.addFruit(0);
						Row new_2 = row.addFruit(1);
						
						boolean b0 = true;
						boolean b1 = true;
						
						if (
							(i>2)
							&&
							(new_1.fruits[i-1] == new_1.fruits[i-2]) 
							&& 
							(new_1.fruits[i-2] == new_1.fruits[i-3])
							) {
								b0 = false;	
						} 
						
						if (
							(i>2)
							&&
							(new_2.fruits[i-1] == new_2.fruits[i-2]) 
							&& 
							(new_2.fruits[i-2] == new_2.fruits[i-3])
							) {
								b1 = false;	
							}
						
						if(b0) {
							stable_rows.add(new_1);
						}
						
						if(b1) {
							stable_rows.add(new_2);
						}
					}
					
					final_stable_rows = new LinkedList<Row>();
					
					for(Row row : stable_rows) {
						final_stable_rows.add(row);
					}
				}
				
			}
			
			
		}
		return final_stable_rows;
		
		//throw new Error("Méthode allStableRows(int width) à compléter (Question 1)");
	}

	// vérifie si la ligne peut s'empiler avec les lignes r1 et r2
	// sans avoir trois fruits du même type adjacents
	boolean areStackable(Row r1, Row r2) {
		
		if((r1.fruits.length != this.fruits.length) || (r2.fruits.length != this.fruits.length)) {
			return false;
		} else {
			int size = this.fruits.length;
			
			for(int i = 0; i < size ; i++) {
				if((this.fruits[i] == r1.fruits[i]) && (this.fruits[i] == r2.fruits[i])) {
					return false;
				}
			}
			
			return true;
		} 
		
		//throw new Error("Méthode areStackable(Row r1, Row r2) à compléter (Question 1)");
	}
}

// COMPTAGE NAIF
class CountConfigurationsNaive { // comptage des configurations stables

	// Question 2

	// renvoie le nombre de grilles dont les premières lignes sont r1 et r2,
	// dont les lignes sont des lignes de rows et dont la hauteur est height
	static long count(Row r1, Row r2, LinkedList<Row> rows, int height) {
		
		long counter = 0;
		
		if (height <= 1) {
			return 0;
		}
		
		else if(height == 2) {
			return 1;
		}
		
		else {
			
			for (Row r3 : rows) {
				
				if (r3.areStackable(r1,r2)) {
					counter += count(r2, r3, rows, height-1);;
				}
			}
			
			return counter;
		}
		
		//throw new Error(
		//		"Méthode count(Row r1, Row r2, LinkedList<Row> rows, int height) de la classe CountConfigurationsHashNaive à compléter (Question 2)");

	}

	// renvoie le nombre de grilles à n lignes et n colonnes
	static long count(int n) {
		
		if (n==0) {
			return 1;
		} else {
			
		
		
		LinkedList<Row> rows = Row.allStableRows(n);
		
		long counter = 0;
		
		for(Row r1 : rows) {
			for (Row r2 : rows) {
				counter+=count(r1, r2, rows, n);
			}
		}
		
		return counter;
		
		}
		//throw new Error("Méthode count(int n) de la classe CountConfigurationsHashNaive à compléter (Question 2)");
	}
}

// CONSTRUCTION ET UTILISATION D'UNE TABLE DE HACHAGE

class Quadruple { // quadruplet (r1, r2, height, result)
	Row r1;
	Row r2;
	int height;
	long result;

	Quadruple(Row r1, Row r2, int height, long result) {
		this.r1 = r1;
		this.r2 = r2;
		this.height = height;
		this.result = result;
	}
}

class HashTable { // table de hachage
	final static int M = 50000;
	Vector<LinkedList<Quadruple>> buckets;

	// Question 3.1

	// constructeur
	HashTable() {
		throw new Error("Constructeur HashTable() à compléter (Question 3.1)");
	}

	// Question 3.2

	// renvoie le code de hachage du triplet (r1, r2, height)
	static int hashCode(Row r1, Row r2, int height) {
		throw new Error("Méthode hashCode(Row r1, Row r2, int height) à compléter (Question 3.2)");
	}

	// renvoie le seau du triplet (r1, r2, height)
	int bucket(Row r1, Row r2, int height) {
		throw new Error("Méthode bucket(Row r1, Row r2, int height) à compléter (Question 3.2)");
	}

	// Question 3.3

	// ajoute le quadruplet (r1, r2, height, result) dans le seau indiqué par la
	// methode bucket
	void add(Row r1, Row r2, int height, long result) {
		throw new Error("Méthode add(Row r1, Row r2, int height, long result) à compléter (Question 3.3)");
	}

	// Question 3.4

	// recherche dans la table une entrée pour le triplet (r1, r2, height)
	Long find(Row r1, Row r2, int height) {
		throw new Error("Méthode Quadruple find(Row r1, Row r2, int height) à compléter (Question 3.4)");
	}

}

class CountConfigurationsHashTable { // comptage des configurations stables en utilisant notre table de hachage
	static HashTable memo = new HashTable();

	// Question 4

	// renvoie le nombre de grilles dont les premières lignes sont r1 et r2,
	// dont les lignes sont des lignes de rows et dont la hauteur est height
	// en utilisant notre table de hachage
	static long count(Row r1, Row r2, LinkedList<Row> rows, int height) {
		throw new Error(
				"Méthode count(Row r1, Row r2, LinkedList<Row> rows, int height) de la classe CountConfigurationsHashTable à compléter (Question 4)");
	}

	// renvoie le nombre de grilles a n lignes et n colonnes
	static long count(int n) {
		throw new Error("Méthode count(int n) de la classe CountConfigurationsHashTable à compléter (Question 4)");
	}
}

//UTILISATION DE HASHMAP

class Triple { // triplet (r1, r2, height)
	// à compléter
}

class CountConfigurationsHashMap { // comptage des configurations stables en utilisant la HashMap de java
	static HashMap<Triple, Long> memo = new HashMap<Triple, Long>();

	// Question 5

	// renvoie le nombre de grilles dont les premières lignes sont r1 et r2,
	// dont les lignes sont des lignes de rows et dont la hauteur est height
	// en utilisant la HashMap de java
	static long count(Row r1, Row r2, LinkedList<Row> rows, int height) {
		throw new Error(
				"Méthode count(Row r1, Row r2, LinkedList<Row> rows, int height) de la classe CountConfigurationsHashMap à compléter (Question 5)");
	}

	// renvoie le nombre de grilles à n lignes et n colonnes
	static long count(int n) {
		throw new Error("Méthode count(int n) de la classe CountConfigurationsHashMap à compléter (Question 5)");
	}
}
