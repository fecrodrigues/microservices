<template>
     <!-- NavigationBar Section -->
  <nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
      
      <!-- Brand and toggle get grouped for better mobile display -->
      <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#main-navbar" aria-expanded="false">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <router-link class="navbar-brand" to="/">
          <img class="img-responsive" src="assets/images/logo.png" style="margin-top: -10px;" alt="logo">
        </router-link>
      </div>

      <!-- Collect the nav links, forms, and other content for toggling -->
      <div class="collapse navbar-collapse" id="main-navbar">
        
        <ul v-if="!loggedIn" class="nav navbar-nav navbar-right">
            <li class="active"><router-link to="/">Inicio</router-link></li>
            <li><router-link to="/cadastro">Cadastre-se</router-link></li>
            <li><router-link to="/login">Entrar</router-link></li>
        </ul>

        <ul v-if="loggedIn" class="nav navbar-nav navbar-right">
            <li class="active"><router-link to="/">Inicio</router-link></li>
            <li><router-link to="/painel">Painel</router-link></li>
            <li><router-link @click="logout" to="/">Sair</router-link></li>
        </ul>
      </div><!-- /.navbar-collapse -->

    </div><!-- /.container-fluid -->
  </nav>

</template>

<script>
export default {
    name: 'Header',
    data() {
      return {
        loggedIn: localStorage.getItem('userLogged')? true: false
      }
    },
    methods: {

      logout() {
        localStorage.removeItem('userLogged');
      }

    }
}
</script>
