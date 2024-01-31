package com.casestudy.amadeusflightservice.response.base;

import com.casestudy.amadeusflightservice.exception.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseResponse<T> {
    private BaseBody<T> body;
    private Status status;

}
