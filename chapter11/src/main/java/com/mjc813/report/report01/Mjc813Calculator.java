package com.mjc813.report.report01;

public class Mjc813Calculator {
    /**
     * arrs 매개변수가 null 이면 0 을 리턴.
     * arrs 배열의 원소가 null 이면 그 원소는 계산에서 제외된다.
     * @param arrs
     * @return
     */
    public Long strongSum( Integer ... arrs ) {
        if ( arrs == null ) {
            return 0L;
        }
        Long result = 0L;
        for ( Integer num : arrs ) {
//			if ( num != null ) {
//				result += num;
//			}
            try {
                result += num;
            } catch (NullPointerException ex) {
                System.err.println(ex.getMessage());
            }
        }
        return result;
    }

    public Long sum( Integer ... arrs ) throws NullPointerException {
        if ( arrs == null ) {
            throw new NullPointerException("arrs is null");
        }
        Long result = 0L;
        // arrs 값들의 모든 합을 구해야 합니다.
        // 다만 arrs 배열이 null 일 수 있으므로 예외처리를 이곳이든 이곳을 호출하는 메소드에서든 꼭 해야 합니다.
        for ( Integer n : arrs ) {
            result += n;
        }
        return result;
    }

    public Long sum( Integer[] arrs, Integer start, Integer end) throws NullPointerException, ArrayIndexOutOfBoundsException {
        if ( arrs == null ) {
            throw new NullPointerException("arrs is null");
        }
        if ( start == null ) {
            throw new NullPointerException("start is null");
        }
        if ( end == null ) {
            throw new NullPointerException("end is null");
        }
        Long result = 0L;
        // arrs 배열중에서 start ~ end 인덱스 까지의 원소들의 모든 합을 구해야 합니다.
        // 다만 arrs 배열이 null 일 수 도 있고, 원소가 null 일 수도 있고
        // 배열 인덱스 start ~ end 가 문제가 생길 수 도 있습니다.
        // 그러므로 예외처리를 꼭 해야 합니다.
        for ( int i = start; i <= end; i++ ) {
            result += arrs[i];
        }
        return result;
    }
}
