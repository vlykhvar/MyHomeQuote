package com.test.myhomequote.data;

import common.ERepositoryType;
import org.springframework.stereotype.Component;

@Component
public class UserResultRepository extends ResultRepository {

    @Override
    public ERepositoryType getType() {
        return ERepositoryType.USER;
    }
}
