<template>
    <Header/>

        <div class="container">
            <div class="col-md-6 col-md-offset-3 col-sm-12">
                <div class="card card-container">
                    
                    <p>Bem-vindo ao Jusnexo</p>
                    <p>Cadastre-se para aproveitar a plataforma</p>
                    
                    <form class="form-signin" @submit.prevent="createUser">
                        <span id="reauth-email" class="reauth-email"></span>
                        
                        <input v-model="username" type="email" id="inputEmail" class="form-control" placeholder="Email" required autofocus>
                        <input v-model="password"  type="password" id="inputPassword" class="form-control" placeholder="Senha" required>

                        <input v-model="firstname" type="text" class="form-control" placeholder="Nome" required>
                        <input v-model="lastname" type="text" class="form-control" placeholder="Sobrenome" required>
                        <input v-model="birthdate" type="date" class="form-control" placeholder="Data de Aniversário" required>
                        <input v-model="phone" type="text" class="form-control" placeholder="Celular" required>

                        <p class="form-paragraph"><input v-model="advocate" v-bind:value="true" type="checkbox"/> Sou um advogado</p>

                        <div v-if="advocate === true">
                            <textarea v-model="biography" class="form-control" rows="10" placeholder="Conte-nos um pouco sobre sua carreira (biografia)" required></textarea>

                            <div class="col-md-12">
                                <p class="form-paragraph">Área(s) de atuação</p>

                                <div class="col-md-6 col-sm-12">
                                    <p class="form-paragraph"><input value="civel" id="areasofexpertise" v-model="areasofexpertise" type="checkbox"/> Direito Cível</p>
                                </div>
                                
                                <div class="col-md-6 col-sm-12">
                                    <p class="form-paragraph"><input value="trabalhista" id="areasofexpertise" v-model="areasofexpertise" type="checkbox"/> Direito Trabalhista</p>
                                </div>

                                <div class="col-md-6 col-sm-12">
                                    <p class="form-paragraph"><input value="tributario" id="areasofexpertise" v-model="areasofexpertise" type="checkbox"/> Direito Tributário</p>
                                </div>

                                <div class="col-md-6 col-sm-12">
                                    <p class="form-paragraph"><input value="familia" id="areasofexpertise" v-model="areasofexpertise" type="checkbox"/> Direito de Família</p>
                                </div>

                                <div class="col-md-6 col-sm-12">
                                    <p class="form-paragraph"><input value="previdenciario" id="areasofexpertise" v-model="areasofexpertise" type="checkbox"/> Direito Previdenciário</p>
                                </div>

                                <div class="col-md-6 col-sm-12">
                                    <p class="form-paragraph"><input value="criminal" id="areasofexpertise" v-model="areasofexpertise" type="checkbox"/> Direito Criminal</p>
                                </div>

                            </div>

                            <input type="text" v-model="oabnumber" class="form-control" placeholder="Número da OAB" required>
                        </div>
                       
                        <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit">Criar Conta</button>
                    </form><!-- /form -->
                   
                </div><!-- /card-container -->
            </div>
        </div><!-- /container -->

    <Footer/>
</template>

<script>
import Header from '@/components/Header.vue'
import Footer from '@/components/Footer.vue'
import Swal2 from 'sweetalert2';

export default {
    name: 'signin',
    components: {
        Header,
        Footer
    },
    data() {
        return {
            username: null,
            advocate: false,
            biography: null,
            birthdate: null,
            firstname: null,
            lastname: null,
            oabnumber: null,
            areasofexpertise: [],
            phone: null,
            password: null 
        }
    },
    methods: {
        
        async createUser() {

            Swal2.fire('Efetuando cadastro... Aguarde');
            Swal2.showLoading();
            const rawResponse = await fetch('http://localhost/api/v1/users', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ 
                    username: this.username, 
                    advocate: this.advocate, 
                    biography: this.biography, 
                    birthdate: this.birthdate, 
                    firstname: this.firstname, 
                    lastname: this.lastname, 
                    oabnumber: this.oabnumber, 
                    areasofexpertise: this.areasofexpertise, 
                    phone: this.phone, 
                    password: this.password 
                })
            })

            const response = await rawResponse.json();
            Swal2.close();

            if(response.uuid) {
                localStorage.setItem('userLogged', response.uuid);
                this.$router.push({ name: 'ControlPanel' });
            } else {
                Swal2.fire(response.detail, '', 'error' )
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
    
    p {
        font-size: 16px;
    }

    .form-paragraph {
        margin-bottom: 10px; 
        text-align: left; 
        font-size: 14px;
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
        margin-top: 150px !important;
        margin-bottom: 150px !important;
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
    .form-signin input[type=date],
    .form-signin textarea,
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
