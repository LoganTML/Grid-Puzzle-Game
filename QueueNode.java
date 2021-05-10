public class QueueNode<T> {
    private T data;
    private QueueNode<T> next;
    private QueueNode<T> prev;

    public QueueNode(T value) {
        this.data = value;
        this.next = null;
        this.prev = null;
    }

    public T getData() {
        return data;
    }

    public void setData(T value) {
        this.data = value;
    }

    public QueueNode<T> getNext() {
        return next;
    }

    public QueueNode<T> getPrev() {
        return prev;
    }

    public void setNext(QueueNode<T> next) {
        this.next = next;
    }

    public void setPrev(QueueNode<T> prev) {
        this.prev = prev;
    }

    public String toString() {
        return data + "";
    }
}
