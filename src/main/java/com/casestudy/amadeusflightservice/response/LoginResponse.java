package com.casestudy.amadeusflightservice.response;

import com.casestudy.amadeusflightservice.response.base.BaseResponse;
import com.casestudy.amadeusflightservice.response.body.LoginResponseBody;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponse extends BaseResponse<LoginResponseBody> {
}
