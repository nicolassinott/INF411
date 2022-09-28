/* TD1. Bataille
 * Ce fichier contient deux classes :
 * 		- Deck représente un paquet de cartes,
 * 		- Battle représente un jeu de bataille.
 */

import java.util.LinkedList;

class Deck { // représente un paquet de cartes

	LinkedList<Integer> cards;

	// les méthodes toString, hashCode, equals, et copy sont utilisées pour
	// l'affichage et les tests, vous ne devez pas les modifier.

	@Override
	public String toString() {
		return cards.toString();
	}

	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	public boolean equals(Object o) {
		Deck d = (Deck) o;
		return cards.equals(d.cards);
	}

	Deck copy() {
		Deck d = new Deck();
		for (Integer card : this.cards)
			d.cards.addLast(card);
		return d;
	}

	// constructeur d'un paquet vide
	Deck() {
		cards = new LinkedList<Integer>();
	}

	// constructeur à partir du champ
	Deck(LinkedList<Integer> cards) {
		this.cards = cards;
	}

	// constructeur d'un paquet de cartes complet trié avec nbVals valeurs
	Deck(int nbVals) {
		cards = new LinkedList<Integer>();
		for (int j = 1; j <= nbVals; j++)
			for (int i = 0; i < 4; i++)
				cards.add(j);
	}

	// Question 1

	// prend une carte du paquet d pour la mettre à la fin du paquet courant
	int pick(Deck d) {

		if (!d.cards.isEmpty()) {
			int pick_card = d.cards.pop();

			this.cards.addLast(pick_card);

			return pick_card;

		}

		else {
			return -1;
		}

		// throw new Error("Méthode pick(Deck d) à compléter (Question 1)");
	}

	// prend toutes les cartes du paquet d pour les mettre à la fin du paquet
	// courant
	void pickAll(Deck d) {
		int size = d.cards.size();
		for (int j = 0; j < size; j++) {
			pick(d);
		}

		// throw new Error("Méthode pickAll(Deck d) à compléter (Question 1)");
	}

	// vérifie si le paquet courant est valide
	boolean isValid(int nbVals) {

		int[] tab = new int[nbVals];

		for (int i = 0; i < nbVals; i++) {
			tab[i] = 0;
		}

		for (int i : this.cards) {
			if (i > nbVals || i <= 0) {
				return false;
			} else {
				tab[i - 1]++;
				if (tab[i - 1] == 5) {
					return false;
				}
			}

		}

		return true;
		// throw new Error("Méthode isValid(int nbVals) à compléter (Question 1)");
	}

	// Question 2.1

	// choisit une position pour la coupe
	int cut() {
		// throw new Error("Méthode cut() à compléter (Question 2.1)");

		int cut_bin = 0;

		for (int i : this.cards) {
			double l = Math.random();
			if (l > 0.5) {
				cut_bin++;
			}
		}

		return cut_bin;

	}

	// coupe le paquet courant en deux au niveau de la position donnée par cut()
	Deck split() {
		// throw new Error("Méthode split() à compléter (Question 2.1)");
		int cut_number = this.cut();
		Deck split_deck = new Deck();

		for (int i = 0; i < cut_number; i++) {
			split_deck.cards.add(this.cards.pop());
		}

		return split_deck;

	}

	// Question 2.2

	// mélange le paquet courant et le paquet d
	void riffleWith(Deck d) {

		Deck deck1 = this.copy();
		this.cards = new LinkedList<Integer>();

		while ((!d.cards.isEmpty()) || (!deck1.cards.isEmpty())) {
			if (Math.random() < (float) d.cards.size() / (d.cards.size() + deck1.cards.size())) {
				pick(d);
			} else {
				pick(deck1);
			}
		}

//		if (Math.random()>0.5) {
//			
//		}

		// throw new Error("Méthode riffleWith(Deck d) à compléter (Question 2.2)");
	}

	// Question 2.3

	// mélange le paquet courant au moyen du riffle shuffle
	void riffleShuffle(int m) {

		for (int i = 0; i < m; i++) {
			Deck split_deck = this.split();
			this.riffleWith(split_deck);
		}
		// throw new Error("Méthode riffleShuffle(int m) à compléter (Question 2.3)");
	}
}

class Battle { // représente un jeu de bataille

	Deck player1;
	Deck player2;
	Deck trick;

	// constructeur d'une bataille sans cartes
	Battle() {
		player1 = new Deck();
		player2 = new Deck();
		trick = new Deck();
	}

	// constructeur à partir des champs
	Battle(Deck player1, Deck player2, Deck trick) {
		this.player1 = player1;
		this.player2 = player2;
		this.trick = trick;
	}

	// copie la bataille
	Battle copy() {
		Battle r = new Battle();
		r.player1 = this.player1.copy();
		r.player2 = this.player2.copy();
		r.trick = this.trick.copy();
		return r;
	}

	// chaîne de caractères représentant la bataille
	@Override
	public String toString() {
		return "Joueur 1 : " + player1.toString() + "\n" + "Joueur 2 : " + player2.toString() + "\nPli "
				+ trick.toString();
	}

	// Question 3.1

	// constructeur d'une bataille avec un jeu de cartes de nbVals valeurs
	Battle(int nbVals) {

		this.player1 = new Deck();
		this.player2 = new Deck();
		this.trick = new Deck();

		Deck new_deck = new Deck(nbVals);

		new_deck.riffleShuffle(7);

		int i = 0;

		while (!new_deck.cards.isEmpty()) {
			if (i % 2 == 0) {
				this.player1.pick(new_deck);
				i++;
			} else {
				this.player2.pick(new_deck);
				i++;
			}
		}

	}

	// Question 3.2

	// teste si la partie est terminée
	boolean isOver() {

		if (this.player1.cards.isEmpty() || this.player2.cards.isEmpty()) {
			return true;
		} else {
			return false;
		}

		// throw new Error("Méthode isOver() à compléter (Question 3.2)");
	}

	// effectue un tour de jeu
	boolean oneRound() {
		
		this.trick.pick(this.player1);
		this.trick.pick(this.player2);
		
		if (this.trick.cards.get(0) > this.trick.cards.get(1)) {
			this.player1.pickAll(this.trick);
		}
		
		else if (this.trick.cards.get(0) < this.trick.cards.get(1)) {
			this.player2.pickAll(this.trick);
		}
		
		else {
			this.trick.pick(this.player1);
			this.trick.pick(this.player2);
			oneRound();
		}
		
		throw new Error("Méthode oneRound() à compléter (Question 3.2)");
	}

	// Question 3.3

	// renvoie le gagnant
	int winner() {
		throw new Error("Méthode winner() à compléter (Question 3.3)");
	}

	// effectue une partie avec un nombre maximum de coups fixé
	int game(int turns) {
		throw new Error("Méthode game(int turns) à compléter (Question 3.3)");
	}

	// Question 4.1

	// effectue une partie sans limite de coups, mais avec détection des parties
	// infinies
	int game() {
		throw new Error("Méthode game() à compléter (Question 4.1)");
	}

	// Question 4.2

	// effectue des statistiques sur le nombre de parties infinies
	static void stats(int nbVals, int nbGames) {
		throw new Error("Méthode stats(int bvVals, int nb_of_games) à compléter (Question 4.2)");
	}
}
