class Solution{
    public ListNode swapPairs(ListNode head) {
    // dummy node to simplify pointer handling
    ListNode dummy = new ListNode(0);
    dummy.next = head;

    ListNode prev = dummy;

    // traverse pairs
    while (head != null && head.next != null) {
        ListNode first = head;
        ListNode second = head.next;

        // Swapping logic
        prev.next = second;
        first.next = second.next;
        second.next = first;

        // Move pointers forward for next iteration
        prev = first;
        head = first.next;
    }

    return dummy.next;
}
}
