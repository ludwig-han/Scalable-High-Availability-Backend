import http from 'k6/http';
import { sleep } from 'k6';

export const options = {
  stages: [
    { duration: '1m', target: 50 },
    { duration: '2m', target: 50 },
    { duration: '1m', target: 0 },
  ],
};

export default function () {
  http.get('http://3.39.24.95:8080/api/test/db');
  sleep(1);
}