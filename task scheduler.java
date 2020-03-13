// Time Complexity : O(n)
// Space Complexity : O(1) max is 26 alphabets
// Did this code successfully run on Leetcode :
// Any problem you faced while coding this :
// Your code here along with comments explaining your approach

class Solution {
    public int leastInterval(char[] tasks, int n) {
        //edge cases
        if(tasks.length == 0 || tasks == null) return 0;
        if(n==0) return tasks.length;
        
        // Logic
        // maxfreq is max number of times a task is repeated
        // max count is how many tasks have maxfreq eg. maxcount=2 for (AAABBB) A=3, B=3    
        int maxfreq = 0, maxcount = 0;
        
        // populate map with task and occurence
        HashMap<Character, Integer> map = new HashMap<>();
        for(char task : tasks) {
            if(!map.containsKey(task)) {
                map.put(task,1);
            }
            else {
                map.put(task,map.get(task)+1);
                maxfreq = Math.max(maxfreq, map.get(task));
            }
        }
        
        //Get the maxcount for maxfreq by iterating all freq in map
        for(int taskcount : map.values()) {
            if(taskcount == maxfreq) maxcount++ ;
        } 
        
        int partitions = maxfreq - 1; // A _ _ A _ _ A between A's (partitions = 2)
        int empty = (n-(maxcount-1)) * partitions; // A _ _ A _ _ A (empty = 4) or AB _ AB _ AB (empty = 2) if 3A3B
        int pendingTasks = tasks.length - (maxfreq * maxcount); // Tasks to be filled
        int idle = Math.max(0, empty - pendingTasks);  //idle _ which remains after every task is scheduled

        return tasks.length + idle; //Return total
    }
}