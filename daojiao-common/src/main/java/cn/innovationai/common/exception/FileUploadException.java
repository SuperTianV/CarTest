package cn.innovationai.common.exception;

/**
 * 文件上传异常
 * 
 * @author innovationai
 */
public class FileUploadException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    protected final String message;

    public FileUploadException(String message)
    {
        this.message = message;
    }

    public FileUploadException(String message, Throwable e)
    {
        super(message, e);
        this.message = message;
    }

    @Override
    public String getMessage()
    {
        return message;
    }
}
