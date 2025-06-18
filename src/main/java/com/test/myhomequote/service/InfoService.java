package com.test.myhomequote.service;

import com.test.myhomequote.data.LevelResultRepository;
import com.test.myhomequote.data.UserResultRepository;
import com.test.myhomequote.service.dto.InfoBO;
import com.test.myhomequote.service.result.ResultService;
import common.ERepositoryType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class InfoService {

    @Autowired
    private ResultService resultService;

    private static final Logger log = LoggerFactory.getLogger(InfoService.class);

    public void setInfo(InfoBO infoBO) {
        log.info("set result {} for user id {} and level id {}", infoBO.getResult(), infoBO.getUserId(), infoBO.getLevelId());
        resultService.getRepository(ERepositoryType.LEVEL).add(infoBO.getLevelId(), infoBO.getUserId(), infoBO.getResult());
        resultService.getRepository(ERepositoryType.USER).add(infoBO.getUserId(), infoBO.getLevelId(), infoBO.getResult());
    }

    public List<InfoBO> getResultInfoByLevelId(Long levelId) {
        log.info("get result info by level id {}", levelId);
        var optionalResultsByUserId = resultService.getRepository(ERepositoryType.LEVEL).getById(levelId);
        if (optionalResultsByUserId.isEmpty()) {
            return Collections.emptyList();
        }
        var infoBOs = new ArrayList<InfoBO>();
        optionalResultsByUserId.get().descendingMap().forEach((result, userIds) -> {
            for (var userIdIter = userIds.descendingIterator(); userIdIter.hasNext(); ) {
                Long userId = userIdIter.next();
                infoBOs.add(new InfoBO(userId, levelId, result));
            }
        });
        return infoBOs;
    }

    public List<InfoBO> getResultInfoByUserId(Long userId) {
        log.info("get result info by user id {}", userId);
        var optionalResultsByLevelId = resultService.getRepository(ERepositoryType.USER).getById(userId);
        if (optionalResultsByLevelId.isEmpty()) {
            return Collections.emptyList();
        }
        var infoBOs = new ArrayList<InfoBO>();
        optionalResultsByLevelId.get().descendingMap().forEach((result, levelIds) -> {
            for (var levelIIter = levelIds.descendingIterator(); levelIIter.hasNext(); ) {
                Long levelId = levelIIter.next();
                infoBOs.add(new InfoBO(userId, levelId, result));
            }
        });
        return infoBOs;
    }
}
