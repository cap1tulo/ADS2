public class MyStack<T> {
    private MyLinkedList<T> list;

    public MyStack() {
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
            throw new IllegalStateException("Stack is empty");
        }
        return list.getLast();
    }

    public void push(T item) {
        list.addLast(item);
    }

    public T pop() {
        if (empty()) {
            throw new IllegalStateException("Stack is empty");
        }
        T lastItem = list.getLast();
        list.removeLast();
        return lastItem;
    }
}
