package cn.innovationai.system.domain.model;

import lombok.Data;

import java.io.Serializable;
@Data
public class ZhiyunHttpRes<T> implements Serializable {
    private static final long serialVersionUID = -3440061414071692254L;
    private T result;
    private String status;
}
