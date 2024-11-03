export default class {
  async handleSearch() {
    const query = this.getEl('search-input').value;
    if(!query) {
      return;
    }
    console.log('SEARCHING...', query);
    const response = await fetch(`/search?q=${query}`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
      },
    });
    if(!response.ok) {
      console.error('Failed to search');
      return;
    }
    const results = await response.json();
    this.emit('search-results', results);
  }
}