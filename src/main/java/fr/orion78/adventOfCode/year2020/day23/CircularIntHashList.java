package fr.orion78.adventOfCode.year2020.day23;

import java.util.List;

public class CircularIntHashList {
    private static class Node {
        private final int item;
        Node next;
        Node prev;

        Node(Node prev, int element, Node next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    private final Node[] nodes;

    public CircularIntHashList(int size, List<Integer> cupsInit) {
        if (cupsInit.size() == 0) {
            throw new RuntimeException("Needs items for init");
        }

        nodes = new Node[size];

        int firstItem = cupsInit.get(0);
        Node firstNode = new Node(null, firstItem, null);
        firstNode.next = firstNode;
        firstNode.prev = firstNode;
        nodes[firstItem] = firstNode;

        for (int i = 1; i < cupsInit.size(); i++) {
            putItemAfter(cupsInit.get(i - 1), cupsInit.get(i));
        }
    }

    public void putItemAfter(int itemToFind, int itemToPut) {
        Node itemToFindNode = nodes[itemToFind];
        Node nodeToPut = new Node(itemToFindNode, itemToPut, itemToFindNode.next);
        itemToFindNode.next.prev = nodeToPut;
        itemToFindNode.next = nodeToPut;
        nodes[itemToPut] = nodeToPut;
    }

    public int getItemAfter(int item) {
        return nodes[item].next.item;
    }

    public int removeItemAfter(int item) {
        Node itemNode = nodes[item].next;
        nodes[itemNode.item] = null;
        itemNode.prev.next = itemNode.next;
        itemNode.next.prev = itemNode.prev;
        return itemNode.item;
    }
}
