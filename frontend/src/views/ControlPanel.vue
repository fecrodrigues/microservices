<template>
    <Header/>
    <div class="container">
        <h1>Bem vindo ao painel de controle</h1>
    </div>
    
    <Footer/>
</template>

<script>
import Header from '@/components/Header.vue'
import Footer from '@/components/Footer.vue'

export default {
    name: 'ControlPanel',
    components: {
        Header,
        Footer
    }
}
</script>
