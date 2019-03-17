package gogreenserver.responses;

public class PositiveResponse extends Response {

    public PositiveResponse(Object data) {
        super(true, data);
    }
}
