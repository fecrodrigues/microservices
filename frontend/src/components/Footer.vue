<template>

    <footer  v-bind:class="{ 'navbar navbar-fixed-bottom': footerFixed }" class="footer">
        <div class="container">
            <div class="row">
                <div class="col-sm-6">
                    <p>
                        @ Jusnexo, todos os Direitos reservados.
                    </p>
                </div>
                <div class="col-sm-6">
                    <div class="footer-social">
                        <a href="#"><i class="ion-social-facebook"></i></a>
                        <a href="#"><i class="ion-social-linkedin-outline"></i></a>
                        <a href="#"><i class="ion-social-skype-outline"></i></a>
                        <a href="#"><i class="ion-social-twitter"></i></a>
                    </div>
                </div>
            </div>
        </div>
    </footer>

</template>

<script>
export default {
    name:'Footer',
    props: {
        footerFixed: Boolean
    }
}
</script>

<style scoped>
    footer {
        width: 100vw;
    }
</style>
