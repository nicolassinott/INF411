import java.util.Scanner;

class arithmetics{

    static int gcd(int u, int v){
        S
        if(v<0) v = -v;
        if(u<0) u = -u;
                
        while(v!=0){
            int old_v = v;
            v = u % v;
            u = old_v;
        }

        return u;

    }

    static int exp(int x, int n){
        if(n == 0) return 1;
        int r = exp(x * x, n/2);
        return (n%2 == 0) ? r : x*r;
    }

    static int exp_2(int x, int n){
        if(n == 0) return 1;
        int r = exp_2(x,n/2);
        return (n%2 == 0) ? r*r : x*r*r;
    }

    static int exp_while(int x, int n){
        
        int result = 1;
        
        // Invariance of the loop: r x**(n) = x0 ** n0
        while(n != 0){
            if(n % 2 == 1) result *= x;
            x *= x;
            n /= 2;
        }

        return result;
    }



    public static void main(String args[]) {

        //System.out.println("Write the two numbers you wish to compute the gcd");

        //Scanner s = new Scanner(System.in);
        //Scanner t = new Scanner(System.in);
        //int u = s.nextInt();
        //int v = t.nextInt();
        //System.out.println("The greatest commun divisor is: " + gcd(u,v));

        System.out.println(exp_while(5,3));

    }
    
}


