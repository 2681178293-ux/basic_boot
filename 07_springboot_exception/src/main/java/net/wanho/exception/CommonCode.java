package net.wanho.exception;

public enum CommonCode implements ExceptionResult {
    // ========== HTTP 标准状态码 ==========
    BAD_REQUEST(400, "请求参数错误"),
    UNAUTHORIZED(401, "未登录或登录已过期"),
    FORBIDDEN(403, "权限不足"),
    NOT_FOUND(404, "资源不存在"),
    METHOD_NOT_ALLOWED(405, "请求方式不支持"),
    CONFLICT(409, "资源冲突"),
    INTERNAL_ERROR(500, "服务器内部错误"),
    SERVICE_UNAVAILABLE(503, "服务暂不可用"),

    // ========== 参数校验 10001~10099 ==========
    PARAM_MISSING(10001, "参数缺失"),
    PARAM_INVALID(10002, "参数校验失败"),
    PARAM_TYPE_ERROR(10003, "参数类型错误"),
    PARAM_LENGTH_LIMIT(10004, "参数长度超限"),

    // ========== Token / 认证 20001~20099 ==========
    TOKEN_EXPIRED(20001, "Token已过期"),
    TOKEN_INVALID(20002, "Token无效"),
    TOKEN_MISSING(20003, "Token缺失"),

    // ========== 用户业务 30001~30099 ==========
    USER_NOT_FOUND(30001, "用户不存在"),
    USER_PASSWORD_ERROR(30002, "用户名或密码错误"),
    USER_LOCKED(30003, "账号已被锁定"),
    USER_DELETED(30004, "用户已被删除"),

    // ========== 数据操作 50001~50099 ==========
    DATA_NOT_FOUND(50001, "数据不存在"),
    DATA_DUPLICATE(50002, "数据已存在"),

    // ========== 文件业务 60001~60099 ==========
    FILE_UPLOAD_ERROR(60001, "文件上传失败"),
    FILE_SIZE_LIMIT(60002, "文件大小超限"),

    // ========== 通用业务 90001~90099 ==========
    OPERATION_FAILED(90001, "操作失败"),
    ;

    private final int code;
    private final String msg;

    CommonCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String msg() {
        return msg;
    }
}
