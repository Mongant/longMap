package de.comparus.opensource.longmap;

import java.util.*;

public class LongMapImpl<V> implements LongMap<V> {

    private Set<Node<V>> table;

    public V put(long key, V value) {
        if (table == null || table.size() == 0) {
            table = new HashSet<>();
        }
        if(!table.add(newNode(key, value))) {
            remove(key);
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
            table.remove(node.get());
            val = node.get().getValue();
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
        boolean isPresent = false;
        if(table != null) {
            isPresent = table.stream().anyMatch(e -> e.getKey() == key);
        }
        return isPresent;
    }

    public boolean containsValue(V value) {
        boolean isPresent = false;
        if(table != null) {
            isPresent = table.stream().anyMatch(e -> e.getValue().equals(value));
        }
        return isPresent;
    }

    @SuppressWarnings("unchecked")
    public long[] keys() {
        long[] keys = new long[table.size()];
        Object[] arrObjects;
        arrObjects = table.toArray();
        for(int i = 0; i < arrObjects.length; i++) {
            Node<V> node = (Node<V>) arrObjects[i];
            keys[i] = node.getKey();
        }
        return keys;
    }

    @SuppressWarnings("unchecked")
    public V[] values() {
        V[] values = (V[]) new Object[table.size()];
        Object[] arrObjects;
        arrObjects = table.toArray();
        for(int i = 0; i < arrObjects.length; i++) {
            Node<V> node = (Node<V>) arrObjects[i];
            values[i] = node.getValue();
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

    /**
     * Basic object for saving key and value parameters
     *
     * @param <V> type of value
     */
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
        public final boolean equals(Object o) {
            if (o == this) {
                return true;
            } else {
                if (o instanceof Node) {
                    Node<V> o2 = (Node<V>)o;
                    return Objects.equals(this.key, o2.getKey());
                }
                return false;
            }
        }

        @Override
        public final int hashCode() {
            return Objects.hashCode(this.key);
        }

        @Override
        public final String toString() {
            return key + "=" + value;
        }
    }

    /**
     * Create a new node
     *
     * @param key object key (must be unique)
     * @param value object value
     * @return {@link Node}
     */
    private Node<V> newNode(long key, V value) {
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
