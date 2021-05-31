<template>
    <div id="inicio">
        <b-row>
            <b-col cols="12" :lg="admin ? '6' : '12'">
                <h3 class="text-center">Publicações</h3>
                <b-list-group>
                    <b-list-group-item 
                        v-for="publicacao of publicacoes" 
                        :key="publicacao.id" button
                        class="text-center">{{ publicacao.titulo }}</b-list-group-item>
                    <b-list-group-item 
                        v-if="publicacoes.length == 0" 
                        button disabled 
                        class="text-center">Nada a apresentar</b-list-group-item>
                </b-list-group>
            </b-col>
            <b-col v-if="admin" cols="12" lg="6">
                <h3 class="text-center">Usuários</h3>
                <b-table striped hover :items="usuarios"></b-table>
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
    created() {
        this.usuario = this.$store.getters.doneUsuario
        this.admin = this.usuario.permissoes[0].titulo == 'ROLE_ADMIN'
        
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
</script>

<style>

</style>