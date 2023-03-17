package com.zzw.Entity.redisStruct;

public class ListNode<V> {
    public V val;
    public ListNode next;

    public ListNode(V val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public ListNode(V val) {
        this.val = val;
    }

    public ListNode() {
    }
}
