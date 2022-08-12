package net.movielot.movielot.util;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import net.movielot.movielot.response.SuccessResponse;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResponseUtil {

    public static final String SUCCESS_MESSAGE = "success";

    public static <T> SuccessResponse<T> createSuccess(T contents) {
        return SuccessResponse.<T>builder().message(SUCCESS_MESSAGE).contents(contents).build();
    }

}
