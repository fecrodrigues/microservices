<template>

    <div class="container">
        <div class="col-md-6 col-md-offset-3 col-sm-12">
            <div class="card card-container">
            
                <p>Aqui você pode editar os seus dados</p>
                
                <form class="form-signin" @submit.prevent="editUser">
                    <span id="reauth-email" class="reauth-email"></span>
                    
                    <input v-model="userEdited.username" type="email" id="inputEmail" class="form-control" placeholder="Email" required autofocus>
                    <input v-model="userEdited.password" type="password" id="inputPassword" class="form-control" placeholder="Senha" required>

                    <input v-model="userEdited.firstname" type="text" class="form-control" placeholder="Nome" required>
                    <input v-model="userEdited.lastname" type="text" class="form-control" placeholder="Sobrenome" required>
                    <input v-model="userEdited.birthdate" type="date" class="form-control" placeholder="Data de Aniversário" required>
                    <input v-model="userEdited.phone" type="text" class="form-control" placeholder="Celular" required>

                    <p class="form-paragraph"><input v-model="userEdited.advocate" v-bind:value="true" type="checkbox"/> Sou um advogado</p>

                    <div v-if="userEdited?.advocate">

                        <textarea v-model="userEdited.biography" class="form-control" rows="10" placeholder="Conte-nos um pouco sobre sua carreira (biografia)" required></textarea>

                        <div class="col-md-12">
                            <p class="form-paragraph">Área(s) de atuação</p>

                            <div class="col-md-6 col-sm-12">
                                <p class="form-paragraph"><input value="civel" id="areasofexpertise" v-model="userEdited.areasofexpertise" type="checkbox"/> Direito Cível</p>
                            </div>
                            
                            <div class="col-md-6 col-sm-12">
                                <p class="form-paragraph"><input value="trabalhista" id="areasofexpertise" v-model="userEdited.areasofexpertise" type="checkbox"/> Direito Trabalhista</p>
                            </div>

                            <div class="col-md-6 col-sm-12">
                                <p class="form-paragraph"><input value="tributario" id="areasofexpertise" v-model="userEdited.areasofexpertise" type="checkbox"/> Direito Tributário</p>
                            </div>

                            <div class="col-md-6 col-sm-12">
                                <p class="form-paragraph"><input value="familia" id="areasofexpertise" v-model="userEdited.areasofexpertise" type="checkbox"/> Direito de Família</p>
                            </div>

                            <div class="col-md-6 col-sm-12">
                                <p class="form-paragraph"><input value="previdenciario" id="areasofexpertise" v-model="userEdited.areasofexpertise" type="checkbox"/> Direito Previdenciário</p>
                            </div>

                            <div class="col-md-6 col-sm-12">
                                <p class="form-paragraph"><input value="criminal" id="areasofexpertise" v-model="userEdited.areasofexpertise" type="checkbox"/> Direito Criminal</p>
                            </div>

                        </div>

                        <input v-model="userEdited.oabnumber" type="text" class="form-control" placeholder="Número da OAB" required>
                    </div>
                    
                    <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit">Atualizar meus dados</button>
                    <button @click="deleteUser" class="btn btn-danger btn-block" type="button">Exlcuir minha conta</button>
                </form><!-- /form -->
                
            </div><!-- /card-container -->
        </div>
    </div><!-- /container -->


</template>

<script>
import Swal2 from 'sweetalert2';

export default {
    name: 'MyProfle',
    props: {
        userInfo: Object,
        uuid: String
    },
    data() {
        return {
           userEdited: {}
        }
    },
    watch: {
        userInfo() {
            this.userEdited = Object.assign({}, this.userInfo);
        } 
    },
    methods: {

        async editUser() {
            let infoToEdit = Object.assign({}, this.userEdited);
            infoToEdit.areasofexpertise = Object.assign([], infoToEdit.areasofexpertise);

            const userUuid = infoToEdit.uuid;
            delete infoToEdit.uuid;

            Swal2.fire('Efetuando atualização cadastral... Aguarde');
            Swal2.showLoading();
            const rawResponse = await fetch('http://localhost/api/v1/users/' + userUuid, {
                method: 'PUT',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(infoToEdit)
            })

            const response = await rawResponse.json();
            Swal2.close();

            if(response.uuid) {
                Swal2.fire('Informações alteradas com sucesso', '', 'success' )
            } else {
                Swal2.fire(response.detail, '', 'error' )
            }
            
            console.log(response, 'response')
        },

        deleteUser() {

            const userInfo = Object.assign({}, this.userInfo);

            Swal2.fire({
                title: 'Você tem certeza?',
                text: "Você não podera voltar atrás!",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Sim, excluir!',
                cancelButtonText: 'Cancelar'
            }).then(async (result) => {
                if (result.isConfirmed) {

                    Swal2.fire('Efetuando exclusão da conta... Aguarde');
                    Swal2.showLoading();
                    const rawResponse = await fetch('http://localhost/api/v1/users/' + userInfo.uuid, {
                        method: 'DELETE'
                    });

                    const response = await rawResponse.json();
                    Swal2.close();

                    if(response.message) {
                        localStorage.removeItem('userLogged');
                        this.$router.push({ name: 'Home' })

                        Swal2.fire(
                            'Conta exluida com sucesso!',
                            '',
                            'success'
                        )
                    } else {
                        Swal2.fire(
                            'Não foi possivel excluir a conta!',
                            '',
                            'error'
                        )
                    }                   
                
                }
            })
            
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
        margin-top: 20px !important;
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

