package gogreenserver.responses;

public class NegativeResponse extends Response {

    public NegativeResponse(Object data) {
        super(true, data);
    }
}
