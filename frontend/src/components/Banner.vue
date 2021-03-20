<template>

     <!-- Header Section -->
    <header id="home" class="overlay-dark">
        <div id="myCarousel" style="height: 92vh;" class="carousel slide" data-ride="carousel">
            <!-- Indicators -->
            <ol class="carousel-indicators">
                <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                <li data-target="#myCarousel" data-slide-to="1"></li>
                <li data-target="#myCarousel" data-slide-to="2"></li>
            </ol>

            <!-- Wrapper for slides -->
            <div class="carousel-inner">
                <div class="item active">
                <img src="la.jpg" alt="Los Angeles">
                    <h3>Chicago</h3>
                    <p>Thank you, Chicago!</p>
                </div>

                <div class="item">
                <img src="chicago.jpg" alt="Chicago">
                </div>

                <div class="item">
                <img src="ny.jpg" alt="New York">
                </div>
            </div>

            <!-- Left and right controls -->
            <a class="left carousel-control" href="#myCarousel" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="right carousel-control" href="#myCarousel" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right"></span>
                <span class="sr-only">Next</span>
            </a>
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
</style>
