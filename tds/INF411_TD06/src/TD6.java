import java.util.Arrays;

class Fenwick {
	Fenwick left;
	Fenwick right;
	final int lo;
	final int hi;
	int acc;

	// Question 2.1

	// Constructeur
	Fenwick(int lo, int hi) {
		// assert hi > lo;
		this.lo = lo;
		this.hi = hi;
		this.acc = 0;
		if ((hi - lo) == 1) {
			this.left = null;
			this.right = null;
		} else {
			this.left = new Fenwick(lo, (lo + hi) / 2);
			this.right = new Fenwick((lo + hi) / 2, hi);
		}

		// throw new Error("Méthode Fenwick(int lo, int hi) à completer (Question
		// 2.1)");
	}

	// Question 2.2

	// renvoie la feuille associée à l'intervalle [i,i+1[ si elle existe et null
	// sinon.
	Fenwick get(int i) {
		if (this.lo == i & this.hi == i + 1) {
			return this;
		} else {
			if (i < (this.lo + this.hi) / 2) {
				if (this.left == null) {
					return null;
				} else {
					return this.left.get(i);
				}
			} else {
				if (this.right == null) {
					return null;
				} else {
					return this.right.get(i);
				}
			}
		}

		// throw new Error("Méthode Fenwick get(int i) à compléter (Question 2.2)");
	}

	// Question 2.3

	// augmente la valeur stockée dans la case d'indice i du tableau
	// et actualise l'arbre pour maintenir les propriétés d'un arbre de Fenwick.
	void inc(int i) {
		if (i < this.lo || i >= this.hi)
			return;

		this.acc++;

		if (this.left == null && this.right == null) {
			return;
		}

		if (i < (this.lo + this.hi) / 2) {
			this.left.inc(i);
		} else {
			this.right.inc(i);
		}

		// throw new Error("Méthode inc(int i) à compléter (Question 2.3)");
	}

	// Question 2.4

	// renvoie la somme des valeurs des cases d'indice < i
	int cumulative(int i) {
		if (i < this.lo)
			return 0;

		if (i >= this.hi)
			return this.acc;

		Fenwick noeud = this;
		int soma = 0;
		boolean b = true;

		while (b) {
			if (noeud.left == null && noeud.right == null) {
				b = false;
			} else {
				if (i >= (noeud.lo + noeud.hi) / 2) {
					if (noeud.left != null)
						soma += noeud.left.acc;
					noeud = noeud.right;
				} else {
					noeud = noeud.left;
				}
			}

		}

		return soma;

		// throw new Error("Méthode cumulative(int i) à compléter (Question 2.4)");
	}

}

class CountInversions {

	// Question 3.1 :

	// implémentation naive, quadratique pour calculer le nombre d'inversions
	static int countInversionsNaive(int[] a) {
		int inversions = 0;
		int La = a.length;

		for (int i = 0; i < La; i++) {
			for (int j = 0; j < La; j++) {
				if (j > i && a[j] < a[i]) {
					inversions++;
				}
			}
		}

		return inversions;
		// throw new Error("Méthode countInversionsNaive(int[] a) à compléter (Question
		// 3.1)");
	}

	// Question 3.2 :

	// une implémentation moins naive, mais supposant une plage d'éléments pas trop
	// grande espace O(max-min)

	static int countInversionsFen(int[] a) {

		if (a.length < 1)
			return 0;

		int inversions = 0;

		int min = a[0];
		int max = a[0];

		for (int i : a) {
			if (i > max)
				max = i;
			if (i < min)
				min = i;
		}

		Fenwick lista = new Fenwick(min, max + 1);

		for (int i = a.length-1; i >= 0; i--) {
			lista.inc(a[i]);
			inversions += lista.cumulative(a[i]);
		}

		return inversions;
		// throw new Error("Méthode countInversions(int[] a) à completer (Question
		// 3.2)");
	}

	// Question 3.3

	// une implémentation encore meilleure, sans rien supposer sur les éléments
	// cette fois
	static int countInversionsBest(int[] a) {
		int [] a_sorted = Arrays.copyOf(a, a.length);
		Arrays.sort(a_sorted);
			
		for(int i = 0; i< a.length; i++) {
			int index = Arrays.binarySearch(a_sorted, a[i]);
			a[i] = index;
		}
			
		return countInversionsFen(a);	
		
		//throw new Error("Méthode countInversionsBest(int[] a) à completer (Question 3.3)");
	}

}
