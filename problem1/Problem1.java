package problem1;
import java.io.*;
import java.util.*;

class Pair {
	int u;
	char c;
	
	public Pair(int u, char c) {
		this.u = u;
		this.c = c;
	}
	
	public int getU() {
		return this.u;
	}
	
	public int getC() {
		return this.c;
	}
}

class DTM {
	int n;
	int[] t;
	HashMap<Pair, Integer> m;
	
	public DTM(int n, int[] t, HashMap<Pair, Integer> m) {
		this.n = n;
		this.t = t;
		this.m = m;
	}
	
	public boolean isTerminate(int u) {
		for (int i = 0; i<this.t.length; i++) {
			if (t[i] == u) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isAccepted(String word) {
		int u = 1;
		int i = 0;
		while (i != word.length()) {
			for (Pair p: this.m.keySet()) {
				if (p.getU() == u && p.getC() == word.charAt(i)) {
					u = this.m.get(p);
					break;
				}
			}
			i++;
		}
		return this.isTerminate(u);
	}
}

public class Problem1 {
	public static void main(String[] args) {
		try {
			Scanner s = new Scanner(new File("problem1.in"));
			try {
				String word = s.next();
				int n = s.nextInt();
				int m = s.nextInt();
				int k = s.nextInt();
				int[] t = new int[k];
				for (int i = 0; i<k; i++) {
					t[i] = s.nextInt();
				}
				int a;
				int b;
				char c;
				HashMap<Pair, Integer> q = new HashMap<>();
				for (int i = 0; i<m; i++) {
					a = s.nextInt();
					b = s.nextInt();
					c = s.next().charAt(0);
					q.put(new Pair(a, c), b);
				}
				DTM machine = new DTM(n, t, q);
				String result;
				if (machine.isAccepted(word)) {
					result = "Accepts";
				} else {
					result = "Rejects";
				}
				Writer w = new FileWriter("problem1.out");
				try {
					w.write(result);
				} finally {
					w.close();
				}
			} finally {
				s.close();
			}
		} catch (FileNotFoundException e) {
			System.out.println("An error occured: file not found. " + e.getMessage());
		} catch (IOException e) {
			System.out.println("An error while I/O: " + e.getMessage());
		}
	}
}