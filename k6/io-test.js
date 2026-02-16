import http from 'k6/http';
import { sleep } from 'k6';

export const options = {
  stages: [
    { duration: '1m', target: 100 }, // I/O는 동시 접속자가 많아야 티가 남
    { duration: '2m', target: 200 }, // 200명까지 늘려봄
    { duration: '1m', target: 0 },
  ],
};

export default function () {
  http.get('http://3.39.24.95:8080/api/test/io');
  sleep(1);
}