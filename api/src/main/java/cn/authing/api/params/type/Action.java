package cn.authing.api.params.type;

public enum Action {
    /**
     * 修改二维码状态为已扫码状态，当移动 APP 扫了码之后，应当立即执行此操作；
     */
    SCAN,
    /**
     * 修改二维码状态为已授权，执行此操作前必须先执行 `SCAN 操作；
     */
    CONFIRM,
    /**
     * 修改二维码状态为已取消，执行此操作前必须先执行 `SCAN 操作；
     */
    CANCEL,
}
