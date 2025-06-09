package back.vybz.feed_read_service.feed.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TaggedHuman {

    //공통 UUID
    private String uuid;
    // 휴먼 타입(Busker, User)
    private HumanType humanType;
    // 닉네임
    private String nickname;
}