<template>
    <div id="inicio">
        <b-row>
            <b-col cols="12" :lg="admin ? '6' : '12'">
                <h3 class="text-center">Publicações</h3>
                <b-list-group>
                    <b-list-group-item 
                        v-for="publicacao of publicacoes" 
                        :key="publicacao.id">

                        <div class="d-flex w-100 justify-content-between">
                            <h5 class="mb-1">{{ publicacao.titulo }}</h5>
                            <b-button variant="danger" @click="excluirPostagem(publicacao.id)">Excluir</b-button>
                        </div>

                        <p class="mb-1">
                            {{ publicacao.conteudo }}
                        </p>

                        <small class="text-muted">{{ publicacao.tags.join(' ') }}</small>
                    </b-list-group-item>
                    <b-list-group-item 
                        v-if="publicacoes.length == 0" 
                        button disabled 
                        class="text-center">Nada a apresentar</b-list-group-item>
                </b-list-group>
            </b-col>
            <b-col v-if="admin" cols="12" lg="6">
                <h3 class="text-center">Usuários</h3>
                <b-table striped hover :items="usuarios" />
            </b-col>
        </b-row>
    </div>
</template>

<script>
import api from '../api'

export default {
    data() {
        return {
            usuario: null,
            admin: false,
            usuarios: [],
            publicacoes: []
        }
    },
    methods: {
        excluirPostagem(id) {
            
            this.$swal
                .fire({
                    title: 'Excluindo publicação, aguarde...'
                })
            this.$swal
                .showLoading()

            api.excluirPostagem(id)
                .then(() => this.buscarPublicacoes() && this.$swal.fire({
                    title: 'Sucesso!',
                    text: 'A postagem foi excluída com sucesso.',
                    icon: 'success'
                }))
                .catch(() => this.$swal.fire({
                    title: 'Erro!',
                    text: 'Não foi possível excluir a publicação.',
                    icon: 'error'
                }))
        },
        buscarPublicacoes() {

            api.buscarPublicacoesPorUsuario(this.usuario.id)
                .then(res => this.publicacoes = res.data.map(pub => ({
                    id: pub.id,
                    titulo: pub.titulo,
                    conteudo: pub.conteudo,
                    tags: pub.tags.map(tag => tag.nome)
                })))
                .catch(err => console.log(err))

            return true;
        }
    },
    created() {
        this.usuario = this.$store.getters.doneUsuario
        if(this.usuario) {
            this.admin = this.usuario.permissoes[0].titulo == 'ROLE_ADMIN'
            
            this.buscarPublicacoes()

            if (this.admin) {
                api.buscarTodosUsuarios()
                    .then(res => this.usuarios = res.data.map(usr => ({
                            id: usr.id,
                            nome: usr.nome,
                            tipo: usr.permissoes[0].titulo == 'ROLE_ADMIN' ? 'Administrador' : 'Usuário',
                            publicacoes: 0,
                            ativo: usr.ativo ? 'Sim' : 'Não'
                        })))
                    .then(() => this.usuarios.forEach(usr => {
                        api.buscarPublicacoesPorUsuario(usr.id)
                            .then(res => usr.publicacoes = res.data.length)
                    }))
            }
        }
    }
}
</script>

<style>

</style>