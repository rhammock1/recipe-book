require('dotenv').config();
import path from 'path';
import express from "express";
import compressionMiddleware from "compression";
import markoMiddleware from "@marko/express";
import db from './db';
import log from './log';
import routes from './app/routes';

const {
  PORT = 8080,
} = process.env;

const app = express();

process.on('SIGTERM', async () => {
  log.warn('Process terminate signal received.');
  try {
    express.close(); // this may not be right
    log.warn('SERVER HAS STOPPED ACCEPTING CONNECTIONS');
  } catch(e) {
    log.warn('THERE WAS AN ERROR SHUTTING DOWN', e);
  } finally {
    process.exit(0);
  }
});

const migrate = async () => {
  log.warn('Running migration script');
  try {
    await db.upgrade('./db', 'db_migrate', ['db_functions', 'db_triggers']);
  } catch(e) {
    log.error('error in migrate', e);
    await db.end();
    log.warn('Shutting down with error');
    process.exit(1);
  }
};

app
  .use(compressionMiddleware()) // Enable gzip compression for all HTTP responses.
  .use("/assets", express.static(path.join(__dirname, "dist/client"))) // Serve assets generated from webpack.
  .use(markoMiddleware()) // Enables res.marko. 
  .use(routes)
  .listen(PORT, async (err) => {
    if(err) {
      throw err;
    }

    await migrate();
    await db.load();

    if(PORT) {
      log.warn(`Listening on port ${PORT}`);
    }
  });
