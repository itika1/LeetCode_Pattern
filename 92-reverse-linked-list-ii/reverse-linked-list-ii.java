class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || left == right) return head;

        // Step 1️⃣ - Create a dummy node to handle edge cases easily
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // Step 2️⃣ - Reach node before `left`
        ListNode prev = dummy;
        for (int i = 1; i < left; i++) {
            prev = prev.next;
        }

        // Step 3️⃣ - Start reversing the sublist
        ListNode curr = prev.next;  // current node to reverse
        ListNode next = null;

        for (int i = 0; i < right - left; i++) {
            next = curr.next;
            curr.next = next.next;
            next.next = prev.next;
            prev.next = next;
        }

        // Step 4️⃣ - Return the updated list
        return dummy.next;
    }
}
