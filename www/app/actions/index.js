import db from '../../db';

const search = async (query) => {
  console.log('SEARCH QUERY: ', query);
  const {rows: [{go_search: results}]} = await db.file('db/search/go_search.sql', {query});
  return results;
};

export {search};