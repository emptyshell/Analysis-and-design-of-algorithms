import java.util.*;
import java.lang.Math;
import java.util.Scanner;


class sarcina2 {
	public static int a[] = new int[100000];
	static long startTime,stopTime,duration;
	static long iteratii=0;

	public static void main (String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.printf("Introduceti numarul de elemente al vectorului: ");
		int n = input.nextInt();
		System.out.printf("\n1.MergeSort\n2.qSort\n3.insertSort\n$: ");
		int optSortare = input.nextInt();
		switch (optSortare) {
			case 1: {
				System.out.printf("\nMergeSort:\n1.Cazul cel mai favorabil\n2.Cazul mediu\n3.Cazul cel mai defavorabil\n$: ");
				int optCase = input.nextInt();
				switch (optCase) {
					case 1: {
						for (int i=0; i<n; i++) {
							a[i] = 10000+i;
						}
						displayVector(n);
						System.out.printf("\nVectorul sortat: \n");
						startTime = System.nanoTime();
						mergeSort(0,n-1);
						stopTime = System.nanoTime();
						duration = stopTime - startTime;
						displayVector(n);
						System.out.printf("\n");
						System.out.println("Iteratii: "+iteratii+"\tTimp: "+duration+"nS");
						break;
					}
					case 2: {
						randVector(n);
						displayVector(n);
						System.out.printf("\nVectorul sortat: \n");
						startTime = System.nanoTime();
						mergeSort(0,n-1);
						stopTime = System.nanoTime();
						duration = stopTime - startTime;
						displayVector(n);
						System.out.printf("\n");
						System.out.println("Iteratii: "+iteratii+"\tTimp: "+duration+"nS");
						break;
					}
					case 3: {
						for (int i=0; i<n; i++) {
							a[i] = 100000-i;
						}
						displayVector(n);
						System.out.printf("\nVectorul sortat: \n");
						startTime = System.nanoTime();
						mergeSort(0,n-1);
						stopTime = System.nanoTime();
						duration = stopTime - startTime;
						displayVector(n);
						System.out.printf("\n");
						System.out.println("Iteratii: "+iteratii+"\tTimp: "+duration+"nS");
						break;
					}
				}
				break;
			}
			case 2: {
				System.out.printf("\nqSort:\n1.Cazul cel mai favorabil\n2.Cazul mediu\n3.Cazul cel mai defavorabil\n$: ");
				int optCase = input.nextInt();
				switch (optCase) {
					case 1: {
						for (int i=0; i<n; i++) {
							a[i] = 10000+i;
						}
						displayVector(n);
						System.out.printf("\nVectorul sortat: \n");
						startTime = System.nanoTime();
						qSort(0,n-1);
						stopTime = System.nanoTime();
						duration = stopTime - startTime;
						displayVector(n);
						System.out.printf("\n");
						System.out.println("Iteratii: "+iteratii+"\tTimp: "+duration+"nS");
						break;
					}
					case 2: {
						randVector(n);
						displayVector(n);
						System.out.printf("\nVectorul sortat: \n");
						startTime = System.nanoTime();
						qSort(0,n-1);
						stopTime = System.nanoTime();
						duration = stopTime - startTime;
						displayVector(n);
						System.out.printf("\n");
						System.out.println("Iteratii: "+iteratii+"\tTimp: "+duration+"nS");
						break;
					}
					case 3: {
						for (int i=0; i<n; i++) {
							a[i] = 100000-i;
						}
						displayVector(n);
						System.out.printf("\nVectorul sortat: \n");
						startTime = System.nanoTime();
						qSort(0,n-1);
						stopTime = System.nanoTime();
						duration = stopTime - startTime;
						displayVector(n);
						System.out.printf("\n");
						System.out.println("Iteratii: "+iteratii+"\tTimp: "+duration+"nS");
						break;
					}
				}
				break; 
			}
			case 3: {
				System.out.printf("\ninsertSort:\n1.Cazul cel mai favorabil\n2.Cazul mediu\n3.Cazul cel mai defavorabil\n$: ");
				int optCase = input.nextInt();
				switch (optCase) {
					case 1: {
						for (int i=0; i<n; i++) {
							a[i] = 10000+i;
						}
						displayVector(n);
						System.out.printf("\nVectorul sortat: \n");
						startTime = System.nanoTime();
						insertSort(n);
						stopTime = System.nanoTime();
						duration = stopTime - startTime;
						displayVector(n);
						System.out.printf("\n");
						System.out.println("Iteratii: "+iteratii+"\tTimp: "+duration+"nS");
						break;
					}
					case 2: {
						randVector(n);
						displayVector(n);
						System.out.printf("\nVectorul sortat: \n");
						startTime = System.nanoTime();
						insertSort(n);
						stopTime = System.nanoTime();
						duration = stopTime - startTime;
						displayVector(n);
						System.out.printf("\n");
						System.out.println("Iteratii: "+iteratii+"\tTimp: "+duration+"nS");
						break;
					}
					case 3: {
						for (int i=0; i<n; i++) {
							a[i] = 100000-i;
						}
						displayVector(n);
						System.out.printf("\nVectorul sortat: \n");
						startTime = System.nanoTime();
						insertSort(n);
						stopTime = System.nanoTime();
						duration = stopTime - startTime;
						displayVector(n);
						System.out.printf("\n");
						System.out.println("Iteratii: "+iteratii+"\tTimp: "+duration+"nS");
						break;
					}
				}
				break; 
			}
		}
	
	}

	// algoritmul merge sort ---------------------------------------->
	public static void interclasare ( int p, int m, int q) {
		int i = p, j=m+1, k=0;
		int b[] = new int[100000];
		while (i<=m && j<=q) {
			if (a[i]<a[j]) 
				b[k++] = a[i++];
			else
				b[k++] = a[j++];
			iteratii++;
		}
		while (i<= m) {
			b[k++] = a[i++];
			iteratii++;
		}
		while (j<=q) {
			b[k++] = a[j++];
			iteratii++; 
		}
		for (i=p; i<=q; i++) {
			a[i] = b[i-p];
			iteratii++;
		}
	}
	
	public static void mergeSort (int p, int q) {
		if (q>p) {
			int m= (p+q)/2;
			mergeSort(p,m);
			mergeSort(m+1,q);
			interclasare(p,m,q);
		}
	}
	//----------------------------------------------------------------<
	public static void randVector (int n) {
		for (int i=0; i<n; i++) {
			a[i] = (int)(Math.random()*10000+1);
		}
	}

	public static void displayVector (int n) {
		for (int i = 0; i<n; i++) {
			System.out.printf("%d\t",a[i]);
		}
	}
	//Algoritmul sortarea rapida -------------------------------------->
	public static int divide (int p, int q) {
		int st=p, dr = q, x=a[p];
		while (st<dr) {
			iteratii++;
			while (st<dr && a[dr]>=x) {
				dr--;
				iteratii++;
			}
			a[st] = a[dr];
			while (st<dr && a[st]<=x) {
				st++;
				iteratii++;
			}
			a[dr] = a[st];
		}
		a[st] = x;
		return st;
	}

	public static void qSort (int p,int q) {
		int m = divide(p,q);
		if (m-1 > p) qSort(p,m-1);
		if (m+1 < q) qSort(m+1,q);
	}
	//------------------------------------------------------------------<
	//algoritmul bubble sort-------------------------------------------->
	public static void insertSort(int n) {
		for (int i=0; i<n; i++) {
			int v=a[i];
			int poz;
			for (poz = i; poz-1>=0 && a[poz-1]>v; poz--) {
				a[poz] = a[poz-1];
				iteratii++;
			}
			iteratii++;
			a[poz]=v;
		}
	}
	//------------------------------------------------------------------<
}