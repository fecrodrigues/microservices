<template>
    <Header />
    
    <div class="content">
        <h1>Lawyer profile</h1>
    </div>

    <Footer v-bind:footerFixed="true"/>
</template>

<script>
import Header from '@/components/Header.vue'
import Footer from '@/components/Footer.vue'

export default {
    name: 'LawyerProfile',
     created() {
        const lawyerID = this.$route.query.idAdv;
         
        fetch('http://localhost:8001/users/' + lawyerID)
            .then((response => response.json()))
            .then(res => {
                console.log('res', res)
            })
    },
    components: {
        Header,
        Footer
    }
}
</script>

<style scoped>
  .content {
       margin-top: 100px;
    }
</style>
