package fr.orion78.adventOfCode.year2020.day23;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CircularHashList<T> {
    private static class Node<E> {
        private final E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    private final Map<T, Node<T>> nodes = new HashMap<>();

    public CircularHashList(List<T> cupsInit) {
        if (cupsInit.size() == 0) {
            throw new RuntimeException("Needs items for init");
        }

        T firstItem = cupsInit.get(0);
        Node<T> firstNode = new Node<>(null, firstItem, null);
        firstNode.next = firstNode;
        firstNode.prev = firstNode;
        nodes.put(firstItem, firstNode);

        for (int i = 1; i < cupsInit.size(); i++) {
            putItemAfter(cupsInit.get(i - 1), cupsInit.get(i));
        }
    }

    public void putItemAfter(T itemToFind, T itemToPut) {
        Node<T> itemToFindNode = nodes.get(itemToFind);
        Node<T> nodeToPut = new Node<>(itemToFindNode, itemToPut, itemToFindNode.next);
        itemToFindNode.next.prev = nodeToPut;
        itemToFindNode.next = nodeToPut;
        nodes.put(itemToPut, nodeToPut);
    }

    public T getItemAfter(T item) {
        return nodes.get(item).next.item;
    }

    public T removeItemAfter(T item) {
        Node<T> itemNode = nodes.remove(nodes.get(item).next.item);
        itemNode.prev.next = itemNode.next;
        itemNode.next.prev = itemNode.prev;
        return itemNode.item;
    }
}
