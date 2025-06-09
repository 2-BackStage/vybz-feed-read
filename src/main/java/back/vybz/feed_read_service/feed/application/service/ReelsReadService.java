package back.vybz.feed_read_service.feed.application.service;

import back.vybz.feed_read_service.feed.dto.request.RequestScrollReelsDto;
import back.vybz.feed_read_service.feed.dto.response.ResponseScrollReelsDto;

public interface ReelsReadService {
    ResponseScrollReelsDto getReelsScrollList(RequestScrollReelsDto request);
    ResponseScrollReelsDto getBuskerReelsScrollList(RequestScrollReelsDto request);
}