
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class Lilman {
        public static int countSubarrays(int[] nums) {
        Set<Integer[]> mySet = new HashSet<>();
        int count = 0;
            for(int i = 0; i < nums.length-1;i++){
                for(int j = i+1; j <nums.length; j++){
                    for(int k = 0; k < nums.length; k++){
                        if((nums[k]/2) == nums[i]+nums[j]){
                            Integer[] test = {nums[i], nums[j]};
                            Integer[] test2 = {nums[j], nums[i]};
                            boolean dop = true;
                            for(Integer[] elem : mySet){
                                if ((elem[0]==test[0] && elem[1]==test[1] )  || (elem[0]==test2[0] && elem[1]==test2[1] )){
                                    dop = false;
                                    break;
                                }
                            }
                            
                        if(dop){
                        mySet.add(test);
                        count++;
                        }
                        }
                    }
                }
            }
    
            return count;
        }
        

    public static void main(String[] args){
        int[] nums = {1,2,1,4,1};
        System.out.println(countSubarrays(nums));
    }
}