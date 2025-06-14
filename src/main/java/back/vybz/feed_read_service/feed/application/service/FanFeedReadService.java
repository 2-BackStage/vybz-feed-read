package back.vybz.feed_read_service.feed.application.service;

import back.vybz.feed_read_service.feed.dto.request.RequestScrollFanFeedDto;
import back.vybz.feed_read_service.feed.dto.response.ResponseFanFeedDto;
import back.vybz.feed_read_service.feed.dto.response.ResponseScrollFanFeedDto;

public interface FanFeedReadService {
    ResponseScrollFanFeedDto getFanFeedScrollList(RequestScrollFanFeedDto requestScrollFanFeedDto);
    ResponseScrollFanFeedDto getUserFanFeedScrollList(RequestScrollFanFeedDto requestScrollFanFeedDto);
    ResponseFanFeedDto getFanFeedDetail(String fanFeedId);
}
