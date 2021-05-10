public class EmptyQueueException extends RuntimeException {
    public EmptyQueueException(String msg) {
        super(msg);
    }

    public EmptyQueueException() {
        super();
    }
}
