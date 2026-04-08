import http from 'k6/http';
import { sleep, check } from 'k6';

export const options = {
  // 부하를 점진적으로 늘려서 한계점을 찾습니다
  stages: [
    { duration: '30s', target: 50 },   // 워밍업: 50명
    { duration: '30s', target: 100 },  // 중간: 100명
    { duration: '30s', target: 150 },  // 압박: 150명
    { duration: '30s', target: 0 },    // 종료
  ],
};

export default function () {
  const BASE_URL = 'http://15.165.41.85:8080/api/test';

  const res = http.get(`${BASE_URL}/io-wait`, { timeout: '10s' });

  check(res, {
    'status is 200': (r) => r.status === 200,
    'response time < 2s': (r) => r.timings.duration < 2000,
  });

  sleep(0.5);
}