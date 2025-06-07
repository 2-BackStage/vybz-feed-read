package back.vybz.feed_read_service.common.exception;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {

    private final BaseResponseStatus status;

    public BaseException(BaseResponseStatus status) {
        this.status = status;
    }

    public BaseException(BaseResponseStatus status, String message) {
        super(message);
        this.status = status;
    }
}
