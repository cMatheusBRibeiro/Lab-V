import axios from 'axios'
import store from '../store'

export default {
    buscarTodosUsuarios() {
        return this.get('/usuario')
    },
    login(dados) {
        return this.post('/login', dados)
    },
    cadastrar(dados) {
        return this.post('/usuario', dados)
    },
    buscarPublicacoesPorUsuario(id) {
        return this.get('/usuario/' + id + '/publicacao')
    },
    post(rota, dados) {
        return axios.post(rota, dados, (store.getters.doneToken) ? {
            headers: {
                'Authorization': store.getters.doneToken
            }
        } : null)
    },
    get(rota) {
        return axios.get(rota, (store.getters.doneToken) ? {
            headers: {
                'Authorization': store.getters.doneToken
            }
        } : null)
    }
};