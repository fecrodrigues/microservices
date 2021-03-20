<template>
  <div class="home">
    <Header/>
    <Banner/>
    <WhoHeAre/>
    <WhyUs/>
    <Areas/>
    <Footer/>
  </div>
</template>

<script>
// @ is an alias to /src
import Header from '@/components/Header.vue'
import Banner from '@/components/Banner.vue'
import WhoHeAre from '@/components/WhoHeAre.vue';
import WhyUs from '@/components/WhyUs.vue'
import Areas from '@/components/Areas.vue'
import Footer from '@/components/Footer.vue'

export default {
  name: 'Home',
  components: {
    Header,
    Banner,
    WhoHeAre,
    WhyUs,
    Areas,
    Footer
  }
}
</script>
