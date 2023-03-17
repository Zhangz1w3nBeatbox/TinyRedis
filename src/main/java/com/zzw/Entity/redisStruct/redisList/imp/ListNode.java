package com.zzw.Entity.redisStruct.redisList.imp;

import com.zzw.Entity.redisStruct.redisList.redisList;

public class ListNode<V> implements redisList<V> {

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
