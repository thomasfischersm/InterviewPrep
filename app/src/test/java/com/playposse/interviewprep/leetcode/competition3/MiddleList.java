package com.playposse.interviewprep.leetcode.competition3;

/**
 * Created by thoma on 7/28/2018.
 */
public class MiddleList {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    class Solution {
        public ListNode middleNode(ListNode head) {
            ListNode fast = head;
            ListNode slow = head;

            boolean flip = false;
            while (fast != null) {
                fast = fast.next;
                if (flip) {
                    slow = slow.next;
                }
                flip = !flip;
            }
            return slow;
        }
    }
}
