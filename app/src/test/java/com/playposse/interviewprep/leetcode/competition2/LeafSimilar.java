package com.playposse.interviewprep.leetcode.competition2;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by thoma on 7/21/2018.
 */
public class LeafSimilar {

    class Solution {
        public boolean leafSimilar(TreeNode root1, TreeNode root2) {
            if ((root1 == null) && (root2 == null)) {
                return true;
            }

            if ((root1 == null) || (root2 == null)) {
                return false;
            }

            List<TreeNode> sequence1 = getSequence(root1);
            List<TreeNode> sequence2 = getSequence(root2);
            return equals(sequence1, sequence2);
        }

        private List<TreeNode> getSequence(TreeNode root) {
            Stack<TreeNode> stack = new Stack<>();
            stack.add(root);

            List<TreeNode> result = new ArrayList<>();
            while (!stack.isEmpty()) {
                TreeNode node = stack.pop();

                if (node.left != null) {
                    stack.push(node.left);
                }

                if (node.right != null) {
                    stack.push(node.right);
                }

                if ((node.left == null) && (node.right == null)) {
                    result.add(node);
                }
            }

            return result;
        }

        private boolean equals(List<TreeNode> sequence1, List<TreeNode> sequence2) {
            if (sequence1.size() != sequence2.size()) {
                return false;
            }

            for (int i = 0; i < sequence1.size(); i++) {
                if (sequence1.get(i).val != sequence2.get(i).val) {
                    return false;
                }
            }

            return true;
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
