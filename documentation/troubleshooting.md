# RestAPI json 응답 Test 캐스팅 문제
* 상황: DB에 저장된 Long형태 id 유효성 검사에서 캐스팅 문제 발생
* 문제: Long타입이지만 json으로 파싱하면 int형으로 자동 캐스팅
![](./imgs/troubleshoot/missing_type.png)

* 해결: String타입으로 변환 후 Long타입으로 캐스팅