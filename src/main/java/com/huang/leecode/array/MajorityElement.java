package main.java.com.huang.leecode.array;

/**
 *
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/majority-element
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class MajorityElement {

    public static void main(String[] args) {

        int[] nums = {3,2,3,123,123,123,123,12,3,5,43,34,5,23,41,1,1,1,1,1,1,1,1};

        System.out.println(new MajorityElement().majorityElement(nums));

    }

    public int majorityElement(int[] nums) {

        if (nums.length == 1) {
            return nums[0];
        }

        Integer majority = nums[0];

        int majorityCount = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == majority) {
                majorityCount++;
                continue;
            }

            majorityCount--;
            if (majorityCount == 0) {
                majority = nums[++i];
                majorityCount = 1;
            }
        }

        return majority;
    }


}

