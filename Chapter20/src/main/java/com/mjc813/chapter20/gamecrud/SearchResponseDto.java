package com.mjc813.chapter20.gamecrud;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SearchResponseDto {
    private Integer count;
    private List<GameDto> list;
    private Integer curPage;
    private Integer rowsPerPage;

    public void setData(SearchRequestDto requestDto) {
        this.setCurPage(requestDto.getCurPage());
        this.setRowsPerPage(requestDto.getRowsPerPage());
    }
}
