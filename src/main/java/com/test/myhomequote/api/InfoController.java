package com.test.myhomequote.api;

import com.test.myhomequote.api.dto.request.InfoRequest;
import com.test.myhomequote.api.dto.resonse.InfoResponse;
import com.test.myhomequote.service.InfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.test.myhomequote.mapper.InfoMapper.*;

@RestController
@RequestMapping("/info")
@Validated
@Tag(name = "Info API", description = "Set and retrieve user/level score info")
public class InfoController {

    @Autowired
    private InfoService infoService;

    @Operation(summary = "Set user result for a level")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated"),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @PutMapping("/setinfo")
    public void setUserInfo(@Valid @RequestBody InfoRequest infoRequest) {
        infoService.setInfo(toInfoBO(infoRequest));
    }

    @Operation(summary = "Get top 20 results for a specific level")
    @ApiResponse(responseCode = "200", description = "List of user results on level",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = InfoResponse.class))))
    @GetMapping("/levelinfo/{levelId}")
    public List<InfoResponse> getLevelInfo(@PathVariable Long levelId) {
        return toInfoResponseList(infoService.getResultInfoByLevelId(levelId));
    }

    @Operation(summary = "Get top 20 results for a specific user")
    @ApiResponse(responseCode = "200", description = "List of user's results across levels",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = InfoResponse.class))))
    @GetMapping("/userinfo/{userId}")
    public List<InfoResponse> getUserInfo(@PathVariable Long userId) {
        return toInfoResponseList(infoService.getResultInfoByUserId(userId));
    }
}
