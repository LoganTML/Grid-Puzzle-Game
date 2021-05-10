public class LinkedQueue<T> implements Queue<T> {
    private QueueNode<T> front;
    private QueueNode<T> back;
    private int size;

    public LinkedQueue() {
        front = null;
        back = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public void enqueue(T element) {
        QueueNode<T> newNode = new QueueNode<T>(element);
        if (size == 0) {
            front = newNode;
        } else {
            newNode.setNext(back);
            back.setPrev(newNode);
        }
        back = newNode;
        size++;
    }

    public T dequeue() throws EmptyQueueException {
        if (size==0) {
            throw new EmptyQueueException();
        } else if (size == 1) {
            T a = front.getData();
            front = null;
            back = null;
            size = 0;
            return a;
        } else {
            T a = front.getData();
            QueueNode<T> second = front.getPrev();
            second.setNext(null);
            front = second;
            size--;
            return a;
        }
    }

    public T peek() {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        return front.getData();
    }

    public T peekBack() {
        if (isEmpty()) {
            return null;
        }
        return back.getData();
    }

    public void makeEmpty() {
        front = null;
        back = null;
    }

}