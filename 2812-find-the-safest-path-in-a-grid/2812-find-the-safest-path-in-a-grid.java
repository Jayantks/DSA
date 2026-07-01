class Solution {
    int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
    int n;

    public int maximumSafenessFactor(List<List<Integer>> grid) {
        n = grid.size();
        int[][] dist = new int[n][n];
        for (int[] row : dist) Arrays.fill(row, -1);

        // Step 1: Multi-source BFS
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (grid.get(i).get(j) == 1) {
                    dist[i][j] = 0;
                    q.offer(new int[]{i, j});
                }

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            for (int[] d : dirs) {
                int nr = curr[0]+d[0], nc = curr[1]+d[1];
                if (nr>=0 && nr<n && nc>=0 && nc<n && dist[nr][nc]==-1) {
                    dist[nr][nc] = dist[curr[0]][curr[1]] + 1;
                    q.offer(new int[]{nr, nc});
                }
            }
        }

        // Step 2: Binary Search on answer
        int lo = 0, hi = 2 * n;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (canReach(mid, dist)) lo = mid + 1;
            else hi = mid - 1;
        }
        return hi;
    }

    // Step 3: BFS check
    boolean canReach(int minSafe, int[][] dist) {
        if (dist[0][0] < minSafe || dist[n-1][n-1] < minSafe) return false;
        boolean[][] vis = new boolean[n][n];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0,0});
        vis[0][0] = true;
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            if (curr[0]==n-1 && curr[1]==n-1) return true;
            for (int[] d : dirs) {
                int nr = curr[0]+d[0], nc = curr[1]+d[1];
                if (nr>=0 && nr<n && nc>=0 && nc<n 
                    && !vis[nr][nc] && dist[nr][nc]>=minSafe) {
                    vis[nr][nc] = true;
                    q.offer(new int[]{nr, nc});
                }
            }
        }
        return false;
    }
}