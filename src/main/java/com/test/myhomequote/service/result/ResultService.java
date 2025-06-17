package com.test.myhomequote.service.result;

import com.test.myhomequote.data.ResultRepository;
import com.test.myhomequote.service.InfoService;
import common.ERepositoryType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;

@Service
public class ResultService {

    private static final Logger log = LoggerFactory.getLogger(InfoService.class);
    private final Map<ERepositoryType, ResultRepository> repositoryMap;

    @Autowired
    public ResultService(Collection<ResultRepository> repository) {
        repositoryMap = repository.stream().collect(Collectors.toMap(ResultRepository::getType, identity()));
    }

    public ResultRepository getRepository(ERepositoryType type) {
        if (!repositoryMap.containsKey(type)) {
            log.error("No ResultRepository found for type {}", type);
            throw new IllegalArgumentException("No ResultRepository found for type " + type);
        }
        return repositoryMap.get(type);
    }
}
