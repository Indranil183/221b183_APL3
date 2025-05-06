const express = require('express');
const app = express();
const yargs = require('yargs');
const axios = require('axios');

app.use(express.json());

const argv = yargs.argv;
const port = argv.port;
const fellows = argv.fellows ? argv.fellows.split(',').map(Number) : [];

let electionTimeout = null;
let heartBeatInterval = null;
let role = 'follower'; // default role
let currentTerm = 0; // initialize term
let voteReceived = 0;
let votedFor = null;

function resetElectionTimeout() {
  if (electionTimeout) clearTimeout(electionTimeout);
  // Random election timeout between 500ms to 800ms
  const timer = Math.floor(Math.random() * 300) + 500;
  electionTimeout = setTimeout(conductElection, timer);
}

async function conductElection() {
  role = 'candidate';
  currentTerm++; // increment term as we are starting the next election
  votedFor = port;
  voteReceived = 1;

  resetElectionTimeout(); // reset election timeout after starting election

  // Send requests to fellows for votes
  for (let fellow of fellows) {
    try {
      const res = await axios.post(`http://localhost:${fellow}/start-election`, {
        term: currentTerm,
        candidateId: port,
      });

      if (res.data.voteGranted) {
        voteReceived++;
        if (voteReceived > (fellows.length + 1) / 2) {
          becomeLeader();
        }
      }
    } catch (err) {
      console.log(`Failed to contact fellow ${fellow}:`, err.message);
    }
  }
}

function becomeLeader() {
  if (role !== 'candidate') return;

  role = 'leader';
  console.log(`${port} becomes the leader`);

  // Stop contesting, as we've become the leader
  if (electionTimeout) clearTimeout(electionTimeout);

  if (heartBeatInterval) clearInterval(heartBeatInterval);

  // Send heartbeats to fellow nodes every 100ms
  heartBeatInterval = setInterval(() => {
    for (const fellow of fellows) {
      axios.post(`http://localhost:${fellow}/heartbeat`, {
        leader: port,
        term: currentTerm,
      }).catch(err => {
        console.log(`[${port}] heartbeat to ${fellow} failed:`, err.message);
      });
    }
  }, 100);
}

app.post('/start-election', (req, res) => {
  const { term, candidateId } = req.body;
  let voteGranted = false;

  if (term > currentTerm) {
    currentTerm = term;
    role = 'follower';
    votedFor = null;
  }

  if (term === currentTerm && (votedFor === null || votedFor === candidateId)) {
    votedFor = candidateId;
    voteGranted = true;
    resetElectionTimeout(); // reset election timeout as we're now a follower of the candidate
  }

  res.json({ voteGranted });
});

app.post('/heartbeat', (req, res) => {
  const { leader, term } = req.body;
  console.log(`Received heartbeat from ${leader}`);

  if (term > currentTerm) {
    currentTerm = term;
    role = 'follower';
    votedFor = null;
    resetElectionTimeout(); // don't participate in election, assume the new leader
  } else if (term === currentTerm && role !== 'leader') {
    resetElectionTimeout(); // Reset timeout if heartbeat is from current leader
  }

  res.status(200).send();
});

app.listen(port, (err) => {
  if (err) {
    console.log(err);
  } else {
    console.log(`[${port}] with fellows ${fellows}`);
    resetElectionTimeout(); // start election process if no leader is selected
  }
});
