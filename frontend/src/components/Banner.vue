<template>

     <!-- Header Section -->
    <header id="home" class="overlay-dark">
        <div id="carouselExampleSlidesOnly" class="carousel slide" data-ride="carousel">

            <div class="carousel-inner">
                <div class="carousel-item active">
                    <div class="background-dark">
                        <img src="../assets/images/b1.jpg" style="width: 100%" class="d-block w-100 img-fluid" alt="b1">
                    </div>
                     <div class="carousel-caption">
                         <h2>Jusnexo a sua plataforma de direito</h2>
                        <h3>Conheça a plataforma que conecta pessoas e profissionais do direito</h3>
                    </div>
                </div>
            </div>
        </div>
    </header>

</template>

<script>

export default {
    name: 'Banner'
}
</script>

<style scoped>
    #home {
        margin-top: 82px;
    }

    .background-dark {
        filter:brightness(30%)
    }

    .carousel-caption {
        filter: brightness(100%)
    }
</style>
