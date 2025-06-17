package com.test.myhomequote.mapper;

import com.test.myhomequote.api.dto.request.InfoRequest;
import com.test.myhomequote.api.dto.resonse.InfoResponse;
import com.test.myhomequote.service.dto.InfoBO;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class InfoMapper {

    private InfoMapper() {
    }

    public static InfoBO toInfoBO(InfoRequest infoRequest) {
        var infoBO = new InfoBO();
        infoBO.setUserId(infoRequest.getUserId());
        infoBO.setLevelId(infoRequest.getLevelId());
        infoBO.setResult(infoRequest.getResult());
        return infoBO;
    }

    public static List<InfoResponse> toInfoResponseList(Collection<InfoBO> infoBOs) {
        return infoBOs.stream().map(InfoMapper::toInfoResponse).collect(Collectors.toList());
    }

    public static InfoResponse toInfoResponse(InfoBO infoBO) {
        var response = new InfoResponse();
        response.setUserId(infoBO.getUserId());
        response.setLevelId(infoBO.getLevelId());
        response.setResult(infoBO.getResult());
        return response;
    }
}
