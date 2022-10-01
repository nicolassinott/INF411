// Exercise 26: change add of HashTable so that it does not do anything when the element is already at the table
// Exercise 27: add a field size
// Exercise 28: create the method remove 

class Node{
    String element;
    Node next;
    Node(String element, Node next){
        this.element = element;
        this.next = next;
    }
    static boolean contains(Node b, String s){
        for(;b != null; b = b.next){
            if (b.element.equals(s)) return true;
        }
        return false;
    } 

    // Optimal method for ex28 assuming that 26 was done. This method only excludes first element
    static Node remove(Node no, String s){
        if(no == null) return null;
        if(no.element.equals(s)) return no.next;
        no.next = remove(no.next, s);
        return no;
    }
}

class HashTable{
    private Node[] nodes;
    private int size;

    final private static int M = 17;

    HashTable(){
        this.nodes = new Bucket[M];
        this.size = 0;
    }

    private int hash(String s) {
        int h = 0;
        for(int i = 0; i< s.length(); i++){
            h = s.charAt(i) + 19*h;
        }
        return (h & 0x7fffffff) % M;
    }

    int size(){
        return this.size;
    }

    void add(String s) {
        int i = hash(s);
        
        //Start ex 26
        if(!Node.contains(this.nodes[i], s)){
            this.nodes[i] = new Node(s,this.nodes[i]);
            this.size++;
        }
        //End ex 26

        // Original code: this.nodes[i] = new Node(s,this.nodes[i]);

    }

    void remove(String s){
        int i = hash(s);
        if(Node.contains(this.nodes[i], s)){
            this.size--;
            this.nodes[i] = Node.remove(this.nodes[i], s);
        }
        
        // Node no = this.nodes[i];
        // Node previous_no = this.nodes[i];
        // while(no != null){
        //     if(no.element.equals(s)){
        //         this.size--;
        //         if(previous_no.next != null){
        //             previous_no.next = no.next;
        //         }

        //     }
        // }
    }

    boolean contains(){
        return Node.contains(this.nodes[hash(s)],s);
    }
}
