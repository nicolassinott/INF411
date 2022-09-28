/* TD5.  Arbres k-dimensionnels */

import java.util.LinkedList;
import java.util.Vector;

class KDTree {
	int depth;
	double[] point;
	KDTree left;
	KDTree right;

	// Constructeur pour une feuille
	KDTree(double[] point, int depth) {
		this.point = point;
		this.depth = depth;
	}

	public String pointToString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		if (this.point.length > 0)
			sb.append(this.point[0]);
		for (int i = 1; i < this.point.length; i++)
			sb.append("," + this.point[i]);
		sb.append("]");
		return sb.toString();
	}

	// Question 1

	// true si a doit etre range dans le sous-arbre droit, false sinon
	boolean compare(double[] a) {
		int r = this.depth % this.point.length;
		return a[r] >= this.point[r];
		// throw new Error("Methode boolean compare(double[] a) a completer (Question
		// 2.1)");
	}

	// Question 2

	// insere le point a dans l'arbre tree de telle sorte que la propriete
	// d'arbre kd soit preservee, renvoie la racine du nouvel arbre
	static KDTree insert(KDTree tree, double[] a) {

		KDTree treep = tree;
		// KDTree old_tree = tree;

		int a_depth = 0;

		while (treep != null) {
			if (treep.compare(a)) {
				if (treep.right == null) {
					treep.right = new KDTree(a, a_depth);
					treep.right.depth = a_depth + 1;
					return tree;
				}
				treep = treep.right;
				a_depth++;
			} else {
				if (treep.left == null) {
					treep.left = new KDTree(a, a_depth);
					treep.left.depth = a_depth + 1;
					return tree;
				}
				treep = treep.left;
				a_depth++;
			}
		}

		return new KDTree(a, 0);

		/*
		 * if (tree == null) { return new KDTree(a, 0); }
		 * 
		 * boolean c = tree.compare(a);
		 * 
		 * tree.depth += 1;
		 * 
		 * if (c) { tree.right = insert(tree.right, a); } else { tree.left =
		 * insert(tree.left, a); }
		 * 
		 * return tree; // throw new Error("Methode KDTree insert(KDTree tree, double[]
		 * a) a completer // (Question 2.2)");
		 * 
		 */
	}

	// Question 3

	// calcule le carre de la distance euclidienne entre deux points
	static double sqDist(double[] a, double[] b) {

		double res = 0;

		for (int i = 0; i < a.length; i++) {
			res = res + (a[i] - b[i]) * (a[i] - b[i]);
		}

		return res;

		// throw new Error("Methode double sqDist(double[] a, double[] b) a completer
		// (Question 3.1)");
	}

	// renvoie le point de tree le plus proche du point a

	static LinkedList<double[]> inorder(KDTree t) {
		LinkedList<double[]> c = new LinkedList<double[]>();
		if (t == null)
			return c;
		c.addAll(inorder(t.left));
		c.add(t.point);
		c.addAll(inorder(t.right));
		return c;
	}

	static double[] closestNaive(KDTree tree, double[] a) {

		LinkedList<double[]> list_of_nodes = inorder(tree);

		double[] Champion = null;
		double measure = Double.POSITIVE_INFINITY;

		for (double[] l : list_of_nodes) {
			double dist = sqDist(a, l);
			if (dist < measure) {
				Champion = l;
				measure = dist;
			}
		}

		return Champion;
		// throw new Error("Methode double[] closestNaiveKDTree tree, double[] a) a
		// completer (Question 3.2)");
	}

	// renvoie le point le plus proche du point a parmi les points de tree
	// et champion
	static double[] closest(KDTree tree, double[] a, double[] champion) {

		if (tree == null)
			return champion;

		// sert a InteractiveClosest pour afficher la progression
		InteractiveClosest.trace(tree.point, champion);
		
		
		if(sqDist(tree.left.point,a) < sqDist(champion,a)) {
			champion = closest(tree.left,a,champion);
		} else {
			if(sqDist(tree.right.point,a) < sqDist(champion,a)) {
				champion = closest(tree.right,a,champion);
			} else {
				return champion;
			}
		}
		
		return champion;

		//throw new Error(
		//		"Methode double[] closest(KDTree tree, double[] a, double[] champion) a completer (Question 3.3)");
	}

	static double[] closest(KDTree tree, double[] a) {
		return closest(tree, a, tree.point);
		
		//throw new Error("Methode double[] closest(KDTree tree, double[] a) a completer (Question 3.3)");
	}

	// Question 4

	// calcule le nombre de noeuds de l'arbre
	static int size(KDTree tree) {

		return inorder(tree).size();
		// throw new Error("Methode int size(KDTree tree) a completer (Question 4.1)");
	}

	// calcule la somme des points de l'arbre
	static void sum(KDTree tree, double[] acc) {

		//double[] toret = new double[tree.point.length];

		//for (int i = 0; i < toret.length; i++) {
			//toret[i] = 0;
		//}

		LinkedList<double[]> list = inorder(tree);

		for (double[] lis : list) {
			for (int j = 0; j < lis.length; j++) {
				acc[j] = acc[j] + lis[j];
			}
		}

		// throw new Error("Methode int sum(KDTree tree, double[] acc) a completer
		// (Question 4.1)");
	}

	// calcule l'isobarycentre des points de l'arbre
	static double[] average(KDTree tree) {
		
		if(tree == null) {
			return null;
		}
		
		double[] toret = new double[tree.point.length];

		for (int i = 0; i < toret.length; i++) {
			toret[i] = 0;
		}

		LinkedList<double[]> list = inorder(tree);

		for (double[] lis : list) {
			for (int j = 0; j < lis.length; j++) {
				toret[j] = toret[j] + lis[j];
			}
		}
				
		int n_elements = size(tree);

		for (int i = 0; i < toret.length; i++) {
			toret[i] = (toret[i])/(double) n_elements;
		}
		
		return toret;
		
		//throw new Error("Methode double[] average(KDTree tree) a completer (Question 4.1)");
	}

	// elague tree de maniere a ne conserver que
	// maxpoints, renvoie le vecteur de points qui en resulte
	static Vector<double[]> palette(KDTree tree, int maxpoints) {
		throw new Error("Methode Vector<double[]> palette(KDTree, int) a completer (Question 4.2)");
	}

}
