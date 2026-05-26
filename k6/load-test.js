import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
  stages: [
    { duration: '1m', target: 100 }, // 1분 동안 VU 100명까지 증가
    { duration: '3m', target: 400 }, // 3분 동안 VU 400명으로 서버 완전히 조지기 (끝나면 바로 종료)
  ],
};

export default function () {
  // 1부터 100000 사이의 무작위 숫자를 생성하여 매번 다른 키워드를 조회하게 만듭니다.
  // 예: Item_Name_54321, Item_Name_12
  const randomNum = Math.floor(Math.random() * 10000) + 1;
  const url = `http://172.31.51.155:80/api/v1/search?keyword=${randomNum}`;
  
  const res = http.get(url);
  
  check(res, {
    'status is 200': (r) => r.status === 200,
  });
}