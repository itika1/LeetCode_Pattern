class Solution {
    static class TrieNode {
        TrieNode[] children = new TrieNode[2]; // 0 and 1
    }

    TrieNode root = new TrieNode();

    // Insert number into Trie
    public void insert(int num) {
        TrieNode node = root;
        for (int i = 31; i >= 0; i--) {
            int bit = (num >> i) & 1;
            if (node.children[bit] == null) {
                node.children[bit] = new TrieNode();
            }
            node = node.children[bit];
        }
    }

    // Find maximum XOR for a number
    public int getMaxXor(int num) {
        TrieNode node = root;
        int maxXor = 0;
        for (int i = 31; i >= 0; i--) {
            int bit = (num >> i) & 1;
            int toggledBit = 1 - bit; // opposite bit to maximize XOR
            if (node.children[toggledBit] != null) {
                maxXor |= (1 << i); // add this bit to result
                node = node.children[toggledBit];
            } else {
                node = node.children[bit];
            }
        }
        return maxXor;
    }

    public int findMaximumXOR(int[] nums) {
        int max = 0;
        for (int num : nums) {
            insert(num);
        }
        for (int num : nums) {
            max = Math.max(max, getMaxXor(num));
        }
        return max;
    }
}
