<template>
    <div id="adicionar-postagem">
        <b-form @submit="adicionarPostagem">
            <h3 class="text-center">Nova Postagem</h3>

            <b-form-group
                label="Título"
                label-for="input-titulo">
                <b-form-input
                    id="input-titulo"
                    v-model="postagem.titulo"
                    placeholder="Qual o título da postagem?"/>
            </b-form-group>

            <b-form-group
                label="Conteúdo"
                label-for="input-conteudo">
                <b-form-textarea
                    id="input-conteudo"
                    v-model="postagem.conteudo"
                    rows="8"
                    placeholder="Qual o conteúdo da postagem?"/>
            </b-form-group>

            <b-form-group
                label="Tags"
                label-for="input-tags"
                description="Separe cada tag por espaço.">
                <b-form-input
                    id="input-tags"
                    v-model="tags"
                    placeholder="Quer adicionar tags a ela?"/>
            </b-form-group>

            <b-button type="submit" variant="success">Adicionar</b-button>
        </b-form>
    </div>
</template>

<script>
import api from '../../api'

export default {
    data() {
        return {
            postagem: {
                titulo: '',
                conteudo: '',
                usuario: this.$store.getters.doneUsuario,
                tags: []
            },
            tags: ''
        }
    },
    methods: {
        adicionarPostagem (e) {
            e.preventDefault()

            this.postagem.tags = this.tags.split(' ').map(tag => ({
                nome: (tag.startsWith('#')) ? tag : '#' + tag
            }))

            this.$swal
                .fire({
                    title: 'Adicionando postagem, aguarde...'
                })
            this.$swal
                .showLoading()
            
            api.adicionarPostagem(this.postagem)
                .then(() => this.resetPostagem() && this.$swal.fire({
                    title: 'Sucesso!',
                    text: 'Postagem foi adicionada com sucesso.',
                    icon: 'success'
                }))
                .catch(() => this.$swal.fire({
                    title: 'Erro!',
                    text: 'Erro ao enviar postagem, tente novamente.',
                    icon: 'error'
                }))
        },
        resetPostagem() {
            this.postagem = {
                titulo: '',
                conteudo: '',
                usuario: this.$store.getters.doneUsuario,
                tags: []
            }
            this.tags = '';

            return true;
        }
    }
}
</script>

<style>

</style>