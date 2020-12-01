import axios from 'axios'

const ENTRY_API_URL = 'http://localhost:8080'

class EntryDataService {

    retrieveAllEntries(name) {
        console.log('executed service')
        return axios.get(`${ENTRY_API_URL}/entries`);
    }

    retrieveEntries(id) {
        console.log('executed service')
        return axios.get(`${ENTRY_API_URL}/entries/${id}`);
    }

    deleteEntries(id) {
        console.log('executed service')
        return axios.delete(`${ENTRY_API_URL}/entries/${id}`);
    }

    updateEntries(id, entry) {
        console.log('executed update service')
        return axios.put(`${ENTRY_API_URL}/entries/${id}`, entry);
    }

    createEntries(entry) {
        console.log('executed create service')
        return axios.post(`${ENTRY_API_URL}/entries`, entry);
    }
    uploadImage(id, data) {
        console.log('executed create service')
        return axios.post(`${ENTRY_API_URL}/upload/${id}`, data);
    }
}

export default new EntryDataService()