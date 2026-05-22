package com.mjc813.chapter20.gamecrud;

import com.mjc813.chapter20.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GameRestController {
    @Autowired
    private GameService gameService;

    @PostMapping("/api/insert-data")
    @ResponseBody
    public ResponseEntity<CommonResponse> insertData(@RequestBody GameDto gameDto) {
        try {
            System.out.println("insertData" + gameDto.toString());
            this.gameService.insertData(gameDto);
            return ResponseEntity.ok().body(new CommonResponse(0, "OK"));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CommonResponse(-999, "Server Error"));
        }
    }

    @ResponseBody
    @PatchMapping("/api/update-data")
    public ResponseEntity<CommonResponse> updateData(@RequestBody GameDto gameDto) {
        try {
            System.out.println("insertData" + gameDto.toString());
            this.gameService.updateData(gameDto);
            return ResponseEntity.ok().body(new CommonResponse(0, "OK"));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CommonResponse(-999, "Server Error"));
        }
    }

    @ResponseBody
    @DeleteMapping("/api/delete-data")
    public ResponseEntity<CommonResponse> deleteData(@RequestBody GameDto deleteGame) {
        try {
            System.out.println("deleteData, id=" + deleteGame.getId());
            this.gameService.deleteData(deleteGame.getId());
            return ResponseEntity.ok().body(new CommonResponse(0, "OK"));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CommonResponse(-999, "Server Error"));
        }
    }

    @ResponseBody
    @GetMapping("/api/getdata")
    public ResponseEntity<CommonResponse> getData(@RequestParam("id") Integer id) {
        try {
            System.out.println("getData, id=" + id);
            GameDto find = this.gameService.findById(id);
            return ResponseEntity.ok().body(new CommonResponse(0, "OK", find));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CommonResponse(-999, "Server Error", null));
        }
    }

    @GetMapping("/api/search-list")
    @ResponseBody
    public ResponseEntity<CommonResponse> searchList(@ModelAttribute SearchRequestDto searchRequestDto) {
        try {
            System.out.println("searchList, searchDto=" + searchRequestDto);
            SearchResponseDto result = this.gameService.searchList(searchRequestDto);
            return ResponseEntity.ok().body(new CommonResponse(0, "OK", result));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CommonResponse(-999, "Server Error", null));
        }
    }
}
