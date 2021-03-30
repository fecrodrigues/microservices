<template>
    <Header/>
    
    <div class="content" style="min-height: 100%">

        <div class="container">

            <div class="row">
                <div class="welcome-text">
                    <p>Bem vindo, {{userInfo?.firstname}}</p>
                    
                    <p v-if="!userInfo?.advocate">A partir dessa tela vc consegue gerenciar sua conta e encontrar profissionais cadastrados</p>
                    <p v-if="userInfo?.advocate">A partir dessa tela vc consegue gerenciar seus dados cadastrais e futuramente acompanhar e atualizar seus processos</p>

                </div>
                <!--<input type="text" class="form-control" placeholder="Busque por um profissional que possa te ajudar" />-->
            </div>

            <ul class="nav nav-pills">
                <li v-if="!userInfo?.advocate" class="nav-item active">
                    <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">Encontrar Profissionais</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" id="perfil-tab" data-toggle="tab" href="#perfil" role="tab" aria-controls="perfil" aria-selected="true">Meu Perfil</a>
                </li>
            </ul>

            <div class="tab-content" id="myTabContent">
                
                <div class="tab-pane fade show active fade in" id="home" role="tabpanel" aria-labelledby="home-tab">
                        
                    <div v-if="lawyers.length > 0" class="row lawyers-list">
                        
                        <div v-for="lawyer in lawyers" :key="lawyer.uuid"  class="col-md-3">
                                <div class="card">
                                    <!--<img class="profile-img-card" src="//lh3.googleusercontent.com/-6V8xOA6M7BA/AAAAAAAAAAI/AAAAAAAAAAA/rzlHcD0KYwo/photo.jpg?sz=120" alt="" /> -->
                                    <img id="profile-img" class="profile-img-card" src="//ssl.gstatic.com/accounts/ui/avatar_2x.png" />

                                    <div class="card-body">
                                        <p><strong>Nome: </strong> {{lawyer.firstname}} {{lawyer.lastname}}</p>
                                        <p><strong>Áreas de atuação:</strong> {{ getAreasOfExpertise(lawyer.areasofexpertise).join(',') }} </p>

                                        <router-link :to="{ name: 'LawyerProfile', query: { idAdv: lawyer.uuid }}" class="btn btn-primary">Ver Perfil</router-link>
                                    </div>
                                    
                                  
                                </div><!-- /card-container -->
                        </div>

                    </div>

                     <div v-if="lawyers.length === 0" class="row lawyers-list">
                        <p>Nenhum Advogado encontrado</p>
                     </div>

                </div>

                <div class="tab-pane fade" id="perfil" role="tabpanel" aria-labelledby="perfil-tab">
                    <MyProfile v-bind:userInfo="userInfo"/>
                </div>

            </div>

        </div>

    </div>


    <Footer footerFixed="true"/>
</template>

<script>
import Header from '@/components/Header.vue'
import Footer from '@/components/Footer.vue'
import MyProfile from '@/components/MyProfile.vue'

export default {
    async created() {
        const userID = localStorage.getItem('userLogged');

        let rawResponse = await fetch('/api/v1/users/' + userID);
        let rawResponseList = await fetch('/api/v1/users?limit=999&advocate=true');

        const userInfo = await rawResponse.json();
        const lawyers = await rawResponseList.json();

        this.userInfo = userInfo;
        this.lawyers = lawyers;
        
    },
    name: 'ControlPanel',
    components: {
        Header,
        Footer,
        MyProfile
    },
    methods: {
        getAreasOfExpertise(areasofexpertise) {
            if(areasofexpertise) {
                return areasofexpertise.map((area) => {
                    switch(area) {
                        case 'civel':
                            return 'Direito Cível';
                        case 'trabalhista':
                            return 'Direito Trabalhista';
                        case 'tributario':
                            return 'Direito Tributário';
                        case 'familia':
                            return 'Direito de Família';
                        case 'previdenciario':
                            return 'Direito Previdenciário';
                        case 'criminal':
                            return 'Direito Criminal';
                        default:
                            return '';
                    }
                })
            } else {
                return [];
            }
            
        }
    },
    data() {
        return {
            userInfo: null,
            lawyers: []
        }
    }
}
</script>

<style scoped>
    .nav-pills > li.active > a, .nav-pills > li.active > a:focus, .nav-pills > li.active > a:hover {
        color: black;
        background-color: #f0f0f0;
    }

    .wrap {
        min-height: 100%;
    }

    .card {
        padding: 20px;
    }

    .card-body {
        margin-top: 5px;
    }

    .content {
       margin-top: 100px;
    }

    .lawyers-list {
        padding: 30px;
    }

    .welcome-text {
        margin-top: 40px;
        margin-bottom: 40px;
    }

    .tab-pane:not(.active) {
        display: none !important;
    }
</style>
