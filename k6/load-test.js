import http from 'k6/http';
import { sleep, check } from 'k6';

export const options = {
  vus: 30,          // 유저 30명으로 늘려봅시다!
  duration: '30s',  
};

export default function () {
  // localhost 대신 호택님의 EC2 IP를 넣으세요
  const BASE_URL = 'http://3.35.49.11:8080/api/test';
  
  let res = http.get(`${BASE_URL}/db-read`);
  
  check(res, { 'status is 200': (r) => r.status === 200 });
  sleep(0.1);
}