package com.zzw.Entity.redisStruct;

public class ListNode<V> {
    V val;
    ListNode next;

    public ListNode(V val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
