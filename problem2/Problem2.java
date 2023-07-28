package problem2;
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

class NTM {
	int n;
	int[] t;
	HashMap<Pair, List<Integer>> m;
	
	public NTM(int n, int[] t, HashMap<Pair, List<Integer>> m) {
		this.n = n;
		this.t = t;
		this.m = m;
	}
	
	public boolean isTerminate(Set<Integer> u) {
		for (int i = 0; i<this.t.length; i++) {
			for (Integer q: u) {
			    if (t[i] == q) {
				    return true;
			    }
			}
		}
		return false;
	}
	
	public boolean isAccepted(String word) {
		Set<Integer> u = new HashSet<>();
		u.add(1);
		int i = 0;
		while (i != word.length()) {
			Set<Integer> temp = new HashSet<>();
			for (Integer current: u) {
			    for (Pair p: this.m.keySet()) {
				    if (p.getU() == current && p.getC() == word.charAt(i)) {
				    	temp.addAll(this.m.get(p));
					    break;
				    }
			    }
			}
			u = temp;
			i++;
		}
		return this.isTerminate(u);
	}
}

public class Problem2 {
	public static void main(String[] args) {
		try {
			Scanner s = new Scanner(new File("problem2.in"));
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
				HashMap<Pair, List<Integer>> q = new HashMap<>();
				boolean isInMap;
				for (int i = 0; i<m; i++) {
					a = s.nextInt();
					b = s.nextInt();
					c = s.next().charAt(0);
					isInMap = false;
					for (Pair p: q.keySet()) {
						if (p.getU() == a && p.getC() == c) {
							isInMap = true;
							q.get(p).add(b);
							break;
						}
					}
					if (!isInMap) {
						List<Integer> newList = new ArrayList<>();
						newList.add(b);
						q.put(new Pair(a, c), newList);
					}
				}
				NTM machine = new NTM(n, t, q);
				String result;
				if (machine.isAccepted(word)) {
					result = "Accepts";
				} else {
					result = "Rejects";
				}
				Writer w = new FileWriter("problem2.out");
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
