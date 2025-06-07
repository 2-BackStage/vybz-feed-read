package back.vybz.feed_read_service.feed.application.service;

import back.vybz.feed_read_service.feed.dto.request.RequestScrollNoticeDto;
import back.vybz.feed_read_service.feed.dto.response.ResponseNoticeDto;
import back.vybz.feed_read_service.feed.dto.response.ResponseScrollNoticeDto;

public interface NoticeReadService {

    ResponseScrollNoticeDto getNoticeScrollList(RequestScrollNoticeDto request);

    ResponseNoticeDto getNoticeDetail(String noticeId);
}
