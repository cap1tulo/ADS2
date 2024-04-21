public class MyQueue<T> {
    private MyLinkedList<T> list;

    public MyQueue() {
        this.list = new MyLinkedList<>();
    }

    public boolean empty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }

    public T peek() {
        if (empty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return list.getFirst();
    }

    public void enqueue(T item) {
        list.addLast(item);
    }

    public T dequeue() {
        if (empty()) {
            throw new IllegalStateException("Queue is empty");
        }
        T firstItem = list.getFirst();
        list.removeFirst();
        return firstItem;
    }
}
