package back.vybz.feed_read_service.feed.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FeedType {


    REELS("릴스"),
    NOTICE("공지"),
    ABOUT("소개"),
    FAN_FEED("팬 피드");

    private final String description;
}
