package ai.glider.design.common;

class DuplicateItemException extends RuntimeException {

    public DuplicateItemException() {
        super();
    }

    public DuplicateItemException(String message) {
        super(message);
    }
}
