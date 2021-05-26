import axios from 'axios'

const API_URL = 'https://8080-olive-puma-1rmolp82.ws-us07.gitpod.io/' + 'blog/' // URL Application + Base Path

export default {
    getUsers() {
        return axios.get(API_URL + 'usuario')
    },
    login(dados) {
        return axios.post(API_URL + 'login', dados)
    }
};