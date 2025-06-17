package com.test.myhomequote.data;

import common.ERepositoryType;
import org.springframework.stereotype.Component;

@Component
public class LevelResultRepository extends ResultRepository {

    @Override
    public ERepositoryType getType() {
        return ERepositoryType.LEVEL;
    }
}
