import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
  stages: [
    { duration: '30s', target: 50 },  // 30초 동안 동시 사용자 0 -> 50명 점진적 증가
    { duration: '1m', target: 100 },  // 1분 동안 동시 사용자 50 -> 100명 증가
    { duration: '1m', target: 200 },  // 1분 동안 동시 사용자 100 -> 200명 피크 몰아치기
    { duration: '30s', target: 0 },   // 30초 동안 안정적으로 유저 아웃 (다운그레이드)
  ],
  thresholds: {
    http_req_failed: ['rate<0.01'],   // 에러율 1% 미만 유지 조건 (논문 지표용 가이드라인)
    http_req_duration: ['p(95)<2000'], // 95%의 요청은 2초 이내에 응답해야 성공으로 간주
  },
};

export default function () {
  // 30만 건의 문자열을 사정없이 풀 스캔하도록 와일드카드(?keyword=test) 쿼리 전송
  // 오늘 바뀐 새로운 1번 서버 IP로 갱신해 두었습니다.
  const res = http.get('http://3.38.216.87:8080/api/posts?keyword=test');

  // 정상적으로 200 OK 응답을 뱉어내는지 검증
  check(res, {
    'status is 200': (r) => r.status === 200,
  });

  // 유저들의 실제 행동 패턴(Think time)과 가벼운 부하 분산을 위해 0.1초씩 짧은 휴식 부여
  sleep(0.1);
}