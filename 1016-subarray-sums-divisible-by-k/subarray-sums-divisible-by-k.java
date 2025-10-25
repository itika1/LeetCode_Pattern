class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap();
        int prefixSum=0, count=0;
        map.put(0,1);
        for(int num: nums){
            prefixSum+=num;
            int remainder = prefixSum%k;
            if(remainder<0) remainder+=k;
            if(map.containsKey(remainder)){
                count+=map.get(remainder);
            }
            map.put(remainder, map.getOrDefault(remainder,0)+1);
        }
        return count;
    }
}