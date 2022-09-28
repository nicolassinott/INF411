
class PRNG {

	int mod;
	int[] table = new int[55];
	int state;

	public PRNG(int m) {

		this.mod = m;

		// this.state = (int) (55 * Math.random());

		this.state = 0;

		for (int i = 0; i < 55; i++) {
			this.table[i] = (((((((((300007 * i) % m) + 900021) * i) % m) + 700018) * i) % m) + 200007) % m;
		}

	}

	public int getNext() {

		int ret_index = this.state % 55;

		if (this.state < 55) {

			this.state++;

			return this.table[ret_index];

		} else {

			int id1 = ((this.state % 55) + 55 - 24) % 55;

			int id2 = (this.state % 55);

			this.state++;

			this.table[ret_index] = (this.table[id1] + this.table[id2]) % this.mod;

			return this.table[ret_index];

		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}

class Network {

	private int[] link;
	private int[] rank;
	PRNG random_gen;
	int calls;
	int last_caller;
	int last_callee;

	public Network(int m) {

		this.link = new int[m];
		this.rank = new int[m];

		for (int i = 0; i < m; i++) {
			this.link[i] = i;
			this.rank[i] = 1;
		}

		this.random_gen = new PRNG(m);

		this.calls = 0;
	}

	public int getCallCount() {
		return calls;
	}

	public int getLastCaller() {
		return last_caller;
	}

	public int getLastCallee() {
		return last_callee;
	}

	public void runUntilCall(int u) {
		while (true) {
			int caller = random_gen.getNext();
			int callee = random_gen.getNext();

			if (caller != callee) {
				this.calls++;
				this.last_caller = caller;
				this.last_callee = callee;

				if (caller == u | callee == u) {
					return;
				}
			}
		}
	}

	public int find(int number) {
		int p = this.link[number];
		if (p == number)
			return number;
		int r = this.find(p);
		this.link[number] = r;
		return r;
	}

	public void union(int i, int j) {
		int ri = this.find(i);
		int rj = this.find(j);
		if (ri == rj)
			return;
		if (this.rank[ri] < this.rank[rj]) {
			this.link[ri] = rj;
			this.rank[rj] = this.rank[ri] + this.rank[rj];
		} else {
			this.link[rj] = ri;
			this.rank[ri] = this.rank[ri] + this.rank[rj];
		}
	}

	public void runUntilConnected(int u, int v) {
		while (true) {

			if (this.find(u) == this.find(v)) {
				return;
			}

			int caller = random_gen.getNext();
			int callee = random_gen.getNext();

			if (caller != callee) {
				this.calls++;
				this.last_caller = caller;
				this.last_callee = callee;
				union(caller, callee);
			}
		}
	}

	public int getContactCount(int u) {
		return this.rank[this.find(u)];
	}

}
