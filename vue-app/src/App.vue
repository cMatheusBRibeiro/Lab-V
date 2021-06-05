<template>
    <div id="app">
        <header v-if="usuario">
            <b-navbar toggleable="lg" type="dark" :variant="usuario.permissoes[0].titulo == 'ROLE_ADMIN' ? 'dark' : 'danger'">
                <b-navbar-brand to="/inicio">Blog</b-navbar-brand>

                <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

                <b-collapse id="nav-collapse" is-nav>
                    <b-navbar-nav>
                        <b-nav-item to="/inicio">Inicio</b-nav-item>
                        <b-nav-item-dropdown text="Adicionar">
                            <b-dropdown-item to="/adicionar/postagem">Postagem</b-dropdown-item>
                        </b-nav-item-dropdown>
                    </b-navbar-nav>

                    <!-- Right aligned nav items -->
                    <b-navbar-nav class="ml-auto">
                        <b-nav-item to="/perfil">Perfil</b-nav-item>
                        <b-nav-item @click="logout">Sair</b-nav-item>
                    </b-navbar-nav>
                </b-collapse>
            </b-navbar>
        </header>
        <main class="container mt-4">
            <router-view/>
        </main>
    </div>
</template>

<script>
export default {
    data() {
        return {
            usuario: null
        }
    },
    methods: {
        logout: function() {
            this.$store.dispatch('removerDadosLogin')
            this.$router.push('login')
        }
    },
    created() {
        setInterval(() => {
            this.usuario = this.$store.getters.doneUsuario
            if (this.usuario == null) {

                if(this.$router.currentRoute.path == '/login' || this.$router.currentRoute.path == '/novo-usuario')
                    return
                else
                    this.$router.push('/login')
            }
            else {

                if(this.$router.currentRoute.path == '/login' || this.$router.currentRoute.path == '/novo-usuario')
                    this.$router.push('/inicio')

            }
        }, 100)
    }
}
</script>