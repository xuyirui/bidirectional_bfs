package bidirectional_bfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    /**
     * @param graph a list of Undirected graph node
     * @param s, t two Undirected graph nodes
     * @return an integer
     * find shortest path length between s and t, return -1 if not connected
     */
	public static void main(String[] args) {
		UndirectedGraphNode s = new UndirectedGraphNode(1);
		UndirectedGraphNode s2 = new UndirectedGraphNode(2);
		s.neighbors.add(s2);
		s2.neighbors.add(s);
		UndirectedGraphNode t = new UndirectedGraphNode(3);
		UndirectedGraphNode t2 = new UndirectedGraphNode(4);
		t.neighbors.add(t2);
		t2.neighbors.add(t);
		
		System.out.println(new Solution().bfsShortestPath(null, s, t));
	}
    public int bfsShortestPath(List<UndirectedGraphNode> graph,
                          UndirectedGraphNode s,
                          UndirectedGraphNode t) {
        
        if (s == t) {
            return 0;
        }
        Set<UndirectedGraphNode> set1 = new HashSet<>();
        Set<UndirectedGraphNode> set2 = new HashSet<>();
        s.visited = true;
        t.visited = true;
        set1.add(s);
        set2.add(t);
        return bfsShortestPathHelper(set1, set2, 0);
    }
    
    
    private int bfsShortestPathHelper(Set<UndirectedGraphNode> set1, Set<UndirectedGraphNode> set2, int distance) {
        if (set1.isEmpty()) {
            return -1;
        }
        if (set1.size() > set2.size()) {
            return bfsShortestPathHelper(set2, set1, distance);
        }
        
        Set<UndirectedGraphNode> set = new HashSet<>();
        for (UndirectedGraphNode u : set1) {
            for (UndirectedGraphNode v : u.neighbors) {
                if (set2.contains(v)) {
                    return distance + 1;
                }
                if (!v.visited) {
                	v.visited = true;
                    set.add(v);
                }
            }
        }
        return bfsShortestPathHelper(set2, set, distance + 1);
    }
}



//  Definition for Undirected graph.
class UndirectedGraphNode {
	int label;
	boolean visited;
	List<UndirectedGraphNode> neighbors;

	UndirectedGraphNode(int x) {
		label = x;
		visited = false;
		neighbors = new ArrayList<UndirectedGraphNode>();
	}
}
 