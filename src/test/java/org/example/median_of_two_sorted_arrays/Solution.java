package org.example.median_of_two_sorted_arrays;

class Solution {
      public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length == 0) {
          return nums2[nums2.length / 2] * 0.5 + nums2[(nums2.length - 1) / 2] * 0.5;
        }

        if (nums2.length == 0) {
          return nums1[nums1.length / 2] * 0.5 + nums1[(nums1.length - 1) / 2] * 0.5;
        }
        
        int[] a, b;
        if (nums1.length <= nums2.length) {
            a = nums1; b = nums2;
        } else {
            a = nums2; b = nums1;
        }

        int total = a.length + b.length;
        int half = (total - 1) / 2;

        int left = 0, right = a.length - 1;
        while (true) {
            int i = left <= right ? (left + right) / 2 : -1;
            int j = half - i - 1;

            int aLeft = (i >= 0) ? a[i] : Integer.MIN_VALUE;
            int aRight = (i + 1 < a.length) ? a[i + 1] : Integer.MAX_VALUE;
            int bLeft = (j >= 0) ? b[j] : Integer.MIN_VALUE;
            int bRight = (j + 1 < b.length) ? b[j + 1] : Integer.MAX_VALUE;

            if (aLeft <= bRight && bLeft <= aRight) {
                if (total % 2 != 0) {
                    return Math.max(aLeft, bLeft);
                } else {
                    return Math.max(aLeft, bLeft) * 0.5 + Math.min(aRight, bRight) * 0.5;
                }
            } else if (aLeft > bRight) {
                right = i - 1;
            } else if (bLeft > aRight) {
                left = i + 1;
            }
        }
    }
}