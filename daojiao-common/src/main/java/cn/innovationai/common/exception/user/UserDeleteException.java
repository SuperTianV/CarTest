package cn.innovationai.common.exception.user;

/**
 * 用户账号已被删除
 * 
 * @author innovationai
 */
public class UserDeleteException extends UserException
{
    private static final long serialVersionUID = 1L;

    public UserDeleteException()
    {
        super("user.password.delete", null);
    }
}
