import java.util.*;
class sarcina1 {
	
	static List<Integer> f = new ArrayList<Integer>();
	static long iteratii=0;
	static long startTime,stopTime,duration;

	public static void main(String[] args) {
		int n,
			opt;
		char exit = 'n';
		Scanner input = new Scanner(System.in);
		while ((exit == 'n') || (exit == 'N')) {
		System.out.printf("Introduceti numarul de inceput: ");
		n = input.nextInt();
			System.out.printf("1.Algoritmul recursiva\n2.Algoritmul iterativa\n3.Algoritmul divizarii\n~:");
			opt = input.nextInt();
			switch (opt) {
				case 1: {
					startTime = System.nanoTime();
					int fibb = fib1(n);
					stopTime = System.nanoTime();
					duration = stopTime - startTime;
					System.out.print("Numarul fibonaci: "+fibb+"\tIteratii: "+iteratii+"\tTimp: "+duration+"nS");
					break;	
				}
				case 2: {
					startTime = System.nanoTime();
					int fib2 = fib2(n);
					stopTime = System.nanoTime();
					duration = stopTime - startTime;
					System.out.println("Numarul fibonaci: "+fib2+"\tIteratii: "+iteratii+"\tTimp: "+duration+"nS");
					break;
				}
				case 3: {
					startTime = System.nanoTime();
					int fib3 = fib3(n);
					stopTime = System.nanoTime();
					duration = stopTime - startTime;
					System.out.println("Numarul fibonaci: "+fib3+"\tIteratii: "+iteratii+"\tTimp: "+duration+"nS");
					break;
				}
				default: {
					System.out.print("Optiune gresita!!!");
					break;
				} 
			}
			System.out.print("\nEsiti? [y/n]: ");
			exit = input.next().charAt(0);
		}	
	}

	public static int fib1(int n) {
		if (n<2) {
			iteratii++;
			return n;
		}
		else {
			iteratii++;
			return fib1(n-1)+fib1(n-2);
		}
	}

	public static int fib2(int n) {
		int i=1,j=0,k;
		iteratii = 0;
		for (k=0;k<n;k++) {
			j=i+j;
			i=j-i;
			iteratii++;
		}
		return j;
	}

	public static int fib3(int n) {
		int i=1,j=0,k=0,h=1,t;
		iteratii = 0;
		while (n>0) {
			if (n % 2 != 0) {
				t=j*h;
				j=i*h+j*k+t;
				i=i*k+t;
			}
			t = (int)Math.pow(h,2);
			h = 2*k*h+t;
			k = (int)Math.pow(k,2)+t;
			n = n/2;
			iteratii++;
		}
		return j;
	}

}