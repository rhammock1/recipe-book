import moment from 'moment';

const LOG_LEVELS = {
  error: 0,
  warn: 1,
  info: 2,
  verbose: 3,
  debug: 4,
  silly: 5
};
const ENV_LOG_LEVEL = LOG_LEVELS[process.env.LOG_LEVEL];

const logger = (level, ...params) => {
  if(LOG_LEVELS[level] > ENV_LOG_LEVEL) {
    return;
  }
  const timestamp = moment().toISOString(); // UTC timestamp
  console.log(level, timestamp, ...params);
};

const log = {
  error: (...params) => logger('error', ...params),
  warn: (...params) => logger('warn', ...params),
  info: (...params) => logger('info', ...params),
  verbose: (...params) => logger('verbose', ...params),
  debug: (...params) => logger('debug', ...params),
  silly: (...params) => logger('silly', ...params),
};

export default log;