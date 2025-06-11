package com.test.myhomequote.service;

import com.test.myhomequote.data.LevelResultRepository;
import com.test.myhomequote.data.UserResultRepository;
import com.test.myhomequote.service.dto.InfoBO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class InfoService {

    @Autowired
    private LevelResultRepository levelResultRepository;

    @Autowired
    private UserResultRepository userResultRepository;

    private static final Logger log = LoggerFactory.getLogger(InfoService.class);

    public void setInfo(InfoBO infoBO) {
        log.info("set result {} for user id {} and level id {}", infoBO.getResult(), infoBO.getUserId(), infoBO.getLevelId());
        levelResultRepository.add(infoBO.getLevelId(), infoBO.getUserId(), infoBO.getResult());
        userResultRepository.add(infoBO.getUserId(), infoBO.getLevelId(), infoBO.getResult());
    }

    public List<InfoBO> getResultInfoByLevelId(Long levelId) {
        log.info("get result info by level id {}", levelId);
        var resultsByUserId = levelResultRepository.getById(levelId);
        List<InfoBO> infoBOs = new ArrayList<>();
        resultsByUserId.forEach((userId, results) -> {
            results.forEach(result -> {
                infoBOs.add(new InfoBO(userId, levelId, result));
            });
        });
        return infoBOs.stream().sorted(Comparator.comparing(InfoBO::getResult).reversed()
                .thenComparing(InfoBO::getUserId))
                .collect(Collectors.toList());
    }

    public List<InfoBO> getResultInfoByUserId(Long userId) {
        log.info("get result info by user id {}", userId);
        var resultsByUserId = userResultRepository.getById(userId);
        List<InfoBO> infoBOs = new ArrayList<>();
        resultsByUserId.forEach((levelId, results) -> {
            results.forEach(result -> {
                infoBOs.add(new InfoBO(userId, levelId, result));
            });
        });
        return infoBOs.stream().sorted(Comparator.comparing(InfoBO::getResult).reversed()
                .thenComparing(InfoBO::getLevelId).reversed())
                .collect(Collectors.toList());
    }
}
