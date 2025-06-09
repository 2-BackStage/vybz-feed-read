package back.vybz.feed_read_service.feed.application.service;

import back.vybz.feed_read_service.feed.dto.response.ResponseAboutDto;

public interface AboutReadService {
    ResponseAboutDto getAboutRead(String aboutId);
}
