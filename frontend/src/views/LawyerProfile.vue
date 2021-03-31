<template>
    <Header />
    
    <div class="content">
        <h2>Perfil do advogado</h2>
        <div class="container adv-profile">
            <div class="row align-left">
                
                <div class="profile-info">
                    <div class="col-md-3">
                        <img id="profile-img" class="profile-img-card" src="//ssl.gstatic.com/accounts/ui/avatar_2x.png" />
                    </div>

                    <div class="col-md-6 profile-data">
                        <p><strong>Nome:</strong> {{lawyer?.firstname}} {{lawyer?.lastname}}</p>
                        <p><strong>Áreas de atuação:</strong> {{getAreasOfExpertise(lawyer?.areasofexpertise).join(',')}} </p>
                        <p><strong>Número da OAB:</strong> {{lawyer?.oabnumber}}</p>
                        <p><strong>Biografia:</strong>  {{lawyer?.biography}}</p>
                    </div>
                </div>
            </div>

            <div class="row chat-content">
                Envie uma mensagem para o profissional

                <div class="chat-messages col-md-12">
                    
                    <div class="chat-message-user">
                        <p><strong>Fulano</strong></p>
                        <p>adhuiosahduashuidsahiudiasuhiuasiuhdihsaudhiuasihudisahudhiuashiud asdasdasdasdsadsadsadsad sad dassadasdsadsa</p>
                    </div>

                    <div class="chat-message-lawyer">
                        <p><strong>adv</strong></p>
                        <p>adhuiosahduashuidsahiudiasuhiuasiuhdihsaudhiuasihudisahudhiuashiud asdyuihsadyuyhuasdaysduhyuhgasdauydshguaydghsaudsyghuydgshadsuyg</p>
                    </div>

                     <div class="chat-message-user">
                        <p><strong>Fulano</strong></p>
                        <p>adhuiosahduashuidsahiudiasuhiuasiuhdihsaudhiuasihudisahudhiuashiud asdasdasdasdsadsadsadsad sad dassadasdsadsa</p>
                    </div>

                     <div class="chat-message-lawyer">
                        <p><strong>adv</strong></p>
                        <p>adhuiosahduashuidsahiudiasuhiuasiuhdihsaudhiuasihudisahudhiuashiud asdyuihsadyuyhuasdaysduhyuhgasdauydshguaydghsaudsyghuydgshadsuyg</p>
                    </div>

                     <div class="chat-message-lawyer">
                        <p><strong>adv</strong></p>
                        <p>adhuiosahduashuidsahiudiasuhiuasiuhdihsaudhiuasihudisahudhiuashiud asdyuihsadyuyhuasdaysduhyuhgasdauydshguaydghsaudsyghuydgshadsuyg</p>
                    </div>

                     <div class="chat-message-lawyer">
                        <p><strong>adv</strong></p>
                        <p>adhuiosahduashuidsahiudiasuhiuasiuhdihsaudhiuasihudisahudhiuashiud asdyuihsadyuyhuasdaysduhyuhgasdauydshguaydghsaudsyghuydgshadsuyg</p>
                    </div>

                     <div class="chat-message-lawyer">
                        <p><strong>adv</strong></p>
                        <p>adhuiosahduashuidsahiudiasuhiuasiuhdihsaudhiuasihudisahudhiuashiud asdyuihsadyuyhuasdaysduhyuhgasdauydshguaydghsaudsyghuydgshadsuyg</p>
                    </div>

                     <div class="chat-message-lawyer">
                        <p><strong>adv</strong></p>
                        <p>adhuiosahduashuidsahiudiasuhiuasiuhdihsaudhiuasihudisahudhiuashiud asdyuihsadyuyhuasdaysduhyuhgasdauydshguaydghsaudsyghuydgshadsuyg</p>
                    </div>

                     <div class="chat-message-lawyer">
                        <p><strong>adv</strong></p>
                        <p>adhuiosahduashuidsahiudiasuhiuasiuhdihsaudhiuasihudisahudhiuashiud asdyuihsadyuyhuasdaysduhyuhgasdauydshguaydghsaudsyghuydgshadsuyg</p>
                    </div> 

                    <!-- <div class="chat-message-empty">
                        <p>Você não possui um histórico de mensagens com este profissional.</p>
                    </div> -->


                </div>

                <div class="col-md-12 message-input">
                    <input class="form-control" placeholder="Digite uma mensagem" />
                </div>
            </div>

        </div>
    </div>

    <Footer/>
</template>

<script>
import Header from '@/components/Header.vue'
import Footer from '@/components/Footer.vue'

export default {
    name: 'LawyerProfile',
    created() {
        const lawyerID = this.$route.query.idAdv;
         
        fetch('/api/v1/users/' + lawyerID)
            .then((response => response.json()))
            .then(res => {
                this.lawyer = res;
            });
            
    },
    data() {
        return {
            lawyer: null
        }
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
    components: {
        Header,
        Footer
    }
}
</script>

<style scoped>
    .chat-message-user {
        background-color: #f0f0f0;
        border-radius: 20px;
        float: left;
        text-align: left;
        padding: 10px;
        display: inline-block;
        margin: 10px;
    }

    .chat-message-lawyer {
        text-align: left;
        float: right;
        padding: 10px;
        background-color: lightgrey;
        border-radius: 20px;
        margin: 10px;
    }

    .message-input {
        margin-bottom: 30px;
    }

    .chat-messages {
        height: 30vh;
        overflow-x: auto;
        padding: 30px;
        margin-bottom: 30px;
    }

    .chat-content {
        margin-top: 50px;
    }

    .align-left {
        text-align: left;
    }

    .profile-data {
        margin-top: 30px;
    }

    .adv-profile {
        margin-top: 90px;
    }

    .content {
       margin-top: 120px;
    }

    .chat-message-empty{
        color: gray;
        opacity: .8;
        font-size: 20px;
    }

    @media only screen and (max-width: 600px) {
        
        .chat-message-user {
            float: unset;
        }

        .chat-message-lawyer {
            float: unset;
        }

        .chat-message-user p {
            word-break: break-all;
            width: 100%;
            max-width: 100%;
        }

        .chat-message-lawyer p {
            word-break: break-all;
            width: 100%;
            max-width: 100%;
        }
    } 

    
</style>
