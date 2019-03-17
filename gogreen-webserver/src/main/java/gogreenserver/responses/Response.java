package gogreenserver.responses;

public class Response {

    private boolean okMessage;
    private Object data;

    public Response(boolean okMessage) {
        this.okMessage = okMessage;
    }

    public Response(boolean okMessage, Object data) {
        this.okMessage = okMessage;
        this.data = data;
    }

    public boolean getOkMessage() {
        return okMessage;
    }

    public void setOkMessage(boolean okMessage) {
        this.okMessage = okMessage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
