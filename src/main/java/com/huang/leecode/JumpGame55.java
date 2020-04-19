package com.huang.leecode;

/**
 *
 */
public class JumpGame55 {

    public static void main(String[] args) {

    }

    public boolean canJump(int[] nums) {

        int maxRange = 0;

        for (int i =0; i< nums.length && i <= maxRange; i++) {
            maxRange = (i + nums[i]) > maxRange ? (i + nums[i]) : maxRange;
            if (maxRange >= nums.length -1) {
                return true;
            }
        }

        return false;
    }

}
