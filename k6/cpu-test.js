import http from 'k6/http';
import { sleep } from 'k6';

export const options = {
  stages: [
    { duration: '1m', target: 30 }, // 사용자를 30명까지 늘림
    { duration: '2m', target: 30 }, // 30명 유지 (여기서 CPU 50% 도달 확인)
    { duration: '1m', target: 0 },
  ],
};

export default function () {
  // 연산 강도를 100만으로 설정
  http.get('http://3.39.24.95:8080/api/test/cpu?range=1000000');
  sleep(0.5); // 너무 연타하면 금방 죽으니 0.5초 간격
}