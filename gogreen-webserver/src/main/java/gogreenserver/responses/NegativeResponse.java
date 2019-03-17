package gogreenserver.responses;

public class NegativeResponse extends Response {
    public NegativeResponse(boolean okMessage) {
        super(false);
    }

    public NegativeResponse(boolean okMessage, Object data) {
        super(true, data);
    }
}
