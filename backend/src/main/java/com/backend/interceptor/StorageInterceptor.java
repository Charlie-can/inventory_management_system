package com.backend.interceptor;


import com.backend.utils.InterceptJudgment;
import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class StorageInterceptor implements HandlerInterceptor {


    private InterceptJudgment interceptJudgment;

    @Autowired
    public void setInterceptJudgment(InterceptJudgment interceptJudgment) {
        this.interceptJudgment = interceptJudgment;
    }

    @Override
    public boolean preHandle(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response, @NonNull Object handler) throws Exception {

        Map<String, Object> authorityJudgment = interceptJudgment.authorityJudgment(request, response, InterceptJudgment.AuthorityEnum.STORAGEPERSON);

        if ((Boolean) authorityJudgment.get("return")) {
            return true;
        }
        response = (HttpServletResponse) authorityJudgment.get("response");

        return false;



    }


}
