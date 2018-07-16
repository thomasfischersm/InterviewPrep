package com.playposse.interviewprep.leetcode;

import org.junit.Test;

/**
 * Created by thoma on 7/14/2018.
 */
public class MergeTwoLists {

    @Test
    public void test() {
        ListNode l1 = new ListNode(1, new ListNode(2, new ListNode(4, null)));
        ListNode l2 = new ListNode(1, new ListNode(3, new ListNode(4, null)));

        ListNode resultNode = new Solution().mergeTwoLists(l1, l2);
    }

      public class ListNode {
          int val;
          ListNode next;
          ListNode(int x) { val = x; }

          public ListNode(int val, ListNode next) {
              this.val = val;
              this.next = next;
          }
      }

    class Solution {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if (l2 == null) {
                return l1;
            }

            if (l1 == null) {
                return l2;
            }

            final ListNode head;
            if (l1.val < l2.val) {
                head = l1;
                l1 = l1.next;
            } else {
                head = l2;
                l2 = l2.next;
            }

            ListNode tail = head;
            while ((l1 != null) && (l2 != null)) {
                if (l1.val < l2.val) {
                    tail.next = l1;
                    tail = tail.next;
                    l1 = l1.next;
                } else {
                    tail.next = l2;
                    tail = tail.next;
                    l2 = l2.next;
                }
            }

            while (l1 != null) {
                tail.next = l1;
                tail = tail.next;
                l1 = l1.next;
            }

            while (l2 != null) {
                tail.next = l2;
                tail = tail.next;
                l2 = l2.next;
            }

            return head;
        }
    }
}
