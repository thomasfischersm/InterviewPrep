package com.playposse.interviewprep.leetcode.competition0;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by thoma on 7/7/2018.
 */
public class DeepestNodes {

    class Solution {
        public TreeNode subtreeWithAllDeepest(TreeNode root) {
            List<TreeNode> lastDepth = new LinkedList<>();
            List<TreeNode> depth = new LinkedList<>();

            // Find deepest nodes.
            lastDepth.add(root);
            while (true) {
                for (TreeNode node : lastDepth) {
                    if (node.left != null) {
                        depth.add(node.left);
                    }
                    if (node.right != null) {
                        depth.add(node.right);
                    }
                }

                if (depth.size() == 0) {
                    break;
                }

                lastDepth = depth;
                depth = new LinkedList<>();
            }

            TreeNode node = root;
            while (true) {
                if (isChild(node.left, lastDepth) || isChild(node.right, lastDepth)) {
                    return node;
                }

                if (hasNoneOfTheChildren(node.left, lastDepth)) {
                    node = node.right;
                } else if (hasNoneOfTheChildren(node.right, lastDepth)) {
                    node = node.left;
                }
            }
        }

        private boolean hasNoneOfTheChildren(TreeNode root, List<TreeNode> children) {
            if (root == null) {
                return true;
            }

            List<TreeNode> lastDepth = new LinkedList<>();
            List<TreeNode> depth = new LinkedList<>();

            // Find deepest nodes.
            lastDepth.add(root);
            while (true) {
                for (TreeNode node : lastDepth) {
                    if (isChild(node, children)) {
                        return false;
                    }

                    if (node.left != null) {
                        depth.add(node.left);
                    }
                    if (node.right != null) {
                        depth.add(node.right);
                    }
                }

                if (depth.size() == 0) {
                    break;
                }

                lastDepth = depth;
                depth = new LinkedList<>();
            }

            return true;
        }

        private boolean isChild(TreeNode node, List<TreeNode> children) {
            for (TreeNode child : children) {
                if (node == child) {
                    return true;
                }
            }
            return false;
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
