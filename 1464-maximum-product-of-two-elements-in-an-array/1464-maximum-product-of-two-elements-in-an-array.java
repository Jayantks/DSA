class Solution {
    public int maxProduct(int[] nums) {
      int a = 0 ;
      int b = 0 ;

      for ( int num : nums ){
        int oa = a ; 

        a = Math.max(a , num);
        b = Math.max(b , Math.min(oa , num));
      } 
      return (a-1) * (b-1);  
    }
}