import http from 'k6/http';
import { check, sleep } from 'k6';
import { SharedArray } from 'k6/data';

// ---- CONFIG ----
export const options = {
  scenarios: {
    load_test: {
      executor: 'ramping-vus',
      startVUs: 0,
      stages: [
        { duration: '30s', target: 10 },
        { duration: '1m', target: 20 },
        { duration: '1m', target: 40 },
        { duration: '10s', target: 0 },
      ],
    },
  },
  thresholds: {
    http_req_duration: ['p(95)<50'], // 95% under 50ms
    http_req_failed: ['rate<0.01'],   // <1% errors
  },
};

// ---- TEST DATA ----
const movieIds = new SharedArray('movie IDs', function () {
  const file = open('./movie_ids.csv');
  return file
    .split('\r\n')
    //.slice(1) // skip header
    .filter(line => line.trim() !== '')
    .map(line => {
      return line;
    });
});

// ---- HELPERS ----
function randomMovieId() {
  return movieIds[Math.floor(Math.random() * movieIds.length)];
}

function randomUsername() {
  return `user_${Math.random().toString(36).substring(2, 8)}`;
}

function randomRating() {
  return Math.floor(Math.random() * 10) + 1; // 1–10
}

function randomComment() {
  const comments = [
    'Great movie!',
    'Not bad',
    'Could be better',
    'Loved it',
    'Terrible experience',
    'Amazing visuals',
    'Story was weak',
    'Highly recommended',
  ];
  return comments[Math.floor(Math.random() * comments.length)];
}

// ---- TEST EXECUTION ----
export default function () {
  const url = 'http://mdb-gateway:8080/api/movie-reviews';

  const payload = JSON.stringify({
    movieId: randomMovieId(),
    username: randomUsername(),
    rating: randomRating(),
    comment: randomComment(),
  });

  const params = {
    headers: {
      'Content-Type': 'application/json',
    },
  };

  http.post(url, payload, params);
}
