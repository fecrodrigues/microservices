<template>
  <div id="login" class="home">
    <Header/>
    
    <div class="container">
        <div class="col-md-5 col-md-offset-4 col-sm-12">
          <div class="card card-container">
              <!--<img class="profile-img-card" src="//lh3.googleusercontent.com/-6V8xOA6M7BA/AAAAAAAAAAI/AAAAAAAAAAA/rzlHcD0KYwo/photo.jpg?sz=120" alt="" /> -->
              <!--<img id="profile-img" class="profile-img-card" src="//ssl.gstatic.com/accounts/ui/avatar_2x.png" />-->
              <img alt="Jusnexo logo" src="../assets/images/logo-no-text.png">
              <p>Bem-vindo ao Jusnexo</p>
              <p>Efetue login para continuar</p>
              
              <form class="form-signin" @submit.prevent="doLogin">
                  <span id="reauth-email" class="reauth-email"></span>
                  <input v-model="email" type="email" id="inputEmail" class="form-control" placeholder="Email" required autofocus>
                  <input v-model="senha"  type="password" id="inputPassword" class="form-control" placeholder="Senha" required>
          
                  <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit">Entrar</button>
              </form><!-- /form -->
              <!--<a href="#" class="forgot-password">
                  Forgot the password?
              </a>-->
          </div><!-- /card-container -->
        </div>
    </div><!-- /container -->

    <Footer footerFixed="true"/>
  </div>
</template>

<script>

import Header from '@/components/Header';
import Footer from '@/components/Footer';
import Swal2 from 'sweetalert2';

export default {
  name: 'Login',
  params: {
      titulo: 'Login'
  },
  components: {
      Header,
      Footer
  },
  data() {
    return {
      email: null,
      senha: null
    }
  },
  methods: {
        
    async doLogin() {

        var details = {
            'username': this.email,
            'password': this.senha,
            'grant_type': 'password'
        };

        var formBody = [];
            for (var property in details) {
            var encodedKey = encodeURIComponent(property);
            var encodedValue = encodeURIComponent(details[property]);
            formBody.push(encodedKey + "=" + encodedValue);
        }

        formBody = formBody.join("&");

        Swal2.fire('Efetuando login... Aguarde');
        Swal2.showLoading();
        const rawResponse = await fetch(process.env.VUE_APP_BACKEND_API + '/api/v1/auth/token', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: formBody
        })

        const response = await rawResponse.json();
        Swal2.close();

        if(response.uuid) {
            localStorage.setItem('userLogged', response.uuid);
            this.$router.push({ path: '/painel' });
        } else {
            Swal2.fire(response.detail, '', 'error')
        }
      
    }

  }
}
</script>

<style scoped>

  body, html {
      height: 100%;
      background-repeat: no-repeat;
      background-image: linear-gradient(rgb(104, 145, 162), rgb(12, 97, 33));
  }

  .card-container.card {
      /*max-width: 350px;*/
      padding: 40px 40px;
      background-color: #fff;
  }

  .btn {
      font-weight: 700;
      height: 36px;
      -moz-user-select: none;
      -webkit-user-select: none;
      user-select: none;
      cursor: default;
  }

  /*
  * Card component
  */
  .card {
      margin-top: 213px !important;
      margin-bottom: 200px !important;
      background-color: #F7F7F7;
      /* just in case there no content*/
      padding: 20px 25px 30px;
      margin: 0 auto 25px;
      /* margin-top: 50px;*/
      /* shadows and rounded borders */
      -moz-border-radius: 2px;
      -webkit-border-radius: 2px;
      border-radius: 2px;
      -moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
      -webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
      box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  }

  .profile-img-card {
      width: 96px;
      height: 96px;
      margin: 0 auto 10px;
      display: block;
      -moz-border-radius: 50%;
      -webkit-border-radius: 50%;
      border-radius: 50%;
  }

  /*
  * Form styles
  */
  .profile-name-card {
      font-size: 16px;
      font-weight: bold;
      text-align: center;
      margin: 10px 0 0;
      min-height: 1em;
  }

  .reauth-email {
      display: block;
      color: #404040;
      line-height: 2;
      margin-bottom: 10px;
      font-size: 14px;
      text-align: center;
      overflow: hidden;
      text-overflow: ellipsis;
      white-space: nowrap;
      -moz-box-sizing: border-box;
      -webkit-box-sizing: border-box;
      box-sizing: border-box;
  }

  .form-signin #inputEmail,
  .form-signin #inputPassword {
      direction: ltr;
      height: 44px;
      font-size: 16px;
  }

  .form-signin input[type=email],
  .form-signin input[type=password],
  .form-signin input[type=text],
  .form-signin button {
      width: 100%;
      display: block;
      margin-bottom: 10px;
      z-index: 1;
      position: relative;
      -moz-box-sizing: border-box;
      -webkit-box-sizing: border-box;
      box-sizing: border-box;
  }

  .form-signin .form-control:focus {
      border-color: rgb(104, 145, 162);
      outline: 0;
      -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgb(104, 145, 162);
      box-shadow: inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgb(104, 145, 162);
  }

  .btn.btn-signin {
      /*background-color: #4d90fe; */
      background-color: rgb(104, 145, 162);
      /* background-color: linear-gradient(rgb(104, 145, 162), rgb(12, 97, 33));*/
      padding: 0px;
      font-weight: 700;
      font-size: 14px;
      height: 36px;
      -moz-border-radius: 3px;
      -webkit-border-radius: 3px;
      border-radius: 3px;
      border: none;
      -o-transition: all 0.218s;
      -moz-transition: all 0.218s;
      -webkit-transition: all 0.218s;
      transition: all 0.218s;
  }

  .btn.btn-signin:hover,
  .btn.btn-signin:active,
  .btn.btn-signin:focus {
      background-color: rgb(12, 97, 33);
  }

  .forgot-password {
      color: rgb(104, 145, 162);
  }

  .forgot-password:hover,
  .forgot-password:active,
  .forgot-password:focus{
      color: rgb(12, 97, 33);
  }
</style>

<style scoped>

  .home {
    height: 100%;
    display: flex;
    justify-content: center;
    align-content: center;
    align-items: center;
    flex-direction: column;
  }

</style>