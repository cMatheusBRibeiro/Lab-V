<template>
    <div id="app">
        <header v-if="usuario">
            <b-navbar toggleable type="dark" variant="dark">
                <b-navbar-brand href="#">Blog</b-navbar-brand>

                <b-navbar-toggle target="navbar-toggle-collapse">
                    <template #default="{ expanded }">
                        <b-icon v-if="expanded" icon="chevron-bar-up"></b-icon>
                        <b-icon v-else icon="chevron-bar-down"></b-icon>
                    </template>
                </b-navbar-toggle>

                <b-collapse id="navbar-toggle-collapse" is-nav>
                    <b-navbar-nav class="ml-auto">
                        <b-nav-item href="#">Link 1</b-nav-item>
                        <b-nav-item href="#">Link 2</b-nav-item>
                        <b-nav-item href="#" disabled>Disabled</b-nav-item>
                    </b-navbar-nav>
                </b-collapse>
            </b-navbar>
        </header>
        <main class="container">
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
    created() {
        setInterval(() => {
            if (this.$store.getters.doneUsuario.id != undefined)
                this.usuario = this.$store.getters.doneUsuario
        }, 500)
    }
}
</script>