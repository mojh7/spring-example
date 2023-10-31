/*
package com.example.geo.dto;

import com.example.geo.domain.SearchByType;
import com.example.geo.domain.SnsType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostSearchCondition {

    private final String hashtag;
    private final SnsType type;
    private final SearchByType searchBy;
    private final String search;

    @Builder
    private PostSearchCondition(String hashtag, SnsType type, SearchByType searchBy, String search) {
        this.hashtag = hashtag;
        this.type = type;
        this.searchBy = searchBy;
        this.search = search;
    }

    public static PostSearchCondition of(String hashtag, SnsType type, SearchByType searchBy, String search) {
        return PostSearchCondition.builder()
                .hashtag(hashtag)
                .type(type)
                .searchBy(searchBy)
                .search(search)
                .build();
    }

}
*/
