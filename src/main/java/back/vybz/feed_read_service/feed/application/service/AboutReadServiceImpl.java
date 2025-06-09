package back.vybz.feed_read_service.feed.application.service;

import back.vybz.feed_read_service.common.exception.BaseException;
import back.vybz.feed_read_service.common.exception.BaseResponseStatus;
import back.vybz.feed_read_service.feed.domain.AboutRead;
import back.vybz.feed_read_service.feed.dto.response.ResponseAboutDto;
import back.vybz.feed_read_service.feed.infrastructure.AboutReadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AboutReadServiceImpl implements AboutReadService{

    private final AboutReadRepository aboutReadRepository;

    @Override
    public ResponseAboutDto getAboutRead(String aboutId){
        AboutRead aboutRead = aboutReadRepository.findById(aboutId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.ABOUT_NOT_FOUND));
        return ResponseAboutDto.from(aboutRead);
    }


}
