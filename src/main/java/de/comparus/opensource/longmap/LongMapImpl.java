package de.comparus.opensource.longmap;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LongMapImpl<V> implements LongMap<V> {

    private List<Node<V>> table;

    public V put(long key, V value) {

        if (table == null || table.size() == 0) {
            table = new ArrayList<>();
        }

        Optional<Node<V>> node = table.stream().filter(e -> e.getKey() == key).findFirst();
        if(node.isPresent()) {
            V oldVal;
                oldVal = node.get().value;
                table.set(table.indexOf(node.get()), newNode(key, value));
            return oldVal;
        } else {
            table.add(newNode(key, value));
        }
        return null;
    }

    public V get(long key) {
        V val = null;
        Optional<Node<V>> node = table.stream().filter(e -> e.getKey() == key).findFirst();
        if(node.isPresent()) {
            val = node.get().value;
        }
        return val;
    }

    public V remove(long key) {
        V val = null;
        Optional<Node<V>> node = table.stream().filter(e -> e.getKey() == key).findFirst();
        if(node.isPresent()) {
            val = node.get().getValue();
            table.remove(node.get());
        }
        return val;
    }

    public boolean isEmpty() {
        boolean isEmpty = true;
        if(table != null) {
            isEmpty = table.isEmpty();
        }
        return isEmpty;
    }

    public boolean containsKey(long key) {
        return table.stream().anyMatch(e -> e.getKey() == key);
    }

    public boolean containsValue(V value) {
        return table.stream().anyMatch(e -> e.getValue() == value);
    }

    public long[] keys() {
        long[] keys = new long[table.size()];
        for(int i = 0; i < table.size(); i++) {
            keys[i] = table.get(i).getKey();
        }
        return keys;
    }
//
    @SuppressWarnings("unchecked")
    public V[] values() {
        V[] values = (V[]) new Object[table.size()];
        for(int i = 0; i < table.size(); i++) {
            values[i] = table.get(i).getValue();
        }
        return values;
    }

    public long size() {
        int n = 0;
        if(table != null) {
            n = table.size();
        }
        return n;
    }

    public void clear() {
        if(table != null) {
            table.clear();
        }
    }

    private static class Node<V> {
        private final long key;
        private final V value;

        Node(long key, V value) {
            this.key = key;
            this.value = value;
        }

        public long getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public final String toString() {
            return key + "=" + value;
        }
    }

    // Create a node
    Node<V> newNode(long key, V value) {
        return new Node<>(key, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Node<V> n : table) {
            if(n != null) {
                sb.append(n.toString()).append("; ");
            }
        }
        return sb.toString();
    }
}
