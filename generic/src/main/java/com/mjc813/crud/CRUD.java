package com.mjc813.crud;

public interface CRUD<TYPE> {
    void add(TYPE item);	// item 을 마지막 순서에 추가한다.
    int size();	// 전체 item 의 갯수를 리턴한다.
    TYPE set(int index, TYPE item);	// index 번째에 item 객체로 셋팅한다. 리턴은 item 값이다.
    TYPE remove(int index);	// index 번째의 item 을 삭제한다. 리턴은 item 값이다.
    TYPE get(int index);	// index 번째의 item 을 리턴한다.
    String getJson(int index);	// index 번째의 item 을 JSON 문자열로 리턴한다. 옵션
    String getJsonAllItems();	// 모든 item 들을 JSON 문자열로 리턴한다. 옵션
}
