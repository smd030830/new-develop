package com.mjc813.chapter20.gamecrud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {
    @Autowired
    private IGameMybatis gameMybatis;

    public void insertData(GameDto newGame) {
        this.gameMybatis.insertData(newGame);
    }

    public void updateData(GameDto gameData) {
        this.gameMybatis.updateData(gameData);
    }

    public void deleteData(Integer id) {
        this.gameMybatis.deleteData(id);
    }

    public GameDto findById(Integer id) {
        return this.gameMybatis.findById(id);
    }

    public SearchResponseDto searchList(SearchRequestDto requestDto) {
        SearchResponseDto result = new SearchResponseDto();
        result.setData(requestDto);
//		result.setCurPage(requestDto.getCurPage());
//		result.setRowsPerPage(requestDto.getRowsPerPage());
        requestDto.calculate();
        Integer count = this.gameMybatis.countList(requestDto);
        result.setCount(count);
        result.setList(this.gameMybatis.searchList(requestDto));
        return result;
    }
}
