import java.util.Date;

public class ErrorDetail {
    private Date timestamp;
    private String message;
    private String detail;

    public ErrorDetail(Date timestamp, String message, String detail) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = detail;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetail() {
        return detail;
    }
}