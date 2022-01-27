package com.edgedo.demo.exception;

/**
 * @Author:Qiutianzhu
 * @Date 2022-01-27 16:53
 * @Description: 涉及数据库业务异常错误信息
 */
public class DatabaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DatabaseException() {
        super();
    }

    public DatabaseException(String message) {
        super(message);
    }

    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public DatabaseException(Throwable cause) {
        super(cause);
    }

}
