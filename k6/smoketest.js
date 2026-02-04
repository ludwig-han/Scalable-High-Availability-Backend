import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
  vus: 3, // Key for Smoke test. Keep it at 2, 3, max 5 VUs
  duration: '30s', // This can be shorter or just a few iterations
};

export default () => {
  const urlRes = http.get('http://43.201.65.156:8080/home');
  check(urlRes, { 'status returned 200': (r) => r.status == 200 })
  sleep(1);
  // MORE STEPS
  // Here you can have more steps or complex script
  // Step1
  // Step2
  // etc.
};