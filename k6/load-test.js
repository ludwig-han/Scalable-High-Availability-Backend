import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
  stages: [
    { duration: '1m', target: 100 }, // 1분 동안 VU 100명까지 증가
    { duration: '3m', target: 400 }, // 3분 동안 VU 400명으로 서버 완전히 조지기 (끝나면 바로 종료)
  ],
};

export default function () {
  const res = http.get('http://3.36.47.190:8080/api/v1/search?keyword=패키지');
  check(res, { 'status is 200': (r) => r.status === 200 });
  sleep(0.1); 
}