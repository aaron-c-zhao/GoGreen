package gogreenserver.responses;

public class PositiveResponse extends Response {

    public PositiveResponse(boolean okMessage) {
        super(true);
    }

    public PositiveResponse(boolean okMessage, Object data) {
        super(true, data);
    }
}
