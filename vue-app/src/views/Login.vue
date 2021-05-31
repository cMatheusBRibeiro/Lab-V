<template>
    <div id="login">
        <app-box>
            <h3 class="text-center border-bottom pb-3">
                <em>LOGIN</em>
            </h3>
            <b-form @submit="login">
                <b-form-group
                    label="Usuário"
                    label-for="input-user">
                    <b-form-input
                        id="input-user"
                        v-model="formulario.login"/>
                </b-form-group>
                <b-form-group
                    label="Senha"
                    label-for="input-password"
                    class="mb-0">
                    <b-form-input
                        id="input-password"
                        :type="(mostrarSenha) ? 'text' : 'password'"
                        v-model="formulario.password"/>
                </b-form-group>
                <b-form-group>
                    <b-form-checkbox
                        v-model="mostrarSenha">
                        Mostrar senha
                    </b-form-checkbox>
                </b-form-group>

                <b-button block variant="success" type="submit">Entrar</b-button>

                <b-row class="d-flex align-items-center py-2">
                    <b-col cols="5">
                        <hr class="border border-secondary">
                    </b-col>
                    <b-col class="text-center">
                        OU
                    </b-col>
                    <b-col cols="5">
                        <hr class="border border-secondary">
                    </b-col>
                </b-row>

                <b-link to="novo-usuario" class="btn btn-primary btn-block">Cadastre-se</b-link>
            </b-form>
        </app-box>
    </div>
</template>

<script>
import api from '../api'
import AppBox from '../components/shared/AppBox'

export default {
    components: {
        AppBox
    },
    data() {
        return {
            formulario: {
                login: '',
                password: ''
            },
            mostrarSenha: false
        }
    },
    methods: {
        login(e) {
            e.preventDefault()

            this.$swal.fire({
                title: 'Enviando credenciais, aguarde...'
            })
            this.$swal.showLoading()

            api.login(this.formulario)
                .then((res) => {
                    this.$store.dispatch('setDadosLogin', res.data)
                    this.$router.push('inicio')
                    this.$swal.fire({
                        title: 'Sucesso!',
                        text: 'Login efetuado com sucesso.',
                        icon: 'success'
                    })
                })
                .catch(err => {
                    this.$swal.fire({
                        title: 'Erro!',
                        text: this.tratarErro(err.response.status),
                        icon: 'error'
                    })
                })
        },
        tratarErro(status) {
            let erros = {
                403: 'Usuário ou senha inválidos.'
            }
            return erros[status]
        }
    }
}
</script>

<style lang="scss" scoped>
#login {
    width: 400px;
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);
}
</style>