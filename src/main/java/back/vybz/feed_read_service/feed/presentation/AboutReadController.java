package back.vybz.feed_read_service.feed.presentation;

import back.vybz.feed_read_service.common.entity.BaseResponseEntity;
import back.vybz.feed_read_service.feed.application.service.AboutReadService;
import back.vybz.feed_read_service.feed.dto.response.ResponseAboutDto;

import back.vybz.feed_read_service.feed.vo.response.ResponseAboutVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/read/feed")
@RequiredArgsConstructor
public class AboutReadController {

    private final AboutReadService aboutReadService;

    @Operation(
            summary = "자기소개 단건 조회 API",
            description = "자기소개 단건을 조회하는 API입니다.",
            tags = {"ABOUT-READ-SERVICE"}
    )
    @GetMapping("/about/{aboutId}")
    public BaseResponseEntity<ResponseAboutVo> getAbout(@PathVariable String aboutId) {
        ResponseAboutDto responseAboutDto = aboutReadService.getAboutRead(aboutId);
        return BaseResponseEntity.ok(responseAboutDto.toVo());
    }
}
